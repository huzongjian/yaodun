package com.springmvc.relationdrug.rules.domain;

import org.apache.commons.lang.StringUtils;

import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.pojo.ReportDetail;

/**
 * 
 * Summary : 检测结果描述
 * 
 */
public class DayNumReportDetail extends ReportDetail {
	public void setRemark(String type, String drugName, int realDayNum,
			int standardDayNum) {
		StringBuilder remark_conn = new StringBuilder();
		if (StringUtils.isNotBlank(type)) {
			remark_conn.append(type).append("类");
		}
		remark_conn.append("药品【").append(drugName).append("】").append("用药天数为：")
				.append(realDayNum).append("天，标准用药天数应小于等于：")
				.append(standardDayNum).append("天。");
		this.setRemark(remark_conn.toString());
	}

	public DayNumReportDetail(RuleTypeEnum ruleType) {
		super(ruleType);
	}

}
