package com.test.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Movies")
public class Movie {

	
	@Id
    @GeneratedValue
    private long id;
    private String title;
    private String category;
    private String star;
    
    public Movie(){
        super();
    }
    public Movie(Long id, String title, String category, String star) {
        super();
        this.id = id;
        this.title = title;
        this.category = category;
        this.star=star;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}
    
}
