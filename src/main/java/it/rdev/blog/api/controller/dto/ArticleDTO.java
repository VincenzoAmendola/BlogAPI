package it.rdev.blog.api.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ArticleDTO {
	private String titolo;
	private String sottotitolo;
	private String autore;
	private String categoria;
	private LocalDate Data_pubblicazione;
	private LocalDate Data_ultimamodifica;
	private LocalDate Data_creazione;
	private String testo;
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
	public LocalDate getData_pubblicazione() {
		return Data_pubblicazione;
	}
	public void setData_pubblicazione(LocalDate data_pubblicazione) {
		Data_pubblicazione = data_pubblicazione;
	}
	public LocalDate getData_ultimamodifica() {
		return Data_ultimamodifica;
	}
	public void setData_ultimamodifica(LocalDate data_ultimamodifica) {
		Data_ultimamodifica = data_ultimamodifica;
	}
	public LocalDate getData_creazione() {
		return Data_creazione;
	}
	public void setData_creazione(LocalDate data_creazione) {
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
	
	
}
