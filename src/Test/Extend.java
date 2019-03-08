package Test;

class Person{
	Person(){}
	protected String head;
    protected String body;
    protected String hand;
    protected String foot;
    protected double height;
    protected double weight;
    protected int age;

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setIntroduce(String head,String body,String foot,double height,double weight,int age){
    	this.head=head;
    	this.body=body;
    	this.foot=foot;
    	this.height=height;
    	this.weight=weight;
    	this.age=age;
    }
    private String property;//建立property属性
	private String getProperty() {
		return property;
	}
	private void setProperty(String property) {
		this.property = property;
	}
	public String introduce(){
		return "大家好我有一个"+this.head+"的头，我的身体很"+this.body+"，我的脚很"+this.foot+",我的身高是"+this.height+"米，我的体重是"+this.weight+"千克,我的年龄是"+this.age+"岁";
	}
}
class Student extends Person{
	Student(){}
	private String curriculum;
	Student(String head,String body,String hand,String foot,double height,double weight,int age,String curriculum){
		this.head=head;
    	this.body=body;
    	this.hand=hand;
    	this.foot=foot;
    	this.height=height;
    	this.weight=weight;
    	this.age=age;
    	this.curriculum=curriculum;
	}
	public void setIntroduce(String head,String body,String foot,double height,double weight,int age) {
		this.head=head;
    	this.body=body;
    	this.foot=foot;
    	this.height=height;
    	this.weight=weight;
    	this.age=age;
	}
	public void setProfession() {
		System.out.println("我的职业是学生");
	}
}
class UniversityStudent extends Student{
	private double height;
	private double weight;
	private int age;
	UniversityStudent(){
		
	}
	UniversityStudent(double height,double weight,int age){
		 super(null,null,null,null,height,weight,age,null);   
	     }
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
public class Extend {
    public static void main(String[] args) {
		Person per=new UniversityStudent();
		per.setAge(40);
        per.setHeight(1.8);
        per.setWeight(60);
        System.out.println("父类年龄"+per.getAge()+"父类身高"+per.getHeight()+"父类体重"+per.getWeight());
        UniversityStudent universitystudent=(UniversityStudent)per;
        universitystudent.setAge(20);
        universitystudent.setHeight(1.7);
        universitystudent.setWeight(70);
        System.out.println("子类年龄"+universitystudent.getAge()+"子类身高"+universitystudent.getHeight()+"父类体重"+universitystudent.getWeight());
    }
}
//final关键字定义的类为所有子类的超类，若Person类改为final则子类无法继承Person类，子类继承的时候出现报错
//若将Person类的setIntroduce方法设置成final方法则子类无法继承此方法出现错误