import java.util.ArrayList;

public class PancakeHouseMenu {
	ArrayList<MenuItem> menuItems;
	
	public PancakeHouseMenu() {
		menuItems = new ArrayList<MenuItem>();
		
		addItem("K&B's Pancake Breakfast", "K&B's description", true,2.99);
		addItem("Regular Pancake Breakfast", "Regular Pancake Breakfast's description", false,2.99);
		addItem("Blueberry Pancake Breakfast", "Blueberry's description", true,3.49);
		addItem("Waffles Pancake Breakfast", "Waffles' description", true,3.59);
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.add(menuItem);
	}
	
	public Iterator createIterator() {
		return new PancakeHouseMenuIterator(menuItems);
	}
}
