package jdbc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;


public class JDBCTest {
	@Test
	public void testStatement() throws Exception {
		Connection conn=getConnection();
		
		String sql="INSERT INTO customers (NAME,EMAIL,BIRIM) "+"VALUES('xcv','bggd@qq.com','2000-2-5')";
		
		Statement statement=conn.createStatement();
		statement.executeUpdate(sql);
		
		statement.close();
		conn.close();
	}
	public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties properties=new Properties();
		
		InputStream in=this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		
		String user =properties.getProperty("user");
		String password =properties.getProperty("password");
		String jdbcUrl =properties.getProperty("jdbcUrl");
		String driver =properties.getProperty("driver");
		
		Class.forName(driver);
		
		return DriverManager.getConnection(jdbcUrl,user,password);
	}
	
	/*public void testDriverManager() throws SQLException, IOException, ClassNotFoundException {
		String driverClass =null;
		String jdbcUrl=null;
		String user=null;
		String password=null;
		//读取类路径下的jdbc.properties文件
		
		InputStream in=getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties =new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl=properties.getProperty("jdbcUrl");
		user=properties.getProperty("user");
		password=properties.getProperty("password");
			
		Class.forName(driverClass);
		Connection connection=DriverManager.getConnection(jdbcUrl,user,password);
		System.out.println(connection);
	}*/

	/**
	 * Driver 是一个接口：数据库厂商必须要提供实现的接口，能从其中获取数据库连接。
	 * @throws SQLException 
	 */
	//@Test
	/*public void testDriver() throws SQLException {
		//创建Driver实现类对象
		Driver driver =new com.mysql.cj.jdbc.Driver();
		//准备数据库基本信息URL，user，password
		String url="jdbc:mysql://localhost:3306/atguigu?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";	
		Properties info=new Properties();
		info.put("user","root");
		info.put("password","root");
		Connection connection= driver.connect(url, info);
		System.out.println(connection);
	}*/
	/**
	 * 编写一个通用方法，在不修改程序的情况下，获取连接数据库的方法
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	/*public Connection getConnection() throws Exception {
		String driverClass =null;
		String jdbcUrl=null;
		String user=null;
		String password=null;
		//读取类路径下的jdbc.properties文件
		
		InputStream in=getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties =new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl=properties.getProperty("jdbcUrl");
		user=properties.getProperty("user");
		password=properties.getProperty("password");
		
		//@SuppressWarnings("deprecation")
		Driver driver =(Driver) Class.forName(driverClass).newInstance();
		
		Properties info =new Properties();
		info.put("user",user);
		info.put("password",password);
		
		
		Connection connection= driver.connect(jdbcUrl, info);
		
		return connection;
	}
	
	public void testGetConnection() throws Exception{
		System.out.println(getConnection2());
	}*/
}
