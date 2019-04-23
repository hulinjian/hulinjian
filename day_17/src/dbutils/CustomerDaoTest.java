package dbutils;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class CustomerDaoTest {
	CustomerDao customerDao =new  CustomerDao();
	@Test
	public void testBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() throws Exception {
		Connection connection =null;
		try {
			connection=JDBCTool.getConnection();
			String sql="SELECT ID,NAME customersName,EMAIL,BIRTH FORM customers WHERE ID = ?";
			Customer customer =customerDao.get(connection, sql, 1);
			System.out.println(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, null, connection);
		}
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
