package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Library;

import com.example.demo.service.Libraryservice;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/library")
public class Librarycontroller {

	@Autowired
	Libraryservice libService;
	
	@Operation(summary = "Creates a new bookrecord")
	@ApiResponses(value = {@ApiResponse(responseCode = "201" , description = "Bookrecord created successfully"),
		                   @ApiResponse(responseCode = "400", description = "Book is invalid")})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Library> create(final @RequestBody Library employee)
	{
		Library createEmployee = libService.create(employee);
		return ResponseEntity.ok(createEmployee);
	}
	
	@Operation(summary = "Get an Book with given id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "getting Book successfully"),
			               @ApiResponse(responseCode = "401", description = "invalid credentials"),
			               @ApiResponse(responseCode = "404", description = "Book not found")})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Optional<Library>> read(final @PathVariable Long id){
		Optional<Library> createdEmployee = libService.read(id);
		return ResponseEntity.ok(createdEmployee);
	}
	
	@Operation(summary = "Update the Book by given id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book updated successfully"),
			               @ApiResponse(responseCode = "400", description = "Book is invalid"),
			               @ApiResponse(responseCode = "404", description = "Book not found")})
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Library> update(final @RequestBody Library employee)
	throws JsonProcessingException
	{
		final Library updatedEmployee = libService.update(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@Operation(summary = "Delete the Book by given id")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book deleted successfully"),
			               @ApiResponse(responseCode = "401", description = "invalid credentials"),
			               @ApiResponse(responseCode = "404", description = "Book not found")})
	@DeleteMapping(value = "/{id}")
	public void delete(final @PathVariable Long id) {
		libService.delete(id);
	}
	
	@GetMapping("/sort/{field}")
	public Iterable<Library> sorting(@PathVariable String field){
		return libService.sorting(field);
	}
	@GetMapping("paging/{page}/{size}")
	public Page<Library> paging(@PathVariable int page,@PathVariable int size){
		return libService.spaging(page,size);
	}
	@GetMapping("sortwithpage/{field}/{page}/{size}")
	public Page<Library> sortpaging(@PathVariable String field,@PathVariable int page,@PathVariable int size){
		return libService.sortingandpaging(field,page,size);
}
	
}