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
import com.springmvc.relationdrug.rules.service.SpecialCrowdCheckService;
import com.springmvc.relationdrug.service.impl.RuleChecker;

@Component("specialCrowdChecker")
@Transactional
@Service
public class SpecialCrowdChecker implements RuleChecker {

	@Resource
	@Autowired
	private SpecialCrowdCheckService specialCrowdCheckService;
	private boolean able = true;

	public void prevLoading(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) {
	}

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {
		System.err.println(patientInfo.getBirthday());
		if (patientInfo.getBirthday() == null) {
			return null;
		} else {
			return specialCrowdCheckService.check(patientInfo, cellList);
		}
	}

	public boolean isAble() {
		return able;
	}

	public void enable(boolean able) {
		this.able = able;
	}

}
