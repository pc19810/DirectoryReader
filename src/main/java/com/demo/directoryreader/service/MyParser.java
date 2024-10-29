package com.demo.directoryreader.service;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Path;

@Service
public class MyParser {

    public CompilationUnit createCompilationsFromSingleFile(Path path){
        try {
            return  StaticJavaParser.parse( path.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
