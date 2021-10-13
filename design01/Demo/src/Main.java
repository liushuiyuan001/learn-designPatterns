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
		String[] names = new String[]{"zs","ls"};
		p.testMultipleArray(names);
		p.testMultipleArray("ww","wl");
		String bob = "Bob";
		p.name = bob;
		System.out.println(p.name);
		bob = "Alice";
		System.out.println(p.name);
		p.array = names;
		System.out.println(Arrays.toString(p.array));
		names[0] = "zss";
		System.out.println(Arrays.toString(p.array));
		
		Income[] incomes = new Income[] {
			new Income(3000),
			new Salary(7500),
			new SpecialAllowance(15000)
		};
		System.out.println(totalTax(incomes));
		
	}
	
	public static  double totalTax(Income... incomes) {
		double total = 0;
		for (Income income : incomes) {
			total += income.getTax();
		}
		return total;
	}
}

class Person {
	String name;
	String[] array;
	
	Person() {
	
	}
	
	Person(String name, String[] array) {
		this.name = name;
		this.array = array;
	}

	void sayHello() {
		System.out.println("Person Hello");
	}
	
	void sayHello(String name) {
		System.out.println(name);
	}
	
	String sayHello(int age) {
		return "test";
	}
	
	public void testMultipleArray(String... names) {
		 System.out.println(Arrays.toString(names));
	}
	
}

class Student extends Person{
	
	Student() {
	
	}
	
	Student(String name, String[] array) {
		super(name, array);
	}
	
	void sayHello(){
	  System.out.println("Student Hello");
	}
}

class Income {
	double income;
	
	Income(double income) {
		this.income = income;
	}
	
	double getTax() {
		return income * 0.1;
	}
}

class Salary extends Income {
	Salary(double income) {
		super(income);
	}
	
	@Override
	double getTax() {
		if(income <= 5000) {
			return 0;
		}
		return (income - 5000) * 0.2;
	}
}

class SpecialAllowance extends Salary {
	SpecialAllowance(double income) {
		super(income);
	}
	
	@Override
	double getTax() {
		return 0;
	}
}