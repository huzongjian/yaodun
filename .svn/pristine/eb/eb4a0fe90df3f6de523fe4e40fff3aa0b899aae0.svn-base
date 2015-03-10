package com.springmvc.relationdrug.rules.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.relationdrug.dao.ConflictFromDrugsDao;
import com.springmvc.relationdrug.domain.CompatTaboo;
import com.springmvc.relationdrug.domain.ConflictFromDrugs;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.DrugDrug;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;

/**
 * 配伍禁忌检测
 * 
 */
@Transactional
@Service
public class MultiDrugsMathupService {
	@Resource
	@Autowired
	private ConflictFromDrugsDao conflictFromDrugsDao;

	/**
	 * 配伍提示等级
	 */
	private static List<String> levelOne = new ArrayList<String>();
	private static List<String> levelTwo = new ArrayList<String>();
	private static List<String> levelThree = new ArrayList<String>();
	// private static List<ConflictFromDrugs> conflictList = new
	// ArrayList<ConflictFromDrugs>();
	// private static Map<String, ConflictFromDrugs> conflictMap = new
	// HashMap<String, ConflictFromDrugs>();
	private static boolean checked = false;

	public List<ReportDetail> check(List<PrescribeCell> cellList)
			throws CheckException {

		List<ReportDetail> reportList = new ArrayList<ReportDetail>();
		try {
			/**
			 * 根据配置文件获取提示等级
			 */
			List<Long> cellid = new ArrayList<Long>();
			for (int i = 0; i < cellList.size(); i++) {
				CompatTaboo tabo = cellList.get(i).getBasicDrugCheck()
						.getCompatTaboo();
				if (tabo != null) {
					cellid.add(Long.parseLong((tabo.getCompatTabooId().toString())));
				}

			}

			getConflictLevel();
			List<ConflictFromDrugs> conflictList = new ArrayList<ConflictFromDrugs>();
			Map<String, ConflictFromDrugs> conflictMap = new HashMap<String, ConflictFromDrugs>();
			if (!cellid.isEmpty()) {
				conflictList = conflictFromDrugsDao.findbytabooid(cellid);// 查询所有用药冲突记录
				if (conflictList.size() > 0) {
					if (conflictMap.isEmpty()) {
						// map所有冲突药品
						for (ConflictFromDrugs dc : conflictList) {
							conflictMap.put(dc.getCompatTaboo1().getCompatTabooId() + "-"
									+ dc.getCompatTaboo2().getCompatTabooId(), dc);// 组合A-B型为key
						}
					}

					// 获取药品列表任意两个的组合
					List<DrugDrug> drugdrugList = this.DrugDrugGroup(cellList);

					// 存在组合则继续
					if (drugdrugList.size() > 0) {
						for (DrugDrug drug : drugdrugList) {

							String groupkey1 = drug.getCompatTaboo1().getCompatTabooId()
									+ "-" + drug.getCompatTaboo2().getCompatTabooId();// 组合A-B型

							ConflictFromDrugs conflictDrug = null;
							if (conflictMap.containsKey(groupkey1)) {// 键值相等时说明存在冲突
								conflictDrug = conflictMap.get(groupkey1);
							} else {
								String groupkey2 = drug.getCompatTaboo2()
										.getCompatTabooId()
										+ "-"
										+ drug.getCompatTaboo1().getCompatTabooId();// 组合B-A型
								if (conflictMap.containsKey(groupkey2)) {// 键值相等时说明存在冲突
									conflictDrug = conflictMap.get(groupkey2);
								}
							}

							if (conflictDrug == null) {
								continue;
							}

							StringBuilder sb = new StringBuilder();
							sb.append("药品【").append(drug.getDrugname1())
									.append("】和药品【")
									.append(drug.getDrugname2())
									.append("】建议不要同时使用！");
							/*
							 * sb.append("药品【") .append(drug.getDrugname1())
							 * .append("】和药品【") .append(drug.getDrugname2())
							 * .append("】存在用药冲突，冲突类型为：")
							 * .append(conflictDrug.getConflictType()
							 * .getText());
							 */

							ReportDetail reportDetail = new ReportDetail(
									RuleTypeEnum.multiDrugsMathupChecker);

							// 判断配伍禁忌等级
							GradeEnum grade = null;
							String level = String.valueOf(conflictDrug
									.getConflictType());

							if (checkInLevelOne(level)) {
								grade = GradeEnum.WARN;
							} else if (checkInLevelTwo(level)) {
								grade = GradeEnum.CAUTION;
							} else if (checkInLevelThree(level)) {
								grade = GradeEnum.BAN;
							}
							reportDetail.setGrade(grade);
							reportDetail.setRemark(sb.toString());
							reportList.add(reportDetail);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CheckException(e);
		}

		if (reportList.isEmpty()) {
			ReportDetail reportDetail = new ReportDetail(
					RuleTypeEnum.multiDrugsMathupChecker);
			reportDetail.setGrade(GradeEnum.WARN);
			reportDetail.setRemark("无");
			reportList.add(reportDetail);
		}
		return reportList;
	}

	/**
	 * 分组并组合
	 * 
	 * @param cellList
	 * @return
	 */
	public List<DrugDrug> DrugDrugGroup(List<PrescribeCell> cellList) {

		// 同组药品归类
		Map<String, List<PrescribeCell>> groupDrugs = new HashMap<String, List<PrescribeCell>>();
		for (PrescribeCell cell : cellList) {
			// 0表示没有分组,跳过,配伍禁忌为空，跳过
			if (cell.getGroupNo() == null || cell.getGroupNo() == 0
					|| cell.getBasicDrugCheck().getCompatTaboo() == null) {
				continue;
			}

			List<PrescribeCell> preList = null;

			// 分组存在则获取分组对应的值
			if (groupDrugs.containsKey(String.valueOf(1))) {
				preList = groupDrugs.get(String.valueOf(1));
			} else {// 不存在，添加分组，和对应值
				preList = new ArrayList<PrescribeCell>();
				groupDrugs.put(String.valueOf(1), preList);
			}
			preList.add(cell);// 更新

			/*
			 * //分组存在则获取分组对应的值 if
			 * (groupDrugs.containsKey(String.valueOf(cell.getGroupNo()))) {
			 * preList = groupDrugs.get(String.valueOf(cell.getGroupNo())); }
			 * else {//不存在，添加分组，和对应值 preList = new ArrayList<PrescribeCell>();
			 * groupDrugs.put(String.valueOf(cell.getGroupNo()), preList); }
			 * preList.add(cell);//更新
			 */
		}

		// 同组两两组合
		List<DrugDrug> drugdrugList = new ArrayList<DrugDrug>();
		for (Map.Entry<String, List<PrescribeCell>> entry : groupDrugs
				.entrySet()) {
			// 只有一条时直接跳过
			if (1 == entry.getValue().size()) {
				continue;
			}
			List<PrescribeCell> preList = entry.getValue();

			for (int i = 0; i < preList.size() - 1; i++) {
				PrescribeCell cell1 = preList.get(i);
				for (int j = i + 1; j < preList.size(); j++) {
					PrescribeCell cell2 = preList.get(j);
					DrugDrug drugs = new DrugDrug();
					drugs.setCompatTaboo1(cell1.getBasicDrugCheck()
							.getCompatTaboo());
					drugs.setCompatTaboo2(cell2.getBasicDrugCheck()
							.getCompatTaboo());
					drugs.setDrugname1(cell1.getDrugName());
					drugs.setDrugname2(cell2.getDrugName());
					drugdrugList.add(drugs);
				}
			}
		}
		return drugdrugList;
	}

	/**
	 * 根据配置文件获取提示等级
	 */
	private void getConflictLevel() {
		if (!checked) {
			checked = !checked;
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream("conflictlevel.properties");
			Properties p = new Properties();
			try {
				p.load(inputStream);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String level1 = p.getProperty("level1");
			String level2 = p.getProperty("level2");
			String level3 = p.getProperty("level3");
			String level1s[] = level1.split("#");
			String level2s[] = level2.split("#");
			String level3s[] = level3.split("#");
			for (int i = 0; i < level1s.length; i++) {
				levelOne.add(level1s[i]);
			}
			for (int i = 0; i < level2s.length; i++) {
				levelTwo.add(level2s[i]);
			}
			for (int i = 0; i < level3s.length; i++) {
				levelThree.add(level3s[i]);
			}
		} else {
			// do nothing
		}
	}

	/**
	 * 检查是不是在第1级中
	 * 
	 * @param level
	 * @return
	 */
	private boolean checkInLevelOne(String level) {
		for (int i = 0; i < levelOne.size(); i++) {
			if (level.equals(levelOne.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查是不是在第2级中
	 * 
	 * @param level
	 * @return
	 */
	private boolean checkInLevelTwo(String level) {
		for (int i = 0; i < levelTwo.size(); i++) {
			if (level.equals(levelTwo.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查是不是在第3级中
	 * 
	 * @param level
	 * @return
	 */
	private boolean checkInLevelThree(String level) {
		for (int i = 0; i < levelThree.size(); i++) {
			if (level.equals(levelThree.get(i))) {
				return true;
			}
		}
		return false;
	}

}
