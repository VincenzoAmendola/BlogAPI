package it.rdev.blog.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.rdev.blog.api.controller.dto.ArticleDTO;
import it.rdev.blog.api.customexceptions.IdNotFoundException;
import it.rdev.blog.api.customexceptions.UserNotProprietaryException;
import it.rdev.blog.api.dao.ArticleDao;
import it.rdev.blog.api.dao.CategoryDao;
import it.rdev.blog.api.dao.TagDao;
import it.rdev.blog.api.dao.UserDao;
import it.rdev.blog.api.dao.entity.Article;
import it.rdev.blog.api.dao.entity.Category;
import it.rdev.blog.api.dao.entity.Tag;
import it.rdev.blog.api.dao.entity.User;
// Every Method in this class takes in input the ID of the user logged in, so the service can choose which articles can be taken by the controller.
@Service
public class BlogArticleService {

	@Autowired
	private ArticleDao articleDao;
	@Autowired 
	private UserDao userDao;
	@Autowired
	private CategoryDao catDao;
	@Autowired
	private TagDao tagDao;
	
	
	// Get services 
	public Set<ArticleDTO> findAllPublished(Long idUserLogin){
		Set<Article> articles = articleDao.findAll();
		Set<ArticleDTO> articlesDTO = ConverterArticleEntitytoDTO(articles, idUserLogin);
		return articlesDTO;
	}
	
//	public Page<ArticleDTO> findAll(Long idUserLogin, Pageable page){
//		Page<Article> pagArticles = articleDao.findAuthorized(idUserLogin, page);
//		//articleDTO.map(articlesList)
//		
//	}
	
	
	public Set<ArticleDTO> findByAutore(String s, Long idUserLogin){
		Set<Article> articles = articleDao.findByAutore(s);
		Set<ArticleDTO> articlesDTO = ConverterArticleEntitytoDTO(articles, idUserLogin);
		return articlesDTO;
	}
	
	public Set<ArticleDTO> findByTags(String s, Long idUserLogin){
		Set<Article> articles = articleDao.findByTags(s);
		Set<ArticleDTO> articlesDTO = ConverterArticleEntitytoDTO(articles, idUserLogin);
		return articlesDTO;
	}
	
	public Set<ArticleDTO> findByKeywords(String s, Long idUserLogin){
		Set<Article> articles = articleDao.findByKeywords(s);
		Set<ArticleDTO> articlesDTO = ConverterArticleEntitytoDTO(articles, idUserLogin);
		return articlesDTO;
	}
	
	public Set<ArticleDTO> findByStato(String s, Long idUserLogin){
		boolean b=true;
		if(s.equalsIgnoreCase("Bozza")) {
		b = false;
		}
		Set<Article> articles = articleDao.findByStato(b);
		Set<ArticleDTO> articlesDTO = new HashSet<>();
		for(Article a : articles) {
			ArticleDTO aDTO = new ArticleDTO();
			if(a.isStato() || idUserLogin.equals(a.getAutore().getId()) ) {
			aDTO.setAutore(a.getAutore().getUsername());
			aDTO.setCategoria(a.getCategoria().getNomeCat());
			aDTO.setData_creazione(a.getData_creazione());
			aDTO.setData_pubblicazione(a.getData_pubblicazione());
			aDTO.setData_ultimamodifica(a.getData_ultimamodifica());
			aDTO.setStato(a.isStato());
			aDTO.setSottotitolo(a.getSottotitolo());
			aDTO.setTesto(a.getTesto());
			aDTO.setTitolo(a.getTitolo());
			articlesDTO.add(aDTO);
			}
		}
		return articlesDTO;
	}
	
	public ArticleDTO findByID(Long id, Long idUserLogin) {
		Optional<Article> a2 = articleDao.findById(id);
		Article a = new Article();
		ArticleDTO aDTO = new ArticleDTO();
		if(!a2.isEmpty()) {
			a= a2.get();
		}
		if(a.isStato() || idUserLogin.equals(a.getAutore().getId())) {
		aDTO.setAutore(a.getAutore().getUsername());
		aDTO.setCategoria(a.getCategoria().getNomeCat());
		aDTO.setData_creazione(a.getData_creazione());
		aDTO.setData_pubblicazione(a.getData_pubblicazione());
		aDTO.setData_ultimamodifica(a.getData_ultimamodifica());
		aDTO.setStato(a.isStato());
		aDTO.setSottotitolo(a.getSottotitolo());
		aDTO.setTesto(a.getTesto());
		aDTO.setTitolo(a.getTitolo());
		}
		return aDTO;
	}
	
	// Method which takes a Set<Article> in input and transforms it into a Set<ArticleDTO>, it works with all published articles.
	public Set<ArticleDTO> ConverterArticleEntitytoDTO(Set<Article> articles, Long id){
		Set<ArticleDTO> articlesDTO = new HashSet<>();
		for(Article a : articles) {
			ArticleDTO aDTO = new ArticleDTO();
			if(a.isStato() || id.equals(a.getAutore().getId()) ) {
			aDTO.setAutore(a.getAutore().getUsername());
			aDTO.setCategoria(a.getCategoria().getNomeCat());
			aDTO.setData_creazione(a.getData_creazione());
			aDTO.setData_pubblicazione(a.getData_pubblicazione());
			aDTO.setData_ultimamodifica(a.getData_ultimamodifica());
			aDTO.setStato(a.isStato());
			aDTO.setSottotitolo(a.getSottotitolo());
			aDTO.setTesto(a.getTesto());
			aDTO.setTitolo(a.getTitolo());
			articlesDTO.add(aDTO);
			}
		}
		return articlesDTO;
	}
	
	// Delete Services, it returns -1 if the article is not found, -2 if an article is found but the user that tries to delete it is not the author,
	// and it return 0 if the delete is completed.
	public int deleteById(Long id, Long idUserLogin) {
		Optional<Article> a2 = articleDao.findById(id);
		
		if(a2.isEmpty()) {
			return -1;
		}else {
			Article a = a2.get();
			if(idUserLogin.equals(a.getAutore().getId())){
				articleDao.deleteById(id);
			}
			else {
				return -2;
			}
		}
		return 0;
	}
	
	// Post service, it controls if the author in the post call is the same one as the user logged in, then controls the other parameters and 
	// if everything is successful it saves the new Article, otherwise it launches an exception which will be caught by the controller;
	public void saveArticle(ArticleDTO article, Long idUserLogin) throws Exception {
		Article newArt = new Article();
		Optional<User> oUser = userDao.findById(idUserLogin);
		User user = new User();
		if(oUser.isPresent()) {
			user=oUser.get();
		}
		User userNominatedInNewArticle = userDao.findByUsername(article.getAutore());
		if(!user.equals(userNominatedInNewArticle)) {
			throw new UserNotProprietaryException();
		}
		Category category = catDao.findByNomecat(article.getCategoria());
		if(category==null) {
			throw new Exception();
		}
		Set<Tag> tags = new HashSet<>();
		if(article.getTags()!=null) {
		if(!article.getTags().isEmpty()) {
		for(String s : article.getTags()) {
			Tag tag = tagDao.findByNometag(s);
			if(tag!=null) {
				tags.add(tag);
			}
		}
		}
		}
		newArt.setTitolo(article.getTitolo());
		newArt.setSottotitolo(article.getSottotitolo());
		newArt.setAutore(user);
		newArt.setCategoria(category);
		newArt.setTags(tags);
		newArt.setStato(false);
		newArt.setData_creazione(LocalDateTime.now());
		newArt.setData_ultimamodifica(LocalDateTime.now());
		newArt.setTesto(article.getTesto());
		articleDao.save(newArt);
	}
	
	// Update service, it takes the article by the given id, can throw the custom exception "IdNotFoundException", 
	// checks if the user logged in is the same as the author of the article,
	// if not it throws a custom exception called "UserNotProprietaryException",
	// otherwise it proceeds to update the article with the new informations taken in input by the controller.
	// if something goes wrong it throws an Exception.
	// If everything is ok it updates the article with the given id
	public void updateById(ArticleDTO article, Long idUserLogin, Long id) throws Exception {
		Optional<Article> artToUpdate = articleDao.findById(id);
		Optional<User> oUser = userDao.findById(idUserLogin);
		User user = new User();
		Article newArt = new Article();
		if(oUser.isPresent()) {
			user=oUser.get();
		}
		if(artToUpdate.isEmpty()) {
			throw new IdNotFoundException();
		}else {
			newArt = artToUpdate.get();
		}
		User userNominatedInArticle = userDao.findByUsername(article.getAutore());
		if(!user.equals(userNominatedInArticle)) {
			throw new UserNotProprietaryException();
		}
		Category category = catDao.findByNomecat(article.getCategoria());
		if(category==null) {
			throw new Exception();
		}
		Set<Tag> tags = new HashSet<>();
		if(article.getTags()!=null) {
			if(!article.getTags().isEmpty()) {
		for(String s : article.getTags()) {
			Tag tag = tagDao.findByNometag(s);
			if(tag!=null) {
				tags.add(tag);
			}
		}
			}
		}
		// i save the old state, to control if the update is gonna publish, or if the update is on an already published article
		boolean oldState = newArt.isStato();
		newArt.setTitolo(article.getTitolo());
		newArt.setSottotitolo(article.getSottotitolo());
		newArt.setAutore(user);
		newArt.setCategoria(category);
		newArt.setTags(tags);
		newArt.setStato(article.isStato());
		if(newArt.isStato() && !oldState) {
			newArt.setData_pubblicazione(LocalDateTime.now());
		}
		newArt.setData_ultimamodifica(LocalDateTime.now());
		newArt.setId(id);
		newArt.setTesto(article.getTesto());
		articleDao.save(newArt);
	}
}