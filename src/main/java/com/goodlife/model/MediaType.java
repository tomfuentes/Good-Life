package com.goodlife.model;

public enum MediaType {
	IMAGE(1), TEXT(2), VIDEO(3), AUDIO(4), PDF(5);
	
	private final Integer mediaType;
	
	private MediaType(Integer mediaType) {
	    this.mediaType = mediaType;
	}
	
	public Integer getMediaType() {
	    return this.mediaType;
	}
}
