import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
public interface State extends Serializable {
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}
