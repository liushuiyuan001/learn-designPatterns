import java.io.*;

public class Ser {
	public static void main(String[] args) {
//		serObj();
//		serObjProcess();
//		writeFile();
		readFile();
	}
	
	public static void serObj() {
		Employee e1 = new Employee();
		e1.name = "zs";
		e1.address = "ss";
		e1.SSN = 12345;
		e1.number = 101;
		
		Employee e2 = new Employee();
		e2.name = "ls";
		e2.address = "lll";
		e2.SSN = 123456;
		e2.number = 102;
		
		Employee e3 = new Employee();
		e3.name = "ww";
		e3.address = "www";
		e3.SSN = 1234567;
		e3.number = 103;
		
		try {
			FileOutputStream out = new FileOutputStream("./Demo1/src/employee.ser");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			
			oos.writeObject(e1);
			oos.writeObject(e2);
			oos.writeObject(e3);
			
			oos.close();
			out.close();
			
			System.out.println("Success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void serObjProcess() {
		Employee e1 = null;
		Employee e2 = null;
		Employee e3 = null;
		
		try {
			FileInputStream in = new FileInputStream("./Demo1/src/employee.ser");
			ObjectInputStream oos = new ObjectInputStream(in);
			e1 = (Employee) oos.readObject();
			e2 = (Employee) oos.readObject();
			e3 = (Employee) oos.readObject();
			printEmployee(e1);
			printEmployee(e2);
			printEmployee(e3);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile() {
		try {
			FileWriter f = new FileWriter("./Demo1/foo.txt");
			
			f.write("Hello foo!");
			f.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void readFile() {
		try {
			FileReader r = new FileReader("./Demo1/foo.txt");
			
			BufferedReader reader = new BufferedReader(r);
			
			String line = null;
			
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printEmployee(Employee e) {
		System.out.println(e.name);
		System.out.println(e.address);
		System.out.println(e.SSN);
		System.out.println(e.number);
	}
}

class Employee implements Serializable {
	public String name;
	public String address;
	public transient int SSN;
	public int number;
	public void mailCheck(String email){
		System.out.println("email :" + email);
	}
}
