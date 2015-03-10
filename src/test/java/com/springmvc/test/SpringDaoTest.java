package com.springmvc.test;


import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sdicons.json.validator.impl.predicates.False;
import com.springmvc.base.util.StringManager;
import com.springmvc.relationdrug.dao.BasicDrugCheckDao;
import com.springmvc.relationdrug.dao.BasicDrugDataDao;
import com.springmvc.relationdrug.dao.BasicDrugRelationshipDao;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.BasicDrugData;
import com.springmvc.relationdrug.domain.BasicDrugRelationship;
/**
 * 
* @ClassName: SpringDaoTest 
* @Description: junit测试
* @author HuZongJian
* @date 2014-8-1 下午4:24:27 
*
 */
@ContextConfiguration(locations={"classpath:com/springmvc/test/config/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class SpringDaoTest extends TestCase {
    @Resource
	private BasicDrugRelationshipDao bDao;
    @Resource
    private BasicDrugCheckDao bCheckDao;
    @Resource
    private BasicDrugDataDao bdataDao;
    @Test
	public void testQuery(){
		/*List<BasicDrugRelationship> list =bDao.findAll();
		for(BasicDrugRelationship b:list){
			 b.setCommodityNameSymbol(StringManager.getFirstPinYin(b.getCommodityName()));
			 b.setProductNameSymbol(StringManager.getFirstPinYin(b.getProductName()));
			 bDao.update(b);
		}*/
    	List<BasicDrugData> list2 =bdataDao.findAll();
    	for(BasicDrugData l:list2){
    		l.setSymbol(StringManager.getFirstPinYin(l.getDrugName()));
    		bdataDao.update(l);
    	}
    	
		System.err.println("success");
	}
}
