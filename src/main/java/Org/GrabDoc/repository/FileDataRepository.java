package Org.GrabDoc.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Org.GrabDoc.entities.FileData;

import java.util.List;
import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Long> {
    Optional<FileData> findByName(String name);
    Optional<FileData> findByNewname(String newname);
    Optional<FileData> findById(String userId);
    List<FileData> findAllByUserId(String userId);
    List<FileData> findAllByName(String name);
    
}
