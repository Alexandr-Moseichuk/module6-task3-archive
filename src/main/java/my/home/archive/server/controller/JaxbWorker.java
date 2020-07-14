package my.home.archive.server.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import my.home.archive.server.model.Student;
import my.home.archive.server.model.User;

public class JaxbWorker {
	private FileSystem fileSystem;

	public JaxbWorker() {
		fileSystem = new FileSystem();
	}

	public void studentToXml(Student student) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File file = fileSystem.createFileForStudent(student);
			
			if (file != null) {
				marshaller.marshal(student, file);
				System.out.println("Info: создан файл студента " + file.getAbsolutePath());
			} else {
				System.out.println("ERROR: ошибка создания файла ");
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Student xmlToStudent(int id) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File file = fileSystem.getStudentFile(id);
			
			Student student;
			if (file != null) {
				student = (Student) unmarshaller.unmarshal(file);
				System.out.println("Info: прочитан файл студента " + file.getAbsolutePath());
			} else {
				System.out.println("ERROR: ошибка чтения файла");
				student = new Student();
			}
			
			return student;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void userToXml(User user) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			File file = fileSystem.createFileForUser(user);
			
			if (file != null) {
				marshaller.marshal(user, file);
				System.out.println("Info: создан файл пользователя " + file.getAbsolutePath());
			} else {
				System.out.println("ERROR: ошибка создания файла ");
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User xmlToUser(String username) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File file = fileSystem.getUserFile(username);
			
			User user = new User();
			if (file != null) {
				user = (User) unmarshaller.unmarshal(file);
				System.out.println("Info: прочитан файл пользователя " + file.getAbsolutePath());
			} else {
				System.out.println("ERROR: ошибка чтения файла");
			}

			return user;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
