package com.springmvc.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;
import javax.xml.rpc.ServiceException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.springmvc.base.util.DateUtils;
import com.springmvc.base.util.EntityToJson;
import com.springmvc.base.util.StringUtils;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.BasicDrugData;
import com.springmvc.relationdrug.domain.CheckUpdate;
import com.springmvc.relationdrug.domain.FeedBack;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.KnowledgeReply;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.OwnKnowledge;
import com.springmvc.relationdrug.domain.ThirdParty;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.domain.UserQuestion;
import com.springmvc.relationdrug.domain.UserQuestionReply;
import com.springmvc.relationdrug.service.CheckUpdateService;
import com.springmvc.relationdrug.service.FeedBackService;
import com.springmvc.relationdrug.service.KnowledgeReplyService;
import com.springmvc.relationdrug.service.KnowledgeService;
import com.springmvc.relationdrug.service.OwnDoctorService;
import com.springmvc.relationdrug.service.OwnKnowledgeService;
import com.springmvc.relationdrug.service.ThirdPartyService;
import com.springmvc.relationdrug.service.UserQuestionReplyService;
import com.springmvc.relationdrug.service.UserQuestionService;
import com.springmvc.relationdrug.service.impl.BasicDrugCheckService;
import com.springmvc.relationdrug.service.impl.PrescriptionVerifier;
import com.springmvc.relationdrug.service.impl.UserServiceImpl;

@Controller
@RequestMapping("User")
public class UserController {

	@Resource
	private UserServiceImpl userService;
	@Resource
	private PrescriptionVerifier prescriptionVerifier;
	@Resource
	private BasicDrugCheckService basicDrugCheckService;
	@Resource
	private OwnDoctorService ownDoctorService;
	@Resource
	private KnowledgeService knowledgeService;
	@Resource
	private OwnKnowledgeService ownKnowledgeService;
	@Resource
	public UserQuestionService userQuestionService;
	@Resource
	public UserQuestionReplyService userQuestionReplyService;
	@Resource
	public KnowledgeReplyService knowledgeReplyService;
	@Resource
	public FeedBackService feedBackService;
	@Resource
	public CheckUpdateService checkUpdateService;
	@Resource
	public ThirdPartyService thirdPartyService;

	@RequestMapping("add1")
	@ResponseBody
	private void addUser1(String matchInfo, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServiceException {
		// userService.save(user);
		// return "user/success";
		// String userjson =
		// "{\"id\":\"username\",\"value\":\"value\",\"info\":\"info\"}";
		// Long id =1L;
		List<BasicDrugCheck> listbasicDrugCheck = basicDrugCheckService
				.findByNameAndSymbol(matchInfo);
		JsonArray array = new JsonArray();
		JsonObject obj = new JsonObject();
		obj.addProperty("id", listbasicDrugCheck.get(0).getBasicDrugCheckId());
		obj.addProperty("value", listbasicDrugCheck.get(0).getDrugName());
		obj.addProperty("info", listbasicDrugCheck.get(0).getSymbol());
		JsonObject obj1 = new JsonObject();
		obj1.addProperty("id", listbasicDrugCheck.get(1).getBasicDrugCheckId());
		obj1.addProperty("value", listbasicDrugCheck.get(1).getDrugName());
		obj1.addProperty("info", listbasicDrugCheck.get(1).getSymbol());
		array.add(obj);
		array.add(obj1);
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		out.print(array);

	}

	@RequestMapping("/Login")
	public String Login(User user, HttpServletRequest request) {

		Md5PasswordEncoder md = new Md5PasswordEncoder();
		user.setPassword(md.encodePassword(user.getPassword(), null));
		User u = userService.login(user);
		request.setAttribute("user", u);
		if (u == null) {
			request.setAttribute("result", "帐号或者密码错误！");
			return "redirect:..";

		} else {
			return "mainCheck";
		}
	}

	/**
	 * 
	 * @Title: changePassword
	 * @Description:修改密码
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-8 下午1:45:43
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject outobj = new JSONObject();
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject outjson = new JSONObject();
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			Md5PasswordEncoder md = new Md5PasswordEncoder();
			String userId = baseobject.getString("userId");
			String oldPassowrd = baseobject.getString("oldPassword");
			if (StringUtils.isNullOrBlank(userId)) {
				outobj.put("code", "998");
				outobj.put("msg", "");
				outobj.put("data", "");
			} else {
				User user = userService.get(Long.parseLong(userId));
				user.setPassword(md.encodePassword(oldPassowrd, null));
				User u = userService.login(user);
				if (u == null) {
					outobj.put("code", "1003");
					outobj.put("msg", "旧密码错误");
					outobj.put("data", "");
				} else {
					user.setPassword(md.encodePassword(
							baseobject.getString("passWord"), null));
					userService.update(user);
					JSONObject userjson = new JSONObject();
					EntityToJson.entityToJSON(user, userjson);
					outobj.put("code", "1000");
					outobj.put("msg", "success");
					outobj.put("data", userjson);
				}

			}
		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "修改失败");
			outobj.put("data", "");
		}
		out.print(outobj);
	}

	/**
	 * 
	 * @Title: LoginInterface
	 * @Description: 登陆
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-8 下午1:46:03
	 */
	@RequestMapping(value = "/loginInterface", method = RequestMethod.POST)
	@ResponseBody
	public void LoginInterface(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject outobj = new JSONObject();
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			User user = new User();
			user.setUserName(baseobject.getString("userName"));
			user.setPassword(baseobject.getString("passWord"));
			Md5PasswordEncoder md = new Md5PasswordEncoder();
			user.setPassword(md.encodePassword(user.getPassword(), null));
			User u = userService.login(user);
			JSONObject userjosn = new JSONObject();
			EntityToJson.entityToJSON(u, userjosn);
			if (u == null) {
				outobj.put("code", "1002");
				outobj.put("msg", "用户名或者密码错误");
				outobj.put("data", "");
			} else {
				outobj.put("code", "1000");
				outobj.put("msg", "success");
				outobj.put("data", userjosn);
			}

		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "登陆失败");
			outobj.put("data", "");
		}
		out.print(outobj);

	}

	/**
	 * 
	 * @Title: AddInterface
	 * @Description: 注册
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-8 下午1:46:17
	 */
	@RequestMapping(value = "/addInterface", method = RequestMethod.POST)
	@ResponseBody
	public void AddInterface(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject outobj = new JSONObject();
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			User user = new User();
			user.setUserName(baseobject.getString("userName"));
			user.setEmail(baseobject.getString("email"));
			user.setPassword(baseobject.getString("passWord"));
			user.setTelephone(baseobject.getString("telephone"));
			Date birthday = DateUtils.toDate(baseobject.getString("birthday"));
			String gender = baseobject.getString("sex");
			user.setGender(gender);
			user.setBirthday(birthday);
			Md5PasswordEncoder md = new Md5PasswordEncoder();
			user.setPassword(md.encodePassword(user.getPassword(), null));
			String status = userService.addUser(user);
			if (status.equals("failure")) {
				outobj.put("code", "1002");
				outobj.put("msg", "用户名已注册");
				outobj.put("data", "");
			} else {
				JSONObject userjson = new JSONObject();
				EntityToJson.entityToJSON(user, userjson);
				outobj.put("code", "1000");
				outobj.put("msg", "success");
				outobj.put("data", userjson);
			}
		} catch (Exception e) {
			outobj.put("code", "1001");
			outobj.put("msg", "注册失败");
			outobj.put("data", "");
		}
		out.print(outobj);
	}

	/**
	 * 
	 * @Title: DoctorList
	 * @Description: 药师列表
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-2 上午11:30:50
	 */

	@RequestMapping(value = "/doctorList", method = RequestMethod.POST)
	@ResponseBody
	public void DoctorList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
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
			String userId = baseobject.getString("userId");
			JSONArray jsonArray = new JSONArray();
			for (User u : l) {
				JSONObject json = new JSONObject();
				if (StringUtils.isNullOrBlank(userId)) {
					json.put("status", "0");
				} else {
					int count = ownDoctorService.countByProperty("user",
							new User(Long.parseLong(userId)));
					if (count > 0) {
						json.put("status", "1");
					} else {
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

	/**
	 * 
	 * @Title: AttentionDoctor
	 * @Description: 关注 医师
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-2 上午11:31:05
	 */
	@RequestMapping(value = "/attentionDoctor", method = RequestMethod.POST)
	@ResponseBody
	public void AttentionDoctor(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				User doctor = new User(Long.parseLong(baseobject
						.getString("doctorId")));
				String operation = baseobject.getString("operation");
				if (operation.equals("1")) {
					OwnDoctor ownDoctor = new OwnDoctor();
					ownDoctor.setUser(user);
					ownDoctor.setDoctor(doctor);
					ownDoctor.setCreatetime(DateUtils.now());
					String[] pro = { "user", "doctor" };
					Object[] oc = { user, doctor };
					List<OwnDoctor> o = ownDoctorService.findByPropertys(pro,
							oc);
					if (o.isEmpty()) {
						ownDoctorService.save(ownDoctor);
						outjson.put("code", "1000");
						outjson.put("msg", "success");
						outjson.put("data", "");
					} else {
						outjson.put("code", "1002");
						outjson.put("msg", "failure");
						outjson.put("data", "");
					}

				} else if (operation.equals("0")) {
					ownDoctorService.cancelAttentiondoctor(user, doctor);
					outjson.put("code", "1000");
					outjson.put("msg", "success");
					outjson.put("data", "");
				}

			}

		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "操作失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: AttentionDoctorList
	 * @Description: 所关注的医生信息列表
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-2 下午1:13:56
	 */
	@RequestMapping(value = "/attentionDoctorList", method = RequestMethod.POST)
	@ResponseBody
	public void AttentionDoctorList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "");
				outjson.put("data", "");
			} else {
				User user = new User(baseobject.getLong("userId"));
				Integer pageNum = baseobject.getInt("pageNum");
				Integer pagesize = 15;
				List<OwnDoctor> l = ownDoctorService.findbyUser(user, pageNum,
						pagesize);
				JSONArray jsonArray = new JSONArray();
				for (OwnDoctor u : l) {
					JSONObject json = new JSONObject();
					User doctor = u.getDoctor();
					json.put("doctorId", doctor.getUserId());
					json.put("doctorName", doctor.getUserName());
					json.put("professional", doctor.getUserType());
					json.put("description", doctor.getInrtoduction());
					json.put("img", doctor.getImg());
					json.put("status", "1");
					jsonArray.add(json);
				}
				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", jsonArray);
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "加载失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: KnowledgeList
	 * @Description: 文章列表
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-2 下午3:29:41
	 */

	@RequestMapping(value = "/knowledgeList", method = RequestMethod.POST)
	@ResponseBody
	public void KnowledgeList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String knowledgeTpye = baseobject.getString("knowledgeType");
			Integer pageNum = baseobject.getInt("pageNum");
			Integer pagesize = 15;
			List<Knowledge> list = new ArrayList<Knowledge>();
			if (knowledgeTpye.equals("0")) {

				list = this.knowledgeService.findAll(pageNum, pagesize);
			} else {
				list = this.knowledgeService.findByType(knowledgeTpye, pageNum,
						pagesize);
			}
			int countDiscuss = 0;
			JSONArray jsonArray = new JSONArray();
			for (Knowledge k : list) {
				JSONObject json = new JSONObject();
				countDiscuss = this.knowledgeReplyService.countByProperty(
						"knowledge", k);
				EntityToJson.entityToJSON(k, json);
				json.put("createtime", k.getCreatetime().toString());
				json.put("countDiscuss", countDiscuss);
				jsonArray.add(json);
			}
			outjson.put("code", "1000");
			outjson.put("msg", "success");
			outjson.put("data", jsonArray);
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "加载失败");
			outjson.put("data", e.getMessage());
		}
		out.print(outjson);
	}

	

	/**
	 * 
	 * @Title: KnowledgeDetail
	 * @Description:文章详细包括收藏人数，是否已经收藏
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-9 下午2:55:07
	 */
	@RequestMapping(value = "/knowledgeDetail", method = RequestMethod.POST)
	@ResponseBody
	public void KnowledgeDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String knowledgeId = baseobject.getString("knowledgeId");
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "");
				outjson.put("data", "");
			} else {
				Knowledge knowledge = knowledgeService.get(Long
						.parseLong(knowledgeId));
				int countAttention = 0;
				int status = 0;
				if (knowledge == null) {
					outjson.put("code", "1002");
					outjson.put("msg", "文章不存在");
					outjson.put("data", "");
				} else {
					JSONObject json = new JSONObject();
					countAttention = this.ownKnowledgeService.countByProperty(
							"knowledge", knowledge);
					User user = new User(Long.parseLong(userId));
					String[] pro = { "user", "knowledge" };
					Object[] oc = { user, knowledge };
					status = this.ownKnowledgeService.countByPropertys(pro, oc);
					json.put("title", knowledge.getTitle());
					json.put("description", knowledge.getDescription());
					json.put("countAttention", countAttention);
					if (status != 0) {
						json.put("status", "1");
					} else {
						json.put("status", "0");
					}
					json.put("createtime", knowledge.getCreatetime().toString());
					outjson.put("code", "1000");
					outjson.put("msg", "success");
					outjson.put("data", json);

				}
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "加载失败");
			outjson.put("data", e.getMessage());
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: DoctorDetail
	 * @Description: 提问详细，包括问题的回复。
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-9 下午2:54:23
	 */
	@RequestMapping(value = "/questionDetail", method = RequestMethod.POST)
	@ResponseBody
	public void DoctorDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			Long quesitonId = baseobject.getLong("quesitonId");
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "");
				outjson.put("data", "");
			} else {
				UserQuestion userQuestion = userQuestionService.get(quesitonId);
				if (userQuestion == null) {
					outjson.put("code", "1002");
					outjson.put("msg", "问题不存在");
					outjson.put("data", "");
				} else {
					JSONObject json = new JSONObject();
					List<UserQuestionReply> u = userQuestionReplyService
							.findByProperty("userQuestion", userQuestion);
					json.put("question", userQuestion.getDescription());
					JSONArray jsonArray = new JSONArray();
					for (UserQuestionReply x : u) {
						JSONObject j = new JSONObject();
						EntityToJson.entityToJSON(x, j);
						if (StringUtils
								.isNullOrBlank(x.getUser().getUserType())) {
							j.put("userType", "0");
						} else {
							j.put("userType", "1");
						}
						j.put("replyTime", x.getCreatetime().toString());
						jsonArray.add(j);
					}
					json.put("questionreply", jsonArray);
					User doctor = userQuestion.getDoctor();
					json.put("createtime", userQuestion.getCreatetime()
							.toString());
					json.put("doctorId", doctor.getUserId());
					json.put("professional", doctor.getUserType());
					json.put("doctorName", doctor.getUserName());
					json.put("doctorImg", doctor.getImg());
					json.put("doctorDescription", doctor.getInrtoduction());
					String[] proper = { "user", "doctor" };
					Object[] name = { userQuestion.getUser(), doctor };
					List<OwnDoctor> s = ownDoctorService.findByPropertys(
							proper, name);
					if (s.isEmpty()) {
						json.put("attionStatus", "0");
					} else {
						json.put("attionStatus", "1");
					}
					outjson.put("code", "1000");
					outjson.put("msg", "success");
					outjson.put("data", json);

				}
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "加载失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: AttentionKnowledge
	 * @Description: 收藏或取消收藏文章
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-2 下午3:29:14
	 */

	@RequestMapping(value = "/attentionKnowledge", method = RequestMethod.POST)
	@ResponseBody
	public void AttentionKnowledge(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "请登陆");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				Knowledge knowledge = new Knowledge(Long.parseLong(baseobject
						.getString("knowledgeId")));
				String operation = baseobject.getString("operation");
				if (com.springmvc.base.util.StringUtils
						.isNullOrBlank(operation)) {
					OwnKnowledge ownKnowledge = new OwnKnowledge();
					ownKnowledge.setUser(user);
					ownKnowledge.setKnowledge(knowledge);
					ownKnowledge.setCreatetime(DateUtils.now());
					ownKnowledgeService.save(ownKnowledge);
				} else {
					ownKnowledgeService.cancelAttentiondoctor(user, knowledge);
				}

				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", "");
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "操作失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	@RequestMapping(value = "/attentionKnowledgeList", method = RequestMethod.POST)
	@ResponseBody
	public void AttentionKnowledgeList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());

			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "请登陆");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				Integer pageNum = baseobject.getInt("pageNum");
				Integer pagesize = 15;
				List<OwnKnowledge> l = ownKnowledgeService.findbyUser(user,
						pageNum, pagesize);
				JSONArray jsonArray = new JSONArray();
				for (OwnKnowledge u : l) {
					JSONObject json = new JSONObject();
					Knowledge k = u.getKnowledge();
					EntityToJson.entityToJSON(k, json);
					json.put("createtime", k.getCreatetime().toString());
					jsonArray.add(json);
				}
				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", jsonArray);
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "关注失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: OwnConsultList
	 * @Description: 我的咨询列表
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-2 下午4:00:20
	 */
	@RequestMapping(value = "/ownConsultList", method = RequestMethod.POST)
	@ResponseBody
	public void OwnConsultList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String userId = baseobject.getString("userId");
			List<UserQuestion> l = new ArrayList<UserQuestion>();
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "请登陆");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				Integer pageNum = baseobject.getInt("pageNum");
				Integer pagesize = 15;
				l = userQuestionService.findbyUser(user, pageNum, pagesize);
				JSONArray jsonArray = new JSONArray();
				for (UserQuestion u : l) {
					JSONObject json = new JSONObject();
					EntityToJson.entityToJSON(u, json);
					String[] x = { "userQuestion" };
					List<UserQuestionReply> uqr = userQuestionReplyService
							.findByProperty("userQuestion", u);
					if (uqr.isEmpty()) {
						json.put("replyStatus", "0");
						json.put("replyTime", "");
					} else {
						if (StringUtils.isNullOrBlank((uqr.get(uqr.size() - 1)
								.getUser().getUserType()))) {
							json.put("replyStatus", "0");
							json.put("replyTime", "");
						} else {
							json.put("replyStatus", "1");
							json.put("replyTime", uqr.get(uqr.size() - 1)
									.getCreatetime().toString());
						}
					}
					jsonArray.add(json);
				}
				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", jsonArray);
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "加载失败");
			outjson.put("data", e.getMessage());
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: QuestionReplyList
	 * @Description: 问题回复列表
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-2 下午4:40:36
	 */

	@RequestMapping(value = "/questionReplyList", method = RequestMethod.POST)
	@ResponseBody
	public void QuestionReplyList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			UserQuestion userQuestion = new UserQuestion(
					baseobject.getLong("questionId"));
			Integer pageNum = baseobject.getInt("pageNum");
			Integer pagesize = 15;
			List<UserQuestionReply> l = userQuestionReplyService
					.findbyUserQuestion(userQuestion, pageNum, pagesize);
			JSONArray jsonArray = new JSONArray();
			for (UserQuestionReply u : l) {
				JSONObject json = new JSONObject();
				JSONObject json1 = new JSONObject();
				JSONObject json2 = new JSONObject();
				JSONObject json3 = new JSONObject();
				EntityToJson.entityToJSON(u, json);
				UserQuestion uQuestion = u.getUserQuestion();
				EntityToJson.entityToJSON(uQuestion, json1);
				EntityToJson.entityToJSON(u.getUser(), json2);
				EntityToJson.entityToJSON(u.getUserQuestion().getUser(), json3);
				json1.put("createtime", uQuestion.getCreatetime().toString());
				json.put("userQuestion", json1);
				json.put("user", json2);
				json.put("askuser", json3);
				json.put("createtime", u.getCreatetime().toString());
				jsonArray.add(json);
			}
			outjson.put("code", "1000");
			outjson.put("msg", "success");
			outjson.put("data", jsonArray);
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "加载失败");
			outjson.put("data", e.getMessage());
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: QuestionReply
	 * @Description: 回复问题
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-8 下午1:47:56
	 */
	@RequestMapping(value = "/questionReply", method = RequestMethod.POST)
	@ResponseBody
	public void QuestionReply(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			UserQuestion userQuestion = new UserQuestion(
					baseobject.getLong("questionId"));
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "请登陆");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				String reply = baseobject.getString("reply");
				Date createtime = DateUtils.now();
				UserQuestionReply userQuestionReply = new UserQuestionReply();
				userQuestionReply.setCreatetime(createtime);
				userQuestionReply.setUser(user);
				userQuestionReply.setUserQuestion(userQuestion);
				userQuestionReply.setReply(reply);
				userQuestionReplyService.save(userQuestionReply);
				JSONObject j = new JSONObject();
				EntityToJson.entityToJSON(userQuestionReply, j);
				j.put("sendTime",
						DateUtils.toString(userQuestionReply.getCreatetime()));
				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", j);
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "评论失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	@RequestMapping(value = "/questionSave", method = RequestMethod.POST)
	@ResponseBody
	public void QuestionSave(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String questionDescription = baseobject.getString("description");
			String userId = baseobject.getString("userId");
			String doctorId = baseobject.getString("doctorId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "请登陆");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				UserQuestion userQuestion = new UserQuestion();
				User doctor = new User();
				if(StringUtils.isNullOrBlank(doctorId)){
					//随机产生一个医生
					List<User> doctorlist =userService.findAllDoctor();
					int x =(int)(Math.random()*doctorlist.size());
					doctor =doctorlist.get(x);
				}else{
					doctor =new User(Long.parseLong(doctorId));
				}
				Date createtime = DateUtils.now();
				userQuestion.setCreatetime(createtime);
				userQuestion.setUser(user);
				userQuestion.setDoctor(doctor);
				userQuestion.setDescription(questionDescription);
				userQuestionService.save(userQuestion);
				JSONObject j = new JSONObject();
				j.put("questionId", userQuestion.getQuestionId());
				j.put("description", userQuestion.getDescription());
				j.put("sendTime",
						DateUtils.toString(userQuestion.getCreatetime()));
				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", j);
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "评论失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: KnowledgeReply
	 * @Description: 评论文章
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-8 下午1:48:20
	 */
	@RequestMapping(value = "/knowledgeReply", method = RequestMethod.POST)
	@ResponseBody
	public void KnowledgeReply(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			Knowledge knowledge = new Knowledge(Long.parseLong(baseobject
					.getString("knowledgeId")));
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "请登陆");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				String reply = baseobject.getString("discuss");
				Date createtime = DateUtils.now();
				KnowledgeReply knowledgeReply = new KnowledgeReply();
				knowledgeReply.setCreatetime(createtime);
				knowledgeReply.setUser(user);
				knowledgeReply.setKnowledge(knowledge);
				knowledgeReply.setReply(reply);
				knowledgeReplyService.save(knowledgeReply);
				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", "");
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "评论失败");
			outjson.put("data", e.getMessage());
		}
		out.print(outjson);
	}

	/**
	 * 
	 * @Title: feedBack
	 * @Description: 反馈
	 * @param @param request
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author HuZongjian
	 * @date 2014-12-8 下午1:48:56
	 */
	@RequestMapping(value = "/feedBack", method = RequestMethod.POST)
	@ResponseBody
	public void feedBack(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String userId = baseobject.getString("userId");
			if (StringUtils.isNullOrBlank(userId)) {
				outjson.put("code", "998");
				outjson.put("msg", "请登陆");
				outjson.put("data", "");
			} else {
				User user = new User(Long.parseLong(userId));
				String description = baseobject.getString("description");
				Date createtime = DateUtils.now();
				FeedBack feedBack = new FeedBack();
				feedBack.setUser(user);
				feedBack.setDescription(description);
				feedBack.setCreatetime(createtime);
				this.feedBackService.save(feedBack);
				outjson.put("code", "1000");
				outjson.put("msg", "success");
				outjson.put("data", "");
			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "反馈失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	@RequestMapping(value = "/checkUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void CheckUpdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "gb2312"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String version = baseobject.getString("version");
			String appType = baseobject.getString("appType");
			List<CheckUpdate> ck = checkUpdateService.findByProperty("appType",
					appType);
			if (ck.isEmpty()) {
				outjson.put("code", "1002");
				outjson.put("msg", "无更新");
				outjson.put("data", "");
			} else {
				int lastCk = ck.size() - 1;
				if (ck.get(lastCk).getAppVersionNo().equals(version)) {
					outjson.put("code", "1002");
					outjson.put("msg", "无更新");
					outjson.put("data", "");
				} else {
					JSONObject ckjson = new JSONObject();
					EntityToJson.entityToJSON(ck.get(lastCk), ckjson);
					outjson.put("code", "1000");
					outjson.put("msg", "success");
					outjson.put("data", ckjson);
				}

			}
		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "检测失败");
			outjson.put("data", "");
		}
		out.print(outjson);
	}

	@RequestMapping(value = "/thirdPartyLogin", method = RequestMethod.POST)
	@ResponseBody
	public void ThirdPartyLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		response.setContentType("applicaiton/json;charset=UTF-8");
		out = response.getWriter();
		JSONObject outjson = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			JSONObject baseobject = JSONObject.fromObject(in.readLine());
			String openId = baseobject.getString("openId");
			String source = baseobject.getString("source");
			String[] propertys = { "openId", "source" };
			Object[] values = { openId, source };
			List<ThirdParty> tp = thirdPartyService.findByPropertys(propertys,
					values);
			User u = new User();
			if (tp.isEmpty()) {
				u.setUserName(openId);
				userService.save(u);
				ThirdParty tpu = new ThirdParty();
				tpu.setOpenId(openId);
				tpu.setSource(source);
				tpu.setUserId(u);
				thirdPartyService.save(tpu);
			} else {
				u = tp.get(0).getUserId();
			}
			JSONObject j = new JSONObject();
			EntityToJson.entityToJSON(u, j);
			outjson.put("code", "1000");
			outjson.put("msg", "success");
			outjson.put("data", j);

		} catch (Exception e) {
			outjson.put("code", "1001");
			outjson.put("msg", "登陆失败");
			outjson.put("data", e.getMessage());
		}
		out.print(outjson);
	}

	@RequestMapping("/add")
	private String addUser(User user, HttpServletRequest request,
			HttpServletResponse response) {
		Md5PasswordEncoder md = new Md5PasswordEncoder();
		user.setPassword(md.encodePassword(user.getPassword(), null));
		String status = userService.addUser(user);
		if (status.equals("failure")) {
			request.setAttribute("result", "帐号已注册！");
			return "account";
		} else {
			request.setAttribute("result", "注册成功!");
			return "login";
		}
	}

	@RequestMapping("/account")
	public String account() {
		return "account";
	}

	@RequestMapping("/loginOut")
	public String LoginOut() {
		return "login";
	}

}
