public class Whip extends CondimentDecorator{
	Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
	
	@Override
	public double cost() {
		return .80 + beverage.cost();
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + " , Whip";
	}
}
