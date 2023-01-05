package Org.GrabDoc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Org.GrabDoc.entities.FileData;
import Org.GrabDoc.service.StorageService;

import java.io.IOException;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/records")
public class StorageController {

	@Autowired
	private StorageService service;



	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploaFileToFIleSystem(@RequestParam("file")MultipartFile file,@RequestParam("userType")String userType,@RequestParam("userId")String userId) throws IOException {
		String uploadImage = service.uploadToFileSystem(file,userType,userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadFileFromFileSystem(@PathVariable String fileName,@RequestParam("userType")String userType,@RequestParam("userId")String userId) throws IOException {
		byte[] imageData=service.downloadFromFileSystem(fileName,userType,userId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

	@GetMapping("/delete/{fileName}")
	public ResponseEntity<?> deleteFilesFromFIleSystem(@PathVariable String fileName,@RequestParam("userType")String userType,@RequestParam("userId")String userId) throws IOException {
		String filestatus=service.deleteFromFileSystem(fileName,userType,userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(filestatus);

	}
	@GetMapping("/update/{fileName}")
	public ResponseEntity<?> updateFilesFromFIleSystem(@RequestParam("file")MultipartFile file,@RequestParam("userType")String userType,@RequestParam("userId")String userId) throws IOException {
		String filestatus=service.updateFileSystem(file,userType,userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(filestatus);

	}
	@GetMapping("/all")
	public ResponseEntity<?> showAllFiles(@RequestParam("userType")String userType,@RequestParam("userId")String userId) throws IOException {
		Stream<FileData> filestream=service.getAllFiles(userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(filestream);

	}

}
