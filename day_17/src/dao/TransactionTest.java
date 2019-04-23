package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class TransactionTest {
	@Test
	public void testTransactionIsolationUpdate() {
		Connection connection =null;
		try {
			connection=JDBCTool.getConnection();
			connection.setAutoCommit(false);
			String sql="UPDATE users SET balance = balance -500 WHERE id = 1";
			updata(connection,sql);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testTransactionIsolationRead() throws Exception {
		String sql="SELECT balance FROM users WHERE id =1";
		Integer balance =getForValue(sql);
		System.out.println(balance);
	}
	public <E> E getForValue(String sql,Object ... args) throws Exception {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		
		try {
			connection =JDBCTool.getConnection();
			System.out.println(connection.getTransactionIsolation());
			//connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			preparedStatement =connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++) {
				preparedStatement.setObject(i+1, args[i]);
			}
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return (E)resultSet.getObject(1);
			}
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			JDBCTool.release(resultSet, preparedStatement, connection);
		}
		return null;
	}
	/**
	 * Tom 给Jerry汇款500元
	 * @throws Exception 
	 */
	@Test
	public void testTransaction() throws Exception {
		Connection connection =null;
		try {
			connection =JDBCTool.getConnection();
			connection.setAutoCommit(false);
			//DAO dao=new DAO();
			String sql="UPDATE users SET balance = balance -500 WHERE id = 1";
			updata(connection,sql);
			//int i=10/0;
			//System.out.println(i+"***");
			sql="UPDATE users SET balance = balance +500 WHERE id = 2 ";
			updata(connection,sql);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}finally {
			JDBCTool.release(null, null, connection);
		}	
	}
	public void updata(Connection connection,String sql,Object...objects ) throws Exception {
		PreparedStatement preparedStatement=null;
		try {
			//connection=JDBCTool.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(1+i,objects[i]);
				}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, preparedStatement, null);
		}
	}

}
