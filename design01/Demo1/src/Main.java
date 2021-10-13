
public class Main {
	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "HELLO".toLowerCase();
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		
		String[] names = new String[]{"Bob","Alice","Grace"};
		System.out.println(String.join(";",names));
		
		Integer n1 = 1;
		int n2 = n1;
		Integer n3 = n2;
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(n1 == n3);
		
		Weekday w = Weekday.WED;
		switch(w) {
			case SUN:
				System.out.println("SUN");
				break;
			case MON:
				System.out.println("MON");
				break;
			case FRI:
				System.out.println("FRI");
				break;
			case WED:
				System.out.println("WED");
				break;
		}
	}
}

enum Weekday {
	SUN,MON,WED,THU,FRI,SAT;
}
