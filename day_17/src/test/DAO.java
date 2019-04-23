package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.JDBCTool;

public class DAO {
	/*public void update(String sql,Object ... objects) throws Exception {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		
		try {
			connection=JDBCTool.getConnection();
			preparedStatement =connection.prepareStatement(sql);
		
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(i + 1, objects[i]);
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, preparedStatement, connection);
		}
	}*/
	<T> T get(Class<T> clazz,String sql,Object ... objects) {
		T entity = null;
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		ResultSet resultset =null;
		
		try {
			connection= JDBCTool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			resultset=preparedStatement.executeQuery();
			if(resultset.next()) {
				Map<String,Object> values =new HashMap<>();
				//获取此 ResultSet 对象的列的编号、类型和属性。
				ResultSetMetaData rsmd=resultset.getMetaData();
				int columnCount =rsmd.getColumnCount();
				for(int i=0;i<columnCount;i++) {
					String columnLabel =rsmd.getColumnLabel(i+1);
					Object columnValue =resultset.getObject(columnCount);
					values.put(columnLabel,columnValue);
					
				}
				Object object=clazz.newInstance();
				for(Map.Entry<String,Object> entry:values.entrySet()) {
					String fieldName=entry.getKey();
					Object fieldValue=entry.getValue();
					//System.out.println(fieldName+":"+fieldValue);
					ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	<T>List<T>getForList(Class<T> clazz,String sql,Object ... args){
		return null;
		
	}
	<E> E getForValue(String sql,Object ... args) {
		return null;
		
	}
}
