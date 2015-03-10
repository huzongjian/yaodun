package com.springmvc.relationdrug.pojo;

/**
 * 
 * Summary : 实体标记
 * 
 * 
 */
public abstract class SignDomain {

	private boolean isLoaded;

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public SignDomain() {
		super();
	}

	public SignDomain(Long id) {
		super();
	}

}
