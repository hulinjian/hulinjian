package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import test.ReviewTest;

public class JDBCTool {
	
	/**
	 * JDBC的工具类 
	 * @param connection
	 */ 
	public static void commit(Connection connection) {
		if(connection!=null) {
			try {
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static DataSource dataSource =null;
	//数据库连接池只能被初始化一次
	static {
		dataSource =new ComboPooledDataSource("helloc3p0");
	}
	public static Connection getConnection2() throws SQLException {
		
		return dataSource.getConnection();
	}
	//回滚事务
	public static void rollback(Connection connection) {
		if(connection!=null) {
			try {
				connection.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	 
	//开始事务
	public static void beginTx(Connection connection) {
		if(connection!=null) {
			try {
				connection.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 修改
	 * @param sql
	 * @param objects
	 * @throws Exception
	 */
	public static void update(String sql,Object ...objects) throws Exception {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		
		try {
			connection=JDBCTool.getConnection();
			preparedStatement =connection.prepareStatement(sql);
		
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(i + 1, objects[i]);
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, preparedStatement, connection);
		}
	}
	/**
	 * 执行连接
	 */
	public static Connection getConnection() throws Exception{
		Properties pro=new Properties();
		InputStream in=ReviewTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		pro.load(in);
		String jdbcUrl=pro.getProperty("jdbcUrl");
		String user=pro.getProperty("user");
		String password=pro.getProperty("password");
		String driver=pro.getProperty("driver");
		
		Class.forName(driver);
		
		Connection conn=DriverManager.getConnection(jdbcUrl,user,password);
		//System.out.println(conn);
		return conn;
	}  
	/**
	 * 执行关闭
	 * @param statement
	 * @param conn
	 * @throws Exception
	 */
	public static void release(ResultSet resultset,Statement statement,Connection connection) throws Exception {
		if(statement!=null) {
			try{
				statement.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(connection!=null) {
			try{
				//数据库连接池的Connection对象进行colse时并不是真的进行了关闭，而是把该数据库连接归还到数据库连接池
				connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		if(connection!=resultset) {
			try{
				connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 执行更新
	 */
	public static  void update(String sql) throws Exception {
		Connection connnection=null;
		Statement statement=null;
		
		try {
			connnection=getConnection();
			statement=connnection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			release(null,statement,connnection);
		}
	}
}
