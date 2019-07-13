package huffumantree1;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huffumantree1.hfmt;
import huffumantree1.hfmt.Node;
import huffumantree1.ReadFile;

public class Test {
	static Map<String,Float> map=new HashMap<String, Float>();
	static final String ORIGINAL_TEST_PATH="D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"original_text.txt";
	static final String KEYHOLE_REPORT_PATH="D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"keyhole_report.txt";
	static String FILE_TEXT;
	static String REGEX="[\\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、.？．\\s]";
	public static void main(String[] args) throws Exception {
		hfmt huffmantree=new hfmt();
		ReadFile readfile=new ReadFile();
		Node root=new hfmt().new Node();
		FILE_TEXT=readfile.readfile(ORIGINAL_TEST_PATH);
		map=readfile.spilt(FILE_TEXT);//给map赋值，键为单词，值为单词出现的次数
		readfile.frequency(map); 	 	// 计算每个单词处出现的频率
		//System.out.println(map);
		huffmantree.addnodes(map);  // 遍历map集合中的键和值，将他作为Node的数据和权重储存在nodes集合里。
		root=huffmantree.createTree(huffmantree.getNodes()); // 创建一个哈夫曼数
		//long startTime=System.nanoTime();
		System.out.println(huffmantree.breadthFirst(root));	 //
		huffmantree.output(root);
		String[] file_text_array=FILE_TEXT.split(REGEX);
		huffmantree.toHufmCode(root,file_text_array);
		//System.out.println(readfile.connect_string("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"code.txt"));
		huffmantree.CodeToString(root,readfile.connect_string(KEYHOLE_REPORT_PATH));
		System.out.println(huffmantree.matching_ratio(root));
		//long endTime=System.nanoTime();
		//System.out.println("运行时间："+(endTime-startTime)+"ns");
	}
	
	
}
