import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		new GreetingServer(8080).run();
	}
}


class GreetingServer extends Thread {
	private ServerSocket serverSocket;
	
	public GreetingServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}
	
	public void run() {
		while(true) {
			
			System.out.println("等待远程连接，端口号：" + serverSocket.getLocalPort() + "...");
			try {
				Socket server = serverSocket.accept();
				System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println(in.readUTF());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}