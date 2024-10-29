package com.demo.directoryreader.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class DirectoryService {

    public Path readDirectoryContent(String projectPath) {
        return Paths.get(projectPath);
    }

}
