import java.rmi.Naming;

public class Test {
    public static void main(String[] args) {
        String[] location = {
//                "rmi://Santafe/gumballmachine",
//                "rmi://Boulder/gumballmachine",
                "rmi://127.0.0.1/gumballmachine"
        };
        System.out.println("00");
    
        GumballMonitor[] monitor = new GumballMonitor[location.length];
        
//        for (int i = 0; i < location.length; i++) {
            System.out.println("0011");
    
            try {
                GumballMachineRemote machine = (GumballMachineRemote) Naming.lookup("rmi://127.0.0.1/gumballmachine");
                System.out.println("0011222111");
    
                GumballMonitor monitorObj = new GumballMonitor(machine);
                monitorObj.report();
    
//                System.out.println(monitor[i]);
            } catch (Exception e) {
                System.out.println("0011222");
    
                e.printStackTrace();
            }
//        }
        
        System.out.println("11");
        
//        for (int i = 0; i < monitor.length; i++) {
//            System.out.println("22");
//            monitor[i].report();
//        }
    }
}
