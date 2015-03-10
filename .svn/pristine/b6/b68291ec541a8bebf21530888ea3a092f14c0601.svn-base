package com.springmvc.relationdrug.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;



/**
 * 
 * Summary : 处方校验器 
 *
 *
 */
@Transactional
public interface RuleChecker{

	/**
	 * 预加载
	 * @param patientInfo
	 * @param pBasicInfo
	 * @param cellList
	 */
	public void prevLoading(PatientInfo patientInfo,PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList);
	
		/**
		 * 规则检测主动作
		 *
		 *
		 * @param patientInfo
		 * @param pBasicInfo
		 * @param cellList
		 * @return
		 * @throws CheckException
		 */
	 public List<ReportDetail> check(PatientInfo patientInfo,PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) throws CheckException;

	 /**
	  * 该规则是否可用
	  * @return
	  */
	 public boolean  isAble();
	 
	 /**
	  * 规则有效性开关
	  * @param able
	  */
	 public void enable(boolean able);
	 
}
