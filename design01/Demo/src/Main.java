import java.util.stream.Stream;

public class Main {
	public static void main(String[] args){
		int a = 10;
		boolean b = false;
		char ch = '中';
		System.out.println("a %d = " + a);
		System.out.println(b);
		System.out.println(ch);
		
		int[] ns = new int[]{1,2,3,4,5,6};
		System.out.println(ns[5]);
		
		String[] str  = new String[]{"a","b","c"};
		String s = str[0];
		s = "d";
		System.out.println(str[0]);
		
		Stream<String> stream = Stream.of("a","b","c","d","e");
		stream.forEach(System.out::println);
	}
}
