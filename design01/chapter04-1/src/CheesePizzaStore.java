public class CheesePizzaStore extends PizzaStore{
	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		}
		return pizza;
	}
}
