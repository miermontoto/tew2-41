package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tewrrss.business.CommunityService;
import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;

@ManagedBean(name = "communities")
public class BeanCommunities implements Serializable {
	private static final long serialVersionUID = -1325688208166211122L;

  private BeanInfo loginInfo;

	private String nombre;
	private String descripcion;
	private CommunityService CS;

	private List<Community> listado;
	private String input;

	public BeanCommunities() {
		CS = Factories.services.createCommunityService();
		loginInfo = new BeanInfo();
		listAll();
	}

	public void search() {
		if (input == null || input.trim().isEmpty()) {
			listAll();
			return;
		}

		this.listado = this.listado.stream().filter(c -> c.getName().contains(input)).collect(Collectors.toList());
	}

	public String getInput() {
		return this.input;
	}

	public void setInput(String inputSearch) {
		this.input = inputSearch;
	}

	public List<Community> getListado() {
		return listado;
	}

	public void setListado(List<Community> listado) {
		this.listado = listado;
	}

	public List<Community> listAll() {
		List<Community> list = CS.listAll();
		this.listado = list;					// Asigno al listado la lista que he obtenido de negocio.
		return list;
	}

	public List<Community> listJoined() {
		return CS.listJoined(loginInfo.getSessionUser());
	}

	public boolean ableToJoin(Community comunidad) {
		return CS.ableToJoin(comunidad, loginInfo.getSessionUser());
	}

	public String delete(Community comunidad) {
		String temp = CS.remove(comunidad);
		listAll();
		return temp;
	}

	public String create() {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (nombre == null || nombre.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyName"), null));
			return null;
		}

		if (descripcion == null || descripcion.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyDesc"), null));
			return null;
		}

		for(Community cm : CS.listAll()) {
			if(cm.getName().equals(nombre)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_existingName"), null));
				return null;
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_create_ok"), null));
		String result = CS.create(new Community(this.nombre, this.descripcion));
		if (!result.equals("success")) return result;
		listAll();
		return CS.join(findByName(this.nombre), loginInfo.getSessionUser());
	}

	public String join(Community community) {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (community == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_join_error"), null));
			return null;
		}

		if (!ableToJoin(community)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_join_error"), null));
			return null;
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_join_ok"), null));
		listAll();
		return CS.join(community, loginInfo.getSessionUser());
	}

	public String leave(Community community) {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (community == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_leave_error"), null));
			return null;
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_leave_ok"), null));
		listAll();
		return CS.leave(community, loginInfo.getSessionUser());
	}

	public Community findByName(String name) {
		Optional<Community> community = CS.findByName(name);
		if (community.isPresent()) {
			return community.get();
		}
		return null;
	}

	public BeanInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(BeanInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
