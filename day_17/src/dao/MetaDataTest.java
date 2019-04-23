package dao;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class MetaDataTest {
	/**
	 * 读取blob数据
	 * 1.使用getBlob方法读取到Blob对象，调用Blob的getBinaryStream方法得到输入流，再使用IO操作
	 * @throws Exception
	 */
	@Test
	public void readBlob() throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet=null;
		try {
			connection=JDBCTool.getConnection();
			String sql="SELECT ID,NAME,EMAIL,BIRTH,PICTURE FROM customers WHERE ID=37";
			preparedStatement =connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int id=resultSet.getInt(1);
				String name =resultSet.getString(2);
				String eamil=resultSet.getString(3);
				System.out.println(id+","+name+","+eamil);
				Blob picture=resultSet.getBlob(5);
				InputStream in=picture.getBinaryStream();
				OutputStream out =new FileOutputStream("桌面.jpg");
				byte [] buffer = new byte[1024];
				int len=0;
				while((len =in.read(buffer))!=-1) {
					out.write(buffer,0,len);
				}
				out.close();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(resultSet, preparedStatement, connection);
		}
	}
	/**
	 * ResultSetMetaData：描述结果集的元数据，可以得到结果集中的基本信息：
	 * 结果集中有哪些列，列名，列的别名等
	 */
	@Test
	public void testResultSetMetaData() {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet=null;
		try {
			connection=JDBCTool.getConnection();
			String sql="SELECT ID,Name name,email ,BIRTH FROM customers";
			preparedStatement =connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			//1.得到ResultSetMetaData对象
			ResultSetMetaData rsmd =resultSet.getMetaData();
			int columnCount =rsmd.getColumnCount();
			System.out.println(columnCount);
			for(int i=0;i<columnCount;i++) {
				String columnLabel =rsmd.getColumnLabel(i+1);
				String columnName =rsmd.getColumnName(i+1);
				
				System.out.println(columnName+":"+columnLabel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * DatabaseMetaData是描述数据库的元数据对象
	 * 可以由Connection得到
	 * 了解，
	 * @throws Exception 
	 */
	@Test
	public void testDatabaseMetaData() throws Exception {
		Connection connection =null;
		ResultSet resultSet=null;
		try {
			connection=JDBCTool.getConnection();
			DatabaseMetaData data=(DatabaseMetaData) connection.getMetaData();
			int version = data.getDatabaseMajorVersion();
			System.out.println(version);
			String user =data.getUserName();
			System.out.println(user);
			resultSet=data.getCatalogs();
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(resultSet,null, connection);
		}
	
		
	}

}
