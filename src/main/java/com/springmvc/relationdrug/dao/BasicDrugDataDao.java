package com.springmvc.relationdrug.dao;

import java.util.List;
import java.util.Map;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.BasicDrugData;
import com.springmvc.relationdrug.enums.DrugCateGoryEnum;

/**
 * 
 * @ClassName: BasicDrugCheckDao
 * @Description:
 * @date 2013-7-16 下午4:07:57
 * 
 */
public interface BasicDrugDataDao extends BaseDao<BasicDrugData, Long> {
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

	public List<BasicDrugData> findByNameAndSymbol(String name);
	public BasicDrugData findByid(Long id);
	public BasicDrugData findByName(String name);


}
