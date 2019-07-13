package Connection_;

public class Add_information {
	public static void main(String args[]) {
		String[] str= {"avial","bad","abs"};
		for(int i=0;i<str.length-1;i++) {
			if(str[i].compareTo(str[i+1])>0) {
				String a;
				a=str[i];
				str[i+1]=str[i];
				str[i]=a;
			}
			System.out.println(str[0]+":"+str[1]);
		}
	}
}
