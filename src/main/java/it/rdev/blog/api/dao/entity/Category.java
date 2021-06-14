package it.rdev.blog.api.dao.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column (nullable=false, length = 30)
	private String nomecat;
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private Set<Article> articles;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeCat() {
		return nomecat;
	}
	public void setNomeCat(String nomeCat) {
		this.nomecat = nomeCat;
	}
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	
}
