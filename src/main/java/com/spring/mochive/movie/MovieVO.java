package com.spring.mochive.movie;

import lombok.Data;

@Data
public class MovieVO {
	
	private long id;
	private String title;
	private String overview;
	private String backdropPath;
	private String posterPath;

}
