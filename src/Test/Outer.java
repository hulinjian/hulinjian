package Test;

public class Outer {
    public static class StaticClass_1{
    	private int a;

		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
    }
	public class InnerClass_1{
		int a;

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}
	}
	public void test(int a){
		System.out.println(a);
	}
	public static void main(String[] args) {
	       int a=1;
	       Outer outer=new Outer();
	       Outer.InnerClass_1 inner=outer.new InnerClass_1();
	       StaticClass_1 static1=new StaticClass_1();
	       outer.test(a);
	       inner.setA(2);
	       outer.test(inner.a);
	       static1.setA(5);
	       outer.test(static1.a);
		}
}
