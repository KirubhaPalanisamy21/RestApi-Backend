package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.example.demo.Repository.Libraryrepository;
import com.example.demo.entity.Library;

@Service
public class Libraryservice {
	@Autowired
	Libraryrepository libRepository;
	public Library create (Library employee) {
		return libRepository.save(employee);
	}
	
	public Optional<Library> read(Long id){
		return libRepository.findById(id);
	}

	public Library update(Library employee) {
		
		return libRepository.save(employee);
	}

	public String delete(Long id) {
		
		libRepository.deleteById(id);
		return "data deleted";
	}
	public Iterable<Library> sorting(String field){
		return libRepository.findAll(Sort.by(field));
	}
	public Page <Library> spaging(int page, int size) {
		Pageable paging=PageRequest.of(page, size);
		return libRepository.findAll(paging);
	}
	public Page <Library> sortingandpaging(String field,int page, int size) {
		Pageable paging=PageRequest.of(page, size).withSort(Sort.by(field));
		return libRepository.findAll(paging);
	}
	public Iterable<Library> query()
	{
		return libRepository.findAll();
	}
}