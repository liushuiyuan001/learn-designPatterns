public abstract class CaffeineBeverage {
	final void prepareRecipes() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {
			addConditions();
		}
	}
	abstract void brew();
	abstract void addConditions();
	
	public void boilWater() {
		System.out.println("boiling water ");
	}
	
	public void pourInCup() {
		System.out.println("pouring into cup");
	}
	
	public boolean customerWantsCondiments() {
		return true;
	}
}
