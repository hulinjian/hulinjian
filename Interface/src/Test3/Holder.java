package Test3;

//此时，出来一个新的操作系统出现，Steve的一系列手机，我们需要再程序中添加
public class Holder {
	
	public void call(Moblie moblie) {
		moblie.call();
	}
	public static void main(String[] args) {
		Holder h=new Holder();
		h.call(new Huawei());
		h.call(new Iphone());
		h.call(new Mi());
		//h.call(new StevePhone1());
		
	}
}
