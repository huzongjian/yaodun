package com.springmvc.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sdicons.json.mapper.MapperException;
import com.springmvc.base.util.CollectionUtils;
import com.springmvc.base.util.DateUtils;
import com.springmvc.base.util.EntityToJson;
import com.springmvc.base.util.StringManager;
import com.springmvc.base.util.StringUtils;
import com.springmvc.relationdrug.dao.BasicDrugDataDao;
import com.springmvc.relationdrug.domain.BasicDrugData;
import com.springmvc.relationdrug.domain.BasicDrugRelationship;
import com.springmvc.relationdrug.domain.Frequency;
import com.springmvc.relationdrug.domain.Otherinfo;
import com.springmvc.relationdrug.domain.Usage;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.enums.AdviceTypeEnum;
import com.springmvc.relationdrug.exception.CheckException;
import com.springmvc.relationdrug.pojo.PatientInfo;
import com.springmvc.relationdrug.pojo.PatientInfo.CureTypeEnum;
import com.springmvc.relationdrug.pojo.PatientInfo.GenderEnum;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo;
import com.springmvc.relationdrug.pojo.PrescribeBasicInfo.TitleEnum;
import com.springmvc.relationdrug.pojo.PrescribeCell;
import com.springmvc.relationdrug.pojo.ReportDetail;
import com.springmvc.relationdrug.pojo.VerifyReport;
import com.springmvc.relationdrug.rules.service.OtherInfoService;
import com.springmvc.relationdrug.service.OwnDoctorService;
import com.springmvc.relationdrug.service.UserService;
import com.springmvc.relationdrug.service.impl.BasicDrugCheckService;
import com.springmvc.relationdrug.service.impl.BasicDrugDataService;
import com.springmvc.relationdrug.service.impl.BasicDrugRelationshipService;
import com.springmvc.relationdrug.service.impl.FrequencyService;
import com.springmvc.relationdrug.service.impl.PrescriptionVerifier;
import com.springmvc.relationdrug.service.impl.UsageService;

/**
 * 
 * @ClassName: DrugBasicCheckController x
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HuZongJian
 * @date 2014-7-8 下午3:19:36
 * 
 */
@Controller
@RequestMapping("DrugBasicCheck")
public class DrugBasicCheckController {
	@Resource
	private PrescriptionVerifier prescriptionVerifier;
	@Resource
	private BasicDrugCheckService basicDrugCheckService;
	@Resource
	private UsageService usageService;
	@Resource
	private FrequencyService frequencyService;
	@Resource
	private OtherInfoService otherInfoService;
	@Resource
	private BasicDrugRelationshipService bRelationshipService;
	@Resource
	private UserService userService;
	@Resource
	private OwnDoctorService ownDoctorService;
	@Resource
	private BasicDrugDataService basicDrugDataService;

	/**
	 * 
	 * @Title: SearchFrequency
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param matchInfo
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException
	 * @param @throws ServiceException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-7-8 下午4:28:39
	 */

	@RequestMapping("searchFrequency/{matchInfo}")
	@ResponseBody
	private void SearchFrequency(@PathVariable String matchInfo,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServiceException {
		List<Frequency> listFrequency = frequencyService
				.findByNameAndSymbol(matchInfo);
		JsonArray array = new JsonArray();

		for (Frequency f : listFrequency) {
			JsonObject obj = new JsonObject();
			obj.addProperty("id", f.getFrequencyId());
			obj.addProperty("name", f.getName());
			obj.addProperty("symbol", f.getSymbol());
			array.add(obj);
		}

		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		out.print(array);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/searchFrequency")
	@ResponseBody
	private void SearchFrequency(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject outobj = new JSONObject();
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		try {
			List<Frequency> listFrequency = frequencyService.findAll();
			JSONArray array = new JSONArray();
			for (Frequency f : listFrequency) {
				JSONObject obj = new JSONObject();
				obj.put("id", f.getFrequencyId());
				obj.put("name", f.getName());
				obj.put("symbol", f.getSymbol());
				array.add(obj);
			}

			outobj.put("code", "1000");
			outobj.put("msg", "success");
			outobj.put("data", array);

		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "查询失败");
			outobj.put("data", "");
		}

		out.print(outobj);
	}

	/**
	 * 
	 * @Title: SearchDrug
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param matchInfo
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException
	 * @param @throws ServiceException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-7-8 下午4:28:48
	 */

	@RequestMapping(value = "searchDrug/{matchInfo}")
	@ResponseBody
	private void SearchDrug(@PathVariable String matchInfo,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServiceException {
		/*List<BasicDrugData>   list =basicDrugDataService.findByNameAndSymbol(matchInfo);
		JSONArray array = new JSONArray();
		for (BasicDrugData basic : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", basic.getId());
			obj.put("drugname", basic.getDrugName());
			obj.put("productName", "");
			obj.put("basicId", basic.getId());
			obj.put("unit", "");
			array.add(obj);
		}*/
		JSONArray array = new JSONArray();
		String drugname =request.getParameter("matchInfo");
		List<BasicDrugRelationship> listbasicDrugCheck = bRelationshipService
				.findByNameAndSymbol(drugname);
		
		for (BasicDrugRelationship basic : listbasicDrugCheck) {
			JSONObject obj = new JSONObject();
			obj.put("id", basic.getBasicDrugRelationShipId());
			if(basic.getCommodityName().equals("")){
				obj.put("drugname", basic.getProductName());	
			}else{
				obj.put("drugname", basic.getCommodityName());
			}
			obj.put("drugname", basic.getProductName());	
			obj.put("productName", basic.getProductName());
			if (basic.getBasicDrugCheck() != null) {
				obj.put("basicId", basic.getBasicDrugCheck()
						.getId());
				obj.put("unit","");
			} else {
				obj.put("basicId", "");
				obj.put("unit", "");
			}
			array.add(obj);
		}
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		out.print(array);
	

	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/searchDrug")
	@ResponseBody
	private void SearchDruginterface(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServiceException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outobj = new JSONObject();
		try {
			// 使用原始的输入流手动解析传入参数。
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject x = JSONObject.fromObject(in.readLine());
			// 解析用户基本信息
			String drugName = x.getString("drugName");
			/*List<BasicDrugData>   list =basicDrugDataService.findByNameAndSymbol(drugName);
			JSONArray array = new JSONArray();
			for (BasicDrugData basic : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", basic.getId());
				obj.put("drugname", basic.getDrugName());
				obj.put("productName", "");
				obj.put("basicId", basic.getId());
				obj.put("unit", "");
				array.add(obj);
			}*/
			
			JSONArray array = new JSONArray();
			List<BasicDrugRelationship> listbasicDrugCheck = bRelationshipService
					.findByNameAndSymbol(drugName);
			
			for (BasicDrugRelationship basic : listbasicDrugCheck) {
				JSONObject obj = new JSONObject();
				obj.put("id", basic.getBasicDrugRelationShipId());
				if(basic.getCommodityName().equals("")){
					obj.put("drugname", basic.getProductName());	
				}else{
					obj.put("drugname", basic.getCommodityName());
				}
				obj.put("drugname", basic.getProductName());	
				//obj.put("drugname", basic.getCommodityName());
				obj.put("productName", basic.getProductName());
				if (basic.getBasicDrugCheck() != null) {
					obj.put("basicId", basic.getBasicDrugCheck()
							.getId());
					obj.put("unit","");
				} else {
					obj.put("basicId", "");
					obj.put("unit", "");
				}
				array.add(obj);
			}
			outobj.put("code", "1000");
			outobj.put("msg", "success");
			outobj.put("data", array);

		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "查询失败");
			outobj.put("data", "");
		}

		out.print(outobj);

	}

	/**
	 * 
	 * @Title: SearchOtherInfo
	 * @Description: 查询其它信息
	 * @param @param matchInfo
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException
	 * @param @throws ServiceException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-10-28 上午11:14:05
	 */
	@RequestMapping("searchOtherInfo/{matchInfo}")
	@ResponseBody
	private void SearchOtherInfo(@PathVariable Integer matchInfo,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServiceException {
		List<Otherinfo> otherinfo = otherInfoService.findbytype(matchInfo);
		JSONArray array = new JSONArray();
		for (Otherinfo l : otherinfo) {
			JSONObject obj = new JSONObject();
			EntityToJson.entityToJSON(l, obj);
			array.add(obj);
		}
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		out.print(array);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/searchOtherInfo")
	@ResponseBody
	private void SearchOtherInfoInterface(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServiceException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outobj = new JSONObject();
		try {

			// 使用原始的输入流手动解析传入参数。
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject x = JSONObject.fromObject(in.readLine());
			// 解析用户基本信息
			Integer otherInfo = x.getInt("otherInfo");
			List<Otherinfo> otherinfo = otherInfoService.findbytype(otherInfo);
			JSONArray array = new JSONArray();
			for (Otherinfo l : otherinfo) {
				JSONObject obj = new JSONObject();
				EntityToJson.entityToJSON(l, obj);
				array.add(obj);
			}
			outobj.put("code", "1000");
			outobj.put("msg", "success");
			outobj.put("data", array);

		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "查询失败");
			outobj.put("data", e.getMessage());
		}

		out.print(outobj);

	}

	/**
	 * 
	 * @Title: SearchUsage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param matchInfo
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException
	 * @param @throws ServiceException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-7-8 下午4:28:55
	 */
	@RequestMapping("searchUsage/{matchInfo}")
	@ResponseBody
	private void SearchUsage(@PathVariable String matchInfo,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServiceException {
		List<Usage> listUsage = usageService.findByNameAndSymbol(matchInfo);
		JsonArray array = new JsonArray();

		for (Usage u : listUsage) {
			JsonObject obj = new JsonObject();
			obj.addProperty("id", u.getUsageId());
			obj.addProperty("name", u.getName());
			obj.addProperty("symbol", u.getSymbol());
			array.add(obj);
		}

		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		out.print(array);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/searchUsage")
	@ResponseBody
	private void SearchUsageInterface(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServiceException {
		JSONObject outobj = new JSONObject();
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		try {
			List<Usage> listUsage = usageService.findAll();
			JSONArray array = new JSONArray();
			for (Usage u : listUsage) {
				JSONObject obj = new JSONObject();
				obj.put("id", u.getUsageId());
				obj.put("name", u.getName());
				obj.put("symbol", u.getSymbol());
				array.add(obj);
			}
			outobj.put("code", "1000");
			outobj.put("msg", "success");
			outobj.put("data", array);

		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "查询失败");
			outobj.put("data", "");
		}

		out.print(outobj);

	}

	private static boolean isNum(String str) {
		try {

			new BigDecimal(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @Title: drugCheckinterface
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException
	 * @param @throws TokenStreamException
	 * @param @throws RecognitionException
	 * @param @throws MapperException
	 * @param @throws CheckException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-10-24 上午9:52:24 参数格式如下： {
	 *       "baseInfo":{"male":0,"age":"0","height"
	 *       :"","weight":"","isLiver":"0","kidney":"0"},
	 *       "drugInfo":[{"drugname"
	 *       :"1_注射用普鲁卡因青霉素","usage":"","dosage":"","frequery":""},
	 *       {"drugname":"1_注射用普鲁卡因青霉素","usage":"","dosage":"","frequery":""} ]
	 *       }
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/drugCheckinterface")
	@ResponseBody
	public void drugCheckinterface(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			TokenStreamException, RecognitionException, MapperException,
			CheckException {

		JSONObject outobj = new JSONObject();
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=utf-8");
		out = response.getWriter();
		try {

			// 使用原始的输入流手动解析传入参数。
			
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(),"gb2312"));
			JSONObject x = JSONObject.fromObject(in.readLine());
			// 解析用户基本信息
			
			JSONObject baseobject =JSONObject.fromObject(x.getString("baseInfo"));
			String checktype = baseobject.getString("checkType");

			PatientInfo patientInfo = new PatientInfo();
			DateUtils.now();
			if (baseobject.getString("age").equals("1")) {
				// 少于16岁的儿童年龄从14岁转化成出生年月
				patientInfo.setBirthday(DateUtils.nextMonths(DateUtils.now(),
						-14 * 12));
			} else if (baseobject.getString("age").equals("2")
					|| baseobject.getString("age").equals("0")) {
				// 成人按25岁转化成出生年月
				patientInfo.setBirthday(DateUtils.nextMonths(DateUtils.now(),
						-25 * 12));
			} else if (baseobject.getString("age").equals("3")) {
				// 老人按52岁转化成出生年月
				patientInfo.setBirthday(DateUtils.nextMonths(DateUtils.now(),
						-52 * 12));

			}
			patientInfo.setCureType(CureTypeEnum.OUT_PATIENT);
			if (baseobject.getString("gender").equals("1")) {
				patientInfo.setGender(GenderEnum.MALE);
			} else {
				patientInfo.setGender(GenderEnum.FEMALE);
				patientInfo.setGestateTime(0);
			}
			patientInfo.setName("某某");
			patientInfo.setSerialNo("12345");
			if (isNum(baseobject.getString("weight"))) {
				patientInfo.setWeight(Integer.parseInt(baseobject
						.getString("weight")));
			}
			if (isNum(baseobject.getString("height"))) {
				patientInfo.setHeight(Integer.parseInt(baseobject
						.getString("height")));
			}
			if (baseobject.getString("isLiver").equals("0")) {
				patientInfo.setLiver(false);
			} else if (baseobject.getString("isLiver").equals("1")) {
				patientInfo.setLiver(true);
			} else if (baseobject.getString("isLiver").equals("2")) {
				patientInfo.setCliver(true);
			}
			String gfr =baseobject.getString("kidney");
					if(StringUtils.isNullOrBlank(gfr)){
						patientInfo.setGfr(0);
					}
					else{
						patientInfo.setGfr(Long.parseLong(gfr));
					}
			
			
			//妊娠期
		 String	isGestate= baseobject.getString("isGestate");
		//妊娠期时间
		 String gestateTime =baseobject.getString("gestateTime");
		 if(isGestate.equals("1")){
			 patientInfo.setGestate(true);
			 //如果选了妊娠期就把年龄设成为成年人
			 patientInfo.setBirthday(DateUtils.nextMonths(DateUtils.now(),
						-25 * 12));
			 if(!StringUtils.isNullOrBlank(gestateTime))
			 patientInfo.setGestateTime(Long.parseLong(gestateTime));
		 }
		 String	isSuckling= baseobject.getString("isSuckling");
		 
		//哺乳期
		 if(isSuckling.equals("1")){
			 patientInfo.setSuckling(true);
		 }	
			
			
			// 解析药品信息
			JSONArray jsonarray = x.getJSONArray("drugInfo");
			List<PrescribeCell> cellList = new ArrayList<PrescribeCell>();
			for (int i = 0; i < jsonarray.size(); i++) {
				JSONObject jsonobject = (JSONObject) jsonarray.get(i);
				PrescribeCell cell = new PrescribeCell();
				cell.setGroupNo(1);
				// 药品的id为空应该去数据查询药品是否存在
				String basicId = jsonobject.getString("basicId");
				/*BasicDrugCheck a = this.basicDrugCheckService.get(Long
						.parseLong(basicId));
				cell.setBasicDrugCheck(a);*/
				System.err.println(basicId);
				BasicDrugData b = this.basicDrugDataService.get(Long
						.parseLong(basicId));
				cell.setBasicDrugData(b);
				String basicName = jsonobject.getString("drugname");
				cell.setDrugName(URLDecoder.decode(basicName,"utf-8"));
				cell.setAdviceType(AdviceTypeEnum.TEMP_ADVICE);
				// 用法如果为空应该去数据查询用法是否存在
				String usage = jsonobject.getString("usage");
				if (isNum(usage)) {
					Usage u = this.usageService.get(Long.parseLong(usage));
					cell.setUsage(u);
				} else {
					if (usage.equals("")) {
						cell.setUsage(null);
					} else {
						Usage u = this.usageService.findByName(usage);
						if (u != null) {
							cell.setUsage(u);
						} else {
							cell.setUsage(null);
						}
					}
				}
				cell.setDaynum(null); // 天数
				String frequery = jsonobject.getString("frequery");
				// 频次
				if (isNum(frequery)) {
					Frequency f = this.frequencyService.get(Long
							.parseLong(frequery));
					cell.setFrequency(f);
				} else {
					if (frequery.equals("")) {
						cell.setFrequency(null);
					} else {
						Frequency f = this.frequencyService
								.findByName(frequery);
						if (f != null) {
							cell.setFrequency(f);
						} else {
							cell.setFrequency(null);
						}
					}
				}
				// 如果用量与单位为手工输入则把单位分出来
				String dosage = jsonobject.getString("dosage");
				if (!StringUtils.isNullOrBlank(dosage)) {
					if (isNum(dosage)) {
						cell.setDosage(Double.parseDouble(dosage));
					} else {
						String unit = splitNotNumber(dosage);
						String d = dosage.replace(unit, "");
						if (isNum(d)) {
							cell.setDosage(Double.parseDouble(d));
							cell.setUnit(unit);
						} else {
							cell.setDosage(0);
						}

					}
				} else {
					cell.setDosage(0);
				}
				// 添加
				cellList.add(cell);
			}

			PrescribeBasicInfo pBasicInfo = new PrescribeBasicInfo();
			pBasicInfo.setCreateTime(DateUtils.now());
			pBasicInfo.setDiseaseCode("A01.101");
			pBasicInfo.setDiseaseName("流行性感冒");
			pBasicInfo.setDivisionCode("dept-2046");
			pBasicInfo.setDivisionName("外科");
			pBasicInfo.setMedicalerName("某医生");
			pBasicInfo.setMedicalerNo("m-3721");
			pBasicInfo.setMedicalerTitle(TitleEnum.MainMedicaler);
			pBasicInfo.setPrescribeNo("1314");
			pBasicInfo.setTotalAmount(BigDecimal.valueOf(999.98));
			JSONArray array = new JSONArray();
			VerifyReport v = prescriptionVerifier.verify(patientInfo,
					pBasicInfo, cellList, "");
			if (v != null) {
				if (CollectionUtils.isNotEmpty(v.getOriginReport())) {
					List<ReportDetail> l = v.getOriginReport();
					List<ReportDetail> l2 = getUniqueSymptom(l);
					List<String> fda =new ArrayList<String>();
					for (ReportDetail report : l2) {
						JSONObject obj = new JSONObject();
						//if (!StringUtils.isNullOrBlank(checktype)) {
						if(!report.getRemark().equals("无")){
							obj.put("type", report.getRuleType().getText()
									+ "：");
							obj.put("grade", report.getGrade().getText());
							obj.put("result", report.getRemark());
							if(!StringUtils.isNullOrBlank(report.getFDA()))
							fda.add(report.getFDA());
							array.add(obj);
					}
					}
					String a="",b="",c="",d="",xz="";
					for(String sw:fda){
						if(sw.equals("A")){
							a ="\nA级在设对照组的药物研究中，在妊娠首3个月的妇女未见到药物对胎儿产生危害的迹象（并且没有在其后6个月具有危害的证据）。该类药物对胎儿的影响甚微";
						}else if(sw.equals("B")){
							b ="\nB级在动物繁殖研究中（并未进行孕妇的对照研究），未见到药物对胎儿的不良影响。或在动物繁殖研究中发现药物有副作用，但这些副作用并未在设对照的、妊娠首3个月的妇女中得到证实。";
						}else if(sw.equals("C")){
							c ="\nC级动物研究证明药物对胎儿有危害性（致畸或胚胎死亡等），或尚无设对照的妊娠妇女研究，或尚未对妊娠妇女及动物进行研究。本类药物只有确定了对孕妇的益处大于对胎儿的危害之后，方可使用。";
						}else if(sw.equals("D")){
							d ="\nD级有明确证据显示，药物对人类胎儿有危害性。但尽管如此，孕妇用药后 绝对有益（例如用该药物来挽救孕妇的生命，或治疗用其他较安全的药物无效的 严重疾病）。";
						}else if(sw.equals("X")){
							xz ="\nX级对动物和人类的药物研究或人类用药的经验表明，药物对胎儿有危害。而且孕妇应用这类药物无益，因此禁用于妊娠或可能怀孕的患者。";
						}else {
						}
					}
					String lengs=a + b + c + d + xz;
					if(!lengs.equals("")){
						JSONObject obj = new JSONObject();
						obj.put("type","说明:");
						obj.put("grade", "提示");
						obj.put("result", "\n\n"+lengs);
						array.add(obj);
					}
				} else {
					JSONObject obj = new JSONObject();
					obj.put("type", "");
					obj.put("grade", "提示");
					obj.put("result", "未发现配伍禁忌和重复用药!");
					array.add(obj);
				}
			}
			outobj.put("code", "1000");
			outobj.put("msg", "success");
			outobj.put("data", array);

		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "检测失败");
			outobj.put("data", e.getMessage());
		}
		out.print(outobj);
	}

	public static List<ReportDetail> getUniqueSymptom(
			List<ReportDetail> disdetail) {
		List<ReportDetail> boxList = new ArrayList<ReportDetail>();
		boolean flag = false;
		String detailid = "";
		for (ReportDetail sta : disdetail) {
			flag = false;
			detailid = "" + sta.getRemark();
			for (ReportDetail n : boxList) {
				if (n.equals(sta)) {
					if (n.getRuleType() == null) {
						n.setRemark(n.getRemark() + sta.getRemark() + ";");
					} else {
						n.setRemark(n.getRemark() + ";" + sta.getRemark());
					}
					flag = true;
					break;
				}
			}
			if (!flag) {
				sta.setRemark(detailid);
				boxList.add(sta);
			}

		}
		return boxList;
	}

	@RequestMapping(method = RequestMethod.POST, value = "drugCheck")
	@ResponseBody
	public void drugCheck(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			TokenStreamException, RecognitionException, MapperException,
			CheckException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		String[] jsonArr = request.getParameterValues("datas[]");
		List<PrescribeCell> cellList = new ArrayList<PrescribeCell>();
		JSONArray jsonarray = JSONArray.fromObject(jsonArr);
		// 把jsonarray转成jsonobject
		JSONObject baseobject = (JSONObject) jsonarray.get(0);
		PatientInfo patientInfo = new PatientInfo();
		DateUtils.now();

		if (baseobject.getString("age").equals("1")) {
			// 少于16岁的儿童年龄从14岁转化成出生年月
			patientInfo.setBirthday(DateUtils.nextMonths(DateUtils.now(),
					-14 * 12));
		} else if (baseobject.getString("age").equals("2")
				|| baseobject.getString("age").equals("0")) {
			// 成人按25岁转化成出生年月
			patientInfo.setBirthday(DateUtils.nextMonths(DateUtils.now(),
					-25 * 12));
		} else if (baseobject.getString("age").equals("3")) {
			// 老人按52岁转化成出生年月
			patientInfo.setBirthday(DateUtils.nextMonths(DateUtils.now(),
					-52 * 12));

		}else{
			
		}
		
		
		
		
		patientInfo.setCureType(CureTypeEnum.OUT_PATIENT);
		if (baseobject.getString("male").equals("0")) {
			patientInfo.setGender(GenderEnum.MALE);
		} else {
			patientInfo.setGender(GenderEnum.FEMALE);
			patientInfo.setGestateTime(0);
		}
		
		if(baseobject.getString("gestate").equals("0")){
			patientInfo.setGender(GenderEnum.FEMALE);
			patientInfo.setGestate(true);
			patientInfo.setGestateTime(3L);
		}	
		if(baseobject.getString("suckling").equals("0")){
				patientInfo.setSuckling(true);
		}
		
		
		patientInfo.setName("某某");
		patientInfo.setSerialNo("12345");
		if (isNum(baseobject.getString("weight"))) {
			patientInfo.setWeight(Integer.parseInt(baseobject
					.getString("weight")));
		}
		if (isNum(baseobject.getString("height"))) {
			patientInfo.setHeight(Integer.parseInt(baseobject
					.getString("height")));
		}
		if (baseobject.getString("isLiver").equals("0")) {
			patientInfo.setLiver(false);
		} else if (baseobject.getString("isLiver").equals("1")) {
			patientInfo.setLiver(true);
		} else if (baseobject.getString("isLiver").equals("2")) {
			patientInfo.setCliver(true);
		}
		patientInfo.setGfr(Long.parseLong(baseobject.getString("gfr")));

		// 从第2条json组开始才是药品数据
		for (int i = 1; i < jsonarray.size(); i++) {
			JSONObject jsonobject = (JSONObject) jsonarray.get(i);
			PrescribeCell cell = new PrescribeCell();

			cell.setGroupNo(1);
			// 药品的id为空应该去数据查询药品是否存在
			String[] basicdrug = jsonobject.getString("drugname").split("_");
			
			
			if (isNum(basicdrug[0])) {
				/*basicDrugDataService a = this.basicDrugCheckService.get(Long
						.parseLong(basicdrug[0]));
				cell.setBasicDrugCheck(a);*/
				System.err.println(basicdrug[0]);
				BasicDrugData b = basicDrugDataService.get(Long
						.parseLong(basicdrug[0]));
				cell.setBasicDrugData(b);
				if (basicdrug.length > 1) {
					cell.setDrugName(basicdrug[1]);
				} else {
					cell.setDrugName(basicdrug[0]);
				}

			} else {
				if (basicdrug[0].equals("")) {
					cell.setBasicDrugCheck(null);
				} else {
					/*BasicDrugCheck b = this.basicDrugCheckService
							.findByName(basicdrug[0]);
					*/
					BasicDrugData b = basicDrugDataService.findByName(basicdrug[0]);
					cell.setBasicDrugData(b);
					if (b != null) {
						cell.setBasicDrugData(b);
					} else {
						cell.setBasicDrugData(null);
					}
				}
				cell.setDrugName(basicdrug[0]);
			}
			cell.setAdviceType(AdviceTypeEnum.TEMP_ADVICE);
			// 用法如果为空应该去数据查询用法是否存在
			String usage = jsonobject.getString("usage");
			if (isNum(usage)) {
				Usage u = this.usageService.get(Long.parseLong(usage));
				cell.setUsage(u);
			} else {
				if (usage.equals("")) {
					cell.setUsage(null);
				} else {
					Usage u = this.usageService.findByName(usage);
					if (u != null) {
						cell.setUsage(u);
					} else {
						cell.setUsage(null);
					}
				}
			}
			cell.setDaynum(null); // 天数
			String frequery = jsonobject.getString("frequery");
			// 频次
			if (isNum(frequery)) {
				Frequency f = this.frequencyService.get(Long
						.parseLong(frequery));
				cell.setFrequency(f);
			} else {
				if (frequery.equals("")) {
					cell.setFrequency(null);
				} else {
					Frequency f = this.frequencyService.findByName(frequery);
					if (f != null) {
						cell.setFrequency(f);
					} else {
						cell.setFrequency(null);
					}
				}
			}
			// 如果用量与单位为手工输入则把单位分出来
			String dosage = jsonobject.getString("dosage");
			if (!StringUtils.isNullOrBlank(dosage)) {
				if (isNum(dosage)) {
					cell.setDosage(Double.parseDouble(dosage));
				} else {
					String unit = splitNotNumber(dosage);
					String d = dosage.replace(unit, "");
					if (isNum(d)) {
						cell.setDosage(Double.parseDouble(d));
						cell.setUnit(unit);
					} else {
						cell.setDosage(0);
					}

				}
			} else {
				cell.setDosage(0);
			}
			// 添加
			cellList.add(cell);
		}

		/*
		 * patientInfo.setBirthday(DateUtils.toDate("1973-06-13",
		 * DateUtils.YEAR_MONTH_DAY));
		 * patientInfo.setCureType(CureTypeEnum.OUT_PATIENT);
		 * patientInfo.setGender(GenderEnum.FEMALE);
		 * patientInfo.setGestateTime(3L); patientInfo.setName("������");
		 * patientInfo.setSerialNo("12345"); patientInfo.setWeight(56);
		 * patientInfo.setHeight(167);
		 */

		PrescribeBasicInfo pBasicInfo = new PrescribeBasicInfo();
		pBasicInfo.setCreateTime(DateUtils.now());
		pBasicInfo.setDiseaseCode("A01.101");
		pBasicInfo.setDiseaseName("流行性感冒");
		pBasicInfo.setDivisionCode("dept-2046");
		pBasicInfo.setDivisionName("外科");
		pBasicInfo.setMedicalerName("某医生");
		pBasicInfo.setMedicalerNo("m-3721");
		pBasicInfo.setMedicalerTitle(TitleEnum.MainMedicaler);
		pBasicInfo.setPrescribeNo("1314");
		pBasicInfo.setTotalAmount(BigDecimal.valueOf(9.98));
		JsonArray array = new JsonArray();
		VerifyReport v = prescriptionVerifier.verify(patientInfo, pBasicInfo,
				cellList, "");
		if (v != null) {
			if (CollectionUtils.isNotEmpty(v.getOriginReport())) {
				for (ReportDetail report : v.getOriginReport()) {
					JsonObject obj = new JsonObject();
					obj.addProperty("type", report.getRuleType().getText()
							+ "：");
					obj.addProperty("grade", report.getGrade().getText());
					obj.addProperty("result", report.getRemark());
					array.add(obj);
				}
				
				
			} else {
				JsonObject obj = new JsonObject();
				obj.addProperty("type", "");
				obj.addProperty("grade", "提示");
				obj.addProperty("result", "未发现配伍禁忌和重复用药!");
				array.add(obj);
				
				
			}
		}
		out.print(array);
	}

	// 取字条串中的数据，返回第一个数字
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	// 取字符串最后面的非数字字符串
	public static String splitNotNumber(String content) {
		Pattern pattern = Pattern.compile("\\D+");
		StringBuilder s = new StringBuilder(content);
		Matcher matcher = pattern.matcher(s.reverse());
		while (matcher.find()) {
			StringBuilder st = new StringBuilder(matcher.group(0));
			return st.reverse().toString();
		}
		return "";
	}

	public static void main(String[] args) {
		System.err.println(splitNotNumber("12.3slb"));
		System.err.println(isNum("12.334"));
	}

	
	
	@Resource
	BasicDrugDataDao bdataDao;
	@RequestMapping("updatedrugrelationship")
	@ResponseBody
	public void updatedrugrelationship(String datas,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<BasicDrugRelationship> list = bRelationshipService.findAll();
		for (int i = 0; i < list.size(); i++) {
			BasicDrugRelationship b = new BasicDrugRelationship();
		//	BasicDrugCheck s = new BasicDrugCheck();
			b = list.get(i);
			//s = basicDrugCheckService.findByName(b.getProductName());
			//b.setBasicDrugCheck(s);
			b.setCommodityNameSymbol(StringManager.getFirstPinYin(b
					.getCommodityName()));
			b.setProductNameSymbol(StringManager.getFirstPinYin(b
					.getProductName()));
			bRelationshipService.update(b);
		}
		/*List<BasicDrugData> list2 =bdataDao.findAll();
    	for(BasicDrugData l:list2){
    		l.setSymbol(StringManager.getFirstPinYin(l.getDrugName()));
    		bdataDao.update(l);
    	}
		*/
		
		JsonArray array = new JsonArray();
		JsonObject obj = new JsonObject();
		obj.addProperty("type", "");
		obj.addProperty("grade", "提示");
		obj.addProperty("result", "更新完成!");
		array.add(obj);
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=gbk");
		out = response.getWriter();
		out.print(array);

	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/drugtext")
	@ResponseBody
	public void drugtext(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			TokenStreamException, RecognitionException, MapperException,
			CheckException {
		
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			Integer pageNum = baseobject.getInt("pageNum");
			Integer pagesize = 15;
			List<User> l = this.userService.findDoctor(pageNum, pagesize);
			String userId =baseobject.getString("userId");
			JSONArray jsonArray = new JSONArray();
			for (User u : l) {
				JSONObject json = new JSONObject();
				if(StringUtils.isNullOrBlank(userId)){
					json.put("status", "0");
				}else{
					
				int count=	ownDoctorService.countByProperty("user", new User(Long.parseLong(userId)));
					if(count>0){
						json.put("status", "1");
					}else{
						json.put("status", "0");
					}
				}
				
				json.put("doctorId", u.getUserId());
				json.put("doctorName", u.getUserName());
				json.put("professional", u.getUserType());
				json.put("description", u.getInrtoduction());
				json.put("img", u.getImg());
				jsonArray.add(json);
			}
			outjson.put("code", "1000");
			outjson.put("msg", "success");
			outjson.put("data", jsonArray);
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "加载失败");
			outjson.put("data", "");
		}
		out.print(outjson);
		}
	
	

}
