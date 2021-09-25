import java.util.ArrayList;

public class PancakeHouseMenuIterator implements Iterator{
	ArrayList<MenuItem> menuItems;
	int position = 0;
	
	public PancakeHouseMenuIterator(ArrayList<MenuItem> menuItems){
		this.menuItems = menuItems;
	}
	
	
	@Override
	public boolean hasNext() {
		if(position >= menuItems.size() || menuItems.get(position) == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public Object next() {
		return menuItems.get(position++);
	}
}
