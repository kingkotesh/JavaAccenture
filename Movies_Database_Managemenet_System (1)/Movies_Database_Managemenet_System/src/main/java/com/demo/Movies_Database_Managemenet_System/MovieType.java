package com.demo.Movies_Database_Managemenet_System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieType {

	@Id
	@Column(name = "GenreId", length = 10)
	private int genreid ;
	@Column(name = "Genre", length = 50)
	private String genre ;
	public MovieType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieType(int genreid, String genre) {
		super();
		this.genreid = genreid;
		this.genre = genre;
	}
	public int getGenreid() {
		return genreid;
	}
	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "MovieType [genreid=" + genreid + ", genre=" + genre + "]";
	}
	
	
}
