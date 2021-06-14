package it.rdev.blog.api.dao.entity;

import java.time.LocalDateTime;
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
	@Column(unique = true, length = 30, nullable=false)
	private String titolo;
	@Column (nullable=false)
	private String testo;
	@Column (length = 30)
	private String sottotitolo;
	@Column
	private LocalDateTime data_pubblicazione;
	@Column
	private LocalDateTime data_ultimamodifica;
	@Column (nullable=false)
	private LocalDateTime data_creazione;
	@Column (nullable=false) // Lo stato è TRUE se l'articolo è stato pubblicato mentre è in FALSE se è in bozza
	private boolean stato;
	@ManyToOne()
	@JoinColumn(name = "categoria", referencedColumnName="id")
	private Category categoria;
	@ManyToMany(targetEntity= Tag.class, cascade=CascadeType.ALL)
	@JoinTable(name="TagxArticle", joinColumns = @JoinColumn(name = "idArticle"), inverseJoinColumns = @JoinColumn(name = "idtag"))
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSottotitolo() {
		return sottotitolo;
	}

	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}

	public LocalDateTime getData_pubblicazione() {
		return data_pubblicazione;
	}

	public void setData_pubblicazione(LocalDateTime data_pubblicazione) {
		this.data_pubblicazione = data_pubblicazione;
	}

	public LocalDateTime getData_ultimamodifica() {
		return data_ultimamodifica;
	}

	public void setData_ultimamodifica(LocalDateTime data_ultimamodifica) {
		this.data_ultimamodifica = data_ultimamodifica;
	}

	public LocalDateTime getData_creazione() {
		return data_creazione;
	}

	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public Category getCategoria() {
		return categoria;
	}

	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public User getAutore() {
		return autore;
	}

	public void setAutore(User autore) {
		this.autore = autore;
	}
	
	
}
