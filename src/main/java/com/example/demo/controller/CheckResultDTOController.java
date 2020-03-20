package com.example.demo.controller;

import com.example.demo.common.ResultDTO;

import com.example.demo.service.CheckResultDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongqiang.zhu
 * @date 2020/3/20 0:35
 */
@RestController
@RequestMapping("/project")
public class CheckResultDTOController {

	@Autowired
	private CheckResultDTOService checkResultDTOService;

	@RequestMapping(name = "校验ResultDTO", path = "/check", method = RequestMethod.GET)
	public ResultDTO getPersonByDateFormat(){

		return checkResultDTOService.checkUserInfo();
	}
}
