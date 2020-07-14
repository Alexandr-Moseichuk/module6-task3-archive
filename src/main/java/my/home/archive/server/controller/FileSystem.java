package my.home.archive.server.controller;

import java.io.File;
import java.io.IOException;

import my.home.archive.server.model.Student;
import my.home.archive.server.model.User;

public class FileSystem {
	private static final String FILE_EXTENTION = ".xml";
	private static final String FILES_FOLDER = "files/";
	private static final String USERS_FOLDER = "users/";
	
	public FileSystem() {
		File files = new File(FILES_FOLDER);
		File users = new File(USERS_FOLDER);
		
		if (!files.exists()) {
			files.mkdirs();
		}
		
		if (!users.exists()) {
			users.mkdirs();
		}
	}
	
	public File createFileForStudent(Student student) throws IOException {
		String filePath = student.getId() + FILE_EXTENTION;
		File file = new File(FILES_FOLDER, filePath);
		
		file.setWritable(true);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		return file;
	}
	
	public File getStudentFile(int id) throws IOException {
		String filePath = FILES_FOLDER + id + FILE_EXTENTION;
		File file = new File(filePath);
		
		if (!file.exists()) {
			return null;
		}
		
		return file;
	}
	
	public File createFileForUser(User user) throws IOException {
		String filePath =  USERS_FOLDER + user.getUsername() + FILE_EXTENTION;
		File file = new File(filePath);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		return file;
	}
	
	public File getUserFile(String username) throws IOException {
		String filePath = USERS_FOLDER + username + FILE_EXTENTION;
		File file = new File(filePath);
		
		if (!file.exists()) {
			return null;
		}
		
		return file;
	}

}
