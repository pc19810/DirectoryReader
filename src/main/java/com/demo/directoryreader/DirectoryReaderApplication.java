package com.demo.directoryreader;

import com.demo.directoryreader.service.DirectoryService;
import com.demo.directoryreader.service.IsMaven;
import com.demo.directoryreader.service.MyParser;
import com.demo.directoryreader.util.AppConfig;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DirectoryReaderApplication {


	public static void main(String[] args) {
		ApplicationContext context =new AnnotationConfigApplicationContext(AppConfig.class);

		DirectoryService directoryService = context.getBean(DirectoryService.class);
		Path directorypath = directoryService.readDirectoryContent(args[0]);
		IsMaven isMaven=context.getBean(IsMaven.class);
		if(isMaven.isMavenProject(directorypath)){

			System.out.println("Its a Maven Project!!");
			try(Stream<Path>stream= Files.walk(directorypath)){
				stream.filter(path-> path.toString().endsWith(".java")).
				forEach(path->javaProcessor(path,context));
			}
			catch (IOException e) {
				e.printStackTrace();
			}




		}

		else{
			System.out.println("Not a Maven Project!!");
		}


	}
	private static void javaProcessor(Path filePath,ApplicationContext context){

		MyParser myParser = context.getBean(MyParser.class);
		CompilationUnit compilationsFromSingleFile = myParser.createCompilationsFromSingleFile(filePath);
		compilationsFromSingleFile.findAll(StringLiteralExpr.class).stream()
				.filter(literal -> literal.getValue().toLowerCase().startsWith("select") || literal.getValue().toLowerCase().startsWith("update"))
				.forEach(literal -> System.out.println("Found query: " + literal.getValue()));

	}

}
