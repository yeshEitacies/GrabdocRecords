package Org.GrabDoc.repository;

import org.springframework.data.repository.CrudRepository;

import Org.GrabDoc.entities.FileData;

public interface FileCountRepository extends CrudRepository<FileData, Long> {
        long countByName(String name);
 

}
