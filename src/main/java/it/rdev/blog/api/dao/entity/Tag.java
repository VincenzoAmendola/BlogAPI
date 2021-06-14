package it.rdev.blog.api.dao.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column (nullable=false, length = 30)
	private String nometag;
	@ManyToMany(targetEntity=Article.class, mappedBy="tags")
	private Set<Article> articles;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNometag() {
		return nometag;
	}
	public void setNometag(String nometag) {
		this.nometag = nometag;
	}
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	
}
