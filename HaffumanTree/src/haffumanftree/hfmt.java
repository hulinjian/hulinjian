package haffumanftree;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;


public class hfmt {
	
	static readFile read=new readFile();
	static List<Node> nodes = new ArrayList<Node>();
	static List<Node> code_nodes=new ArrayList<Node>();
	static Map<String, Float> map;
	static String FILE_TEXT;
	static String hfmCodeStr;
	static String result;
	static boolean target=false;
	static int match;
	public static void main(String[] args) throws Exception {
		//List<Node> nodes = new ArrayList<Node>();
		String file_path="D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"original_text.txt";
		FILE_TEXT=read.readfile(file_path);
		map=read.spilt(FILE_TEXT);//map键对应的是单词,值对应为权重
		//map=read.frequency(map);
		//System.out.println(map);
		
		Set<Entry<String, Float>> set=map.entrySet();
		Iterator<Entry<String, Float>> it=set.iterator();
		while(it.hasNext()) {
		Entry<String,Float> next=it.next();
		String key=next.getKey();
		Float value=next.getValue();
		nodes.add(new Node(key,value));
		//System.out.println(map);
		}
		
		Node root = hfmt.createTree(nodes);
		
		System.out.println(breadthFirst(root));
		output(root);
		String[] file_text_array=FILE_TEXT.split("\\s+");
		//hfmCodeStr=
		toHufmCode(root,file_text_array);//只能输出一个结果
		
		CodeToString(root,read.readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"keyhole_report.txt"));
		System.out.println(matching_ratio(root));
	}

	
	public static class Node<E>{
		E data;		
		public double weight;//权重
		public String code;
		public Node leftChild;
		public Node rightChild;
		
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
			//super();//这个super()是想要干什么，调用Node父类的构造函数？
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
	
	/**
	 * 构造哈夫曼树
	 */
	private static Node createTree(List<Node> nodes) {
		while(nodes.size()>1) {
			quickSort(nodes);
			Node left =nodes.get(nodes.size()-1);//排序后的最小值
			Node right=nodes.get(nodes.size()-2);//排序后的次小值
			left.code="0";
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

	public static void quickSort(List<Node> nodes) {
		subSort(nodes,0,nodes.size()-1);
	}

	/**
	 * 对节点进行排序
	 * @param nodes
	 * @param start
	 * @param end
	 */
	private static void subSort(List<Node> nodes, int start, int end) {
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
			/*for(int i=start;i<nodes.size()-1;i++) {
				for(int j=i+1;j<nodes.size();j++) {
					Node tem;
					if(nodes.get(i).weight>nodes.get(j).weight) {
						tem=nodes.get(i);
						nodes.set(i, nodes.get(j));
						nodes.set(j, tem);
					}
				}
			}*/
		}
		
	}
	/**
	 * 交换
	 * @param nodes
	 * @param i
	 * @param j
	 */
	private static void swap(List<Node> nodes, int i, int j) {
		Node tmp;
		tmp=nodes.get(i);
		nodes.set(i, nodes.get(j));
		nodes.set(j, tmp);
	}
	/**
	 * 广度优先遍历（对最大程度辐射能够覆盖的节点并对其进行访问）不懂
	 * @param root
	 * @return
	 */
	public static List<Node> breadthFirst(Node root){
		Queue<Node> queue=new ArrayDeque<Node>();
		List<Node> list=new ArrayList<Node>();
		if(root!=null) {
			queue.offer(root);//将根元素加入队列	
		}
		while(!queue.isEmpty()) {
			list.add(queue.peek());
			Node p=queue.poll();
			if(p.leftChild !=null) {
				queue.offer(p.leftChild);
			}
			if(p.rightChild!=null) {
				queue.offer(p.rightChild);
			}
		}
		return list;	
	}
	private static void setCode(Node root) {
		if(root.rightChild!=null) {
			root.rightChild.code=root.code+"0";
			setCode(root.rightChild);
		}
		if(root.leftChild!=null) {
			root.leftChild.code=root.code+"1";
			setCode(root.leftChild);
		}
	}
	
	private static void output(Node node) throws Exception {
		
		if(node.leftChild==null && node.rightChild==null) {
			//System.out.println(node.data+":"+node.weight+":"+node.code);
			String file_path="D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"Huffman_coding_table.txt";
			String str=node.data+"="+node.code+"\r\n";
			read.writefile(file_path,str);
			/*Node newnode=new Node(node.data,node.code,node.weight);
			code_nodes.add(newnode);
			System.out.println(code_nodes);*/
		}
		if(node.leftChild!=null) {
			output(node.leftChild);
		}
		if(node.rightChild!=null) {
			output(node.rightChild);
		}
	}
	/**
	 * 编码
	 * @throws Exception 
	 * 
	 */

	public static String toHufmCode(Node root,String[] str) throws Exception {
		//String hfmCodeStr;
		for(int i=0;i<str.length;i++) {
			//System.out.println(str[i]);
			String s=str[i]+"";
			//System.out.println(s);
			search(root,s);
		}
		return hfmCodeStr;//hfmCodeStr;
		
	}
	private static void search(Node root,String str) throws Exception {
		//String hfmCodeStr;
		if(root.leftChild==null&&root.rightChild==null) {
			if(str.equals(root.data)) {
				//System.out.println(1);
				hfmCodeStr=root.code+"";//为什么加上一个hfmCodeStr就会出现死循环？
				System.out.println(hfmCodeStr);
				read.writefile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"keyhole_report.txt",root.code);
				//System.out.print(hfmCodeStr);
			}
		}
		if(root.leftChild!=null) {
			search(root.leftChild,str);
		}
		if(root.rightChild!=null) {
			search(root.rightChild, str);
		}
	}
	/**
	 * 解码
	 * @throws Exception 
	 */
	public static String CodeToString(Node root,String codeStr) throws Exception {
		int start=0;
		int end=1;
		while(end<=codeStr.length()) {
			target=false;
			String s=codeStr.substring(start,end);
			matchCode(root,s);
			if(target) {
				start=end;
			}
			end++;
		}
		return result;
	}

	private static void matchCode(Node root, String code) throws Exception {
		if(root.leftChild==null&&root.rightChild==null) {
			if(code.equals(root.code)) {
				result =root.data+" ";
				System.out.println(result);
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
	}
	public  static double matching_ratio(Node root) throws Exception {
		
		String[] file1=read.readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"original_text.txt").split("\\s+");
		String[] file2=read.readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"decoded_file.txt").split("\\s+");
		for(int i=0;i<file1.length;i++) {
			if(file1[i].equals(file2[i])) {
				match++;
			}
		}
		//System.out.println(root.weight);
		return (match/root.weight);
	}
}
