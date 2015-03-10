package com.springmvc.relationdrug.service.impl;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.BasicDrugCheckDao;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.enums.DrugCateGoryEnum;
/**
 * 
  * @ClassName: BasicDrugCheckService
  * @Description: 药物基表service
  *
 */
@Transactional
@Service("basicDrugCheckService")
public class BasicDrugCheckService extends BaseServiceImpl<BasicDrugCheck,Long>{

	
	@Resource
	@Autowired
	private BasicDrugCheckDao basicDrugCheckDao;
	
	/**
	 * 全面加载
	 * @param id
	 * @return
	 */
	public BasicDrugCheck get(Long id){
		BasicDrugCheck bdc = basicDrugCheckDao.get(id);
		return bdc;
	}
	public List<BasicDrugCheck> findByNameAndSymbol(String name){
		List<BasicDrugCheck> bdc = basicDrugCheckDao.findByNameAndSymbol(name);
		return bdc;
	}
	public BasicDrugCheck findByName(String name){
		return basicDrugCheckDao.findByName(name);
	}
	
	public BasicDrugCheck loadDeepBasicDrugCheck(Long id) throws ServiceException{
		try{
			BasicDrugCheck bdc = basicDrugCheckDao.findByid(id);
			if(bdc == null){
				return null;
			}
			//bdc.setLoaded(true);
		//	Hibernate.initialize(bdc.getCompatTaboo());
			if(bdc.getCompatTaboo() != null){
				bdc.getCompatTaboo().setLoaded(true);
			}
		//	Hibernate.initialize(bdc.getSkinTest());
			if(bdc.getSkinTest() != null)
				bdc.getSkinTest().setLoaded(true);
			return bdc;
			
		}catch(Exception e){
			System.err.println("异常");
			throw new ServiceException(e);
		}
	}
	/**
	 * 动态库调用 全面加载
	 * @param id
	 * @return
	 */
	public BasicDrugCheck loadDeepBasicDrugCheckBydrugcode(String drugcode) throws ServiceException{
		try{
			BasicDrugCheck bdc =basicDrugCheckDao.findBydrugCode(drugcode);
			if(bdc == null){
				return null;
			}
			bdc.setLoaded(true);
		//	Hibernate.initialize(bdc.getCompatTaboo());
			if(bdc.getCompatTaboo() != null){
				bdc.getCompatTaboo().setLoaded(true);
			}
		//	Hibernate.initialize(bdc.getSkinTest());
			if(bdc.getSkinTest() != null)
				bdc.getSkinTest().setLoaded(true);
			return bdc;
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}
	public List<BasicDrugCheck> findByTypeAndName(DrugCateGoryEnum type,String drugname){
		return basicDrugCheckDao.findByTypeAndName(type,drugname);
	}
	@SuppressWarnings("rawtypes")
	public List<BasicDrugCheck> findDrugChecksList(Map argMap)
	{
		return basicDrugCheckDao.findDrugChecksList(argMap);
	}
	public int BasicDrugCheckAllSize()
	{
		return basicDrugCheckDao.BasicDrugCheckAllSize();
	}


	@Override
	public BaseDao<BasicDrugCheck, Long> getDao() {
		return basicDrugCheckDao;
	}

}
