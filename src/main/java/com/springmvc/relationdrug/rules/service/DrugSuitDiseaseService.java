package com.springmvc.relationdrug.rules.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.dao.DrugSuitDiseaseDao;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;

/**
 * 药品是否适用于疾病监测
 * 
 */
@Transactional
@Service
public class DrugSuitDiseaseService {
	@Resource
	private DrugSuitDiseaseDao drugSuitDiseaseDao;

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {

		/*
		 * List<ReportDetail> reportList = new ArrayList<ReportDetail>(); try {
		 * // 疾病编码不为空时继续 if (StringUtils.isNotBlank(pBasicInfo.getDiseaseCode())
		 * && StringUtils.isNotBlank(pBasicInfo.getDiseaseName())) { //
		 * 根据处方疾病编码查找适用表存在的药品分类 List<DrugSuitDisease> suitList =
		 * drugSuitDiseaseDao .queryByDiseaseCode(pBasicInfo.getDiseaseCode());
		 * 
		 * 
		 * // 疾病不为空时，继续检测 if (CollectionUtils.isNotEmpty(suitList)) { //遍历去重复
		 * Set<String> acceptDiseaseSet = new HashSet<String>();
		 * for(DrugSuitDisease disease:suitList){
		 * acceptDiseaseSet.add(disease.getDiseaseIcd10()); }
		 * 
		 * String[] diseaseICDs = pBasicInfo.getDiseaseCode().split( ",");
		 * String[] diseaseNames = pBasicInfo.getDiseaseName().split( ",");
		 * StringBuilder diseaseNameB = new StringBuilder();
		 * 
		 * // 拼接可以匹配上的疾病名称 for (int i = 0; i < diseaseICDs.length; i++) { for
		 * (String icd10 : acceptDiseaseSet) { // 处方icd10和适用表icd10相等时 if
		 * (diseaseICDs[i].equals(icd10)) { diseaseNameB.append(diseaseNames[i])
		 * .append("、"); } } }
		 * 
		 * StringBuilder restDrugNames = new StringBuilder(); // 遍历处方信息 for
		 * (PrescribeCell cell : cellList) { // 根据处方药品id查询三大目录药品编码 DrugBmdDrug
		 * bmddrug = drugBmdDrugDao.queryByDrugid(cell
		 * .getBasicDrugCheck().getId());
		 * 
		 * if (bmddrug != null) { boolean suitFlag = false; for (DrugSuitDisease
		 * suit : suitList) { // 当bmddrugid的开头与适用表drugcode相匹配时，表示药品适用 if
		 * (bmddrug.getBmddrugid().startsWith( suit.getBmddrugId())) { suitFlag
		 * = true; break; } } if (!suitFlag) {// false表示药品不适用
		 * restDrugNames.append( cell.getBasicDrugCheck().getDrugName())
		 * .append("、"); } } } // 当不适用的药品名称存在时，输出检测结果 if (restDrugNames.length()
		 * > 0) { StringBuilder sb = new StringBuilder(); ReportDetail report =
		 * new ReportDetail( RuleTypeEnum.drugSuitDiseaseChecker);
		 * sb.append("药品【") .append(restDrugNames.substring(0,
		 * restDrugNames.length() - 1)) .append("】一般不适用于疾病 ")
		 * .append(diseaseNameB.substring(0, diseaseNameB.length() -
		 * 1)).append("。"); report.setRemark(sb.toString());
		 * report.setGrade(GradeEnum.BAN); reportList.add(report); } } } } catch
		 * (Exception e) { throw new CheckException(e); }
		 */
		// return reportList;
		return null;
	}

}
