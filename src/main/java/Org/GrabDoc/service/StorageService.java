package Org.GrabDoc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Org.GrabDoc.repository.FileCountRepository;
import Org.GrabDoc.entities.FileData;
import Org.GrabDoc.repository.FileDataRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StorageService {


    @Autowired
    private FileDataRepository fileDataRepository;
    @Autowired
    private FileCountRepository fileCountRepository;
    @Autowired
    private ApplicationContext context;

    private final String FOLDER_PATH="C:/Users/yashw/Desktop/storage/";

    

    @SuppressWarnings("unused")
	public String uploadToFileSystem(MultipartFile file, String userType, String userId) throws IOException {
        
    	String filePath=FOLDER_PATH+userId+'/'+file.getOriginalFilename();
    	String str="";
    	Files.createDirectories(Paths.get(filePath));
        
        //FileData fileData= context.getBean("FileData");
        //FileData fileData=new FileData();
    	FileData fileData=context.getBean(FileData.class);
    	int i = (int) fileCountRepository.countByName(file.getOriginalFilename());
    	StringBuilder str2 = new StringBuilder(file.getOriginalFilename());
    	str=str2.insert(str2.lastIndexOf("."),i).toString();
    	System.out.println(str);
        filePath=FOLDER_PATH+userId+'/'+str;
        fileData.setName(file.getOriginalFilename());
        fileData.setNewname(str);
        fileData.setType(file.getContentType());
        fileData.setFilePath(filePath);
        fileData.setUserType(userType);
        fileData.setUserId(userId);;
        		
        fileDataRepository.save(fileData);
        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadFromFileSystem(String fileName, String userType, String userId) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByNewname(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] file = Files.readAllBytes(new File(filePath).toPath());
        return file;
    }
    
    public String deleteFromFileSystem(String fileName, String userType, String userId) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByNewname(fileName);
        String filePath=fileData.get().getFilePath();
        boolean file_Status = Files.deleteIfExists(new File(filePath).toPath());
        if(file_Status==true)
        {
        	return "file deleted";
        }
        else {
        	return "file not found";
        }
    }
     public String updateFileSystem( MultipartFile file, String userType, String userId) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByNewname(file.getName());
        String filePath=fileData.get().getFilePath();
        boolean file_Status = Files.deleteIfExists(new File(filePath).toPath());
        if(file_Status) {}
        String s =uploadToFileSystem( file,userType,userId);
        if (s != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
        
    }   
    public Stream<FileData> getAllFiles(String userId) {
        return fileDataRepository.findAllByUserId(userId).stream();
    }
}
