package test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

public class ReviewTest {
	/**
	 * 连接数据库
	 * @throws Exception
	 */
	public void testResultSet() throws Exception {
		Connection connection =null;
		Statement statement =null;
		ResultSet resultset=null;
		try {
			connection=getConnection();
			statement=connection.createStatement();
			String sql="SELECT id,name,email,birth FROM customers";
			resultset=statement.executeQuery(sql);
			while(resultset.next()) {
				int id =resultset.getInt(1);
				String name=resultset.getString(2);
				String email=resultset.getString(3);
				Date birth =resultset.getDate(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(email);
				System.out.println(birth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			releaseDB(resultset,statement,connection);
		}
	}
	
	public Connection getConnection() throws Exception{
		Properties pro=new Properties();
		InputStream in=ReviewTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		pro.load(in);
		String jdbcUrl=pro.getProperty("jdbcUrl");
		String user=pro.getProperty("user");
		String password=pro.getProperty("password");
		String driver=pro.getProperty("driver");
		
		Class.forName(driver);
		
		Connection conn=DriverManager.getConnection(jdbcUrl,user,password);
		System.out.println(conn);
		return conn;
	}
	@Test
	public void testStatement() throws Exception {
		Connection connection=null;
		Statement statement =null;
		try {
		connection=getConnection();
		statement =connection.createStatement();		
		
		String sql="UPDATE customers SET NAME = 'TOM' "+"WHERE ID =6";
		statement.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			releaseDB(null,statement,connection);
		}
		
	}
	
	public void releaseDB(ResultSet res,Statement sta,Connection con) throws Exception {
		if(res!=null) {
			try {
				res.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sta!=null) {
			try {
				res.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				res.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
