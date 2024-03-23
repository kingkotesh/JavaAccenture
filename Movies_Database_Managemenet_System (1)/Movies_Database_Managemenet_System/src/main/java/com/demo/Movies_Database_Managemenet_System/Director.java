package com.demo.Movies_Database_Managemenet_System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Director {
	@Id
    @Column(name = "DirectorId ", length = 10)
	private int directorid;
	@Column(name = "DirectorName ", length = 50)
	private String directorname ;
	@Column(name = "HitCount ", length = 10)
	private int hitcount ;
	@Column(name = "FlopCount ", length = 10)
	private int flopcount ;
	@Column(name = "AverageCount ", length = 10)
	private int averagecount ;
	public Director() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Director(int directorid, String directorname, int hitcount, int flopcount, int averagecount) {
		super();
		this.directorid = directorid;
		this.directorname = directorname;
		this.hitcount = hitcount;
		this.flopcount = flopcount;
		this.averagecount = averagecount;
	}
	public int getDirectorid() {
		return directorid;
	}
	public void setDirectorid(int directorid) {
		this.directorid = directorid;
	}
	public String getDirectorname() {
		return directorname;
	}
	public void setDirectorname(String directorname) {
		this.directorname = directorname;
	}
	public int getHitcount() {
		return hitcount;
	}
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	public int getFlopcount() {
		return flopcount;
	}
	public void setFlopcount(int flopcount) {
		this.flopcount = flopcount;
	}
	public int getAveragecount() {
		return averagecount;
	}
	public void setAveragecount(int averagecount) {
		this.averagecount = averagecount;
	}
	@Override
	public String toString() {
	    return "Director [directorid=" + directorid + ", directorname=" + directorname + ", hitcount=" + hitcount + ", flopcount=" + flopcount + ", averagecount=" + averagecount + "]";
	}

	

}
