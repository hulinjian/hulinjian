package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import dao.Customer;
import dao.JDBCTool;
import dao.Student;
import jdbc.JDBCTools;

public class JDBCTest {
	@Test
	public void testResultSetMetaData() throws Exception {

		Connection connection=null;
		PreparedStatement preparedStatement =null;
		//1.得到ResultSet对象
		ResultSet resultSet =null;
		
		try {
			String sql="SELECT flow_id flowID,type,id_card idCard,exam_card examCard,studentname,localtion,grade"+
					" FROM examstudent WHERE flow_id = ? ";
			connection=JDBCTool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,5);
				
			resultSet =preparedStatement.executeQuery();
			
			//2.得到ResultStMetaData对象
			ResultSetMetaData rsmd=resultSet.getMetaData();
			//3.创建一个Map<String,Object>对象。键：SQL查询的类的别名,至列的值
			Map<String,Object>values=new HashMap<String,Object>();
			//4.处理结果集，利用ResultSetMetaData填充3对象的Map对象
			
			while(resultSet.next()) {
				for(int i=0;i<rsmd.getColumnCount();i++) {
					String columnLabel =rsmd.getColumnLabel(i+1);
					System.out.println(columnLabel);
					Object columnValue=resultSet.getObject(columnLabel);
					values.put(columnLabel,columnValue);		
					}
			}
			//System.out.println(values);		
			//5.若Map部位空集，利用反射创建clazz对应的对象
			Class clazz=Student.class;
			
			Object object=clazz.newInstance();
			//6.遍历Map对象，利用反射为Class对象的对应的属性赋值
			for(Map.Entry<String,Object> entry:values.entrySet()) {
				String fieldName=entry.getKey();
				Object fieldValue =entry.getValue();
			
				System.out.println(fieldName+":"+fieldValue);
				//ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
			}
			System.out.println(object);
		} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCTool.release(resultSet, preparedStatement, connection);
			}
	}
	
	public Customer getCustomer1(String sql,Object ...objects) throws Exception {
		Customer customer=null;
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultset =null;
		try {
			connection=JDBCTool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			resultset=preparedStatement.executeQuery(sql);
			if(resultset.next()) {
				customer=new Customer();
				customer.setId(resultset.getInt(1));
				customer.setName(resultset.getString(2));
				customer.setEmail(resultset.getString(3));
				customer.setBirth(resultset.getDate(4));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(resultset, preparedStatement, connection);
		}
		return customer;
	}
	
	@Test
	public void testGet() throws Exception {
		String sql="SELECT ID,NAME,EMAIL,BIRTH FROM customers where id = ? ";
		Customer customer =get(Customer.class,sql,8);
		System.out.println(customer);
		
		sql="SELECT flow_id flowID,type,id_card idCard,exam_card examCard,studentname,localtion,grade"+
			" FROM examstudent WHERE flow_id = ? ";
		Student stu=get(Student.class,sql,3);
		System.out.println(stu);
	}
	
	/**
	 * 将获得对象和信息的的方法整合
	 * @param sql
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public <T> T get(Class<T> clazz,String sql,Object ...objects) throws Exception {
		T entity=null;
		
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		
		try {
			
			connection=JDBCTool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(i+1, objects[i]);
				//System.out.println(objects);
			}
				resultSet =preparedStatement.executeQuery();
			//2.得到ResultSetMetaData对象
				ResultSetMetaData rsmd=resultSet.getMetaData();
			//3.创建Map
				Map<String,Object> values =new HashMap<>();
				while(resultSet.next()) {
					for(int i=0;i<rsmd.getColumnCount();i++) {
						String columnLabel =rsmd.getColumnLabel(i+1);
						Object columnValue =resultSet.getObject(i+1);
						values.put(columnLabel,columnValue);
					}
				}
				if(values.size()>0) {
					entity =clazz.newInstance();
					for(Map.Entry<String,Object> entry:values.entrySet()) {
						String fieldName=entry.getKey();
						Object fieldValue=entry.getValue();
						System.out.println(fieldName+":"+fieldValue);
						//ReflectionUtils.setFieldValue(entity, fieldName, fieldValue);
					}
					
					//通过解析SQL语句来判断到底悬着了哪些列entity对象的哪些属性赋值
					
					
				}
		} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCTool.release(resultSet, preparedStatement, connection);
			}
		return entity;
	}
	
	public Customer getCustomer(String sql,Object ...objects) throws Exception {
		Customer customer=null;
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultset =null;
		try {
			connection=JDBCTool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			resultset=preparedStatement.executeQuery(sql);
			if(resultset.next()) {
				customer=new Customer();
				customer.setId(resultset.getInt(1));
				customer.setName(resultset.getString(2));
				customer.setEmail(resultset.getString(3));
				customer.setBirth(resultset.getDate(4));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(resultset, preparedStatement, connection);
		}
		return customer;
	}
	
	public Student getStudent(String sql,Object ...objects) throws Exception {
		Student stu=null;
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		ResultSet resultset =null;
		try {
			connection=JDBCTool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			resultset=preparedStatement.executeQuery(sql);
			if(resultset.next()) {
				stu=new Student();
				stu.setFlowID(resultset.getInt(1));
				stu.setType(resultset.getInt(2));
				stu.setIdCard(resultset.getString(3));
				stu.setExamCard(resultset.getString(4));
				stu.setStudentName(resultset.getString(5));
				stu.setLocation(resultset.getString(6));
				stu.setGrade(resultset.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(resultset, preparedStatement, connection);
		}
		return stu;
	}
	
	/**
	 * 使用PreparedStatement将有效的解决SQL注入问题。
	 * @throws Exception 
	 */
	@Test
	public void testSQLInjection2() throws Exception {
		String username="'a' or password =";
		String password="or '1'='1'";
		String sql="SELECT * FROM users WHERE username = ? AND password = ?";
		//System.out.println(sql);
		Connection connection =null;
		//Statement statement =null;
		PreparedStatement preparedStatement=null;
		ResultSet resultset=null;
		try {
			connection = JDBCTool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			resultset=preparedStatement.executeQuery();
			if(resultset.next()) {
				System.out.println("登录成功");
			}else {
				System.out.println("用户名和密码不匹配或用户名不存在。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(resultset, preparedStatement, connection);
		}
	}
	
	@Test
	public void testPreparedStatement1() throws Exception {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		try {
			connection =JDBCTool.getConnection();
			String sql="INSERT INTO customers(name,email,birth) VALUES(?,?,?)";
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,"atguigu");
			preparedStatement.setString(2, "simple@163.com");
			preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
		
			preparedStatement.executeUpdate();//executeUpdate更新 executeQuery查询
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, preparedStatement,connection);
		}
	}
	
	/**
	 * SQL注入
	 * @param sql
	 * @param objects
	 * @throws Exception
	 */
	@Test
	public void testSQLInjection() throws Exception {
		String username="'a' or password ='";
		String password="'or '1'='1'";
		String sql="SELECT *FROM users WHERE username ="
				+username+" AND password = "+password;
		//System.out.println(sql);
		Connection connection =null;
		Statement statement =null;
		ResultSet resultset=null;
		try {
			connection = JDBCTool.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			
			if(resultset.next()) {
				System.out.println("登录成功");
			}else {
				System.out.println("用户名和密码不匹配或用户名不存在。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(resultset, statement, connection);
		}
	}
	@Test
	public void testPreparedStatement() throws Exception {
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		try {
			connection =JDBCTool.getConnection();
			String sql="INSERT INTO customers(name,email,birth) VALUES(?,?,?)";
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,"atguigu");
			preparedStatement.setString(2, "simple@163.com");
			preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
		
			preparedStatement.executeUpdate();//executeUpdate更新 executeQuery查询
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTool.release(null, preparedStatement,connection);
		}
	}
	
	@Test
	public void testGetStudent() throws Exception {
		//1.得到擦洗类型
		int searchType=getSearchTypeFromConsole();
		
		//2.具体查询学生信息
		Student student =searchStudent(searchType);
		
		//3.打印学生信息
		printStudent(student);
		
	}
	
	private void printStudent(Student student) {
		if(student!=null) {
			System.out.println(student);
		}else {
			System.out.println("查无此人");
		}
		
	}

	private Student searchStudent(int searchType) throws Exception {
		//1.根据输入的searchType，提示用户输入信息：
		//若searchType为1，提示：请输入身份证号，若为2提示：请输入准考证号
		String sql ="SELECT FlowID,Type,IDCard,ExamCard,"+
		"StudentName ,Localtion,Grade "+
		"FROM examstudent "+"WHERE ";
		Scanner scanner=new Scanner(System.in);
		//2.根据searchTyoe确定SQL
		if(searchType==1) {
			System.out.println("请输入准考证号");
			String ExamCard= scanner.next();
			sql=sql+"ExamCard='"+ExamCard+"'";
		}else {
			System.out.println("请输入身份证号：");
			String IDCard=scanner.next();
			sql=sql+"IDCard='"+IDCard+"'";
		}
		//3.执行查询
		Student student=getStudent(sql);
		//4.若存在查询结果，把查询结果封装为Student对象
		
		return student;
	}
	/**
	 * 根据传入的sql返回Student对象
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	private Student getStudent(String sql) throws Exception {
		Student stu=null;
		
		Connection connection=null;
		Statement statement =null;
		ResultSet resultset =null;
		try {
			connection=JDBCTool.getConnection();
			statement=connection.createStatement();
			resultset=statement.executeQuery(sql);
			if(resultset.next()) {
				stu=new Student(resultset.getInt(1), resultset.getInt(2),
						resultset.getString(3), resultset.getString(4), 
						resultset.getString(5), resultset.getString(6),resultset.getInt(7));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(resultset, statement, connection);
		}
		return stu;
	}

	/**
	 * 从控制台读入一个整书，确定要查询的类型
	 * 1.用身份证查询，2.用准考证号查询 其他无效，并提示用户重新输入
	 * @return
	 */
	private int getSearchTypeFromConsole() {
		System.out.println("请输入查询类型:1.用准考证号查询，2.用身份证查询");
		Scanner scanner=new Scanner(System.in);
		int type=scanner.nextInt();
		if(type!=1&&type!=2) {
			System.out.println("输入有误请重新输入");
			throw new RuntimeException();
		}
		//scanner.close();
		return type;
	}

	@Test
	public void testAddNewStudent() throws Exception {
		Student student =getStudentFormConsole();
		addNewStudent(student);
	}
	/**
	 * 从控制台输入学生信息
	 * @return
	 */
	private Student getStudentFormConsole() {
		Scanner scanner=new Scanner(System.in);
			
		Student student =new Student();
		
		System.out.print("FlowID:");
		student.setFlowID(scanner.nextInt());
		System.out.print("Type:");
		student.setType(scanner.nextInt());
		System.out.print("IdCard:");
		student.setIdCard(scanner.next());
		System.out.print("ExamCard:");
		student.setExamCard(scanner.next());
		System.out.print("StudentName:");
		student.setStudentName(scanner.next());
		System.out.print("Location:");
		student.setLocation(scanner.next());
		System.out.print("Grade:");
		student.setGrade(scanner.nextInt());
		//scanner.close();
		return student;
	}
	public void addNewStudent2(Student student) throws Exception {
		String sql="INSERT INTO examstudent(flowid,type,idcard,examcard,studentname,location,grade) VALUES(?,?,?,?,?,?,?)";
		JDBCTool.update(sql, student.getFlowID(),student.getType(),student.getIdCard(),student.getExamCard(),
				student.getStudentName(),student.getLocation(),student.getIdCard());
	}
	public void addNewStudent(Student student) throws Exception {
		String sql="INSERT INTO examstudent "
				+" VALUES("+student.getFlowID()
				+","+student.getType()
				+",'"+student.getIdCard()
				+"','"+student.getExamCard()
				+"','"+student.getStudentName()
				+"','"+student.getLocation()
				+"',"+student.getGrade()+")";
		
		//System.out.println(sql);
		//调用JDBCTools类的update(sql)方法执行插入操作
		JDBCTool.update(sql);
		
	}
}
