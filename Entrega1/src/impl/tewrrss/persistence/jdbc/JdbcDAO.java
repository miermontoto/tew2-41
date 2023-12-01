package impl.tewrrss.persistence.jdbc;

import com.tewrrss.util.Database;

public abstract class JdbcDAO {

	protected static Database getDatabase() {
		return new Database();
	}

}
