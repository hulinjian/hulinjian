package Test;

class Data {
    public static double chain;
    private double math;
	private double english ;
    Data(){
   	System.out.println("【Data类的无参构造方法】");
    }
	Data(double chain){
		 this.chain=chain;
	}
	Data(double math,double english){
	   	 this.math=math;
	   	 this.english=english;
	     }
	public void add(){
	     double sum=this.chain+this.english+this.math;
         System.out.println(sum);
	}
	public double getMath() {
		return math;
	}
	public void setMath(double math) {
		this.math = math;
	}
	public double getEnglish() {
		return english;
	}
	public void setEnglish(double english) {
		this.english = english;
	}
}
public class Main {
	public static void main(String[] args) {
		Data data1=new Data();
		Data data2=new Data(90.0);
		Data data3=new Data(89.5,90.5);
		System.out.println(data1);//为什么会出现一个地址？
		data3.add();
	}
	
}
