public class Singleton {
//	private static Singleton uniqueInstance;
//	private Singleton() {}
//
//	public static synchronized Singleton getInstance() {
//		if (uniqueInstance == null) {
//			uniqueInstance = new Singleton();
//		}
//		return uniqueInstance;
//	}
	
//	private static Singleton uniqueInstance = new Singleton();
//	private Singleton() {}
//
//	public static Singleton getInstance() {
//		return uniqueInstance;
//	}
	
//	private volatile static Singleton uniqueInstance;
//	private Singleton(){}
//	public static Singleton getInstance() {
//		if (uniqueInstance == null) {
//			synchronized(Singleton.class) {
//				if (uniqueInstance == null) {
//					uniqueInstance = new Singleton();
//				}
//			}
//		}
//		return uniqueInstance;
//	}
}
