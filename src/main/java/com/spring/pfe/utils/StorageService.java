package com.spring.pfe.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("uploads");

    public String createNameImage(MultipartFile file){

        try {
            int i = (int) new Date().getTime();
            System.out.println("Integer : " + i);
            String ch=Integer.toString(i);
            String dateString = ch.substring(0,ch.length()-1);
            String ext=file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
            String name=file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf('.'));
            String original=name+"-"+dateString+ext;
            return original;
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }

    }

    public void store(MultipartFile file, String filename) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!"+e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}