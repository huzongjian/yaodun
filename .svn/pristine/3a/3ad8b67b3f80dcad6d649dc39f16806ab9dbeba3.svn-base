package com.springmvc.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.springmvc.relationdrug.domain.User;

@Controller
@RequestMapping("login")
public class LoginController1 {
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response,
			User command) throws IOException {

		String userjson = "{\"username\":\"username\"}";
		PrintWriter out = null;
		response.setContentType("applicaiton/json");
		out = response.getWriter();
		out.write(userjson);

	}

	@RequestMapping("upload")
	public String upload(@RequestParam("filename") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException {

		System.err.println("fileName" + file.getOriginalFilename());
		if (!file.isEmpty()) {
			FileOutputStream os = new FileOutputStream("D:/"
					+ new Date().getTime() + file.getOriginalFilename());
			InputStream in = file.getInputStream();
			int b = 0;
			while ((b = in.read()) != -1) {
				os.write(b);
			}
			os.flush();
			os.close();
			in.close();

		}

		request.setAttribute("command", "upload SUCCESS");
		return "/index/index";
	}

	@RequestMapping("upload2")
	public String upload2(HttpServletRequest request) throws IOException {

		CommonsMultipartResolver cs = new CommonsMultipartResolver(request
				.getSession().getServletContext());
		if (cs.isMultipart(request)) {
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
			Iterator<String> item = mr.getFileNames();
			while (item.hasNext()) {
				MultipartFile file = mr.getFile((String) item.next());
				String filename = file.getOriginalFilename();
				String path = "d:/" + filename;
				File local = new File(path);
				file.transferTo(local);
			}
		}

		request.setAttribute("command", "upload SUCCESS");
		// return "/index/index";
		return null;
	}

	@RequestMapping("login1")
	public String login2(HttpServletRequest request,
			HttpServletResponse response, User command) {
		String username = command.getUserName();
		String password = command.getPassword();

		request.setAttribute("command", "LOGIN SUCCESS, " + username);
		request.setAttribute("command2", "LOGIN SUCCESS, " + password);
		return "/index/index";
	}

}