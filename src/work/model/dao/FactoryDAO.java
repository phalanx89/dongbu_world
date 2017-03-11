/**
 * 
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author DB
 *
 */
public class FactoryDAO {
	private Context context;
	private DataSource ds;
	
	public static FactoryDAO mInstance = new FactoryDAO();

	/**
	 * 
	 * @return
	 */
	public static FactoryDAO getInstance() {
		return mInstance;
	}

	/**
	 * 
	 */
	private FactoryDAO() {
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Connection getConnection() throws SQLException{
		return ds.getConnection();
	}

	/**
	 * Statement, Connection °´Ã¼¸¦ Close
	 * 
	 * @param stmt
	 * @param conn
	 */
	public void close(Statement stmt, Connection conn) {
		close(null, stmt, conn);
	}

	/**
	 * ResultSet, Statement, Connection °´Ã¼¸¦ Close
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}

			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
