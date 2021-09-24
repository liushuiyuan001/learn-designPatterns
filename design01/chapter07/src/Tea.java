public class Tea extends CaffeineBeverage{
	@Override
	void brew() {
		System.out.println("Steeping the tea");
	}
	
	@Override
	void addConditions() {
		System.out.println("Adding lemon");
	}
}
