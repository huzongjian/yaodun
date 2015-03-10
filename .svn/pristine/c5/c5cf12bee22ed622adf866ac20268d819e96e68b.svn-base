package com.springmvc.relationdrug.rules;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.dao.impl.SkinTestDaoImpl;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.SkinTest;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.enums.STDegreeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.service.impl.RuleChecker;

/**
 * 
 * @ClassName: SkinTestOrNotChecker
 * @Description:
 * @date 2014-7-3 上午10:21:27
 * 
 */
@Transactional
@Service
@Component("skinTestOrNotChecker")
public class SkinTestOrNotChecker implements RuleChecker {

	@Resource
	private SkinTestDaoImpl skinTestDaoImpl;

	private boolean able = true;

	public void prevLoading(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList) {
	}

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {
		List<ReportDetail> detail_list = new ArrayList<ReportDetail>();
		for (PrescribeCell prescribeCell : cellList) {
			ReportDetail reportDetail = checkSTorNot(prescribeCell);
			if (reportDetail != null)
				detail_list.add(reportDetail);
		}
		return detail_list;
	}

	/**
	
	 */
	private ReportDetail checkSTorNot(PrescribeCell prescribeCell) {
		BasicDrugCheck basicDrugCheck = prescribeCell.getBasicDrugCheck();
		ReportDetail reportDetail = new ReportDetail(
				RuleTypeEnum.skinTestOrNotChecker);
		if (basicDrugCheck == null) {
			return reportDetail;
		}
		SkinTest st = basicDrugCheck.getSkinTest();
		if (st == null) { // 如果该药品不携带皮试信息 则表示无皮试检测的必要
			return null;
		} else {
			if (prescribeCell.getSkinTested() == null
					|| !prescribeCell.getSkinTested()) {
				reportDetail.setGrade(GradeEnum.BAN);
				if (st.getRequirement().equals(STDegreeEnum.MUST)) {
					reportDetail.setRemark("药品【" + prescribeCell.getDrugName()
							+ "】使用前必须做皮试。");
				} else {
					reportDetail.setRemark("药品【" + prescribeCell.getDrugName()
							+ "】使用前建议做皮试。");
				}
				return reportDetail;

			} else {
				return null;
			}
		}

	}

	public boolean isAble() {
		return this.able;
	}

	public void enable(boolean able) {
		this.able = able;
	}

}
