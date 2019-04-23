package dbutils;

import static org.junit.Assert.*;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.jdbc.CallableStatement;

public class JDBCTest {

	@Test
	public void testCallableStatment() throws Exception {
		Connection connection =null;
		CallableStatement callableStatement=null;
		try {
			connection=JDBCTool.getConnection();
			
			//callableStatement =connection.prepareCall(sql );
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, callableStatement, connection);
		}
	}
	@Test
	public void testJdbcTools() throws Exception {
		Connection connection =JDBCTool.getConnection2();
		System.out.println(connection);
	}
	@Test
	public void testC3P0WithConfigFile() throws SQLException {
		DataSource dataSource =new ComboPooledDataSource("helloc3p0");
		System.out.println("*******");
	
		System.out.println(dataSource);
		
		ComboPooledDataSource comboPooledDataSource =
					(ComboPooledDataSource) dataSource;
		System.out.println("********");
		System.out.println(comboPooledDataSource);
	
	}
	@Test
	public void testC3P0() throws Exception {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver            
		cpds.setJdbcUrl( "jdbc:mysql://localhost/atguigu" );
		cpds.setUser("root");                                  
		cpds.setPassword("root");        
		
		System.out.println(cpds.getConnection());
	}
	@Test
	public void testDBCPWithDataSourceFactory() throws Exception {
		Properties properties=new Properties();
		InputStream inStream =JDBCTest.class.getClassLoader().getResourceAsStream("dbcp.properties");
		properties.load(inStream);
		DataSource dataSource =BasicDataSourceFactory.createDataSource(properties);
		System.out.println(dataSource.getConnection());
		BasicDataSource basicDataSource =(BasicDataSource) dataSource;
		System.out.println(basicDataSource.getMaxWait());
		
	}
	/**
	 * 使用DBCP数据库连接池
	 */
	/*@Test
	public void testDBCP() {
		DataSource dataSource =null;
		//1.创建DBPC数据源实例
		dataSource =new BasicDataSource();
		//2为数据源实例制定必须的属性
		dataSource.setUsername("root");
		dataSource.password("root");
		dataSource.setUrl("jdbc:mysql://localhost//atguigu");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		
		dataSource.setMaxActive(50);
		dataSource.setMinIdle(5);
		dataSource.setMaxWait(1000*5);
		Connection connection =dataSource.getConnection();
		System.out.println(connection);
				}*/
	/**
	 * 插入BLOB类型的数据必须用PreparedStatement，因为BLOB类型的数据无法用字符串拼写
	 * @throws Exception 
	 */
	@Test
	public void testInsertBlob() throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		
		try {
			
			connection=JDBCTool.getConnection();
			String sql="INSERT INTO customers(name,email,birth,picture) VALUES(?,?,?,?)";
			preparedStatement =connection.prepareStatement(sql);
			//preparedStatement.executeQuery(); 
			preparedStatement.setString(1,"BCaD");
			preparedStatement.setString(2, "abcd@atguigu.com");
			preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
			InputStream inputstream =new FileInputStream("timg.jpg");
			preparedStatement.setBlob(4, inputstream);
			preparedStatement.executeUpdate();
		   	//通过getGeneratedKeys()获取包含了新生成的主键的ResultSet对象
		   	//在ResultSet中只有一列GENERATED_KEY,用于存放新生成的主键值
		   	ResultSet rs=preparedStatement.getGeneratedKeys();
		   	if(rs.next()) {
		   		System.out.println(rs.getObject(1));
		   	}
		   	ResultSetMetaData rsmd=rs.getMetaData();
		   	for(int i=0;i<rsmd.getColumnCount();i++) {
		   		System.out.println(rsmd.getColumnName(i+1));
		   	}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, preparedStatement, connection);
		}
	}
	/**
	 * 获取自动生成的主键
	 * @throws Exception 
	 */
	@Test
	public void testGetKeyValue() throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		
		try {
			
			connection=JDBCTool.getConnection();
			String sql="INSERT INTO customers(name,email,birth) VALUES(?,?,?)";
			//使用重载的preparedStatement(sql,flag)来生成PreparedStatemet对象
			preparedStatement =connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//resultSet=preparedStatement.executeQuery(); 
			preparedStatement.setString(1,"ABCD");
			preparedStatement.setString(2, "abcd@atguigu.com");
			preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
		   	preparedStatement.executeUpdate();
		   	//通过getGeneratedKeys()获取包含了新生成的主键的ResultSet对象
		   	//在ResultSet中只有一列GENERATED_KEY,用于存放新生成的主键值
		   	ResultSet rs=preparedStatement.getGeneratedKeys();
		   	if(rs.next()) {
		   		System.out.println(rs.getObject(1));
		   	}
		   	ResultSetMetaData rsmd=rs.getMetaData();
		   	for(int i=0;i<rsmd.getColumnCount();i++) {
		   		System.out.println(rsmd.getColumnName(i+1));
		   	}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, preparedStatement, connection);
		}
	}
}
