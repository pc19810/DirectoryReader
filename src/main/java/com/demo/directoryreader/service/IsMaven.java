package com.demo.directoryreader.service;

import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IsMaven {
    public boolean isMavenProject(Path projectDirectory){
        if(Files.exists(projectDirectory.resolve("pom.xml"))){
            return true;
        }
        return false;
    }
}
