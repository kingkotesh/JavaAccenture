package com.demo.Movies_Database_Managemenet_System;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieId")
    private int movieId;

    @Column(name = "MovieName", length = 40, nullable = false)
    private String movieName;

    @Column(name = "Year_release", nullable = false)
    private int yearRelease;

    @Column(name = "Status", length = 4, nullable = false)
    private String status;

    @Column(name = "Trailer", length = 400, nullable = false)
    private String trailer;

    @Column(name = "Rating", precision = 3, scale = 1, nullable = false)
    private double rating;

    @ManyToOne
    @JoinColumn(name = "GenreId", referencedColumnName = "GenreId", nullable = false)
    private MovieType genre;

    public Movie(int movieId, String movieName, int yearRelease, String status, String trailer, double rating,
			MovieType genre, Director director) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.yearRelease = yearRelease;
		this.status = status;
		this.trailer = trailer;
		this.rating = rating;
		this.genre = genre;
		this.director = director;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(int yearRelease) {
		this.yearRelease = yearRelease;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public MovieType getGenre() {
		return genre;
	}

	public void setGenre(MovieType genre) {
		this.genre = genre;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", yearRelease=" + yearRelease + ", status="
				+ status + ", trailer=" + trailer + ", rating=" + rating + ", genre=" + genre + ", director=" + director
				+ "]";
	}

	@ManyToOne
    @JoinColumn(name = "DirectorId", referencedColumnName = "DirectorId", nullable = false)
    private Director director;

    // Constructors, Getters, and Setters
}
