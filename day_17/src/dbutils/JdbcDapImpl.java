package dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * 使用QueryRunner提供其具体实现
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class JdbcDapImpl<T> implements DAO<T> {

	private QueryRunner queryRunner =null;
	private Class<T> type;
	public JdbcDapImpl() {
		queryRunner =new QueryRunner();
		type =ReflectionUtils.getSuperClassGenricType(getClass(),0);
	}
	@Override
	public void batch(Connection connection, String sql, Object[]... objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E> E getForValue(Connection connection, String sql, Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(Connection connection, String sql, Object... objects) throws SQLException {
		
		return queryRunner.query(connection, sql,new BeanHandler<>(type),objects );
	}

	@Override
	public void update(Connection connection, String sql, Object... objects) {
		// TODO Auto-generated method stub
		
	}

}
