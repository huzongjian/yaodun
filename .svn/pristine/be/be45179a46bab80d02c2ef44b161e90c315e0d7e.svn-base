package com.springmvc.relationdrug.rules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.enums.DrugTypeEnum;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.rules.domain.DayNumReportDetail;
import com.springmvc.relationdrug.service.impl.RuleChecker;

/**
 * 
 * Summary : 用药天数检测器
 */
@Component("dayNum4UseChecker")
@Transactional
@Service
public class DayNum4UseChecker implements RuleChecker {

	/**
	 * 急诊用药天数指标
	 */
	private static final Integer USE_DRUG_DAYS_FOR_EMERGENCY = 3;
	/**
	 * 门诊用药天数指标
	 */
	private static final Integer USE_DRUG_DAYS_FOR_SECTION = 7;

	private boolean able = true;

	public void prevLoading(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) {

	}

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {

		List<ReportDetail> detail_list = new ArrayList<ReportDetail>();

		for (PrescribeCell prescribeCell : cellList) {
			ReportDetail reportDetail = checkDayNum(prescribeCell);
			if (reportDetail != null)
				detail_list.add(reportDetail);
		}
		if (detail_list.isEmpty()) {
			return null;
		} else {
			return detail_list;
		}

	}

	/**
	 * 药品有作用类型则检测 是否大于7天/大于3天 否则检测是否大于7天
	 * 
	 */
	private ReportDetail checkDayNum(PrescribeCell prescribeCell) {
		BasicDrugCheck basicDrugCheck = prescribeCell.getBasicDrugCheck();
		DayNumReportDetail reportDetail = new DayNumReportDetail(
				RuleTypeEnum.dayNum4UseChecker);
		if (basicDrugCheck == null) {
			return null;
		}

		int standardDayNum = 0;
		if (prescribeCell.getDaynum() != null) {
			if (basicDrugCheck.getDrugType() != null) {
				if (prescribeCell.getDaynum() > USE_DRUG_DAYS_FOR_SECTION) {
					standardDayNum = USE_DRUG_DAYS_FOR_SECTION;

				} else if (prescribeCell.getDaynum() > USE_DRUG_DAYS_FOR_EMERGENCY) {
					standardDayNum = USE_DRUG_DAYS_FOR_EMERGENCY;
				}
			} else if (prescribeCell.getDaynum() > USE_DRUG_DAYS_FOR_SECTION) {
				standardDayNum = USE_DRUG_DAYS_FOR_SECTION;
			}
		}
		if (standardDayNum > 0) {
			reportDetail.setGrade(GradeEnum.WARN);
			reportDetail.setRemark(
					basicDrugCheck.getDrugType() == null ? null : DrugTypeEnum
							.values()[Integer.parseInt(basicDrugCheck
							.getDrugType()) + 1].getText(), basicDrugCheck
							.getDrugName(), prescribeCell.getDaynum(),
					standardDayNum);
		} else {
			return null;
		}
		return reportDetail;
	}

	public boolean isAble() {
		return this.able;
	}

	public void enable(boolean able) {
		this.able = able;
	}

}
