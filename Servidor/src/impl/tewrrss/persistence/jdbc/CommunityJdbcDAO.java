package impl.tewrrss.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;
import com.tewrrss.persistence.CommunityDAO;

public class CommunityJdbcDAO extends JdbcDAO implements CommunityDAO {

	List<Community> allCommunities = null;
	boolean dirtyAllCommunities = false;

	@Override
	public boolean add(Community community) {
		boolean added = false;
		String query = "INSERT INTO community VALUES (?, ?)";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getName());
			ps.setString(2, community.getDescription());

			added = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllCommunities &= added;
		return added;
	}

	@Override
	public boolean remove(Community community) {
		boolean removed = true;

		String queryDeleteCommunity = "DELETE FROM community WHERE name = ?";
		String queryDeleteMembers = "DELETE FROM member WHERE community_name = ?";
		String queryDeletePosts = "DELETE FROM post WHERE community_name = ?";

		try {
			PreparedStatement ps1 = getDatabase().getConnection().prepareStatement(queryDeleteMembers);
			PreparedStatement ps2 = getDatabase().getConnection().prepareStatement(queryDeletePosts);
			PreparedStatement ps3 = getDatabase().getConnection().prepareStatement(queryDeleteCommunity);
			ps1.setString(1, community.getName());
			ps2.setString(1, community.getName());
			ps3.setString(1, community.getName());

			// Primero eliminar members�as y publicaciones, luego la comunidad.
			ps1.executeUpdate();
			ps2.executeUpdate();
			removed = ps3.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllCommunities &= removed;
		return removed;
	}

	@Override
	public boolean update(Community community) {
		boolean updated = false;
		String query = "UPDATE community SET description = ? WHERE name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getDescription());
			ps.setString(2, community.getName());

			updated = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllCommunities &= updated;
		return updated;
	}

	@Override
	public List<Community> getCommunities() {
		if (allCommunities != null && !dirtyAllCommunities) return allCommunities;

		List<Community> communities = new ArrayList<>();

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement("SELECT * FROM community");

			ResultSet rs = ps.executeQuery();
			if (rs == null) return communities;

			while (rs.next()) {
				communities.add(new Community(rs.getString("name"), rs.getString("description")));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return communities;
	}

	@Override
	public Optional<Community> findByName(String name) {
		Optional<Community> community = Optional.empty();

		String query = "SELECT * FROM community WHERE name = ?";
		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			if (rs == null) return community;

			if (rs.next()) {
				community = Optional.of(new Community(rs.getString("name"), rs.getString("description")));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return community;
	}

	@Override
	public List<Community> search(String search) {
		List<Community> communities = new ArrayList<>();

		String query = "SELECT * FROM community WHERE name LIKE ?";
		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, "%" + search + "%");

			ResultSet rs = ps.executeQuery();
			if (rs == null) return communities;

			while(rs.next()) {
				communities.add(new Community(rs.getString("name"), rs.getString("description")));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return communities;
	}

	@Override
	public boolean dropAll() {
		getDatabase().executeUpdate("DELETE FROM member"); // First delete all members
		return getDatabase().executeUpdate("DELETE FROM community") == 1; // Then delete all communities
	}
}
