package com.springmvc.relationdrug.dao;

import java.util.List;
import java.util.Map;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.enums.DrugCateGoryEnum;

/**
 * 
 * @ClassName: BasicDrugCheckDao
 * @Description:
 * @date 2013-7-16 下午4:07:57
 * 
 */
public interface BasicDrugCheckDao extends BaseDao<BasicDrugCheck, Long> {
	/**
	 * 
	 * 
	 * @Title: findByid
	 * @Description: 通过id查找药物检测信息
	 * @param @param id
	 * @param @return 设定文件
	 * @return BasicDrugCheck 返回类型
	 * @throws
	 * @date 2013-7-16
	 */

	public List<BasicDrugCheck> findByNameAndSymbol(String name);

	public BasicDrugCheck findByid(Long id);

	public BasicDrugCheck findBydrugCode(String drugCode);

	public BasicDrugCheck findByName(String name);

	/**
	 * 
	 * @Title: findByTypeAndName
	 * @Description:通过药品类型与药品名称，助记符查询前30条数据
	 * @param @param type
	 * @param @param drugname
	 * @param @return 设定文件
	 * @return List<BasicDrugCheck> 返回类型
	 * @throws
	 * @date 2013-8-14 下午5:08:48
	 */
	public List<BasicDrugCheck> findByTypeAndName(DrugCateGoryEnum type,
			String drugname);

	@SuppressWarnings("rawtypes")
	public List<BasicDrugCheck> findDrugChecksList(Map argMap);

	public int BasicDrugCheckAllSize();
}
