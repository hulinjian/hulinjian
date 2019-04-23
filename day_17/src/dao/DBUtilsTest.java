package dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

public class DBUtilsTest {
	QueryRunner queryRunner =new QueryRunner();
	@Test
	public void testQuery() {
		Connection connection=null;
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
