package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread {
	private int numClient;

	public static void main(String[] args) {
		new ServerMT().start();
	}

	
	@Override
	public void run() {
		try {
			ServerSocket ss=new ServerSocket(1234);
			System.out.println("Démarage de serveur ");
			while(true) {
				Socket socket=ss.accept();
				++numClient;
				new Conversation(socket,numClient).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	class Conversation extends Thread{
		private Socket  socket;
		private int numClient;
		public Conversation(Socket s, int num){
			this.socket=s; this.numClient=num;
		}
		@Override
		public void run() {
			try {
				InputStream is=socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is);
				BufferedReader br=new BufferedReader(isr);
				
				OutputStream os=socket.getOutputStream();
				PrintWriter pw=new PrintWriter(os,true);
				String IP=socket.getRemoteSocketAddress().toString();
				System.out.println("connexion du client numéro "+numClient+" IP="+IP);
				pw.println("Bien venue vous etes le client numéro "+numClient);
				while(true){
					String req=br.readLine();
					System.out.println("le client "+IP+" envoyer un requet "+req);
					pw.println(req.length());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
	}

}
