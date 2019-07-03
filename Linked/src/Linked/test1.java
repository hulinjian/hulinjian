package Linked;

import java.io.PrintStream;

interface ILink<E>{
	public void add(E e);//增加数据	
	public int size();//获取数据个数
	public boolean isEmpty();//空集合判断
	public Object[] toArray();//返回集合数据
	public E get(int index) ;//根据索引获取数据
	public void set(int index,E data);//修改索引数据
	public boolean contains(E data);//判断数据是否存在
	public void remove(E data);//删除数据
	public void clean();//清空链表
}
class LinkImpl<E> implements ILink<E>{
	public class Node<E>{   //为了练习这里使用的是public
		private E data;//数据
		private Node next;//节点
		public Node(E data) {
			this.data=data;
		}
		public void setNext(Node<E> next) {
			this.next=next; 
		}
		public E getData() {
			return this.data;
		}
		public Node getNext() {
			return this.next;
		}
		/**
		 * 第一次调用：this=LinkImpl.root
		 * 第二次调用：this=LinkImpl.root.next
		 * @param newNode
		 */
		public void addNode(Node newNode) {
			if(this.next==null) {//意味着保存当前节点
				this.next=newNode;
			}else {			     //往后继续判断
				this.next.addNode(newNode);//添加的内容不应该都添加在最后面吗？为什么会出现不为空的情况
			}
		}
		/**
		 * 第一次调用this=LinkImpl.root
		 * 第二次调用this=LinkImp.root.next
		 */
		public void toArrayNode() {
			LinkImpl.this.returndata[LinkImpl.this.foot++]=this.data;
			if(this.next!=null) {
				this.next.toArrayNode();
			}
		}
		
		public Object getNode(int index){
			
			if(LinkImpl.this.foot++==index) {
				//System.out.println(foot);
				return this.data;
			}else {
				return  this.next.getNode(index);
			}
		}
		public void setNode(int index,E data) {
			if(LinkImpl.this.foot++==index) {
				//System.out.println(foot);
				this.data=data;
			}else {
				this.next.setNode(index,data);			
			}
		}
		/**
		 * 为什么这里有return了还需要返回
		 * @param data
		 * @return
		 */
		public boolean containsNode(E data) {
			if(this.data.equals(data)) {
				panduan = true; 	//找到了
			}else {
				if(this.next==null) {
					panduan = false;  //找不到
				} else{
					 this.next.containsNode(data);
				}
			}
			return panduan;
		}
		public void removeNode(Node previous,E data) {
			if(this.data.equals(data)) {
				previous.next=this.next;
			}else {
				if(this.next!=null) {//有后续节点	
					this.next.removeNode(this, data);
				}
			}
		}
		
	}
	private Node root;
	private int count;
	private int foot;
	private Object [] returndata;
	private boolean panduan;
	@Override
	public void add(E e) {
		if(e==null) {
			return ;
		}
		Node newNode=new Node(e);
		if(this.root==null) {
			this.root=newNode;
		}else {
			//this.root.next=newNode;
			this.root.addNode(newNode);
		}
		this.count++;
	}
	
	
	/*public void print(Node<?> node) {
		if(node!=null) {
			System.out.println(node.getData());
			print(node.getNext());
		}
	}*/
	@Override
	public int size() {
		return this.count;
	}
	@Override
	public boolean isEmpty() {
		return this.count==0;
	}
	@Override
	public Object[] toArray() {
		if(this.isEmpty()) {
			return null;
		}
		this.foot=0;
		this.returndata=new Object[this.count];//开辟一个对象数组
		this.root.toArrayNode();//利用Node类进行递归数据获取
		return this.returndata;
	}

	@Override
	public E get(int index)  {
	
		if(index>this.count) {
			return null;
		}
		//索引数据的获取应该由Node类完成
		this.foot=0;
		return (E) this.root.getNode(index);
	}
	@Override
	public void set(int index, E data) {
		if(index>this.count) {
			return ;
		}
		this.foot=0;
		this.root.setNode(index,data);
	}
	@Override
	public boolean contains(E data) {
		if(data==null) {
			return false;
		}
		return this.root.containsNode(data);
	}
	@Override
	public void remove(E data) {
		if(this.contains(data)) {
			if(this.root.data.equals(data)) {
				this.root=this.root.next;
			}else {//交由Node类
				this.root.next.removeNode(this.root, data);
			}
		}
	}


	@Override
	public void clean() {
		this.root=null;
		this.count=0;
	}
}
public class test1 {
	public static void main(String[] args) {
		ILink<String> all=new LinkImpl<String>();
		System.out.println(all.size()+" " +all.isEmpty());
		all.add("Hello");
		all.add("word !");
		
		all.set(1, "世界");
		//all.clean();
		System.out.println(all.size()+" " +all.isEmpty());
		//all.remove("世界");
		Object result[]=all.toArray();
		if(result!=null) {
			for(Object obj:result) {
				System.out.println(obj);
			}
		}
		//System.out.println(all.get(0)+":"+all.get(1));
		
		System.out.println("--------------");
		System.out.println(all.get(0)+":"+all.get(1));    //get()方法的空指针异常是什么原因导致的？ 发现set()过后会出现空指针异常，这是什么原因呢？先get()再set()就没有问题但是反过来就出问题。
		System.out.println("--------------");
		System.out.println(all.contains("Hello"));
		System.out.println(all.contains("世界"));
		System.out.println("--------------");
		System.out.println();
		/*Node<String> n1 =new Node<String>("火车头");
		Node<String> n2 =new Node<String>("车厢1");
		Node<String> n3 =new Node<String>("车厢2");
		Node<String> n4 =new Node<String>("车厢3");
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		print(n1);*/
	}

	
	
}
