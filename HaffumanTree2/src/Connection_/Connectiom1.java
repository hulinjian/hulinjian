package Connection_1;


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.InputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.Map;
	import java.util.Properties;
	import java.util.Set;
	import java.util.Map.Entry;
	import java.util.regex.Pattern;

	import org.junit.Test;


	public class Connectiom1 {
		String REGEX="[\\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，. 、？．\\s]";
		public Connection getConnection() throws Exception {
			Properties properties =new Properties();
			InputStream inStream=Test1.class.getClassLoader().getResourceAsStream("jdbc.properties");
			properties.load(inStream);
			String user=properties.getProperty("user");
			String password=properties.getProperty("password");
			String jdbcUrl=properties.getProperty("jdbcUrl");
			String driverClass=properties.getProperty("driverClass");
			Class.forName(driverClass);
			Connection connection=DriverManager.getConnection(jdbcUrl,user,password);
			//System.out.println(connection);
			return connection;
		}
		@Test
		public void reaeStatement() throws Exception{
			Connection connection=null;
			ResultSet resultSet=null;
			PreparedStatement perparent=null;
			Map<String,String> map = null;
			//System.out.println("1");
			String sql="SELECT INTO code_table (Word,Code) values(?,?)";
			String sql1="SELECT * from code_table" ;
			try {
				connection=getConnection();
				perparent=connection.prepareStatement(sql1);
				resultSet=perparent.executeQuery(sql1);
				while(resultSet.next()) {
					 String word =resultSet.getString("Word");
					 String code=resultSet.getString("Code");
					// map.put(word,code);
					 System.out.println(word+"::"+code);
				}	
				resultSet.close();
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				releaseDB(resultSet, null, connection);
			} 
				
			
		}
		@Test
		public void writeStatement() throws Exception {
			Connection connection=null;
			Statement statement=null;
			Map<String,String> map=regular();
			try {
				connection=getConnection();
				PreparedStatement perpare=connection.prepareStatement("INSERT INTO code_table (Word,Code) values(?,?)");
				//statement=connection.createStatement();
				Set<Entry<String,String>> set=map.entrySet();
				Iterator<Entry<String,String>> it =set.iterator();
				while(it.hasNext()) {
					Entry<String,String> next=it.next();
					//System.out.println(next.getKey()+":"+next.getValue());
					perpare.setString(1,next.getValue());
					perpare.setString(2,next.getKey());
					perpare.executeUpdate();
					//String sql="INSERT INTO code_table (Word,Code) values ("+
					//"'"+next.getValue()+"','"+next.getKey()+"')";
					//statement.executeUpdate(sql);
				}
				} catch (Exception e) {
				e.printStackTrace();
			}finally {
				releaseDB(null, statement, connection);
			}
		}
		public void releaseDB(ResultSet resultSet,Statement statement,Connection connection) throws Exception {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		public Map regular() throws Exception{
			String[] coding_table=readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"Huffman_coding_table.txt").split("\\s+");
			String[] code_report=readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"keyhole_report.txt").split("\\s+");
			Map<String, String> map=new HashMap<String, String>();
			String regex1="=";//单词为value
			String[] key = new String[code_report.length];
			String[] value = new String[code_report.length];
			Pattern pat1 =Pattern.compile(regex1);
			for(int x=0;x<coding_table.length;x++) {
				String result1[]=pat1.split(coding_table[x]);
				for(int i=0;i<1;i++) {
					//value=result[i];
					key[x]=result1[i+1];
					value[x]=result1[i];
					map.put(key[x], value[x]);
				}
			}
			return map;
		}
		public static String readfile(String str) throws Exception {
			File file=new File(str);
			InputStream input=new FileInputStream(file);
			byte data[]=new byte[(int)file.length()];
			String file_text=new String(data,0,input.read(data));//input.read(data)等同于file.length不过read(data)返回的是int类型，而file.length()返回的是long型
			input.close();
			return file_text;
		}
	}


