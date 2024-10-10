package com.udea_ecomerce.backend.applications;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
    //private final String FOLDER = "src//main//resources//static//images//";
    private static final String FOLDER = "C:/cursos/SringBoot/backend/src/main/resources/static/images/";
    private final String IMG_DEFAULT = "default.jpg";
    private final String URL = "http://localhost:8087/images/";
    
    public String upload(MultipartFile multipartFile) throws IOException{
        if(multipartFile !=null){
            byte [] bytes = multipartFile.getBytes();
            Path path = Paths.get(FOLDER + multipartFile.getOriginalFilename());
            Files.write(path,bytes);
            return URL + multipartFile.getOriginalFilename();
        }
        return URL + IMG_DEFAULT;
    }
    public void delete(String nameFile){
        File file = new File(FOLDER, nameFile);
        file.delete();
    }
}
