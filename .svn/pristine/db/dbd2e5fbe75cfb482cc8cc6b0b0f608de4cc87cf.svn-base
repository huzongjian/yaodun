package com.springmvc.relationdrug.rules.domain;

/**
 * 
 * @ClassName: FormulaCommon
 * @Description: 男女体表面积计算公式分别为： S男=0.0057×身高+0.0121×体重+0.0882，
 *               S女=0.0073×身高+0.0127×体重-0.2106，
 *               若不区别男和女，为中国人适用的通式为S=0.0061×身高+0.0124×体重-0.0099
 * 
 */
public class FormulaCommon {

	/**
	 * 获取体表面积 体表面积（m2）=0.0061×身高（cm）+0.0128×体重(kg)-0.1529
	 * 
	 * @return
	 */
	public static double getBSA(double height, double weight) {
		if (height == 0.0 || weight == 0.0) {
			return 0.0;
		}
		double d;
		d = 0.0061 * height + 0.0128 * weight - 0.1529;
		return d;
	}

	/**
	 * 
	 * @Title: getBSA
	 * @Description: 性别不一样，体表面积计算方式不一样
	 * @param @param height
	 * @param @param weight
	 * @param @param sex 男 女
	 * @param @return 设定文件
	 * @return double 返回类型
	 * @throws
	 */

	public static double getBSA(double height, double weight, String sex) {
		if (height == 0.0 || weight == 0.0) {
			return 0.0;
		}
		double d;
		if (sex == "女") {
			d = 0.0073 * height + 0.0127 * weight - 0.2106;
		} else {
			d = 0.0057 * height + 0.0121 * weight + 0.0882;
		}

		return d;
	}

	/**
	 * 
	 * @param male
	 *            true是男性，false是女性
	 * @param age
	 *            年龄
	 * @param weight
	 *            体重
	 * @param blood
	 *            血肌酐
	 * @return
	 */
	public static double getCCR(boolean male, int age, double weight,
			double blood) {
		double d = (140 - age) * weight / (1.0 * 72) * blood;
		if (male)
			return d;
		else {
			return d * 0.85;
		}
	}

}
