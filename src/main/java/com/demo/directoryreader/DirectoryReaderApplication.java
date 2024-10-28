package com.demo.directoryreader;

import com.demo.directoryreader.service.DirectoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DirectoryReaderApplication {

	private DirectoryService directoryService;

	public void setDirectoryService(DirectoryService directoryService) {
		this.directoryService = directoryService;
	}

	public void readDirectory() {
		System.out.println("Listing files in directory:");
		directoryService.listFilesInDirectory();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DirectoryReaderApplication app = (DirectoryReaderApplication) context.getBean("directoryReaderApplication");
		app.readDirectory();
	}
}
