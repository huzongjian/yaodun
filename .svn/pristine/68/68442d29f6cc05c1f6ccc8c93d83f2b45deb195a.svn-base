package com.springmvc.relationdrug.rules.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.util.StringUtils;
import com.springmvc.relationdrug.dao.DosageDao;
import com.springmvc.relationdrug.domain.Dosage;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.service.impl.DosageService;

/**
 * 频次检测
 * 
 */
@Transactional
@Service
public class FrequencyCheckService {
	@Resource
	private DosageDao dosageDao;
	@Resource
	private DosageService dService;

	public List<ReportDetail> check(List<PrescribeCell> cellList)
			throws CheckException {
		List<ReportDetail> reportList = new ArrayList<ReportDetail>();
		try {
			for (PrescribeCell cell : cellList) {
				// 当频次、用法任意一个为空时，跳过检测
				if (cell.getFrequency() == null || cell.getUsage() == null) {
					continue;
				}

				// 获取处方的药品名称、药品id
				// String drugname = cell.getBasicDrugCheck().getDrugName();
				String drugname = cell.getDrugName();
				Long drugid = cell.getBasicDrugCheck().getBasicDrugCheckId();
				Integer pcCode = cell.getFrequency().getCode();// 对应的频次编码
				// 根据药品id和药品用法查询用量表
				Dosage dosage = dosageDao.findByDrugIdAndUsage(drugid, cell
						.getUsage().getUsageId());
				if (dosage != null) {
					String frequency = dosage.getFrequency();

					if (!StringUtils.isNullOrBlank(frequency)) {
						String fre = frequency.substring(0,
								frequency.length() - 3);// 拆分频次（去掉次/天）
						// 如果频次含有～，说明频次是一个范围，则将数据进行拆分成两个数
						if (fre.contains("～")) {
							String[] freNum = fre.split("～");
							Integer pcmin = Integer.valueOf(freNum[0]);// 最小范围
							Integer pcmax = Integer.valueOf(freNum[1]);// 最大范围

							// 比较处方的频次是否在拆分的两个数据之间，如果小于最小或大于最大，说明频次不正确
							if ((pcCode < pcmin) || (pcCode > pcmax)) {
								ReportDetail report = new ReportDetail(
										RuleTypeEnum.frequencyChecker);
								StringBuilder sb = new StringBuilder();

								sb.append("药品【").append(drugname)
										.append("】的使用频次不正确,正确的使用频次有：")
										.append(frequency);
								report.setRemark(sb.toString());
								report.setGrade(GradeEnum.CAUTION);
								reportList.add(report);
							}
						}// 如果频次没有～，说明是具体的数据
						else {
							Integer pc = Integer.valueOf(fre);

							// 比较处方频次与pc进行比较，只有相等频次才算正确，否则不正确
							if (!pcCode.equals(pc)) {
								ReportDetail report = new ReportDetail(
										RuleTypeEnum.frequencyChecker);
								StringBuilder sb = new StringBuilder();

								sb.append("药品【").append(drugname)
										.append("】的使用频次不正确,正确的使用频次有：")
										.append(frequency);
								report.setRemark(sb.toString());
								report.setGrade(GradeEnum.CAUTION);
								reportList.add(report);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CheckException(e);
		}
		return reportList;
	}

}
