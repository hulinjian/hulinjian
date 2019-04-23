package jdbc;



import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

public class JDBCTest {
	
	/**
	 * ResultSet：结果集，封装了使用JDBC进行查询的结果
	 * 1.调用Statement 对象的executeQuery(sql)可以得到结果集
	 * 2.ResultSet 返回的实际上就是一张数据表，有一个指针指向数据表的第一件的前面
	 * 可以调用next()方法检测下一行是否有效，若有效返回true，且指针下移相对于
	 * Iterator对象的hasNext()和next()方法结合体
	 * 3.当指针对位到一行时，可以调用getxxx(index)或getxxx(columnName)
	 * 获取每一行列的值，例如getInt(1),getString("name")
	 * 4. ResultSet 当然也需要进行关闭
	 * @throws Exception 
	 */
	@Test
	public void testResultSet() throws Exception{
		
		//获取id=4的customers数据表的记录，并打印
		Connection conn=null;
		Statement statement =null;
		ResultSet rs=null;
		
		try {
			//1.获取Connection
			conn =getConnection();
			
			//System.out.println(conn);
			//2.获取Statement
			statement =conn.createStatement();
			//3.准备SQL
			String sql="SELECT ID,NAME,EMAIL,BIRIM "+"FROM customers";
			//4.执行查询，得到ResultSet
			rs=statement.executeQuery(sql);
			//5.处理ResultSet
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString("name");
				String email =rs.getString(1);
				Date birth=rs.getDate(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(birth);
				System.out.println(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//6.关闭数据库资源
			JDBCTools.release(rs, statement, conn);
			/*rs.close();
			statement.close();
			conn.close();*/
		}	
	}
	/**
	 * 	通用的更新的放：包括INSERT,UPDATE,DELETE
	 * @throws Exception 
	 */
	/*public void update(String sql) throws Exception {
		Connection conn=null;
		Statement statement=null;
		
		try {
			conn=getConnection();
			statement=conn.createStatement();
			//String sql="INSERT INTO CUSTOMERS (NAME,EMAIL,BIRIM) "+"VALUES('xyz','bcd@qq.com','2000-2-1')";
			//String sql="UPDATE customers SET name ='Tom' "+"WHERE ID =8";
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(statement,conn);
		
		}
	}*/
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
	/*@Test
	public void testStatement() throws Exception {
		Connection conn=null;
		Statement statement=null;
		try {
			conn=getConnection2();
			statement=conn.createStatement();
			String sql="INSERT INTO CUSTOMERS (NAME,EMAIL,BIRIM) "+"VALUES('xyz','bcd@qq.com','2000-2-1')";
			
			//String sql="UPDATE customers SET name ='Tom' "+"WHERE ID =8";
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(statement!=null)
				statement.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}finally {
				conn.close();
			}
			
		}
		
	}*/
    /*@Test
	public void testDriver() throws SQLException{
		Driver driver =new com.mysql.cj.jdbc.Driver();
		String url="jdbc:mysql://localhost:3306/atguigu?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Properties info=new Properties();
		info.put("user", "root");
		info.put("password", "root");
		Connection connection =driver.connect(url, info);
		System.out.println(connection);
	}*/
	/*public Connection getConnection() throws Exception{
		String driverClass=null;
		String jdbcUrl=null;
		String user=null;
		String password=null;
		
		InputStream in=getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties =new Properties();
		properties.load(in);
		driverClass=properties.getProperty("driver");
		jdbcUrl=properties.getProperty("jdbcUrl");
		user=properties.getProperty("user");
		password=properties.getProperty("password");
		Driver driver=(Driver) Class.forName(driverClass).newInstance();
		Properties info =new Properties();
		info.put("user",user);
		info.put("password", password);
		Connection connection =driver.connect(jdbcUrl, info);
		return connection;
	}
	public void testGetConnection() throws Exception{
		System.out.println(getConnection());
	}*/
	/*public void testGetConnection2() throws ClassNotFoundException, IOException, SQLException {
		System.out.println(getConnection2());
	}*/

}
