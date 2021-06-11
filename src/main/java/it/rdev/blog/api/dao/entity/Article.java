package it.rdev.blog.api.dao.entity;

import java.time.LocalDate;
import java.util.Set;
import it.rdev.blog.api.dao.entity.Category;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "articles")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable=false)
	private String titolo;
	@Column (nullable=false)
	private String testo;
	@Column 
	private String sottotitolo;
	@Column
	private LocalDate data_pubblicazione;
	@Column
	private LocalDate data_ultimamodifica;
	@Column (nullable=false)
	private LocalDate data_creazione;
	@Column (nullable=false) // Lo stato è TRUE se l'articolo è stato pubblicato mentre è in FALSE se è in bozza
	private boolean stato;
	@ManyToOne()
	@JoinColumn(name = "categoria", referencedColumnName="id")
	private Category categoria;
	@ManyToMany(targetEntity= Tag.class, cascade=CascadeType.ALL)
	@JoinTable(name="TagsxArticle", joinColumns = @JoinColumn(name = "idArticle"), inverseJoinColumns = @JoinColumn(name = "idtag"))
	private Set<Tag> tags;
	@ManyToOne()
	@JoinColumn(name = "autore", referencedColumnName="id")
	private User autore;

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
}
