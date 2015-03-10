package com.springmvc.relationdrug.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.springmvc.base.domain.BaseDomain;
import com.springmvc.base.util.JsonValue;
@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name="basic_otherinfo")
public class Otherinfo extends BaseDomain{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 7778580245662724252L;
	@Id  
	@Column(name ="id_") 
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long otherinfoid;
     @Column(name ="title_")
     @JsonValue
     private String title;
     @Column(name ="data_")
     @JsonValue
     private String data;
     @Column(name ="type_")
     @JsonValue
     private Integer type;
	public Long getOtherinfoid() {
		return otherinfoid;
	}
	public void setOtherinfoid(Long otherinfoid) {
		this.otherinfoid = otherinfoid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Otherinfo(Long otherinfoid, String title, String data, Integer type) {
		super();
		this.otherinfoid = otherinfoid;
		this.title = title;
		this.data = data;
		this.type = type;
	}
	public Otherinfo() {
		super();
	}
     
     
}
