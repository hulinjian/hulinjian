package Translat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Test1 {
	public static void main(String[] args) throws Exception {
		Map<String,String> map=new HashMap<>();
		map=regular();
		//Iterator(map);
		decode(map);
	}
	public static Map regular() throws Exception{
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
	public static void Iterator(Map map) {
		Set<Entry<String,String>> set=map.entrySet();
		Iterator<Entry<String,String>> it =set.iterator();
		while(it.hasNext()) {
			Entry<String,String> next=it.next();
			String key1=next.getKey();
			String value1=next.getValue();
			System.out.println(key1+"::----"+value1);
		}
	}
	public String connect_string(String str) throws Exception {
		String str1=readfile(str);
		String[] str2=str1.split("\\s+");
		String str3 = "";
		for(int i=0;i<str2.length;i++) {
			str3=str3+str2[i];
		}
		return str3;
	}
	public static void decode(Map map) throws Exception {
		File file=new File("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"decode.txt");
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		String[] code_report=readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"keyhole_report.txt").split("\\s+");
		OutputStream output=new FileOutputStream(file);
		for(int j=0;j<code_report.length;j++) {
			String code=(String) map.get(code_report[j])+" ";
			output.write(code.getBytes());
		}

		/*Set<Entry<String,String>> set=map.entrySet();
		Iterator<Entry<String,String>> it =set.iterator();	
		int i=0;
		String[] key = new String[code_report.length];
		String[] value=new String[code_report.length];
		while(it.hasNext()) {
			 Entry<String,String> next=it.next();
			 key[i]=next.getKey();
			 System.out.println(key[i]);
			 value[i]=next.getValue();
			 i++;
			System.out.println(next.getKey()+":"+next.getValue());
		}*/
		/*for(int k=0;k<code_report.length;k++) {
			System.out.println(code_report[k]+":");
		}	
		//Iterator(map);
		for(int j=0;j<map.size();j++) {
			//System.out.println(key[j]+":::"+value[j]);
		}*/
				/*for(int j=0;j<code_report.length;j++) {
			System.out.print(code_report[j]+":");
			for(int z=0;z<code_report.length;z++) {
				//if(code_report[j].equals(map.get(code_report[j]))) {//为什么全为空
				System.out.println(map.get(code_report[j]));
				//System.out.println(value[z]+":"+key[z]);
					String recode=key[z]+" ";
					output.write(recode.getBytes());
			}
		}*/
	}
}

