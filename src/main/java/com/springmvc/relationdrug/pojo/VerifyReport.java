package com.springmvc.relationdrug.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.apache.commons.collections.CollectionUtils;

import com.springmvc.base.util.JsonValue;
import com.springmvc.relationdrug.enums.GradeEnum;

/**
 * 
 * Summary : 处方校验报告单
 * 
 * 
 */
@Entity
public class VerifyReport {

	/**
	 * 处方良度
	 */
	@JsonValue
	private GradeEnum severestGrade;

	/**
	 * 执行情况
	 */
	@JsonValue
	private ExeState exeState;

	/**
	 * 备注
	 */
	@JsonValue
	private String remark;

	/**
	 * 分组报告
	 */
	@JsonValue
	private ReportCell[] reportCells = new ReportCell[3];

	/**
	 * 未分组报告
	 */
	@JsonValue
	private List<ReportDetail> originReport;

	{
		exeState = ExeState.SUCCESS;
		severestGrade = GradeEnum.NORMAL;
	}

	public GradeEnum getSeverestGrade() {
		return severestGrade;
	}

	public void setSeverestGrade(GradeEnum severestGrade) {
		this.severestGrade = severestGrade;
	}

	public ExeState getExeState() {
		return exeState;
	}

	public void setExeState(ExeState exeState) {
		this.exeState = exeState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ReportCell[] getReportCells() {
		return reportCells;
	}

	public void setReportCells(ReportCell[] reportCells) {
		this.reportCells = reportCells;
	}

	public List<ReportDetail> getOriginReport() {
		return originReport;
	}

	public void setOriginReport(List<ReportDetail> originReport) {
		this.originReport = originReport;
		conclude4Verification();
	}

	/**
	 * 
	 * Summary : 执行情况
	 * 
	 * 
	 * @author Lineshow Email:lineshow7@gmail.com
	 * 
	 * @since 2013-7-25
	 */
	public enum ExeState {

		SUCCESS("成功"), FAILURE("失败"), EXCEPTION("异常");

		private String text;

		ExeState(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	/**
	 * 分析检测结果
	 */
	private void conclude4Verification() {
		// 拣选器
		Map<String, List<ReportDetail>> selectorMap = new HashMap<String, List<ReportDetail>>();
		// 循环检测结果 进行分类存放
		for (ReportDetail reportDetail : this.originReport) {
			String tempKey = reportDetail.getGrade().toString();
			List<ReportDetail> reportDetails = selectorMap.get(tempKey);
			if (reportDetails == null) {
				reportDetails = new ArrayList<ReportDetail>();
				selectorMap.put(tempKey, reportDetails);
			}
			reportDetails.add(reportDetail);
		}
		// 将map中的值分别存储于ReportCell 同时找出最严重的问题级别
		int i = 0;
		for (GradeEnum grade : GradeEnum.values()) {
			List<ReportDetail> reportDetails = selectorMap
					.get(grade.toString());
			ReportCell reportCell = new ReportCell();
			reportCell.setGrade(grade);
			reportCell.setDetail(reportDetails);
			reportCells[i++] = reportCell;
			if (!CollectionUtils.isEmpty(reportDetails)
					&& severestGrade.equals(GradeEnum.NORMAL)) {
				severestGrade = grade;
			}
			if (i == 3)
				break;
		}
	}
}
