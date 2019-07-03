package Linked;

import Linked.LinkImpl.Node;

interface IGoods{
	public String getName();
	public double getPrice();
}
interface IShopCar{ 	//购物车
	public void add(IGoods goods); 	//添加商品信息	
	public void delete(IGoods goods);	//删除商品
	public Object[] getAll();
}
class ShopCarImpl implements IShopCar{
	private ILink<IGoods> allGoodses=new LinkImpl<IGoods>();
	@Override
	public void add(IGoods goods) {
		this.allGoodses.add(goods);
	}
	@Override
	public void delete(IGoods goods) {
		this.allGoodses.remove(goods);
	}

	@Override
	public Object[] getAll() {
		return this.allGoodses.toArray();
	}
	
}
class Cashier{
	private IShopCar shopcar;
	public  Cashier(IShopCar shopcar) { //计算总价
		this.shopcar=shopcar;
	}
	public double allPrice() {
		Object result[]=this.shopcar.getAll();
		double all=0.0;
		for(Object obj:result) {
			IGoods goods =(IGoods) obj;
			all+=goods.getPrice();
		}
		return all;
	}
	public int allCount() {
		return this.shopcar.getAll().length;
	}
}
class Book implements IGoods {
	private String name;
	private double price;
	public Book(String name,double price) {
		this.name=name;
		this.price=price;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof Book)) {
			return false;
		}
		Book book=(Book) obj;
		return this.name.equals(book.name)&&this.price==book.price;
	}
	public String toString() {
		return "【图书信息】名称："+this.name+"、价格："+this.price;
	}
}
class Bag implements IGoods {
	private String name;
	private double price;
	public  Bag(String name,double price) {
		this.name=name;
		this.price=price;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof Book)) {
			return false;
		}
		Bag bag=(Bag) obj;
		return this.name.equals(bag.name)&&this.price==bag.price;
	}
	public String toString() {
		return "【书包信息】名称："+this.name+"、价格："+this.price;
	}
}
public class shiping {
	public static void main(String[] args) {
		IShopCar car=new ShopCarImpl();
		car.add(new Book("Java开发",79.8));
		car.add(new Book("Oracle开发",89.8));
		car.add(new Bag("小强背包",889.8));
		Cashier cas =new Cashier(car);
		System.out.println("购买总价格："+cas.allPrice()+"、购买总数量"+cas.allCount());
		}
}
