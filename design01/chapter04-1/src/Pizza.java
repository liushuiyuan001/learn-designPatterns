import java.util.ArrayList;

public abstract class Pizza {
	String name;
	String dough;
	String sauce;
	ArrayList<String> toppings = new ArrayList<>();
	
	public void prepare() {
		System.out.println("prepare " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding topping: ");
		for (String topping : toppings) {
			System.out.println("    " + topping);
		}
	}
	
	public void bake() {
		System.out.println("bake");
	}
	
	public void cut() {
		System.out.println("cut");
	}
	
	public void box() {
		System.out.println("box");
	}
	
	public String getName() {
		return name;
	}
}
