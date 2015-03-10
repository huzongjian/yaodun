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
import com.springmvc.relationdrug.rules.service.DrugForbidDiseaseService;
import com.springmvc.relationdrug.service.impl.RuleChecker;

/**
 * 
 * Summary : 疾病禁用药品检测器
 */
@Component("drugForbidDiseaseChecker")
@Transactional
@Service
public class DrugForbidDiseaseChecker implements RuleChecker {
	@Resource
	private DrugForbidDiseaseService forbidDiseaseService;

	private boolean able = true;

	public void prevLoading(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) {
	}

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {
		// return forbidDiseaseService.check(patientInfo, pBasicInfo, cellList);
		return null;
	}

	public boolean isAble() {
		return able;
	}

	public void enable(boolean able) {
		this.able = able;
	}

}
