package it.rdev.blog.api.controller.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ArticleDTO {
	private String titolo;
	private String sottotitolo;
	private String autore;
	private String categoria;
	private LocalDateTime Data_pubblicazione;
	private LocalDateTime Data_ultimamodifica;
	private LocalDateTime Data_creazione;
	private String testo;
	private Set<String> tags;
	@JsonIgnore
	private boolean stato;
	
	// Getters and Setters for every attribute
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
	public String getSottotitolo() {
		return sottotitolo;
	}
	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public LocalDateTime getData_pubblicazione() {
		return Data_pubblicazione;
	}
	public void setData_pubblicazione(LocalDateTime data_pubblicazione) {
		Data_pubblicazione = data_pubblicazione;
	}
	public LocalDateTime getData_ultimamodifica() {
		return Data_ultimamodifica;
	}
	public void setData_ultimamodifica(LocalDateTime data_ultimamodifica) {
		Data_ultimamodifica = data_ultimamodifica;
	}
	public LocalDateTime getData_creazione() {
		return Data_creazione;
	}
	public void setData_creazione(LocalDateTime data_creazione) {
		Data_creazione = data_creazione;
	}
	public boolean isStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	
}
