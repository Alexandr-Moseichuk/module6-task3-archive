package my.home.archive.server;

import java.util.Arrays;

import my.home.archive.server.controller.Server;

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
		Server server = new Server();
		server.start();

		String[] command = {""};
		
		while (!command[0].equals("exit")) {
			command = server.recieveCommand();
			System.out.println("Info: комманда пришла " + Arrays.toString(command));
			
			switch (command[0].toLowerCase()) {
				case "signin":
					server.signIn();
					break;
				case "login":
					server.login(command);
					break;
				case "getfile":
					server.getFile(command);
					break;
				case "createfile":
					server.createFile();
					break;
				case "editfile":
					server.editFile(command);
					break;
				case "exit":
					server.exit();
					continue;
				default:
					System.out.print("Info: неизвестная команда");
					break;
			}
		}
		
	}

}
