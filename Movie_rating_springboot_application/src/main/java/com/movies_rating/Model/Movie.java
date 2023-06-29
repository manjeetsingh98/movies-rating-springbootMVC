package com.movies_rating.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
		

	@Id
	@Column(name="tconst")
	private String tconst;
	
	@Column(name="titleType")
	private String titleType;
	
	@Column(name="primaryTitle")
	private String primaryTitle;
	
	@Column(name="runtimeMinutes")
    private int runtimeMinutes;
	
	@Column(name="genres")
    private String genres;
	
	public String getTconst() {
		return tconst;
	}
	public void setTconst(String tconst) {
		this.tconst = tconst;
	}
	public String getTitleType() {
		return titleType;
	}
	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}
	public String getPrimaryTitle() {
		return primaryTitle;
	}
	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}
	public int getRuntimeMinutes() {
		return runtimeMinutes;
	}
	public void setRuntimeMinutes(int runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public Movie(String tconst, String titleType, String primaryTitle, int runtimeMinutes, String genres) {
		super();
		this.tconst = tconst;
		this.titleType = titleType;
		this.primaryTitle = primaryTitle;
		this.runtimeMinutes = runtimeMinutes;
		this.genres = genres;
	}
	public Movie() {
	}

    
}
