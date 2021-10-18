import java.util.*;

public class CollectionTest {
	public static void main(String[] args) {
//		testArray();
//		testMap();
//		testArrayList();
//		testLinkedList();
//		testHashSet();
		testHashMap();
	}
	
	public static void testArray() {
		List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("World");
		list.add("Haha");
		
		for(String s : list) {
			System.out.println(s);
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		Iterator<String> ite = list.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
		
	}
	
	public static void testMap() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "value1");
		map.put("2", "value2");
		map.put("3", "value3");
		
		System.out.println("通过Map.keySet遍历key和value");
		for (String key : map.keySet()) {
			System.out.println("key= " + key + " and value= " + map.get(key));
		}
		
		System.out.println("通过Map.entrySet使用iterator遍历key和value：");
		Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,String> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getKey());
		}
		
		System.out.println("通过Map.entrySet遍历key和value");
		for (Map.Entry<String,String> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		
		System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
		for (String v : map.values()) {
			System.out.println("value= " + v);
		}
	}
	
	public static void testArrayList() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Runoob");
		list.add("Taobao");
		list.add("Google");
		list.add("Weibo");
		System.out.println(list);
		list.remove(3);
		System.out.println(list);
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		for(String str : list) {
			System.out.println(str);
		}
		Collections.sort(list);
		
	}
	
	public static void testLinkedList() {
		LinkedList<String> list = new LinkedList<>();
		list.add("Google");
		list.add("Runoob");
		list.add("Taobao");
		list.add("Weibo");
		System.out.println(list);
		list.addFirst("Wiki");
		System.out.println(list);
		list.addLast("World");
		System.out.println(list);
		list.removeFirst();
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		
	}
	
	public static void  testHashSet() {
		HashSet<String> set = new HashSet<>();
		set.add("Google");
		set.add("Runoob");
		set.add("Taobao");
		set.add("Zhihu");
		set.add("Runoob");
		System.out.println(set);
		System.out.println(set.contains("Taobao"));
		System.out.println(set.contains("taobao"));
		set.remove("Google");
		
		for(String str : set) {
			System.out.println(str);
		}
	}
	
	public static void testHashMap() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1,"Google");
		map.put(2,"Runoob");
		map.put(3,"Taobao");
		map.put(4,"Zhihu");
		System.out.println(map);
		
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("one", "Google");
		map1.put("two", "Runoob");
		map1.put("three","Taobao");
		map1.put("four", "Zhihu");
		System.out.println(map1);
		
		for(String s : map1.keySet()) {
			System.out.println("key: " + s + " value: "  + map1.get(s));
		}
		
		for(String value : map1.values()) {
			System.out.println("value " + value);
		}
	}
}
