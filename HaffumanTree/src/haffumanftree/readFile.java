package haffumanftree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;



public class readFile  {
	public static int WORDS_NUMBER=0;
	public String readfile(String str) throws Exception {
		File file=new File(str);
		InputStream input =new FileInputStream(file);
		byte data[]=new byte[(int)file.length()];//开辟对应大小的字节空间
		String file_text=new String(data,0,input.read(data));
		return file_text;
		//System.out.println(file_text);
	}
	public void writefile(String str1,String str2) throws Exception {
		File file=new File(str1);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try(OutputStream output=new FileOutputStream(file,true)){
			output.write(str2.getBytes());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 将字符串分割为每个单词。
	 * @param s
	 */
	public static Map spilt(String s) {
		String[] file_text_array=s.split("\\s+");
		Map<String,Float> map = new HashMap<>();
		for(String ss:file_text_array) {
			Float num=map.get(ss);
			//System.out.println(num);
			if(num==null) {
				WORDS_NUMBER++;
				map.put(ss,(float) 1);
			}else {
				WORDS_NUMBER++;			//累计单词总数
				map.put(ss,++num);
			}
		}
			//System.out.println(WORDS_NUMBER);
			//System.out.println(map);
			return map;
			//frequency(map);
		/*for(int i=0;i<file_text_array.length;i++) {
			System.out.println(file_text_array[i]);
		}*/ 
	}
	public static void frequency(Map<String, Float> map) {
		Set<Entry<String, Float>> set=map.entrySet();
		Iterator<Entry<String, Float>> it=set.iterator();
		while(it.hasNext()) {
		Entry<String,Float> next=it.next();
		String key=next.getKey();
		Float value=next.getValue();
		value=(value/WORDS_NUMBER);
		System.out.println(key+":"+value);//这个是每个单词的频率
		}
		//System.out.println(map);

	}
	public static void Iterato(Map<String,Float> map){
		Set<Entry<String, Float>> set=map.entrySet();
		Iterator<Entry<String, Float>> it=set.iterator();
		while(it.hasNext()) {
		Entry<String,Float> next=it.next();
		String key=next.getKey();
		Float value=next.getValue();
		//value=(value/WORDS_NUMBER);
		System.out.println(key+":"+value);//输出对应的键和值
		}
	
	}
}
