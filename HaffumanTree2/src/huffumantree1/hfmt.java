package huffumantree1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

public class hfmt {
	ReadFile read=new ReadFile();
	String hfmCodeStr;//哈夫曼码连接的字符串
	String result;
	int match=1;
	boolean target=false;
	String REGEX="[\\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，. 、？．\\s]";
	List<Node> nodes=new ArrayList<Node>();
	public class Node<E>{
		E data;		
		public double weight;//权重
		public String code;
		public Node leftChild;
		public Node rightChild;
		
		public Node() {
			this.data=null;
			this.weight=0;
			this.code=null;
		}
		public Node(double weight,Node leftChild,Node rightChile) {
			this.weight=weight;
			this.leftChild=leftChild;
			this.rightChild=rightChile;
		}
	
		public Node(E data,String code,double weight) {
			this.data=data;
			this.code=code;
			this.weight=weight;
		}
		public Node(E data,double weight) {
			//super();//这个super()是想要干什么，调用Node父类的构造函数，可是没有父类啊？没搞懂，删了程序照样可以运行
			this.data=data;
			this.weight=weight;
		}
		public Node(E date,double weight,Node leftChild,Node rightChile) {
			this.data=data;
			this.weight=weight;
			this.leftChild=leftChild;
			this.rightChild=rightChile;
		}
		public String toString() {
			return "Node[data=" + data + ", weight=" + weight + ",code="+code+"]"+"\n";
		}
	}
	
	public void addnodes(Map map) {
		Set<Entry<String, Float>> set=map.entrySet();
		Iterator<Entry<String, Float>> it=set.iterator();
		while(it.hasNext()) {
		Entry<String,Float> next=it.next();
		String key=next.getKey();
		Float value=next.getValue();
		nodes.add(new Node(key,value));
		//System.out.println(map);
		}
	}
	public Node createTree(List<Node> nodes) {
		while(nodes.size()>1) {
			quickSort(nodes);
			Node left=nodes.get(nodes.size()-1);
			Node right=nodes.get(nodes.size()-2);//排序后的次小值
			left.code="0" ;
			right.code="1";
			setCode(left);
			setCode(right);
			Node parent=new Node(null,left.weight+right.weight);//父节点等于子节点相加
			parent.leftChild=left;
			parent.rightChild=right;
			
			nodes.remove(nodes.size()-1);
			nodes.remove(nodes.size()-1);//删除子节点
			nodes.add(parent);
			}
		return nodes.get(0);
	}
	public void quickSort(List<Node> nodes) {
		subSort(nodes,0,nodes.size()-1);
	}
	/**
	 * 对节点进行降序排列
	 * @param nodes
	 * @param start
	 * @param end
	 */
	private void subSort(List<Node> nodes, int start, int end) {
		if(start<end) {
			Node base =nodes.get(start);
			int i=start;
			int j=end+1;
			while(true) {
				while(i<end&&nodes.get(++i).weight>=base.weight);  //  条件为假的时候进入下一个循环，从前面开始找：当找到一项是小于第1项进行下一个循环
				while(j>start&&nodes.get(--j).weight<=base.weight); //	从后面开始找：当找到一项大于于第一项结束
				if(i<j) {
					swap(nodes,i,j);
				}else {
					break;
				}
			}
			swap(nodes,start,j);
			//这两步递归是如何实现的？
			subSort(nodes,start,j-1); //递归左边字序列
			subSort(nodes,j+1,end);	  //递归右边子序列
		}
	}
	private void swap(List<Node> nodes, int i, int j) {
		Node tmp;
		tmp=nodes.get(i);
		nodes.set(i, nodes.get(j));
		nodes.set(j, tmp);
	}
	/**
	 * 广度优先遍历
	 * @param root
	 */
	public List<Node> breadthFirst(Node root){
		Queue<Node> queue=new ArrayDeque<Node>();
		List<Node> list=new ArrayList<Node>();
		if(root!=null) {
			queue.offer(root);//将根元素加入队列，并返回true
		}
		while(!queue.isEmpty()) {
			list.add(queue.peek());//peek()返回队列头部元素
			Node p=queue.poll();	// 移除并返回队列头部的元素
			if(p.leftChild !=null) {
				queue.offer(p.leftChild);//添加一个元素，
			}
			if(p.rightChild!=null) {
				queue.offer(p.rightChild);
			}
		}
		return list;	
	}
	private void setCode(Node root) {
		if(root.rightChild!=null) {
			root.rightChild.code=root.code+"0";
			setCode(root.rightChild);
		}
		if(root.leftChild!=null) {
			root.leftChild.code=root.code+"1";
			setCode(root.leftChild);
		}
	}
	public void output(Node node) throws IOException {
		if(node.leftChild==null && node.rightChild==null) {
			String file_path="D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"Huffman_coding_table.txt";
			String str=node.data+"="+node.code+"\r\n";
			//System.out.println(str);
			read.writefile(file_path,str);
		}
		if(node.leftChild!=null) {
			 output(node.leftChild);
		}
		if(node.rightChild!=null) {
			output(node.rightChild);
		}
	}
	
	public String toHufmCode(Node root,String[] str) throws IOException {
		for(int i=0;i<str.length;i++) {
			String s=str[i]+"";
			search(root,s);
		}
		return hfmCodeStr;
	}
	/**
	 * 
	 * @param root
	 * @param str
	 * @throws IOException
	 */
	private void search(Node root,String str) throws IOException {
		if(root.leftChild==null&&root.rightChild==null) {
			if(str.equals(root.data)) {
				hfmCodeStr=root.code+" ";
			//System.out.println(hfmCodeStr);
				read.writefile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"keyhole_report.txt",hfmCodeStr);
			}
		}
		if(root.leftChild!=null) {
			search(root.leftChild,str);
		}
		if(root.rightChild!=null) {
			search(root.rightChild,str);
		}
	}
	/**
	 * 解码
	 * @param root
	 * @param codeStr
	 * @return
	 * @throws IOException
	 */
	public String CodeToString(Node root,String codeStr) throws IOException {
		int start=0;
		int end=1;
		//int i=0;
		while(end<=codeStr.length()) {
			target=false;
			String s= codeStr.substring(start,end);
			matchCode(root,s);
			if(target) {
				start=end;
			}
			end++;	
			//i++;
		}
		return result;
	}
	private void matchCode(Node root,String code) throws IOException {
		if(root.leftChild==null&&root.rightChild==null) {
			if(code.equals(root.code)) {
				result =root.data+" ";
				//System.out.println(result);
				read.writefile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"decoded_file.txt",result);
				target=true;
			}
		}
		if(root.leftChild!=null) {
			matchCode(root.leftChild,code);
		}
		if(root.rightChild!=null) {
			matchCode(root.rightChild,code);
		}
	
		//read.writefile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"decoded_file.txt",result);
	}
	public double matching_ratio(Node root) throws Exception {	
		String[] file1=read.readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"original_text.txt").split(REGEX);
		String[] file2=read.readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"decoded_file.txt").split(REGEX);
		for(int i=0;i<file1.length;i++) {
			if(file1[i].equals(file2[i])) {
				match++;
			}
		}
		return (match/root.weight);
	}
	public List getNodes() {
		return nodes;
	}
}
