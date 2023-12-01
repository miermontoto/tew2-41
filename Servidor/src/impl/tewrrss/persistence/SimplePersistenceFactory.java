package impl.tewrrss.persistence;

import com.tewrrss.persistence.*;
import impl.tewrrss.persistence.jdbc.*;

public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UserDAO getUserDAO() {
		return new UserJdbcDAO();
	}

	@Override
	public CommunityDAO getCommunityDAO() {
		return new CommunityJdbcDAO();
	}

	@Override
	public PostDAO getPostDAO() {
		return new PostJdbcDAO();
	}

}
