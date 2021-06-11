package it.rdev.blog.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rdev.blog.api.controller.dto.ArticleDTO;
import it.rdev.blog.api.dao.ArticleDao;
import it.rdev.blog.api.dao.entity.Article;
// Every Method in this class takes in input the ID of the user logged in, so the service can choose which articles can be taken by the controller.
@Service
public class BlogArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	public Set<ArticleDTO> findAllPublished(Long idUserLogin){
		Set<Article> articles = articleDao.findAll();
		Set<ArticleDTO> articlesDTO = ConverterArticleEntitytoDTO(articles, idUserLogin);
		return articlesDTO;
	}
	
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
		Article a = articleDao.findById(id);
		ArticleDTO aDTO = new ArticleDTO();
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
}
