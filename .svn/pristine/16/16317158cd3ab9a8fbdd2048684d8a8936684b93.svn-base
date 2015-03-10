package com.springmvc.relationdrug.rules;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.domain.Frequency;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.rules.service.FrequencyCheckService;
import com.springmvc.relationdrug.service.impl.FrequencyService;
import com.springmvc.relationdrug.service.impl.RuleChecker;

/**
 * 频次检测器
 */

@Component("frequencyChecker")
@Transactional
@Service
public class FrequencyChecker implements RuleChecker {
	@Resource
	@Autowired
	private FrequencyCheckService frequencyCheckService;
	@Resource
	@Autowired
	private FrequencyService frequencyService;

	private boolean able = true;

	public void prevLoading(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) {
		for (int i = 0; i < cellList.size(); i++) {
			if (cellList.get(i).getFrequency() != null) {
				Frequency frequency = this.frequencyService.get(cellList.get(i)
						.getFrequency().getFrequencyId());
				cellList.get(i).setFrequency(frequency);
			}
		}
	}

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {
		return frequencyCheckService.check(cellList);
	}

	public boolean isAble() {
		return this.able;
	}

	public void enable(boolean able) {
		this.able = able;
	}

}
