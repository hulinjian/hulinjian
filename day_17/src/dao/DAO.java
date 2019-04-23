package dao;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import test.ReflectionUtils;

public class DAO {
	public void update(String sql,Object ... objects) throws Exception {
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
	}
	public <T> T get(Class<T> clazz,String sql,Object ... objects) throws Exception {
		List<T> result= getForList(clazz,sql,objects);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}
		
public <T>List<T>getForList(Class<T> clazz,String sql,Object ... objects) throws Exception{
	List<T> list= new ArrayList<>();
	Connection connection=null;
	PreparedStatement preparedStatement =null;
	ResultSet resultSet =null;
	
	try {
		//1.得到结果集
		connection=JDBCTool.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		for(int i=0;i<objects.length;i++) {
			preparedStatement.setObject(i+1, objects[i]);
			//System.out.println(objects);
		}
			resultSet =preparedStatement.executeQuery();
			//2.处理结果集，得到Map的List，其中一个Map对象就是一条记录，Map的key为reusltSet中的列的别名，Map的value为列的值
			List<Map<String, Object>> values = handleResultSetToMapList(resultSet);
			//判断List是否为空集合,若不为空遍历List，得到Mao对象，把Map对象转为一个Class参数对应的Object对象
			list=transfterMapListToBeanList(clazz,  values);
	} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(resultSet, preparedStatement, connection);
		}
	//System.out.println(list.size());
	return   list;
}
private <T> List<T> transfterMapListToBeanList(Class<T> clazz, List<Map<String, Object>> values)
		throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	List<T> result =new ArrayList<>();
	T bean=null;
	if(values.size()>0) {
		for(Map<String,Object>m:values) {
			bean =clazz.getDeclaredConstructor().newInstance();
			for(Map.Entry<String,Object> entry:m.entrySet()) {		
			String fieldName=entry.getKey();
			//System.out.println(fieldName);
			Object fieldValue=entry.getValue();
			//System.out.println(fieldValue);
			System.out.println(fieldName+":"+fieldValue);
			BeanUtils.setProperty(bean, fieldName.toLowerCase(), fieldValue);
			//ReflectionUtils.setFieldValue(entity, fieldName, fieldValue);
			//Object a=BeanUtils.getProperty(bean, "name");
			System.out.println(BeanUtils.getProperty(bean, fieldName.toLowerCase()));
			System.out.println("=========ss");
			}
			System.out.println(bean.toString());
			result.add(bean);
		}	
	}
	return  result;
}

public List<Map<String, Object>> handleResultSetToMapList(ResultSet resultSet) throws SQLException {
	List<Map<String,Object>> values =new ArrayList<>();
	List<String> columnLabels=getColumnLabels(resultSet);
	Map<String,Object> map =null;
	while(resultSet.next()) {
		map =new HashMap<>();
		//for(int i=0;i<columnLabels.size();i++) 
			for(String columnLabel: columnLabels ){
			Object value =resultSet.getObject(columnLabel);
			//System.out.println(columnLabel);
			//Object columnValue =resultSet.getObject(i+1);
			//System.out.println(columnValue);
			map.put(columnLabel,value);
		}
		values.add(map);
	}
	return values;
}
	/**
	 * 获取结果集的ColumnLabels对应的List
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<String> getColumnLabels(ResultSet rs) throws SQLException{
		List<String> labels =new ArrayList<>();
		ResultSetMetaData rsmd =rs.getMetaData();
		for(int i=0;i<rsmd.getColumnCount();i++) {
			labels.add(rsmd.getColumnLabel(i+1));
		}
		return labels;
		
	}
	public <E> E getForValue(String sql,Object ... args) throws Exception {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		
		try {
			connection =JDBCTool.getConnection();
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
}
