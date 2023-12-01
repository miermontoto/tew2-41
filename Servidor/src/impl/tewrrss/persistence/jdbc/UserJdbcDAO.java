package impl.tewrrss.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.persistence.UserDAO;


public class UserJdbcDAO extends JdbcDAO implements UserDAO {

	private static List<User> allUsers = null;
	private static boolean dirtyAllUsers = true;

	public List<User> listAll() {
		if (allUsers != null && !dirtyAllUsers) {
			return allUsers;
		}

		List<User> users = new ArrayList<>();

		ResultSet rs = getDatabase().executeQuery("SELECT * FROM user");
		if (rs == null) return users;

		try {
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
				user.setUsername(rs.getString("username"));

				users.add(user);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		allUsers = users;
		return users;
	}

	public Optional<User> findByEmail(String email) {
		Optional<User> user = Optional.empty();

		ResultSet rs = getDatabase().executeQuery("SELECT * FROM user WHERE email = '" + email + "'");
		if (rs == null) return user;

		try {
			if (rs.next()) {
				user = Optional.of(new User());
				user.get().setEmail(rs.getString("email"));
				user.get().setPassword(rs.getString("password"));
				user.get().setRole(rs.getInt("role"));
				user.get().setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return user;
	}

	public boolean add(User user) {
		boolean added = false;

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?)");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getRole());

			added = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllUsers &= added;
		return added;
	}

	@Override
	public List<User> getUsersInCommunity(Community community) {
		List<User> users = new ArrayList<>();

		String query = "SELECT u.email, u.username " +
			"FROM member as m " +
			"INNER JOIN user as u ON m.user_email = u.email " +
			"WHERE m.community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getName());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return users;

			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));

				users.add(user);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return users;
	}

	@Override
	public boolean remove(User user) {
		boolean removed = false;

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement("DELETE FROM user WHERE email = ?");
			ps.setString(1, user.getEmail());

			removed = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllUsers &= removed;
		return removed;
	}

	@Override
	public boolean update(User user) {
		boolean updated = false;

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement("UPDATE user SET username = ?, password = ?, role = ? WHERE email = ?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getRole());
			ps.setString(4, user.getEmail());

			updated = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllUsers &= updated;
		return updated;
	}

	@Override
	public boolean dropAll() {
		return getDatabase().executeUpdate("DELETE FROM user") >= 1;
	}
}
