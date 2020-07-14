package my.home.archive.server.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ConnectionManager {
	private Socket clientSocket;
	private BufferedReader in;
	private BufferedWriter out;
	
	public ConnectionManager(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public void connect() throws IOException {
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		System.out.println("Info: создано поключение");
	}
	
	public void disconnect() {
		try {
			clientSocket.close();
			in.close();
			out.close();
		} catch (IOException e) {
			System.out.println("ERROR: ошибка закрития подключения");
			e.printStackTrace();
		}
		
	}
	
	public void waitForMessage() {
		System.out.println("Info<< Жду сообщения от клиента...");
		try {
			while(clientSocket.getInputStream().available() == 0) {
				System.out.println("Info: Zzzzz....");
				Thread.sleep(1500);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String recieveMessage() {
		StringBuilder message = new StringBuilder();
		System.out.println("Info<< принимаю сообщение");
		try {
			while (in.ready()) {
				System.out.println("Info<< принимаю линию");
				message.append(in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return message.toString();
	}
	
	/*
	public void sendMessage(String message) throws IOException {
		out.write(message);
		out.newLine();
		out.flush();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Client<< Сообщение отправлено...");
	}
	*/
	
	public Object receiveFile() throws IOException, ClassNotFoundException {
		System.out.println("Info<< Принимаю файл...");
		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		
		Object obj = ois.readObject();
		
		System.out.println("Info<< Файл принят...");
		
		return obj;
	}
	
	public void sendFile(Object obj) throws IOException {
		System.out.println("Info<< Отправляю файл...");
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		
		oos.writeObject(obj);
		oos.flush();
		
		System.out.println("Info<< Файл отправлен...");
	}

}
