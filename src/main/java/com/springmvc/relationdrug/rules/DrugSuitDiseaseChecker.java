package com.springmvc.relationdrug.rules;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.rules.service.DrugSuitDiseaseService;
import com.springmvc.relationdrug.service.impl.RuleChecker;

@Component("drugSuitDiseaseChecker")
@Transactional
@Service
public class DrugSuitDiseaseChecker implements RuleChecker {
	@Resource
	private DrugSuitDiseaseService drugNotBeForDiseaseService;
	private boolean able = true;

	public void prevLoading(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) {

	}

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {
		// return drugNotBeForDiseaseService.check(patientInfo, pBasicInfo,
		// cellList);
		return null;
	}

	public boolean isAble() {
		return able;
	}

	public void enable(boolean able) {
		this.able = able;
	}

}
