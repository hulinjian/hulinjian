package dbutils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

public class DBUtilsTest {
	
	@Test
	public void testScalarHandler() throws Exception {
		Connection connection =null;
		try {
			connection=JDBCTool.getConnection();
			String sql="SELECT NAME,EMAIL,BIRTH FROM customers WHERE ID = ?";
			Object result =  queryRunner.query(connection, sql, new ScalarHandler(),1);
	 	System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, null, connection);
		}
	}
	@Test
	public void testMapListHandler() throws Exception {
		Connection connection =null;
		try {
			connection=JDBCTool.getConnection();
			String sql="SELECT ID,NAME,EMAIL,BIRTH FROM customers";
			List<Map<String,Object>> result =  queryRunner.query(connection, sql, new MapListHandler());
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, null, connection);
		}
	}
	@Test
	public void testMapHandler() throws Exception  {
			Connection connection=null;
			try {
				connection=JDBCTool.getConnection();
				String sql="SELECT ID,NAME,EMAIL,BIRTH FROM customers WHERE ID =?";
				Customer customer =(Customer) queryRunner.query(connection, sql, new BeanHandler(Customer.class),1);
				System.out.println(customer);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCTool.release(null, null, connection);
			}
	}
	@Test
	public void testBeanHanlder() throws Exception {
		Connection connection=null;
		try {
			connection=JDBCTool.getConnection();
			String sql="SELECT ID,NAME,EMAIL,BIRTH FROM customers WHERE ID =?";
			Customer customer =(Customer) queryRunner.query(connection, sql, new BeanHandler(Customer.class),1);
			System.out.println(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, null, connection);
		}
	}
	QueryRunner queryRunner =new QueryRunner();
	class MyResultSetHandler implements ResultSetHandler{

		@Override
		public Object handle(ResultSet resultSet) throws SQLException {
			//System.out.println("handle...");
			List<Customer> customers =new ArrayList<>();
			while(resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String name =resultSet.getString(2);
				String email =resultSet.getString(2);
				Date birth =resultSet.getDate(4);
				Customer customer=new Customer(id,name,email,birth);
				customers.add(customer);
			}
			return customers;
		}
		
	}
	@Test
	public void testQuery() throws Exception {
		Connection connection=null;
		try {
			connection=JDBCTool.getConnection();
			String sql ="SELECT id,name,email,birth FROM customers";
			Object object=queryRunner.query(connection, sql,new MyResultSetHandler());
			System.out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, null, connection);
		}
	}
	
	/**
	 * 测试QueryRunner类的update方法
	 * @throws Exception 
	 */
	@Test
	public void testQueryRunnerUpdate() throws Exception {
		//1.创建
		QueryRunner queryRunner =new QueryRunner();
		//2.使用updata
		String sql="DELETE FROM customers WHERE id IN (?,?)";
		Connection connection=null;
		try {
			connection=JDBCTool.getConnection2();
			queryRunner.update(connection, sql,40,41);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, null, connection);
		}
		
	
	}

}
