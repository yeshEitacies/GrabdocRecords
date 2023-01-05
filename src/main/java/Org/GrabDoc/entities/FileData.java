package Org.GrabDoc.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "FILE_DATA5")
@Component
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userType;
    private String name;
   @Column(unique=true)
    private String newname;
    private String type;
    private String filePath;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilePath() {
		return filePath;
	}
	public FileData(Long id, String name, String type, String filePath) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.filePath = filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
//	public FileData(String name, String type, String filePath) {
//		super();
//		this.name = name;
//		this.type = type;
//		this.filePath = filePath;
	public FileData() {
		super();
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId2) {
		this.userId = userId2;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
}

