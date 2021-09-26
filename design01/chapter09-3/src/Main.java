public class Main {
	public static void main(String[] args) {
		MenuComponent pancakeHouseMenu = new Menu("pancake house menu", "breakfast");
		MenuComponent dinnerMenu = new Menu("diner menu", "lunch");
		MenuComponent cafeMenu = new Menu("Cafe menu", "Dinner");
		
		MenuComponent allMenus = new Menu("All Menus", "All menus combined");
		
		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinnerMenu);
		allMenus.add(cafeMenu);
		
		dinnerMenu.add(new MenuItem("Pasta", "Pasta's description", true, 3.89));
		
		MenuComponent dessertMenu = new Menu("Dessert","Dessert's description");
		dinnerMenu.add(dessertMenu);
		
		dessertMenu.add(new MenuItem("Apple pie", "Apple pie's description", true, 1.59));
		
		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu();
	}
}
