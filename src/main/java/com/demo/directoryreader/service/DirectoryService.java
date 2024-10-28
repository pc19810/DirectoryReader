package com.demo.directoryreader.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class DirectoryService {

    private String directoryPath;

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void listFilesInDirectory() {
        Path path = Paths.get(directoryPath);

        try (Stream<Path> paths = Files.list(path)) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> System.out.println("File: " + file.getFileName()));
        } catch (IOException e) {
            System.out.println("An error occurred while reading the directory.");
            e.printStackTrace();
        }
    }
}
