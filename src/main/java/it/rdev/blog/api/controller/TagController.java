package it.rdev.blog.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.controller.dto.TagDTO;
import it.rdev.blog.api.service.BlogTagsService;

@RestController
public class TagController {
	
	@Autowired
	private BlogTagsService bts;
	
	@GetMapping(value = "/api/tag")
	public ResponseEntity<?> getCategoria(){
		Set<TagDTO> tags = bts.getAll();
		if(tags.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<TagDTO>>(tags, HttpStatus.OK);
	}
}