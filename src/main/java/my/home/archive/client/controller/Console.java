package my.home.archive.client.controller;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Console {
	private static Console instance;
	private static Scanner scanner;
	
	private Console() {
		scanner = new Scanner(System.in);
	}
	
	public static Console getInstance() {
		if (instance == null) {
			instance = new Console();
		}
		return instance;
	}
	
	public String nextCommand() {
		System.out.print(">> ");
		return scanner.nextLine();
	}
	
	public String nextUsername() {
		System.out.print(">> ");
		String s = scanner.nextLine();
		while (s.contains(" ") || s.length() < 6) {
			System.out.println("Client<< Ошибка! Имя пользователя не может содержать пробел или быть короче 6 символов!");
			s = scanner.nextLine();
		}
		return s;
	}
	
	public String nextPassword() {
		System.out.print(">> ");
		String s = scanner.nextLine();
		while (s.contains(" ") || s.length() < 6) {
			System.out.println("Client<< Ошибка! Пароль не может содержать пробел или быть короче 6 символов!");
			s = scanner.nextLine();
		}
		return s;
	}
	
	public int nextInt() {
		int number = 0;
		System.out.print(">> ");
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
			System.out.print(">> ");
		}
		number = scanner.nextInt();
		scanner.nextLine();                // сьедает перевод на новую строку. Иначе следующее чтение строки забагует
		return number;
	}
	
	public String nextEmail() {
		System.out.print(">> ");
		String email = scanner.nextLine();
		Pattern pattern = Pattern.compile("[^@]+@[^@]+\\.[^@]+"); //данная проверка не 100%. Нужно отсылать подтверждение на почту
		while (!pattern.matcher(email).matches()) {
			System.out.println("Некорректный e-mail. Введите другой.");
			System.out.print(">> ");
			email = scanner.nextLine();
		}
		return email;
	}
	
	public boolean nextBoolean() {
		int i;
		
		do {
			i = nextInt();
		} while(i < 1 || i > 2);
		
		return i == 1 ? true : false;
	}
	

}