package db.bean;

import java.io.Serializable;

public class GenreBean implements Serializable {
	private int genre_no;
	private String genre_title;
	
	public GenreBean() {}
	
	public GenreBean(int genre_no, String genre_title) {
		this.setGenre_no(genre_no);
		this.setGenre_title(genre_title);
	}

	public int getGenre_no() {
		return genre_no;
	}

	public void setGenre_no(int genre_no) {
		this.genre_no = genre_no;
	}

	public String getGenre_title() {
		return genre_title;
	}

	public void setGenre_title(String genre_title) {
		this.genre_title = genre_title;
	}
}
