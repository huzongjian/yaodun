package com.springmvc.relationdrug.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.util.CollectionUtils;
import com.springmvc.base.util.DateUtils;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.BasicDrugData;
import com.springmvc.relationdrug.domain.VerifyRecord;
import com.springmvc.relationdrug.enums.GradeEnum;
import com.springmvc.relationdrug.enums.RuleTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.pojo.VerifyReport;
import com.springmvc.relationdrug.pojo.VerifyReport.ExeState;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 * Summary : 处方校验器
 * 
 */
@Transactional
@Service
public  class PrescriptionVerifier {
	
	private Logger log = Logger.getLogger(PrescriptionVerifier.class);
	@Resource
	private List<RuleChecker> ruleCheckers;
	
	@Resource
	BasicDrugCheckService basicDrugCheckService;
	
	@Resource
	VerifyRecordService verifyRecordService;
	@Resource
	BasicDrugDataService basicDrugDataService;
	
	/**
	 * 拣选checkers
	 * checkerInds[i]与{@link RuleTypeEnum}顺序对应
	 * @param checkerInds
	 */
	public final void adjustRuleCheckers(int[] checkerInds) {
		log.info("turn checkers (index:" + Arrays.toString(checkerInds)
				+ ") on...");
		synchronized (ruleCheckers) {
			for (RuleChecker checker : ruleCheckers) {
				checker.enable(false);
			}
			for (int i : checkerInds) {
				ruleCheckers.get(i).enable(true);
			}
		}
		log.info("turn checkers (index:" + Arrays.toString(checkerInds)
				+ ") on successfully!");
	}
	
	/**
	 * 检测
	 *
	 * @since 2013-7-31
	 * @param patientInfo
	 * @param pBasicInfo
	 * @param cellList
	 * @return
	 */
	public VerifyReport verify(PatientInfo patientInfo,
		PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList,String his){
	
		
		log.info("the verification for prescrption["+pBasicInfo.getPrescribeNo()+"] is starting... ");
		
		VerifyReport vr = new VerifyReport();
		
		List<ReportDetail> detail_list = new ArrayList<ReportDetail>();
		
		washPrescription12(patientInfo, pBasicInfo, cellList,his);	
		if(CollectionUtils.isEmpty(cellList)){
			vr.setExeState(ExeState.SUCCESS);//执行成功
			vr.setSeverestGrade(null); //良度未知
			vr.setRemark("检验程序未执行 由于处方药物均不存在于可检测药物列表中。");
			log.info("havn't executed verify process for prescription["+pBasicInfo.getPrescribeNo()+"] [all drug infos in the prescription are not existed in checking list]");
			return vr;
		}
		
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("ruleCheck.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		for(RuleChecker checker : ruleCheckers){
			String ruleName = checker.getClass().getSimpleName().split("Checker")[0]+"Checker";
			
			String u =p.getProperty(ruleName);
				try{
					if(u.equals("on")){
					if(checker.isAble()){ //假如规则可用则执行 否则跳过
						checker.prevLoading(patientInfo, pBasicInfo, cellList);
						List<ReportDetail> details = checker.check(patientInfo, pBasicInfo, cellList);
						log.info("finish executing rule "+ruleName);
						if(CollectionUtils.isNotEmpty(details)){
							Iterator<ReportDetail> rd_it = details.iterator();
							while(rd_it.hasNext()){//过滤检测结果为正常的
								if(rd_it.next().getGrade().equals(GradeEnum.NORMAL)){
									rd_it.remove();
								}
							}
							detail_list.addAll(details);
						}
					}
					}
				}catch(CheckException ce){
					log.info("some exceptions occur in verifying for prescription["+pBasicInfo.getPrescribeNo()+"]");
					log.warn("some exceptions occur in checking",ce);
				}
			
			
		}
		//如果返回结果为空 则表示通过检测 否则返回检测结果集合
		if(CollectionUtils.isEmpty(detail_list)){
			log.info("the verification for prescrption["+pBasicInfo.getPrescribeNo()+"] is finished;result:[eligible].");
		}else{
			vr.setOriginReport(detail_list);		
			log.info("the verification for prescrption["+pBasicInfo.getPrescribeNo()+"] is finished;result:[underproof].");
		}
		
		//保存处方检测记录
		VerifyRecord record = new VerifyRecord();
		record.setVerifyTime(DateUtils.now());
		record.setPrescribeCode(pBasicInfo.getPrescribeNo());
		record.setMedicalerNo(pBasicInfo.getMedicalerNo());
		try{
			//verifyRecordService.insert(record);
			//verifyRecordService.saveVerifyRecord(record, detail_list);
		}catch(Exception e){
			log.warn("some exceptions occur in saving verify-record for prescrption["+pBasicInfo.getPrescribeNo()+"]", e);
		}
		return vr;
	}
	
	/**
	 * 入参预先处理
	 * @param patientInfo
	 * @param pBasicInfo
	 * @param cellList
	 */
	private void washPrescription(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList,String his){
		Iterator<PrescribeCell>  cell_it = cellList.iterator();
		//循环加载药品基础信息 同时排除不可检测药品 
		while(cell_it.hasNext()){
			PrescribeCell pc = cell_it.next();
			if(pc.getBasicDrugCheck() == null){
				cell_it.remove();
			}else{
				try{
					
						BasicDrugCheck bdc = basicDrugCheckService.loadDeepBasicDrugCheck(pc.getBasicDrugCheck().getBasicDrugCheckId());
						if(bdc == null){
							cell_it.remove();
						}else{
							if(his.equals("HIS"))
							{
								
							}else
							{
								bdc.setDrugName(bdc.getDrugName()); //转码
								pc.setBasicDrugCheck(bdc);
							}
						}
					
				}catch(ServiceException e){
					log.warn("initial basic info exception", e);
				}
			}
		} 
	}
	
	private void washPrescription12(PatientInfo patientInfo,
			PrescribeBasicInfo pBasicInfo, List<PrescribeCell> cellList,String his){
		Iterator<PrescribeCell>  cell_it = cellList.iterator();
		//循环加载药品基础信息 同时排除不可检测药品 
		while(cell_it.hasNext()){
			PrescribeCell pc = cell_it.next();
			if(pc.getBasicDrugData() == null){
				cell_it.remove();
			}else{
				try{
					
						BasicDrugData bdc = basicDrugDataService.loadDeepBasicDrugCheck(pc.getBasicDrugData().getId());
						if(bdc == null){
							cell_it.remove();
						}else{
							if(his.equals("HIS"))
							{
								
							}else
							{
								//bdc.setDrugName(bdc.getDrugName()); //转码
								pc.setBasicDrugData(bdc);
							}
						}
					
				}catch(ServiceException e){
					log.warn("initial basic info exception", e);
				}
			}
		} 
	}
	
	
}
