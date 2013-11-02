package com.example.birdnote.model;

public class Image {

	private long id;
	private String thumb;
	private String main_pic;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	public String getMainPic() {
		return main_pic;
	}

	public void setMainPic(String main_pic) {
		this.main_pic = main_pic;
	}
	
	@Override
	public String toString() {
		return thumb + "\n" + main_pic;
	}
}
