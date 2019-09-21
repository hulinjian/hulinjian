package Test1;

//定义4款手机，现在Holder只能使用Huawei中的call方法，但是我们如果想使用，其他手机的call该怎么办？这里先引用继承来解决
public class Holder {
	public void call_Huawei (Huawei huawei) {
		huawei.call(); 
	}
	public void call_Iphone(Iphone iphone) {
		iphone.call();
	}
	/*public void call_StevePhone1(StevePhone1 stevephone1) {
		stevephone1.call();
	}*/
	public static void main(String[] args) {
		new Holder().call_Huawei(new Huawei());
		//new Holder().call_Iphone(new Iphone());
	}
}
