package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBCTools {
	
	/**
	 * 获取连接方法，通过读取配置文件从数据库服务器获取一个连接
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties properties=new Properties();
		
		InputStream in=JDBCTools.class.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		
		String user =properties.getProperty("user");
		String password =properties.getProperty("password");
		String jdbcUrl =properties.getProperty("jdbcUrl");
		String driver =properties.getProperty("driver");
		
		Class.forName(driver);
		
		return DriverManager.getConnection(jdbcUrl,user,password);
	}
	
	public static void release(ResultSet rs, Statement statement,Connection conn) throws Exception {
		if(rs!=null) {
			try{
				rs.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(statement!=null) {
			try{
				statement.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try{
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 关闭Statement和Connection
	 * @param statement
	 * @param conn
	 * @throws Exception
	 */
	public static void release(Statement statement,Connection conn) throws Exception {
		if(statement!=null) {
			try{
				statement.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try{
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
