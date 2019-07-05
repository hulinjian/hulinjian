package huffumantree1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ReadFile {
	private int words_sum;
	/**
	 * 读取文件，并将文件的内容保存为字符串
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public void opendfile(String path) {
		File file=new File(path);
	}
	public String readfile(String str) throws Exception {
		File file=new File(str);
		InputStream input=new FileInputStream(file);
		byte data[]=new byte[(int)file.length()];
		String file_text=new String(data,0,input.read(data));//input.read(data)等同于file.length不过read(data)返回的是int类型，而file.length()返回的是long型
		input.close();
		return file_text;
	}
	/**
	 * 将内容读到文件里面
	 * @param str1
	 * @param str2
	 * @throws IOException
	 */
	public void writefile(String str1,String str2) throws IOException {
		File file=new File(str1);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream output =new FileOutputStream(file,true);
		output.write(str2.getBytes());
		output.close();
	}
	/**
	 * 将字符串按空格分隔为每个单词
	 * @param str
	 * @return
	 */
	public Map<String,Float> spilt(String str){
		String[] file_text_array=str.split("\\s+");
		
		Map<String,Float> map=new HashMap<>();
		for(String st:file_text_array) {
			Float num=map.get(st);
			if(num==null) {
				words_sum++;
				map.put(st,(float)1);
			}else {
				words_sum++;
				map.put(st,++num);
			}
		}
		return map;
	}
	/**
	 * 计算出现频率
	 */
	public void frequency(Map<String,Float> map) {
		Set<Entry<String,Float>> set=map.entrySet();
		Iterator<Entry<String,Float>> it =set.iterator();
		int sum=0;
		while(it.hasNext()) {
			sum++;
			Entry<String,Float> next=it.next();
			String key=next.getKey();
			Float value=next.getValue();
			value=value/words_sum;
			System.out.println(key+":"+value);
		}
	}
	/**
	 * 形成一个map
	 */
	public Map formMap(String[] str,Object[] obj) {
		Map<String,Object> map=new HashMap<>();
		for(int i=0;i<str.length;i++) {
			map.put(str[i],obj[i]);
		}
		return map;
	}	
}
