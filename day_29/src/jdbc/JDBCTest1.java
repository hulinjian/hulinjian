package jdbc;

//import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.mysql.cj.jdbc.Driver;

public class JDBCTest1 {
	/**
	 * Driver 是一个接口：数据库厂商必须要提供实现的接口，能从其中获取数据库连接。
	 * @throws SQLException 
	 */
	@Test
	public void testDriver() throws SQLException{
		Driver driver =new com.mysql.cj.jdbc.Driver();
		String url="jdbc:mysql://localhost:3306/atguigu?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Properties info=new Properties();
		info.put("user", "root");
		info.put("password", "root");
		Connection connection =driver.connect(url, info);
		System.out.println(connection);
	}
}

