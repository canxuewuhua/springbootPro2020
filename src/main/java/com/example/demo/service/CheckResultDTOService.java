package com.example.demo.service;

import com.example.demo.common.CodeMsg;
import com.example.demo.common.ResultDTO;
import com.example.demo.domain.UserVO;
import com.example.demo.exception.ServiceException;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yongqiang.zhu
 * @date 2020/3/20 0:36
 */

@Service
@Slf4j
public class CheckResultDTOService {

	public ResultDTO checkUserInfo(){
		ResultDTO resultDTO = getUserInfo();
		if (resultDTO.checkSuccess()){
			log.info("getUserInfo方法请求成功！！");
		}else{
			log.error("getUserInfo方法已经请求失败了");
		}
		return ResultUtils.success();
	}

	private ResultDTO getUserInfo(){
		try{
			if (false){
				UserVO userVO = new UserVO();
				userVO.setStName("zhangsan");
				ResultDTO base = new ResultDTO();
				base.setCode(100000);
				base.setMessage("请求成功！！");
				base.setData(userVO);
				return base;
			}else{
				log.error("异常情况下打印该日期报错！！");
				ResultDTO base = new ResultDTO();
				base.setCode(20250);
				base.setMessage("请求异常！！");
				return base;
			}
		}catch (Exception e){
			throw  new ServiceException(CodeMsg.DATE_FORMAT_ERROR);
		}
	}
}
