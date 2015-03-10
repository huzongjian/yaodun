package com.springmvc.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.relationdrug.domain.LotteryResult;
import com.springmvc.relationdrug.service.impl.LotteryResultServiceImpl;

@Controller
@RequestMapping("LotteryResult")
public class LotteryResultController {
	@Resource
	public LotteryResultServiceImpl lotteryResultService;

	public String Save(LotteryResult result, HttpServletRequest request) {
		long i = lotteryResultService.save(result);
		if (i == 1) {
			return "/";
		} else {
			request.setAttribute("result", "failure");
			return "#";
		}

	}

}
