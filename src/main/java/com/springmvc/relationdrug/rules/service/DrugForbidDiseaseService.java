package com.springmvc.relationdrug.rules.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.util.CollectionUtils;
import com.springmvc.relationdrug.dao.DrugDiseaseDao;
import com.springmvc.relationdrug.domain.DrugDisease;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;

/**
 * 疾病禁用药品检测
 * 
 */
@Transactional
@Service
public class DrugForbidDiseaseService {
	@Resource
	private DrugDiseaseDao drugDiseaseDao;

	private long firstIcdLevel = 1;
	private long secondIcdLevel = 2;
	private long thirdIcdLevel = 3;

	public List<ReportDetail> check(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList)
			throws CheckException {
		List<ReportDetail> reportList = new ArrayList<ReportDetail>();
		try {
			String[] diseaseICDs = pBasicInfo.getDiseaseCode().split(","); // 处方icd10疾病编码
			String[] diseaseNames = pBasicInfo.getDiseaseName().split(","); // 处方疾病名称
			Map<String, String[]> diseaseDrugMap = new HashMap<String, String[]>();
			for (int i = 0; i < diseaseICDs.length; i++) {
				String icd10 = diseaseICDs[i];
				StringBuilder sb = new StringBuilder();

				// 根据icd10和级别获取疾病药品信息
				List<DrugDisease> diseaseList = this.Checkdisease(icd10);
				boolean flag;
				if (CollectionUtils.isNotEmpty(diseaseList)) {// 疾病药品信息存在则继续检测
					for (PrescribeCell cell : cellList) {
						// 根据药品id查询对应的三大目录药品编码
						// 三大目录DrugBmdDrug bmddrug =
						// this.drugBmdDrugDao.queryByDrugid(cell.getBasicDrugCheck().getId());
						// 三大目录if (bmddrug != null)
						// {
						// 三大目录String bmddrugid =
						// bmddrug.getBmddrugid().substring(0,bmddrug.getBmddrugid().length()
						// - 3);//截取后三位
						// 检测编码是否匹配
						String drugid = cell
								.getBasicDrugCheck()
								.getDrugCode()
								.substring(
										0,
										cell.getBasicDrugCheck().getDrugCode()
												.length() - 3);
						flag = this.DrugDiseaseCheck(diseaseList, drugid);
						if (flag) {// flag为true，表示疾病禁用该药品
							sb.append(cell.getBasicDrugCheck().getDrugName())
									.append("、");
							// }
						}
					}
					String drugName = "";
					if (sb.length() > 0) {
						drugName = sb.substring(0, sb.length() - 1);
						diseaseDrugMap.put(icd10, new String[] {
								diseaseNames[i], drugName });// 将疾病和对应的禁用药品进行分类组合
					}
				}
			}

			// 遍历检测的疾病与禁用药品
			for (Map.Entry<String, String[]> entry : diseaseDrugMap.entrySet()) {
				String[] strs = entry.getValue();
				if (strs[1] != "") {// 记录检测结果
					ReportDetail report = new ReportDetail(
							RuleTypeEnum.drugForbidDiseaseChecker);
					report.setRemark("药品【" + strs[1] + "】禁用于疾病" + strs[0] + "。");
					report.setGrade(GradeEnum.BAN);
					reportList.add(report);
				}
			}

		} catch (Exception e) {
			throw new CheckException(e);
		}
		return reportList;

	}

	/**
	 * 根据icd10和级别获取疾病药品信息
	 * 
	 * @author feifangyuan
	 * @since 2013-8-7
	 * @param icd10
	 * @return
	 */
	public List<DrugDisease> Checkdisease(String icd10) {
		List<DrugDisease> dList = null;
		String subIcd;
		// 如果icd10长度大于5刚从每3级查询
		if (icd10.length() > 5) {
			dList = drugDiseaseDao.findDrugDiseaseByIcd(icd10, thirdIcdLevel);
			// 如果在第3级查询为空，并且icd不为特殊编码刚截取从第2级逐级往上查询
			if (CollectionUtils.isEmpty(dList) && icd10.indexOf(".") > 0) {
				subIcd = icd10.substring(0, 5);
				dList = drugDiseaseDao.findDrugDiseaseByIcd(subIcd,
						secondIcdLevel);
				// 如果第二级还是为空的话，继续截取查第1级
				if (CollectionUtils.isEmpty(dList)) {
					subIcd = icd10.substring(0, 3);
					dList = drugDiseaseDao.findDrugDiseaseByIcd(subIcd,
							firstIcdLevel);
				}
			}
		}
		// 如果等于5直接从第2级查询
		else if (icd10.length() == 5) {
			dList = drugDiseaseDao.findDrugDiseaseByIcd(icd10, secondIcdLevel);
		}
		// 如果等于3直接从第1级查询
		else if (icd10.length() == 3) {
			dList = drugDiseaseDao.findDrugDiseaseByIcd(icd10, firstIcdLevel);
		}
		return dList;
	}

	/**
	 * 分类检测
	 * 
	 * @author feifangyuan
	 * @since 2013-8-7
	 * @param diseaseList
	 * @param bmddrugid
	 * @return
	 */
	public boolean DrugDiseaseCheck(List<DrugDisease> diseaseList,
			String bmddrugid) {
		for (DrugDisease dd : diseaseList) {
			// drugid不为空时，继续
			if (StringUtils.isNotBlank(dd.getDrugId())) {
				// 当疾病表的drugid = 药品与三大目录药品对应表的bmddrugid时，说明对应疾病禁用该药品，检测结束
				if (dd.getDrugId().equals(bmddrugid)) {
					return true;
				} else {// 否则继续检测
					String drugLevel[] = { dd.getSixLevel(),
							dd.getFifthLevel(), dd.getFourthLevel(),
							dd.getThirdLevel(), dd.getSecondLevel(),
							dd.getFirstLevel() };
					// 取药品id前面有效的12位作为新的id，相当于6级；
					bmddrugid = bmddrugid.substring(0, 12);
					String disdrugid = dd.getDrugId().substring(0, 12);

					for (int i = 0; i < drugLevel.length; i++) {
						if (StringUtils.isEmpty(drugLevel[i])) {
							continue;
						} else {
							// 当i= 0 时，6级，不做截取；
							if (i > 0)
							/*
							 * 三大目录(i > 0 && i <= 3) { // 当3,4,5级时，截取1位
							 * bmddrugid = bmddrugid.substring(0,
							 * bmddrugid.length() - 1); disdrugid =
							 * disdrugid.substring(0, bmddrugid.length() - 1); }
							 * else if (i > 3)// 1,2级，截取2位
							 */
							{
								bmddrugid = bmddrugid.substring(0,
										bmddrugid.length() - 2);
								disdrugid = disdrugid.substring(0,
										bmddrugid.length() - 2);
							}
							if (bmddrugid.equals(disdrugid)) {// 判断截取后是否相等，相等返回true，否则继续
								return true;
							} else {
								return false;// 不相等，表示药品不在禁用分类下，返回false
							}
						}
					}
				}
			} else// drugid为空返回false
			{
				return false;
			}
		}
		// 能走到这一步，说明六级都未匹配上
		return false;
	}
}
