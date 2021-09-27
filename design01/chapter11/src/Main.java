import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Main {
	public static void main(String[] args) {
//		GumballMachine gumballMachine = new GumballMachine(5);
//
//		System.out.println(gumballMachine);
//
//		gumballMachine.insertQuarter();
//		gumballMachine.turnCrank();
//
//		System.out.println(gumballMachine);
//
//		gumballMachine.insertQuarter();
//		gumballMachine.turnCrank();
//		gumballMachine.insertQuarter();
//		gumballMachine.turnCrank();
//
//		System.out.println(gumballMachine);

		GumballMachine gumballMachine = null;
		GumballMachine gumballMachine1 = null;
		GumballMachine gumballMachine2 = null;
		try {
			gumballMachine = new GumballMachine("seattle", 112);
//			gumballMachine1 = new GumballMachine("boulder", 112);
//			gumballMachine2 = new GumballMachine("santafe", 112);
			try {
				Naming.rebind("/gumballmachine", gumballMachine);
//				Naming.rebind("//seattle.mightygumball.com" + "/gumballmachine", gumballMachine);
//				Naming.rebind("//" + gumballMachine1.getLocation() + "/gumballmachine", gumballMachine1);
//				Naming.rebind("//" + gumballMachine2.getLocation() + "/gumballmachine", gumballMachine2);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		GumballMonitor monitor = new GumballMonitor(gumballMachine);

		monitor.report();
	}
}
