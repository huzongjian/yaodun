package com.springmvc.relationdrug.rules.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.util.MathUtils;
import com.springmvc.relationdrug.dao.impl.DosageDaoImpl;
import com.springmvc.relationdrug.domain.Dosage;
import com.springmvc.relationdrug.enums.DrugTypeEnum;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.rules.domain.FormulaCommon;
import com.springmvc.relationdrug.service.impl.DosageService;

/**
 * 用量检测
 * 
 */
@Transactional
@Service
public class DosageCheckService {
	@Resource
	private DosageDaoImpl dosageDao;
	@Resource
	private DosageService dService;

	public List<ReportDetail> check(PatientInfo patientInfo,
			List<PrescribeCell> cellList) throws CheckException {
		List<ReportDetail> reportList = new ArrayList<ReportDetail>();
		try {
			for (PrescribeCell cell : cellList) {
				// 当药品、用量或用法任意一个为空时，跳过检测
				if (cell.getDosage() == 0.0 || cell.getUsage() == null) {
					continue;
				}
				// 获取处方的药品名称、药品id
				// String drugname = cell.getBasicDrugCheck().getDrugName();
				// 取商品名
				String drugname = cell.getDrugName();
				Long drugid = cell.getBasicDrugCheck().getBasicDrugCheckId();

				ReportDetail report = new ReportDetail(
						RuleTypeEnum.dosageChecker);
				Dosage dosage = dosageDao.findByDrugIdAndUsage(drugid, cell
						.getUsage().getUsageId());

				// Dosage dosage =this.dosageDao.findByBasicId(drugid).get(0);

				// 只有查到对应用量信息后才能继续检测
				if (dosage != null) {
					double surfaceArea = 0;// 体表面积
					double height = patientInfo.getHeight();// 默认身高为0
					double weight = patientInfo.getWeight();// 默认体重为0

					double cellDosage = cell.getDosage(); // 处方输入剂量
					double minDosage = 0.0;
					if (dosage.getMinDosage() != null)
						minDosage = dosage.getMinDosage();// 标准最小用量
					double maxDosage = 0.0;
					if (dosage.getMaxDosage() != null)
						maxDosage = dosage.getMaxDosage();// 标准最大用量
					double maxDosageOnce = 0.0;
					if (dosage.getMaxDosagePerOnce() != null)
						maxDosageOnce = dosage.getMaxDosagePerOnce();// 标准单次最大用量
					double maxDosageInDay = 0.0;// 一天最大用量

					String drugType = "";// 获取药品类型
					if (cell.getBasicDrugCheck().getDrugType() != null) {
						drugType = DrugTypeEnum.values()[Integer.parseInt(cell
								.getBasicDrugCheck().getDrugType()) + 1]
								.getText();
						;
					}
					// 当surfacearea=0.0，说明身高、体重至少一个为0，此时不检测
					surfaceArea = FormulaCommon.getBSA(height, weight);
					StringBuilder sb = new StringBuilder();
					char cha;

					cha = dosage.getFlag().charAt(0);// 取第1位为最小用量
					if (cha == '1') {
						if (MathUtils.mul(dosage.getMinDosage(), weight) != 0.0) {
							// 实际最小用量=体重*最小用量，保留三位小数
							minDosage = MathUtils.round(MathUtils.mul(
									dosage.getMinDosage(), weight), 3);
						} else {
							sb.append(drugType).append("药品【").append(drugname)
									.append("】用量与身高体重有关，如需检测需输入身高体重");
							report.setRemark(sb.toString());
							report.setGrade(GradeEnum.WARN);// 提示
							reportList.add(report);
							continue;
						}
					} else if (cha == '2') {
						if (MathUtils.mul(dosage.getMinDosage(), surfaceArea) != 0.0) {
							// 实际最小用量=体表面积*最小用量，保留三位小数
							minDosage = MathUtils.round(MathUtils.mul(
									dosage.getMinDosage(), surfaceArea), 3);
						} else {
							sb.append(drugType).append("药品【").append(drugname)
									.append("】用量与身高体重有关，如需检测需输入身高体重");
							report.setRemark(sb.toString());
							report.setGrade(GradeEnum.WARN);// 提示
							reportList.add(report);
							continue;
						}
					}

					cha = dosage.getFlag().charAt(1);// 取第2位为最大用量
					if (cha == '1') {
						if (MathUtils.mul(dosage.getMaxDosage(), weight) != 0.0) {
							// 实际最大用量=体重*最大用量，保留三位小数
							maxDosage = MathUtils.round(MathUtils.mul(
									dosage.getMaxDosage(), weight), 3);
						} else {
							sb.append(drugType).append("药品【").append(drugname)
									.append("】用量与身高体重有关，如需检测需输入身高体重");
							report.setRemark(sb.toString());
							report.setGrade(GradeEnum.WARN);// 提示
							reportList.add(report);
							continue;
						}
					} else if (cha == '2') {
						if (MathUtils.mul(dosage.getMaxDosage(), surfaceArea) != 0.0) {
							// 实际最大用量=体表面积*最大用量，保留三位小数
							maxDosage = MathUtils.round(MathUtils.mul(
									dosage.getMaxDosage(), surfaceArea), 3);
						} else {
							sb.append(drugType).append("药品【").append(drugname)
									.append("】用量与身高体重有关，如需检测需输入身高体重");
							report.setRemark(sb.toString());
							report.setGrade(GradeEnum.WARN);// 提示
							reportList.add(report);
							continue;
						}
					}

					cha = dosage.getFlag().charAt(2);// 取第3位为单次最大用量
					if (cha == '1') {
						if (MathUtils.mul(dosage.getMaxDosagePerOnce(), weight) != 0.0) {
							// 实际最大用量=体重*标准单次最大用量，保留三位小数
							maxDosageOnce = MathUtils.round(MathUtils.mul(
									dosage.getMaxDosagePerOnce(), weight), 3);
						} else {
							sb.append(drugType).append("药品【").append(drugname)
									.append("】用量与身高体重有关，如需检测请输入身高体重");
							report.setRemark(sb.toString());
							report.setGrade(GradeEnum.WARN);// 提示
							reportList.add(report);
							continue;
						}
					} else if (cha == '2') {
						if (MathUtils.mul(dosage.getMaxDosagePerOnce(),
								surfaceArea) != 0.0) {
							// 实际最小用量=体表面积*标准单次最大用量，保留三位小数
							maxDosageOnce = MathUtils.round(MathUtils.mul(
									dosage.getMaxDosagePerOnce(), surfaceArea),
									3);
						} else {
							sb.append(drugType).append("药品【").append(drugname)
									.append("】用量与身高体重有关，如需检测请输入身高体重");
							report.setRemark(sb.toString());
							report.setGrade(GradeEnum.WARN);// 提示
							reportList.add(report);
							continue;
						}
					}

					// 计算一天最大用量
					if (cell.getFrequency() == null) {
						maxDosageInDay = cell.getDosage();// 频次为空时，等于处方用量
					} else {// 不为空时，一天最大用量 = //处方输入剂量*频次，保留3位小数
						double frequency = cell.getFrequency().getCode();
						maxDosageInDay = MathUtils.round(
								MathUtils.mul(cellDosage, frequency), 3);
					}

					// 检查最小用量
					if (minDosage > 0.0 && cellDosage < minDosage) {
						sb.append(drugType).append("药品【").append(drugname)
								.append("】单次用量小于成人最小有效用量,成人最小有效用量为：")
								.append(minDosage).append(dosage.getUnit());

						report.setRemark(sb.toString());
						report.setGrade(GradeEnum.WARN);// 提示
						reportList.add(report);
					} else {
						// 检查最大用量
						if (maxDosage > 0.0 && maxDosageInDay > maxDosage) {
							sb.append(drugType).append("药品【").append(drugname)
									.append("】超过成人每日最大用量，成人每日最大用量为：")
									.append(maxDosage).append(dosage.getUnit());
							report.setRemark(sb.toString());
							report.setGrade(GradeEnum.CAUTION);// 提示
							reportList.add(report);
						}
						// 检查单次最大用量
						if (maxDosageOnce > 0.0 && cellDosage > maxDosageOnce) {
							ReportDetail reportbig = new ReportDetail(
									RuleTypeEnum.dosageChecker);
							StringBuilder sBuilder = new StringBuilder();
							sBuilder.append(drugType).append("药品【")
									.append(drugname)
									.append("】超过成人单次最大用量，成人单次最大用量为：")
									.append(maxDosageOnce)
									.append(dosage.getUnit());
							reportbig.setRemark(sBuilder.toString());
							reportbig.setGrade(GradeEnum.WARN);// 提示
							reportList.add(reportbig);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CheckException(e);// 捕获异常
		}
		return reportList;
	}

}
