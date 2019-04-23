package test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Test;
public class Test1 {
	@Test
	public void testGetConnection() throws Exception{
		Properties pro=new Properties();
		InputStream in=this.getClass().getResourceAsStream("jdbc.properties");
		pro.load(in);
		String jdbcUrl=pro.getProperty("jdbcUrl");
		String user=pro.getProperty("user");
		String password=pro.getProperty("password");
		String driver=pro.getProperty("driver");
		
		Class.forName(driver);
		
		Connection conn=DriverManager.getConnection(jdbcUrl,user,password);
		Statement statement=conn.createStatement();
		ResultSet res=null;
		
		System.out.println(conn);
	}
}
