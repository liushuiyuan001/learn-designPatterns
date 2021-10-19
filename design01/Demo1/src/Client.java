import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {
		testClient();
	}
	
	public static void testClient() {
		String host = "localhost";
		int port = 8080;
		
		System.out.println("连接到主机：" + host + " ,端口号：" + port);
		try {
			Socket client = new Socket(host, port);
			System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			System.out.println("服务器响应：" + in.readUTF());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
