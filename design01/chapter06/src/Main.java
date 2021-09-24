public class Main {
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();
		
		WildTurkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);
		
		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();
		
		System.out.println("The Dock says...");
		duck.quack();
		duck.fly();
		
		System.out.println("The TurkeyAdapter says...");
		turkeyAdapter.quack();
		turkeyAdapter.fly();
		
	}
}
