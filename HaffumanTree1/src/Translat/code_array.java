package Translat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;



public class code_array {
	
	public static String readfile(String str) throws IOException {
		File file=new File(str);
		InputStream input=new FileInputStream(file);
		byte data[]=new byte[(int)file.length()];
		String file_text=new String(data,0,input.read(data));//input.read(data)等同于file.length不过read(data)返回的是int类型，而file.length()返回的是long型
		input.close();
		return file_text;
	}
	public static void array() throws IOException{
		String str=readfile("D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"Huffman_coding_table.txt");
		String[] code=str.split("\\s+");
	}
	
}
