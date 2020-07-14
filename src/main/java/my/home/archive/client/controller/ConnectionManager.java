package my.home.archive.client.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import my.home.archive.client.model.User;

public class ConnectionManager {
	private static final String HOST = "localhost";
	private static final int PORT = 4888;
	
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	
	public ConnectionManager() {}
	
	public void connect() {
		try {
			System.out.println("Client<< Выполняю подключение к серверу...");
			socket = new Socket(HOST, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("Client<< Подключение выполненно");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void signIn(User user) throws IOException {
		System.out.println("Client<< Регестрирую пользователя...");
		sendMessage("signin");
		sendFile(user);
	}
	
	public User login(String username, String password) throws IOException, ClassNotFoundException {
		System.out.println("Client<< Отправляю данные авторизации на сервер...");
		sendMessage("login " + username + " " + password);
		waitForAnswer();
		return  (User) receiveFile();
	}
	
	
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
	
	
	public void waitForAnswer() {
		System.out.println("Client<< Жду ответа сервера...");
		try {
			while (socket.getInputStream().available() == 0) {
				System.out.println("Client<< Zzzz...");
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
		
		try {
			while (in.ready()) {
				message.append(in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return message.toString();
	}
	
	public Object receiveFile() throws IOException, ClassNotFoundException {
		System.out.println("Client<< Принимаю файл...");
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		
		Object obj = ois.readObject();
		
		System.out.println("Client<< Файл принят...");
		
		return obj;
	}
	
	public void sendFile(Object obj) throws IOException {
		System.out.println("Client<< Отправляю файл...");
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		
		oos.writeObject(obj);
		oos.flush();
		System.out.println("Client<< Файл отправлен...");
	}
	
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

}
