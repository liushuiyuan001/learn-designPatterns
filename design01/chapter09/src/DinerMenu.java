public class DinerMenu {
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	MenuItem[] menuItems;

	public DinerMenu() {
		this.menuItems = new MenuItem[MAX_ITEMS];
		
		addItem("Vegetarian", "Vegetarian's description", true, 2.99);
		addItem("BLT", "BLT's description", false, 2.99);
		addItem("Soup of the day", "Soup's description", false, 2.99);
		addItem("Hotdog", "hotdog's description", false, 3.05);
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		if(numberOfItems > MAX_ITEMS) {
			System.out.println("Sorry menu is full! Can't add item to menu");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems++;
		}
	}
	
	public Iterator createIterator() {
		return new DinerMenuIterator(menuItems);
	}
}
