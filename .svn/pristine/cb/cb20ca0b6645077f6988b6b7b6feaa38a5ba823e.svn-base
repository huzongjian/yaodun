package com.springmvc.relationdrug.pojo;

import java.util.List;

import javax.persistence.Entity;

import com.springmvc.relationdrug.enums.GradeEnum;

/**
 * 
 * Summary : 处方校验报告单
 * 
 * 
 */
@Entity
public class ReportCell {

	/**
	 * 处方良度
	 */
	private GradeEnum grade;

	private List<ReportDetail> detail;

	/**
	 * 
	 * Summary : 处方良度
	 * 
	 * 
	 * @author Lineshow Email:lineshow7@gmail.com
	 * 
	 * @since 2013-7-25
	 */
	public enum Grade {
		BAN("禁用"), CAUTION("慎用"), WARN("提示"), NORMAL("正常");

		private String text;

		Grade(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	public GradeEnum getGrade() {
		return grade;
	}

	public void setGrade(GradeEnum grade) {
		this.grade = grade;
	}

	public List<ReportDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<ReportDetail> detail) {
		this.detail = detail;
	}

}
