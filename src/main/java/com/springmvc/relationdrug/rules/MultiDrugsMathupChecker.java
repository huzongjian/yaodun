package com.springmvc.relationdrug.rules;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.rules.service.MultiDrugsMathupService;
import com.springmvc.relationdrug.service.impl.BasicDrugCheckService;
import com.springmvc.relationdrug.service.impl.RuleChecker;

/**
 * 配伍禁忌检测
 */
@Component("multiDrugsMathupChecker")
@Transactional
@Service
public class MultiDrugsMathupChecker implements RuleChecker {

	private boolean able = true;

	@Resource
	@Autowired
	MultiDrugsMathupService multiDrugsMathupService;
	@Resource
	@Autowired
	BasicDrugCheckService basicDrugCheckService;

	public void prevLoading(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) {
		/*
		 * for(int i=0;i<cellList.size();i++){ //加载药品基础信息 try { BasicDrugCheck
		 * basicDrugCheck =
		 * this.basicDrugCheckService.loadDeepBasicDrugCheck(cellList
		 * .get(i).getBasicDrugCheck().getId());
		 * basicDrugCheck.setDrugName(Coding
		 * .hex2bin(basicDrugCheck.getDrugName())); //解密
		 * cellList.get(i).setBasicDrugCheck(basicDrugCheck); } catch
		 * (ServiceException e) { e.printStackTrace(); } }
		 */
	}

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {
		return multiDrugsMathupService.check(cellList);
	}

	public boolean isAble() {
		return able;
	}

	public void enable(boolean able) {
		this.able = able;
	}
}
