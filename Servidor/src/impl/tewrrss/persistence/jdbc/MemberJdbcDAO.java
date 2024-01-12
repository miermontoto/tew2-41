package impl.tewrrss.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.persistence.MemberDAO;

public class MemberJdbcDAO extends JdbcDAO implements MemberDAO {

	List<Community> joinedCommunities = null;
	boolean dirtyJoinedCommunities = false;

	@Override
	public List<Community> getJoinedCommunities(User user) {
		if (joinedCommunities != null && !dirtyJoinedCommunities) return joinedCommunities;
		List<Community> communities = new ArrayList<>();

		String query = "select c.* from member as m "
			+ "inner join community as c on m.community_name = c.name "
			+ "where m.user_email = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return communities;

			while (rs.next()) {
				communities.add(new Community(rs.getString("name"), rs.getString("description")));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return communities;
	}

	@Override
	public boolean join(Community community, User user) {
		boolean joined = false;

		String query = "INSERT INTO member VALUES (?, ?)";
		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, community.getName());

			joined = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyJoinedCommunities &= joined;
		return joined;
	}

	@Override
	public boolean leave(Community community, User user) {
		boolean left = false;

		String query = "DELETE FROM member WHERE user_email = ? AND community_name = ?";
		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, community.getName());

			left = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyJoinedCommunities &= left;
		return left;
	}

	@Override
	public List<User> getUsersInCommunity(Community community) {
		List<User> users = new ArrayList<>();

		String query = "SELECT u.email, u.username "
			+ "FROM member as m "
			+ "INNER JOIN user as u ON m.user_email = u.email "
			+ "WHERE m.community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getName());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return users;

			while (rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));

				users.add(user);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return users;
	}

	@Override
	public boolean dropAll() {
		return getDatabase().executeUpdate("DELETE FROM member") == 1;
	}

}
