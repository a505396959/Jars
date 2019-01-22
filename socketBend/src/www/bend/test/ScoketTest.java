package www.bend.test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ScoketTest {
	public static void main(String[] args) throws Exception {
//		new Server().start();
		new Client2().start();
	}
}

class Server extends Thread {
	private ServerSocket server = null;

	public Server() throws Exception {
		server = new ServerSocket(8888);
	}

	public void run() {
		try {
			while (true) {
				Socket client = server.accept();
				OutputStream out = client.getOutputStream();
				out.write("server to client : hello".getBytes());
				out.flush();
				out.close();
				client.close();
				sleep(1000);
			}

		} catch (Exception e) {
			System.out.println("server : " + e.getMessage());
		}
	}
}

class Client2 extends Thread {
	
	int i =0;
	public void run() {
		try {
			while (true) {
				String ip = "192.168.1.242";
				int port = 5000; 
				Socket client = new Socket(ip, port);
				InputStream in = client.getInputStream();
				byte[] buff = new byte[4096];
				StringBuffer sbuff = new StringBuffer();
				int len = 0;
				while ((len = in.read(buff)) != -1) {
					sbuff.append(new String(buff, 0, len));
				}
				in.close();
				client.close();
				System.out.println(sbuff.toString());
				sleep(1000);
				i++;
				System.out.println("这是第："+i+"次监听");
			}

		} catch (Exception e) {
			System.out.println("client : " + e.getMessage());
		}
	}
}