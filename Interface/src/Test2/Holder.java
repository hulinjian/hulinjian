package Test2;

public class Holder {
	public void call_Android(Android android) {
		android.call();
	}
	public void call_Ios(Ios ios) {
		ios.call();
	}
	public void call_Steve(Steve steve) {
		steve.call();
	}
	public static void main(String[] args) {
		Holder h=new Holder();
		h.call_Android(new Huawei());
		h.call_Android(new Vivo());
		h.call_Android(new Mi());
		h.call_Ios(new Iphone());
		h.call_Steve(new StevePhone1());
	}
}
