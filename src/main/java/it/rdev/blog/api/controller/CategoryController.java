package it.rdev.blog.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.controller.dto.CategoryDTO;
import it.rdev.blog.api.service.BlogCategoriesService;

@RestController
public class CategoryController {
	
	@Autowired
	private BlogCategoriesService bcs;
	
	@GetMapping(value = "/api/categoria")
	public ResponseEntity<?> getCategoria(){
		Set<CategoryDTO> categories = bcs.getAll();
		if(categories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<CategoryDTO>>(categories, HttpStatus.OK);
	}
}
