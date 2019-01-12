package it.edu.majoranapa.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server implements Runnable {
	
	private String line;
	private int port;
	ServerSocket server;
	Socket socket;
	
	public Server(int port) {
		this.port = port;
	}

	public void run() {
		try {
			startServer();
		} catch(IOException e) {}
	}
	
	public void startServer() throws IOException{
		server = new ServerSocket(port);
		socket = server.accept();
		
		Scanner in = new Scanner(socket.getInputStream());
		
		while(true) {
			try {
				line = in.nextLine();
				System.out.println(line);
			} catch (NoSuchElementException e) {
				socket.close();
				socket = server.accept();
				in.close();
				in = new Scanner(socket.getInputStream());
			}
		}
	}
	
	public static void sendMessage(String ip, int port, String msg) {
		try {
			Socket socket = new Socket(ip, port);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			out.print(msg);
			out.flush();
			out.close();
			socket.close();
		} catch (IOException e) {}
		
	}

}
