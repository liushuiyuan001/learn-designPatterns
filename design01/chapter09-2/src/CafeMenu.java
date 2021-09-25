import java.util.Hashtable;
import java.util.Iterator;

public class CafeMenu implements Menu{
	Hashtable<String, MenuItem> menuItems = new Hashtable<>();
	
	public CafeMenu() {
		addItem("Veggie burger and Air Fries", "Veggie's description", true, 3.99);
		addItem("Soup of the day", "Soup's description", false, 3.69);
		addItem("Burrito", "Burrito's description", true, 4.29);
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.put(menuItem.getName(), menuItem);
	}
	
	public Iterator createIterator() {
		return menuItems.values().iterator();
	}
}
