package com.demo.Movies_Database_Managemenet_System;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActorId implements Serializable {
    @Column(name = "ActorId")
    private int actorId;

    @Column(name = "MovieId")
    private int movieId;

	public ActorId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActorId(int actorId, int movieId) {
		super();
		this.actorId = actorId;
		this.movieId = movieId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

    // Constructors, Getters, Setters, equals(), and hashCode() methods
}
