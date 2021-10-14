import java.util.ArrayList;
import java.util.List;

public class Generics {
	public static void main(String[] args) {
	     new Generics().go();
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

abstract class Animal {
	void eat(){
		System.out.println("Animal eat");
	}
}

class Dog extends Animal{
	@Override
	void eat(){
		System.out.println("Dog eat");
	}
	void bark(){}
}

class Cat extends Animal {
	@Override
	void eat() {
		System.out.println("Cat eat");
	}
	
	void meow(){}
}