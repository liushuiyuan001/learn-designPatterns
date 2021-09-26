import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent{
	ArrayList<MenuComponent> menuComponents = new ArrayList(10);
	String name;
	String description;
	
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}
	
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}
	
	public MenuComponent getChild(int i) {
		return  (MenuComponent) menuComponents.get(i);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	public void print() {
		System.out.println("----------------Menu-----------");
		System.out.println(getName());
		System.out.println(getDescription());
		System.out.println("--------------------------------");
		
		Iterator iterator = menuComponents.iterator();
		while (iterator.hasNext()) {
			MenuComponent menuComponent = (MenuComponent)iterator.next();
			menuComponent.print();
		}
	}
}
