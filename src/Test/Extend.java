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
    private String property;//����property����
	private String getProperty() {
		return property;
	}
	private void setProperty(String property) {
		this.property = property;
	}
	public String introduce(){
		return "��Һ�����һ��"+this.head+"��ͷ���ҵ������"+this.body+"���ҵĽź�"+this.foot+",�ҵ������"+this.height+"�ף��ҵ�������"+this.weight+"ǧ��,�ҵ�������"+this.age+"��";
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
		System.out.println("�ҵ�ְҵ��ѧ��");
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
        System.out.println("��������"+per.getAge()+"�������"+per.getHeight()+"��������"+per.getWeight());
        UniversityStudent universitystudent=(UniversityStudent)per;
        universitystudent.setAge(20);
        universitystudent.setHeight(1.7);
        universitystudent.setWeight(70);
        System.out.println("��������"+universitystudent.getAge()+"�������"+universitystudent.getHeight()+"��������"+universitystudent.getWeight());
    }
}
//final�ؼ��ֶ������Ϊ��������ĳ��࣬��Person���Ϊfinal�������޷��̳�Person�࣬����̳е�ʱ����ֱ���
//����Person���setIntroduce�������ó�final�����������޷��̳д˷������ִ���