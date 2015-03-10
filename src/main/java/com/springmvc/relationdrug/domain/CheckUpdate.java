package com.springmvc.relationdrug.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.springmvc.base.util.JsonValue;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "G_RESIDENT_SEQ")
@Table(name="b_checkupdate")
public class CheckUpdate {
	@Id
	@Column(name = "Id_")
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@GeneratedValue(generator = "UserGenerator")
	@JsonValue
	private Long checkUpdateId;
	
	@Column(name = "appName_")
	@JsonValue
	private String appName;//app的名称
	@Column(name = "appVersionNo_")
	@JsonValue
	private String appVersionNo;//app的版本号
	@Column(name = "appSize_")
	@JsonValue
	private String appSize;//app的大小K为单位
	@Column(name = "appPath_")
	@JsonValue
	private String appPath;//app的下载地址
	@Column(name = "appLogs_")
	@JsonValue
	private String appLogs;//app更新日志
	@Column(name = "isForce_")
	@JsonValue
	private String isForce;//是否强制更新 1:表示强制更新,0:表示不强制更新(一般默认0 遇到重大bug时可能需要强制更新)
	@Column(name = "hasNew_")
	@JsonValue
	private String hasNew;//:1:表示有新版本,0:表示没有新版本
	
	@Column(name = "appType_")
	@JsonValue
	private String appType;//:1:表示有新版本,0:表示没有新版本

	@Column(name="createtime_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public CheckUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}



	public CheckUpdate(Long checkUpdateId, String appName, String appVersionNo,
			String appSize, String appPath, String appLogs, String isForce,
			String hasNew, String appType, Date createTime) {
		super();
		this.checkUpdateId = checkUpdateId;
		this.appName = appName;
		this.appVersionNo = appVersionNo;
		this.appSize = appSize;
		this.appPath = appPath;
		this.appLogs = appLogs;
		this.isForce = isForce;
		this.hasNew = hasNew;
		this.appType = appType;
		this.createTime = createTime;
	}



	public Long getCheckUpdateId() {
		return checkUpdateId;
	}

	public void setCheckUpdateId(Long checkUpdateId) {
		this.checkUpdateId = checkUpdateId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersionNo() {
		return appVersionNo;
	}

	public void setAppVersionNo(String appVersionNo) {
		this.appVersionNo = appVersionNo;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public String getAppLogs() {
		return appLogs;
	}

	public void setAppLogs(String appLogs) {
		this.appLogs = appLogs;
	}

	public String getIsForce() {
		return isForce;
	}

	public void setIsForce(String isForce) {
		this.isForce = isForce;
	}

	public String getHasNew() {
		return hasNew;
	}

	public void setHasNew(String hasNew) {
		this.hasNew = hasNew;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
