package com.springmvc.relationdrug.rules.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.util.StringUtils;
import com.springmvc.relationdrug.dao.BasicDrugCheckDao;
import com.springmvc.relationdrug.dao.impl.DosageDaoImpl;
import com.springmvc.relationdrug.domain.Dosage;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.service.impl.DosageService;

@Transactional
@Service
public class UsageCheckService {

	@Resource
	private BasicDrugCheckDao basicDrugCheckDao;

	@Resource
	private DosageDaoImpl dosageDao;
	@Resource
	private DosageService dService;

	public List<ReportDetail> check(List<PrescribeCell> cellList)
			throws CheckException {
		List<ReportDetail> reportList = new ArrayList<ReportDetail>();
		String usageName = "";

		try {
			for (PrescribeCell cell : cellList) {
				Long drugid = cell.getBasicDrugCheck().getBasicDrugCheckId(); // 基表药品id

				// String drugname = cell.getBasicDrugCheck().getDrugName();//
				// 药品名称

				String drugname = cell.getDrugName();// 药品名称
				// 根据药品id查询药品所有用法
				usageName = this.getUsagesByDrugCode(drugid);
				// 只有在用法不为空的时候，才会继续检测
				if (!StringUtils.isNullOrBlank(usageName)) {

					ReportDetail report = new ReportDetail(
							RuleTypeEnum.usageChecker);
					String remark = "";

					// 当处方用法为空时，表示用法有误；
					if (cell.getUsage() == null) {

						remark = "药品【" + drugname + "】的用法有：" + usageName;
						report.setGrade(GradeEnum.CAUTION);
						report.setRemark(remark);
						reportList.add(report);
					} else {
						// 查询当前处方药品在用量表对应的用法
						Dosage dosage = this.dService.findByDrugIdAndUsage(
								drugid, cell.getUsage().getUsageId());

						if (dosage == null) {// 为空则用法错误
							remark = "药品【" + drugname + "】的用法不正确，其正确用法有："
									+ usageName;
							report.setGrade(GradeEnum.CAUTION);
							report.setRemark(remark);
							reportList.add(report);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CheckException(e);
		}
		return reportList;
	}

	public String getUsagesByDrugCode(Long basicid) {

		String usageName = "";

		// 根据药品编码查询药品所有用法
		List<Dosage> dosageList = dosageDao.findByBasicId(basicid);
		if (dosageList != null) {
			StringBuilder yfname = new StringBuilder();
			for (Dosage dosage : dosageList) {
				// 查询的用法不为空时，用"、"拼接所有用法
				if (dosage.getUsage() != null) {

					if (!StringUtils.isNullOrBlank(dosage.getUsage().getName())) {
						yfname.append(dosage.getUsage().getName()).append("、");
					}
				}
			}

			// 当用法存在时，去掉最后一个"、"
			if (yfname.length() > 0) {
				usageName = yfname.toString();
				usageName = usageName.substring(0, usageName.length() - 1)
						+ "。";
			}
		}
		return usageName;
	}
}
