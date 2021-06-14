package it.rdev.blog.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.config.JwtTokenUtil;
import it.rdev.blog.api.controller.dto.ArticleDTO;
import it.rdev.blog.api.customexceptions.IdNotFoundException;
import it.rdev.blog.api.customexceptions.UserNotProprietaryException;
import it.rdev.blog.api.service.BlogArticleService;

@RestController
@RequestMapping(value = "/api/articolo")
public class ArticleController {
	@Autowired
	private JwtTokenUtil jwtUtil;
	@Autowired
	private BlogArticleService bas;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestHeader(name = "Authorization", required=false) String token){
		Long idLogin = getIDLogin(token);
		if(idLogin==null) {
			idLogin = 0L;
		}
		Set<ArticleDTO> articles = bas.findAllPublished(idLogin);
		if(articles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<ArticleDTO>>(articles, HttpStatus.OK);
	}
	
	// The idea is to take the login token, see if he is authorized, take his id thanks to the token and use it in the Articles Services
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable final Long id, @RequestHeader(name = "Authorization", required=false) String token){
		Long idLogin = getIDLogin(token);
		if(idLogin==null) {
			idLogin = 0L;
		}
		ArticleDTO articleDTO = bas.findByID(id, idLogin);
		if(articleDTO!=null) {
			return new ResponseEntity<ArticleDTO>(articleDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Delete service, the method controls if the user is logged in with the token, if he isn't logged the controller launches an Unauthorized message
	// if he is, it uses the deleteById that first controls if the article with given id exists, then controls if the logged user is the author, 
	// and only then proceeds to delete the article
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable final Long id, @RequestHeader(name = "Authorization", required=false) String token){
		Long idLogin = getIDLogin(token);
		if(idLogin == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		int i = bas.deleteById(id, idLogin);
		if(i == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if(i == -1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(i== -2) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	// Article creation service, checks if the user is logged in thanks to the token, if not returns the status code 401, 
	// then it calls the saveArticle method and can catch the exception that can be thrown by it.
	// If there are errors in the input request body it returns the status code 400, else, if the save is successful, it launches the 204 status code
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postArticle(@RequestHeader(name = "Authorization", required=false) String token, @RequestBody ArticleDTO article){
		Long idLogin = getIDLogin(token);
		if(idLogin == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			bas.saveArticle(article, idLogin);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// Update service, mapped on PUT calls
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.PUT)
	public ResponseEntity<?> deleteById(@PathVariable final Long id, @RequestHeader(name = "Authorization", required=false) String token, @RequestBody ArticleDTO article){
		Long idLogin = getIDLogin(token);
		if(idLogin == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
		bas.updateById(article, idLogin, id);
		}catch(IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(UserNotProprietaryException e1) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}catch(Exception e2) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	// Simple method to get the id of the user from the token given as a parameter
	public Long getIDLogin(String token) {
		Long idLogin = null;
		if(token != null && token.startsWith("Bearer")) {
			token = token.replaceAll("Bearer ", "");
			idLogin = jwtUtil.getUserIdFromToken(token);
		}
		return idLogin;
	}
	
	
}
