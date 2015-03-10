package com.springmvc.relationdrug.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.springmvc.base.util.JsonValue;

@Entity
@Table(name = "b_knowledge")
public class Knowledge {

	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long knowledgeId;
	@Column(name = "title_")
	@JsonValue
	private String title;
	@Column(name = "description_")
	@JsonValue
	private String description;
	@Column(name = "type_")
	@JsonValue
	private String type;
	@Column(name = "img_")
	@JsonValue
	private String img;
	@Column(name = "createtime_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	public Long getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(Long knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Knowledge(Long knowledgeId, String title, String description, String type,
			String img, Date createtime) {
		super();
		this.knowledgeId = knowledgeId;
		this.title = title;
		this.description = description;
		this.type = type;
		this.img = img;
		this.createtime = createtime;
	}
	public Knowledge() {
		super();
	}
	public Knowledge(long knowledgeId) {
		this.knowledgeId=knowledgeId;
	}

}