package dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

public class DAOTest {

	DAO dao =new DAO();
	@Test
	public void testUpdate() throws Exception {
		String sql ="INSERT INTO customers(name, email, birth) VALUES(?,?,?)";
		dao.update(sql, "xiaoming","xiaoming@.com",new Date(new java.util.Date().getTime()));
	}
	@Test
	public void testGet() throws Exception {
		String sql ="SELECT ID,NAME,EMAIL,BIRTH FROM customers ";
		Customer customer =dao.get(Customer.class, sql);
		System.out.println(customer);
		//System.out.println(customer.toString());
	}

	@Test
	public void testGetForList() throws Exception {
		String sql ="SELECT ID,NAME,EMAIL,BIRTH FROM customers";
		List<Customer> customer = dao.getForList(Customer.class,sql);
		System.out.println(customer);
	}

	@Test
	public void testGetForValue() throws Exception {
		String sql="SELECT exam_card FROM examstudent WHERE flow_id=?";
		String examCard=dao.getForValue(sql,1);
		System.out.println(examCard);
		sql="SELECT max(grade) FROM examstudent";
		int grade=dao.getForValue(sql);
		System.out.println(grade);
	}

}
