package dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 访问数据的DAO接口
 * @author Administrator
 * @param <T>
 *
 */
public interface DAO<T> {
	/**
	 * 批量处理的方法
	 * @param connection
	 * @param sql
	 * @param objects：填充中午饭的Object[] 类型的可变参数
	 */
	void batch(Connection connection,String sql,Object[]...objects );
	/**
	 * 返回具体的一个值，列如总人数，平均工资等
	 * @param connection
	 * @param sql
	 * @param objects
	 * @return
	 */
	<E> E getForValue(Connection connection,String sql,Object ...objects );
	/**
	 * 返回T的一个集合
	 * @param connection
	 * @param sql
	 * @param objects
	 * @return
	 */
	List<T> getForList(Connection connection,String sql,Object ...objects);
	/**
	 * 返回一个T的对象
	 * @param connection
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException 
	 */
	 T get(Connection connection,String sql,Object ... objects) throws SQLException;
	
	/**
	 * INSERT,UPDATE,DELETE
	 * @param connection:数据库连接
	 * @param sql:SQL语句
	 * @param objects:填充占位符的可变参数
	 */
	void update(Connection connection,String sql,Object ...objects );
}
