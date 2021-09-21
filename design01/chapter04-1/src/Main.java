public class Main {
	public static void main(String[] args){
		PizzaStore store = new CheesePizzaStore();
		
		Pizza pizza = store.orderPizza("cheese");
		System.out.println("Joel order a " + pizza.getName());
	}
}
