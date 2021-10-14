import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
//		main.baseType();
//		main.testEnum();
		try {
			main.testReflection();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
	}
	
	void baseType() {
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
	}
	
	void testEnum() {
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
	
	void testReflection() throws NoSuchFieldException {
		Class<Student> stdClass = Student.class;
		System.out.println(stdClass.getField("name"));
		System.out.println(stdClass.getField("score"));
		System.out.println(stdClass.getDeclaredField("grade"));
	}
	
	void testT() {
		ArrayList<Integer> ns = new ArrayList<Integer>();
		ns.add(1);
		System.out.println(ns);
		
		List<String> list = new ArrayList<String>();
		list.add("122");
		System.out.println(list);
		
		Student[] stu = new Student[]{
		
		};
	}
}

class Person {
	public String name;
}

class Student extends Person {
	public int score;
	private int grade;
	public Student() {
	
	}
	public Student(int score, int grade){
		this.score = score;
		this.grade = grade;
	}
}
enum Weekday {
	SUN,MON,WED,THU,FRI,SAT;
}
