package com.springmvc.relationdrug.rules.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.util.DateUtils;
import com.springmvc.base.util.StringUtils;
import com.springmvc.relationdrug.domain.BasicDrugData;
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
public class SpecialCrowdCheckService {
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
					report = this.theOldCheck(cell.getBasicDrugData(), years,
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
					report = this.theChildCheck(cell.getBasicDrugData(),
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
						report = this.theSuckleCheck(cell.getBasicDrugData(),
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
						report = this.theGestateCheck(cell.getBasicDrugData(),
								patientInfo, cell.getDrugName());
						if (report != null
								&& !report.getGrade().equals(Grade.NORMAL)) {
							reportList.add(report);
						}
					}
				}
				// 肝功能患者用药检测
				if (patientInfo.isLiver() || patientInfo.isCliver()) {// 肝功能或重度肝功能
					ReportDetail report = new ReportDetail(
							RuleTypeEnum.specialGroupsChecker);
					report = this.theLiverCheck(cell.getBasicDrugData(),
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
					report = this.theGFRCheck(cell.getBasicDrugData(),
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
	public ReportDetail theOldCheck(BasicDrugData basic, int years,
			String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 当基表的老人禁忌不为空时检测
		if (!StringUtils.isNullOrBlank(basic.getTabooOld())) {

			// j/s/t/w等一般情况时，年龄默认为60岁以上
			if (basic.getTabooOld().length() == 1) {
				if (years > FIFTY) {
					// 检测禁忌类型
					report = this.checkResult(basic.getTabooOld(),
							CheckObjectEnum.THE_OLD.getText(), drugname);
				}
			} else {// 其他情况,说明是此种类型：">80岁j",可能包含>50岁等情形
				String sign = basic.getTabooOld().substring(0, 1);// 获取>或<
				String type = basic.getTabooOld().substring(
						basic.getTabooOld().length() - 1);// 获取最后一位j/t/w/s
				int age = Integer.valueOf(basic.getTabooOld().substring(1,
						basic.getTabooOld().length() - 2));// 获取年龄（单位岁）

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
			if (!StringUtils.isNullOrBlank(basic.getTabooOldReason()))
				report.setRemark(report.getRemark()
						+ basic.getTabooOldReason().replaceAll("\\s", ""));

		}
		return report;
	}

	/**
	 * 儿童用药检测
	 * 
	 */
	public ReportDetail theChildCheck(BasicDrugData basic, long brithday,
			int years, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);

		// 当基表的儿童禁忌不为空时检测
		if (!StringUtils.isNullOrBlank(basic.getTabooChild())) {
			// 一般情况(j/s/t/w)
			if (basic.getTabooChild().length() == 1) {
				if (years < SIXTEEN) {// 16岁以下才检测
					// 检测禁忌类型
					report = this.checkResult(basic.getTabooChild(),
							CheckObjectEnum.THE_CHILDREN.getText(), drugname);
				}
			} else {
				// 如果程序走到这里，说明是此种类型：">10岁j"或“<18岁s”等
				String sign = basic.getTabooChild().substring(0, 1);// 获取>或<
				String type = basic.getTabooChild().substring(
						basic.getTabooChild().length() - 1);// 获取最后一位j/t/w/s
				long age = Long.valueOf(basic.getTabooChild().substring(1,
						basic.getTabooChild().length() - 2)); // 获取年龄
				String unit = basic.getTabooChild().substring(
						basic.getTabooChild().length() - 2,
						basic.getTabooChild().length() - 1);// 年龄单位
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
			if (!StringUtils.isNullOrBlank(basic.getTabooChildReason()))
				report.setRemark(report.getRemark() + ","
						+ basic.getTabooChildReason().replaceAll("\\s", ""));

		}

		return report;
	}

	/**
	 * 妇女哺乳期检测
	 * 
	 */
	public ReportDetail theSuckleCheck(BasicDrugData basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		if (!StringUtils.isNullOrBlank(basic.getTabooSuckling())) {// 哺乳期禁忌不为空时
			// 一般情况(j/s/t/w),长度是1
			if (basic.getTabooSuckling().length() == 1) {
				report = this.checkResult(basic.getTabooSuckling(),
						CheckObjectEnum.THE_SUCKLING.getText(), drugname);
			} // 否则，说明是此种类型："<3月j"
			else {
				// 当输入了哺乳时则检测
				if (patientInfo.getSuckleTime() > 0) {
					String sign = basic.getTabooSuckling().substring(0, 1);// ">或<"
					String type = basic.getTabooSuckling().substring(
							basic.getTabooSuckling().length() - 1);// 获取最后一位j/t/w/s
					long time = Long
							.valueOf(basic.getTabooSuckling().substring(1,
									basic.getTabooSuckling().length() - 2));// 时间，已经全部规范化为月份为单位
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
			if (!StringUtils.isNullOrBlank(basic.getTabooSucklingReason()))
				report.setRemark(report.getRemark()
						+ basic.getTabooSucklingReason().replaceAll("\\s", ""));

		}
		return report;
	}

	/**
	 * 妇女妊娠期检测
	 * 
	 */
	public ReportDetail theGestateCheck(BasicDrugData basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 禁忌标志不为空时检测
		if (!StringUtils.isNullOrBlank(basic.getTabooGestate())) {
			// 一般情况（j/s/t/w）,长度是1
			if (basic.getTabooGestate().length() == 1) {
				report = this.checkResult(basic.getTabooGestate(),
						CheckObjectEnum.THE_GESTATE.getText(), drugname);

			}// 否则说明是此种类型："<3月j"
			else {
				// "<3月j"这种类型，其妊娠期时间必须大于0
				if (patientInfo.getGestateTime() > 0) {
					String sign = basic.getTabooGestate().substring(0, 1);// ">或<"
					String type = basic.getTabooGestate().substring(
							basic.getTabooGestate().length() - 1);// 获取最后一位j/t/w/s
					long time = Long.valueOf(basic.getTabooGestate().substring(
							1, basic.getTabooGestate().length() - 2));// 时间，已经全部规范化为月份为单位
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
			if (!StringUtils.isNullOrBlank(basic.getTabooGestateReason()))
				report.setRemark(report.getRemark() + ","
						+ basic.getTabooGestateReason().replaceAll("\\s", ""));

			if (!StringUtils.isNullOrBlank(basic.getfDA())) {
				String remark = basic.getfDA().replaceAll("\\s", "");
				String  s ="";
			/*	if(remark.equals("A")){
					
					s="\nA级在设对照组的药物研究中，在妊娠首3个月的妇女未见到药物对胎儿产生危害的迹象（并且没有在其后6个月具有危害的证据）。该类药物对胎儿的影响甚微";
				}else if(remark.equals("B")){
					s ="\nB级在动物繁殖研究中（并未进行孕妇的对照研究），未见到药物对胎儿的不良影响。或在动物繁殖研究中发现药物有副作用，但这些副作用并未在设对照的、妊娠首3个月的妇女中得到证实。";
				}else if(remark.equals("C")){
					s ="\nC级动物研究证明药物对胎儿有危害性（致畸或胚胎死亡等），或尚无设对照的妊娠妇女研究，或尚未对妊娠妇女及动物进行研究。本类药物只有确定了对孕妇的益处大于对胎儿的危害之后，方可使用。";
				}else if(remark.equals("D")){
					s ="\nD级有明确证据显示，药物对人类胎儿有危害性。但尽管如此，孕妇用药后 绝对有益（例如用该药物来挽救孕妇的生命，或治疗用其他较安全的药物无效的 严重疾病）。";
				}else if(remark.equals("X")){
					s ="\nX级对动物和人类的药物研究或人类用药的经验表明，药物对胎儿有危害。而且孕妇应用这类药物无益，因此禁用于妊娠或可能怀孕的患者。";
				}else {
					s ="";
				}*/
				report.setfDA(remark);
				report.setRemark(report.getRemark() + "该药品FDA分级为:"
						+ remark+"级"+s);

			}

		}
		return report;
	}

	/**
	 * 肝功能检测
	 * 
	 */
	public ReportDetail theLiverCheck(BasicDrugData basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 轻中度肝功能检测
		if (patientInfo.isLiver()) {
			// 肝功能禁忌标志不为空时检测
			if (!StringUtils.isNullOrBlank(basic.getTabooLiver())) {
				report = this.checkResult(basic.getTabooLiver(),
						CheckObjectEnum.THE_LIVER.getText(), drugname);
			}
			// 重度肝功能检测
		} else if (patientInfo.isCliver()) {
			// 重度肝功能禁忌标志不为空时检测
			if (!StringUtils.isNullOrBlank(basic.getTabooCliver())) {
				report = this.checkResult(basic.getTabooCliver(),
						CheckObjectEnum.THE_CLIVER.getText(), drugname);
			}
		}

		if (!StringUtils.isNullOrBlank(basic.getTabooLiverReason()))
			report.setRemark(report.getRemark()
					+ basic.getTabooLiverReason().replaceAll("\\s", ""));

		return report;
	}

	/**
	 * 肾小球滤过率（肾功能）检测
	 * 
	 */
	public ReportDetail theGFRCheck(BasicDrugData basic,
			PatientInfo patientInfo, String drugname) {
		ReportDetail report = new ReportDetail(
				RuleTypeEnum.specialGroupsChecker);
		// 轻度肾功能
		if (patientInfo.getGfr() == 1 || patientInfo.getGfr() == 2) {// 肾小球滤过率为1,2时轻度
			if (!StringUtils.isNullOrBlank(basic.getTabooKidney())) {// 肾功能禁忌不为空时
				report = this.checkResult(basic.getTabooKidney(),
						CheckObjectEnum.THE_KIDNEY.getText(), drugname);
			}
			// 重度肾功能
		} else if (patientInfo.getGfr() == 3) {// 肾小球=3重度
			if (!StringUtils.isNullOrBlank(basic.getTabooCkidney())) {// 重度肾功能禁忌不为空时
				report = this.checkResult(basic.getTabooCkidney(),
						CheckObjectEnum.THE_CKIDNEY.getText(), drugname);
			}
		}
		if (!StringUtils.isNullOrBlank(basic.getTabookidneyReason())) {
			report.setRemark(report.getRemark()
					+ basic.getTabookidneyReason().replaceAll("\\s", ""));

		}

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
