package com.springmvc.relationdrug.rules.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.util.DateUtils;
import com.springmvc.base.util.StringUtils;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.enums.CheckObjectEnum;
import com.springmvc.relationdrug.enums.DrugUseTypeEnum;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.pojo.PatientInfo.GenderEnum;
import com.springmvc.relationdrug.pojo.ReportCell.Grade;

/**
 * 特殊人群检测（老人、儿童、孕妇、妊娠）/肝肾功能检测;
 * 
 */
@Transactional
@Service
public class SpecialGroupsCheckService {
	// private static int SIXTY = 60;// 60岁
	private static int FIFTY = 50;// 50岁
	private static int WOMAN = 13;// 13岁
	// private static int EIGHTEEN = 18;// 18岁
	private static int SIXTEEN = 16;// 16岁

	public List<ReportDetail> check(PatientInfo patientInfo,
			List<PrescribeCell> cellList) throws CheckException {
		List<ReportDetail> reportList = new ArrayList<ReportDetail>();
		try {
			for (PrescribeCell cell : cellList) {
				
				// 获取出生日期到当前时间的天数
				long brithday = DateUtils.dayDiff(DateUtils.now(),
						patientInfo.getBirthday());
				// 获取出生日期到当前时间的年数
				int years = DateUtils.yearDiff(DateUtils.now(),
						patientInfo.getBirthday());
				// 一般情况（j/s/t/w）只算60岁以上，存在特殊情况（>50岁），所以限制50岁以上进入检测
				if (years > FIFTY) {
					ReportDetail report = new ReportDetail(
							RuleTypeEnum.specialGroupsChecker);
					report = this.theOldCheck(cell.getBasicDrugCheck(), years,
							cell.getDrugName());
					if (report != null
							&& !report.getGrade().equals(Grade.NORMAL)) {
						reportList.add(report);
					}
				}
				// <16岁儿童用药检测，一般情况（j/t/s/w）下儿童是16岁以下，特殊（<18岁）18岁要考虑进来。
				if (years < SIXTEEN) {
					ReportDetail report = new ReportDetail(
							RuleTypeEnum.specialGroupsChecker);
					report = this.theChildCheck(cell.getBasicDrugCheck(),
							brithday, years, cell.getDrugName());
					if (report != null
							&& !report.getGrade().equals(Grade.NORMAL)) {
						reportList.add(report);
					}
				}
				// 性别为女的才有哺乳期用药检测和妊娠期用药检测
				if (patientInfo.getGender() != null
						&& patientInfo.getGender().equals(GenderEnum.FEMALE)) {
					// >13岁妇女,哺乳期妇女用药检测
					if (patientInfo.isSuckling() && years > WOMAN) {
						ReportDetail report = new ReportDetail(
								RuleTypeEnum.specialGroupsChecker);
						report = this.theSuckleCheck(cell.getBasicDrugCheck(),
								patientInfo, cell.getDrugName());
						if (report != null
								&& !report.getGrade().equals(Grade.NORMAL)) {
							reportList.add(report);
						}
					}
					// >13岁妇女,妊娠期妇女用药检测
					
					if (patientInfo.isGestate() && years > WOMAN) {// 妊娠期
						ReportDetail report = new ReportDetail(
								RuleTypeEnum.specialGroupsChecker);
						report = this.theGestateCheck(cell.getBasicDrugCheck(),
								patientInfo, cell.getDrugName());
						if (report != null && !report.getGrade().equals(Grade.NORMAL)) {
							reportList.add(report);
						}
					}
				}
				// 肝功能患者用药检测
				if (patientInfo.isLiver() || patientInfo.isCliver()) {// 肝功能或重度肝功能
					ReportDetail report = new ReportDetail(
							RuleTypeEnum.specialGroupsChecker);
					report = this.theLiverCheck(cell.getBasicDrugCheck(),
							patientInfo, cell.getDrugName());
					if (report != null
							&& !report.getGrade().equals(Grade.NORMAL)) {
						reportList.add(report);
					}
				}
				// 肾小球滤过率（肾功能）用药检测
				if (patientInfo.getGfr() > 0) {// 肾小球滤过率
					ReportDetail report = new ReportDetail(
							RuleTypeEnum.specialGroupsChecker);
					report = this.theGFRCheck(cell.getBasicDrugCheck(),
							patientInfo, cell.getDrugName());
					if (report != null
							&& !report.getGrade().equals(Grade.NORMAL)) {
						reportList.add(report);
					}
				}
			}
		} catch (Exception e) {
			throw new CheckException(e);
		}
		return reportList;
	}

	/**
	 * 老人用药检测
	 */
	public ReportDetail theOldCheck(BasicDrugCheck basic, int years,
			String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 当基表的老人禁忌不为空时检测
		if (!StringUtils.isNullOrBlank(basic.getTaboo4Old())) {

			// j/s/t/w等一般情况时，年龄默认为60岁以上
			if (basic.getTaboo4Old().length() == 1) {
				if (years > FIFTY) {
					// 检测禁忌类型
					report = this.checkResult(basic.getTaboo4Old(),
							CheckObjectEnum.THE_OLD.getText(), drugname);
				}
			} else {// 其他情况,说明是此种类型：">80岁j",可能包含>50岁等情形
				String sign = basic.getTaboo4Old().substring(0, 1);// 获取>或<
				String type = basic.getTaboo4Old().substring(
						basic.getTaboo4Old().length() - 1);// 获取最后一位j/t/w/s
				int age = Integer.valueOf(basic.getTaboo4Old().substring(1,
						basic.getTaboo4Old().length() - 2));// 获取年龄（单位岁）

				// 禁忌拆分后含有“>”的情况
				if (sign.equals(DrugUseTypeEnum.SIGN_GREATER.getText())
						|| sign.equals(DrugUseTypeEnum.SIGN_GREATER2.getText())) {
					if (years > age) {// 当患者年龄大于禁忌最小年龄时检测
						// 检测禁忌类型
						report = this.checkResult(type,
								CheckObjectEnum.THE_OLD.getText(), drugname);
					}
				} else {// 到这里说明禁忌拆分后含有“<”
					if (years > FIFTY && years < age) {// 当患者年龄小于禁忌最大年龄时检测
						// 检测禁忌类型
						report = this.checkResult(type,
								CheckObjectEnum.THE_OLD.getText(), drugname);
					}
				}
			}
			if(!StringUtils.isNullOrBlank(basic.getTabooOldReason()))
				report.setRemark(report.getRemark()+","+basic.getTabooOldReason().replaceAll("\\s", ""));
			
		}
		return report;
	}

	/**
	 * 儿童用药检测
	 * 
	 */
	public ReportDetail theChildCheck(BasicDrugCheck basic, long brithday,
			int years, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);

		// 当基表的儿童禁忌不为空时检测
		if (!StringUtils.isNullOrBlank(basic.getTaboo4Child())) {
			// 一般情况(j/s/t/w)
			if (basic.getTaboo4Child().length() == 1) {
				if (years < SIXTEEN) {// 16岁以下才检测
					// 检测禁忌类型
					report = this.checkResult(basic.getTaboo4Child(),
							CheckObjectEnum.THE_CHILDREN.getText(), drugname);
				}
			} else {
				// 如果程序走到这里，说明是此种类型：">10岁j"或“<18岁s”等
				String sign = basic.getTaboo4Child().substring(0, 1);// 获取>或<
				String type = basic.getTaboo4Child().substring(
						basic.getTaboo4Child().length() - 1);// 获取最后一位j/t/w/s
				long age = Long.valueOf(basic.getTaboo4Child().substring(1,
						basic.getTaboo4Child().length() - 2)); // 获取年龄
				String unit = basic.getTaboo4Child().substring(
						basic.getTaboo4Child().length() - 2,
						basic.getTaboo4Child().length() - 1);// 年龄单位
				if (unit.equals("月")) {
					age = age * 30;
				} else if (unit.equals("岁")) {
					age = age * 365;
				}// 换算后的年龄（单位为天）

				if (sign.equals(DrugUseTypeEnum.SIGN_GREATER.getText())
						|| sign.equals(DrugUseTypeEnum.SIGN_GREATER.getText())) {// 禁忌拆分后含有“>”的情况
					if (years < SIXTEEN && brithday >= age) { // 如果生日年龄大于最小限制年龄且小于16岁时检测
						// 检测禁忌类型
						report = this.checkResult(type,
								CheckObjectEnum.THE_CHILDREN.getText(),
								drugname);
					}
				} else {// 禁忌拆分后含有“<”的情况
					if (brithday < age) {// 如果生日年龄小于最大限制年龄时检测
						// 检测禁忌类型
						report = this.checkResult(type,
								CheckObjectEnum.THE_CHILDREN.getText(),
								drugname);
					}
				}
			}
			if(!StringUtils.isNullOrBlank(basic.getTabooChildReason()))
				report.setRemark(report.getRemark()+","+basic.getTabooChildReason().replaceAll("\\s", ""));
			
		}
		
		return report;
	}

	/**
	 * 妇女哺乳期检测
	 * 
	 */
	public ReportDetail theSuckleCheck(BasicDrugCheck basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		if (!StringUtils.isNullOrBlank(basic.getTaboo4Suckling())) {// 哺乳期禁忌不为空时
			// 一般情况(j/s/t/w),长度是1
			if (basic.getTaboo4Suckling().length() == 1) {
				report = this.checkResult(basic.getTaboo4Suckling(),
						CheckObjectEnum.THE_SUCKLING.getText(), drugname);
			} // 否则，说明是此种类型："<3月j"
			else {
				// 当输入了哺乳时则检测
				if (patientInfo.getSuckleTime() > 0) {
					String sign = basic.getTaboo4Suckling().substring(0, 1);// ">或<"
					String type = basic.getTaboo4Suckling().substring(
							basic.getTaboo4Suckling().length() - 1);// 获取最后一位j/t/w/s
					long time = Long.valueOf(basic.getTaboo4Suckling()
							.substring(1,
									basic.getTaboo4Suckling().length() - 2));// 时间，已经全部规范化为月份为单位
					// ">"大于类型
					if (sign.equals(DrugUseTypeEnum.SIGN_GREATER.getText())
							|| sign.equals(DrugUseTypeEnum.SIGN_GREATER2
									.getText())) {
						// 哺乳时间大于最小限制哺乳期时检测
						if (patientInfo.getSuckleTime() > time) {
							report = this.checkResult(type,
									CheckObjectEnum.THE_SUCKLING.getText(),
									drugname);
						}
					} else {// "<"小于类型
						// 哺乳时间小于最大限制哺乳期时检测
						if (patientInfo.getSuckleTime() < time) {
							report = this.checkResult(type,
									CheckObjectEnum.THE_SUCKLING.getText(),
									drugname);
						}
					}
				}
			}
			if(!StringUtils.isNullOrBlank(basic.getTabooSucklingReason()))
				report.setRemark(report.getRemark()+","+basic.getTabooSucklingReason().replaceAll("\\s", ""));
			
		}
		return report;
	}

	/**
	 * 妇女妊娠期检测
	 * 
	 */
	public ReportDetail theGestateCheck(BasicDrugCheck basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 禁忌标志不为空时检测
		if (!StringUtils.isNullOrBlank(basic.getTaboo4Gestate())) {
			// 一般情况（j/s/t/w）,长度是1
			if (basic.getTaboo4Gestate().length() == 1) {
				report = this.checkResult(basic.getTaboo4Gestate(),
						CheckObjectEnum.THE_GESTATE.getText(), drugname);
				
			}// 否则说明是此种类型："<3月j"
			else {
				// "<3月j"这种类型，其妊娠期时间必须大于0
				if (patientInfo.getGestateTime() > 0) {
					String sign = basic.getTaboo4Gestate().substring(0, 1);// ">或<"
					String type = basic.getTaboo4Gestate().substring(
							basic.getTaboo4Gestate().length() - 1);// 获取最后一位j/t/w/s
					long time = Long
							.valueOf(basic.getTaboo4Gestate().substring(1,
									basic.getTaboo4Gestate().length() - 2));// 时间，已经全部规范化为月份为单位
					if (sign.equals(DrugUseTypeEnum.SIGN_GREATER.getText())
							|| sign.equals(DrugUseTypeEnum.SIGN_GREATER2
									.getText())) {// >类型时
						// 哺乳时间大于最小限制时间时检测
						if (patientInfo.getGestateTime() > time) {
							report = this.checkResult(type,
									CheckObjectEnum.THE_GESTATE.getText(),
									drugname);
						}
					} else {// < 类型时
						// 哺乳时间小于最大限制时间时检测
						if (patientInfo.getGestateTime() < time) {
							report = this.checkResult(type,
									CheckObjectEnum.THE_GESTATE.getText(),
									drugname);
						}
					}
				}
			}
			if(!StringUtils.isNullOrBlank(basic.getTabooGestateReason()))
			report.setRemark(report.getRemark()+","+basic.getTabooGestateReason().replaceAll("\\s", ""));
			
			if(!StringUtils.isNullOrBlank(basic.getfDA()))
				report.setRemark(report.getRemark()+","+"该药品FDA分类为:"+basic.getfDA().replaceAll("\\s", ""));
				
		}
		return report;
	}

	/**
	 * 肝功能检测
	 * 
	 */
	public ReportDetail theLiverCheck(BasicDrugCheck basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 轻中度肝功能检测
		if (patientInfo.isLiver()) {
			// 肝功能禁忌标志不为空时检测
			if (!StringUtils.isNullOrBlank(basic.getTaboo4Liver())) {
				report = this.checkResult(basic.getTaboo4Liver(),
						CheckObjectEnum.THE_LIVER.getText(), drugname);
			}
			// 重度肝功能检测
		} else if (patientInfo.isCliver()) {
			// 重度肝功能禁忌标志不为空时检测
			if (!StringUtils.isNullOrBlank(basic.getTaboo4Cliver())) {
				report = this.checkResult(basic.getTaboo4Cliver(),
						CheckObjectEnum.THE_CLIVER.getText(), drugname);
			}
		}
		
		if(!StringUtils.isNullOrBlank(basic.getTabooLiverReason()))
			report.setRemark(report.getRemark()+","+basic.getTabooLiverReason().replaceAll("\\s", ""));
			
			
		return report;
	}

	/**
	 * 肾小球滤过率（肾功能）检测
	 * 
	 */
	public ReportDetail theGFRCheck(BasicDrugCheck basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 轻度肾功能
		if (patientInfo.getGfr() == 1 || patientInfo.getGfr() == 2) {// 肾小球滤过率为1,2时轻度
			if (!StringUtils.isNullOrBlank(basic.getTaboo4Kidney())) {// 肾功能禁忌不为空时
				report = this.checkResult(basic.getTaboo4Kidney(),
						CheckObjectEnum.THE_KIDNEY.getText(), drugname);
			}
			// 重度肾功能
		} else if (patientInfo.getGfr() == 3) {// 肾小球=3重度
			if (!StringUtils.isNullOrBlank(basic.getTaboo4Ckidney())) {// 重度肾功能禁忌不为空时
				report = this.checkResult(basic.getTaboo4Ckidney(),
						CheckObjectEnum.THE_CKIDNEY.getText(), drugname);
			}
		}
		if(!StringUtils.isNullOrBlank(basic.getTabooCkidneyReason()))
			report.setRemark(report.getRemark()+","+basic.getTabooCkidneyReason().replaceAll("\\s", ""));
	
		return report;
	}

	/**
	 * 特殊人群检测共用方法
	 */
	public ReportDetail checkResult(String tabooType, String patient,
			String drugName) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		StringBuilder sb = new StringBuilder();

		if (tabooType.equalsIgnoreCase(DrugUseTypeEnum.TABOO_J.getText())) {// 是否禁用j
			sb.append("药品【").append(drugName).append("】,").append(patient)
					.append("禁止使用。");
			report.setGrade(GradeEnum.BAN);// 禁用
			report.setRemark(sb.toString());
		} else if (tabooType
				.equalsIgnoreCase(DrugUseTypeEnum.TABOO_S.getText())) {// 是否慎用s
			sb.append("药品【").append(drugName).append("】,").append(patient)
					.append("请谨慎使用。");// 慎用
			report.setGrade(GradeEnum.CAUTION);
			report.setRemark(sb.toString());
		} else if (tabooType
				.equalsIgnoreCase(DrugUseTypeEnum.TABOO_T.getText())) {// 是否提示t
			sb.append("药品【").append(drugName).append("】,").append(patient)
					.append("请酌情使用。");
			report.setGrade(GradeEnum.WARN);// 警告
			report.setRemark(sb.toString());
		} else if (tabooType
				.equalsIgnoreCase(DrugUseTypeEnum.TABOO_W.getText())) {// 无
			report.setGrade(GradeEnum.NORMAL);
		}
		return report;
	}
}
