package com.demo.Movies_Database_Managemenet_System;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Actor {
    public Actor(ActorId id, String actorName, String role, Movie movie) {
		super();
		this.id = id;
		this.actorName = actorName;
		this.role = role;
		this.movie = movie;
	}

	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActorId getId() {
		return id;
	}

	public void setId(ActorId id) {
		this.id = id;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", actorName=" + actorName + ", role=" + role + ", movie=" + movie + "]";
	}

	@EmbeddedId
    private ActorId id;

    @Column(name = "ActorName", length = 20, nullable = false)
    private String actorName;

    @Column(name = "Role", length = 20, nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "MovieId", referencedColumnName = "MovieId", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "FK_Movie_Actor"))
    private Movie movie;

    // Constructors, Getters, and Setters
}
