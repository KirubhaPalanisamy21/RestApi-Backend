package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Library;



@Repository
public interface Libraryrepository extends JpaRepository<Library, Long>{

	
}
