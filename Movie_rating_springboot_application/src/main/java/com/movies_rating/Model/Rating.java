package com.movies_rating.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ratings")
public class Rating {

	@Id
	private String tconst;
    private double averageRating;
    private int numVotes;
    
	public String getTconst() {
		return tconst;
	}
	public void setTconst(String tconst) {
		this.tconst = tconst;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public int getNumVotes() {
		return numVotes;
	}
	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}
	public Rating(String tconst, double averageRating, int numVotes) {
		super();
		this.tconst = tconst;
		this.averageRating = averageRating;
		this.numVotes = numVotes;
	}
	public Rating() {
		
	}

}
