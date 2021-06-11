package it.rdev.blog.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.config.JwtTokenUtil;
import it.rdev.blog.api.controller.dto.ArticleDTO;
import it.rdev.blog.api.service.BlogArticleService;

@RestController
@RequestMapping(value = "/api/articolo")
public class ArticleController {
	@Autowired
	private JwtTokenUtil jwtUtil;
	@Autowired
	private BlogArticleService bas;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestHeader(name = "Authorization") String token){
		Long idLogin = getIDLogin(token);
		Set<ArticleDTO> articles = bas.findAllPublished(idLogin);
		if(articles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<ArticleDTO>>(articles, HttpStatus.OK);
	}
	// The idea is to take the login token, see if he is authorized, take his id thanks to the token and use it in the Articles Services
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable final Long id, @RequestHeader(name = "Authorization") String token){
		Long idLogin = getIDLogin(token);
		ArticleDTO articleDTO = bas.findByID(id, idLogin);
		if(articleDTO!=null) {
			return new ResponseEntity<ArticleDTO>(articleDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public Long getIDLogin(String token) {
		Long idLogin = null;
		if(token != null && token.startsWith("Bearer")) {
			token = token.replaceAll("Bearer ", "");
			idLogin = jwtUtil.getUserIdFromToken(token);
		}
		return idLogin;
	}
}
