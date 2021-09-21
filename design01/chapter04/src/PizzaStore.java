public class PizzaStore {
	
	SimplePizzaFactory factory = new SimplePizzaFactory();
	
	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}
	
	Pizza orderPizza(String type) {
		Pizza pizza;
		pizza = factory.createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
}
