package Translat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Transformation {
	static final String KEYHOLE_REPORT="D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"keyhole_report.txt";
	static final String CODE_PATH="D:"+File.separator+"JavaFile"+File.separator+"study"+File.separator+"code.txt";
	public static void main(String[] args) throws Exception {
		writeFile(KEYHOLE_REPORT,CODE_PATH);
		//recovercode(CODE_PATH);
		//System.out.println(matching_ratio(KEYHOLE_REPORT,CODE_PATH));
	}
	public static String[] readFile(String str) throws IOException{
		File file=new File(str);
		InputStream input=new FileInputStream(file);
		byte data[]=new byte[(int)file.length()];
		String file_text=new String(data,0,input.read(data));
		String str1[]=file_text.split("\\s+");
		//System.out.println(str1[0]+":"+str1[1]);
		input.close();
		return str1;
	}
	//	因为转换的时候会编码前面的0会被默认去除
	/*public static long[] changlong(String str) throws IOException {
		String[] file_text=readFile(str);
		long[] file_long = new long[file_text.length];
		for(int i=0;i<file_text.length;i++) {
			file_long[i]=Long.parseLong(file_text[i]);
			//System.out.println(file_long[i]);
		}
		return file_long;
	}*/
	
	/**
	 * 二进制转换十进制
	 * @param binaryNumber
	 * @return
	 * @throws IOException
	 */
	public static String bin2DecXiao(String binaryNumber) throws IOException {
		Long decimal = 0l;
        Long p = 0l;
        Long binaryNumber1=Long.valueOf(binaryNumber);
        if(binaryNumber.substring(0,1).equals("0")) {
        	return binaryNumber;
        }else {
        	while (true) {
        		if (binaryNumber1== 0) {
        			break;
        		} else {
        			Long temp =  (binaryNumber1 % 10);
        			decimal = (long) (decimal + temp * Math.pow(2, p));
        			binaryNumber1 = binaryNumber1 / 10;
        			p++;
        		}
        	}
        	return decimal.toString();
        }
	}
	/**
	 * 十进制转换二进制
	 * @param code_array1
	 * @return
	 */
	public static String binaryToDecimal(String code_array){
		Integer t = 0;  //用来记录位数
		Long bin = 0l; //用来记录最后的二进制数
		Long r = 0l;  //用来存储余数
		Long code_array1=Long.valueOf(code_array);
		if(code_array.substring(0,1).equals("0")) {
	       	return code_array;
	    }else {
			while(code_array1 != 0){
				r = code_array1 % 2;
				code_array1 = code_array1 / 2;          
				bin =(long) (bin+ r * Math.pow(10,t));
	            t++; 
			}
			return bin.toString();
	    }
	}

	public static void writeFile(String str1,String str2) throws IOException {
		String[] code_array=readFile(str1);
		String result="";
		String changecode="";
		File file1=new File(str1);
		File file2=new File(str2);
		if(!file2.getParentFile().exists()) {
			file2.getParentFile().mkdirs();
		}
		OutputStream output =new FileOutputStream(file2);
		for(int i=0;i<code_array.length;i++) {
			//changecode=bin2DecXiao(code_array[i]);
			result=bin2DecXiao(code_array[i])+" ";//这一行出现错误
			//System.out.println(result+":");
			output.write(result.getBytes());
		}	
		output.close();
	}
	public static void recovercode(String str) throws IOException {
		String[] code_array=readFile(str);
		//Integer[] code_array1=new Integer[code_array.length];
		String changecode;
		String result="";
		for(int i=0;i<code_array.length;i++) {
			//code_array1[i]=(int) Long.parseLong(code_array[i]);
		}
		File file=new File(str);
		if(file.exists()) {
			file.createNewFile();
		}
		//file.getParentFile().mkdirs();
		OutputStream output =new FileOutputStream(file);
		for(int i=0;i<code_array.length;i++) {
			//changecode = binaryToDecimal(code_array[i]);
			//System.out.println(changecode);
			result=binaryToDecimal(code_array[i])+" ";
			output.write(result.getBytes());
		}	
		output.close();
	}
	public static double matching_ratio(String str1,String str2) throws Exception {	
		String[] file1=readFile(str1);
		String[] file2=readFile(str2);
		int match=0;
		for(int i=0;i<file1.length;i++) {
			if(file1[i].equals(file2[i])) {
				match++;
			}
		}
		return (match/file1.length);
	}
}
