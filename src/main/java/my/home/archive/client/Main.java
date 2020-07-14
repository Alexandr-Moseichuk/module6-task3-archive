package my.home.archive.client;

import my.home.archive.client.controller.ArchiveClient;
import my.home.archive.client.controller.Console;

/*Задание 3: создайте клиент-серверное приложение “Архив”.
Общие требования к заданию:
	• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
	• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в
него изменения, или создать новое дело.
Требования к коду лабораторной работы:
	• Для реализации сетевого соединения используйте сокеты.
	• Формат хранения данных на сервере – xml-файлы.*/

public class Main {
	
	public static void main(String[] args) {
		Console console = Console.getInstance();
		ArchiveClient client = new ArchiveClient();
		String command;
		
		System.out.println(ArchiveClient.MENU);
		
		do {
			System.out.println("Client<< Введите команду");
			command = console.nextCommand().toLowerCase();
			switch (command) {
				case "help":
					System.out.println(ArchiveClient.MENU);
					break;
				case "signin":
					client.signIn();
					break;
				case "login":
					client.login();
					break;
				case "getfile":
					client.getFile();
					break;
				case "createfile":
					client.createFile();
					break;
				case "editfile":
					client.editFile();
					break;
				case "exit":
					client.exit();
					continue;
				default:
					System.out.println("Client<< Неизвестная команда");
					break;
			}
		} while(!command.equals("exit"));
	}
	
}
