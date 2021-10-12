import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args){
		int a = 10;
		boolean b = false;
		char ch = '中';
		System.out.println("a %d = " + a);
		System.out.println(b);
		System.out.println(ch);
		
		int[] ns = new int[]{1,2,3,6,5,4};
		System.out.println(ns[5]);
		
		String[] str  = new String[]{"a","b","c"};
		String s = str[0];
		s = "d";
		System.out.println(str[0]);
		
		for (int n:	ns) {
            System.out.println(n);
		}
		System.out.println("Arrays");
		System.out.println(Arrays.toString(ns));
		Arrays.sort(ns);
		System.out.println(Arrays.toString(ns));
		
		Stream<String> stream = Stream.of("a","b","c","d","e");
		stream.forEach(System.out::println);
		
		Person p = new Student();
		p.sayHello();
		
	}
}

class Person {


	 void sayHello() {
		System.out.println("Person Hello");
	}
}

class Student extends Person{

	void sayHello(){
	  System.out.println("Student Hello");
	}
}