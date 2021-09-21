public class CheesePizza extends Pizza{
	public CheesePizza() {
		name = "CheesePizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		toppings.add("Grated Reggino Cheese");
	}
	
	@Override
	public void cut() {
		System.out.println("Cutting the pizza into square slices");
	}
}
