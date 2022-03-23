package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {

	public static void main(String[] args) throws Exception{
		System.out.println("je connecter au server");
		Socket socket=new Socket("localhost",1234);
		InputStream is=socket.getInputStream();
		OutputStream os=socket.getOutputStream();
		//saiser un nombre
		Scanner scanner=new Scanner(System.in);
		System.out.println("donner un nombre");
		int nb=scanner.nextInt();
		System.out.println("j'envoie le nombre"+nb+" au serveur");
		os.write(nb);
		System.out.println("j'attend la reponse");
		int res=is.read();
		System.out.println("la reponse de serveur est  "+res);

	}

}
