import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Generics {
	public static void main(String[] args) {
//	     new Generics().go();
//		 new Generics().testE();
		testLocalDateTime();
//		 testZonedDateTime();
	}
	
	public void go() {
		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Dog());
		takeAnimals(animals);
		
//		ArrayList<Dog> dogs = new ArrayList<>();
//		dogs.add(new Dog());
//		dogs.add(new Dog());
//		takeAnimals(dogs);
		test();
	}
	
	public void testE() {
//		Integer[] intArray = new Integer[]{1,2,3,4,5};
//		Double[] doubleArray = new Double[]{1.0,2.0,3.0,4.0,5.0};
//		printArray(intArray,intArray);
		
//		System.out.println(maximum(1,2));
//		System.out.println(maximum(new Dog(), new Dog()));
	
		Box<Integer> integerBox = new Box<Integer>();
		integerBox.add(10);
		
		Box<String> stringBox = new Box<String>();
		stringBox.add("Hello World");
		
		List<String> names = new ArrayList<String>();
		names.add("icon");
		getData(names);
		
		List<Integer> age = new ArrayList<Integer>();
		age.add(12);
		getData(age);
		
		getUperNumber(age);
	}
	
	public static void testLocalDateTime() {
		// 获取当前的日期时间
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("localDateTime: " +  localDateTime);
		
		LocalDate date1 = localDateTime.toLocalDate();
		System.out.println("date1: " + date1);
		
		int year = localDateTime.getYear();
		Month month = localDateTime.getMonth();
		
		int day = localDateTime.getDayOfMonth();
		int hour = localDateTime.getHour();
		int minute = localDateTime.getMinute();
		int seconds = localDateTime.getSecond();
		
		System.out.println("year:" + year + " month:" + month.getValue() + " day:" + day + " h:" + hour + " m:" + minute + " s:" + seconds);
		
		LocalDateTime date2 = localDateTime.withDayOfMonth(10).withMonth(9).withYear(2012);
		System.out.println("date2: " +  date2);
		
		LocalDate date3 = LocalDate.of(2012, Month.AUGUST,12);
		System.out.println("date3: " + date3);
		
		LocalTime time = LocalTime.of(22,15,23);
		System.out.println("time: " + time);
		
		LocalTime time1 = LocalTime.of(22,15);
		System.out.println("time1: " + time1);
		
		LocalTime time2 = LocalTime.parse("20:15");
		System.out.println("time2 " + time2);
		
	}
	
	public static void testZonedDateTime() {
		// 获取当前时间日期
		ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println(date1);
		
		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println(id);
		
		ZoneId currentZoneId = ZoneId.systemDefault();
		System.out.println(currentZoneId);
	}
	
	public static void getData(List<?> data){
		System.out.println(data.get(0));
	}
	
	public static  void getUperNumber(List<? extends Number> data) {
		System.out.println(data.get(0));
	}
	
	public static <E,T> void printArray(E[] inputArray,T[] otherArray) {
	    for(E e : inputArray) {
			System.out.println(e);
		}
		for(T e : otherArray) {
			System.out.println(e);
		}
	}
	
	public static <T extends Comparable<T>> T maximum(T x,T y) {
		
		return x.compareTo(y) > 0 ? x : y;
	}
	
	public void takeAnimals(ArrayList<? super Animal> animals) {
		for (Object animal : animals) {
			Animal animal1 = (Animal) animal;
			animal1.eat();
		}
	}
	
	void test(){
//		ArrayList<Dog> dog1 = new ArrayList<Animal>();
		
//		ArrayList<Animal> animal1 = new ArrayList<Dog>();
		
		List<Animal> animals = new ArrayList<Animal>();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		List<Object> objList = objects;
		
//		ArrayList<Object> objs = new ArrayList<Dog>();
	}
}

class Box<T> {
	private T t;
	private T name;
	
	public void add(T t) {
		this.t = t;
	}
	
	public T getT() {
		return t;
	}
	
	public T getName() {
		return name;
	}
	
	public void setName(T name) {
		this.name = name;
	}
}
abstract class Animal {
	public String names = "";
	void eat(){
		System.out.println("Animal eat");
	}
}

class Dog extends Animal implements Comparable{
	@Override
	void eat(){
		System.out.println("Dog eat");
	}
	void bark(){}
	
	@Override
	public int compareTo(Object o) {
		Dog g = (Dog)o;
		return (g.names).compareTo(this.names);
	}
}

class Cat extends Animal {
	@Override
	void eat() {
		System.out.println("Cat eat");
	}
	
	void meow(){}
}