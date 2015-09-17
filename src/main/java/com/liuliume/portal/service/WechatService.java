package com.liuliume.portal.service;

import com.liuliume.portal.model.wechat.WechatCheckModel;

public interface WechatService {

	/**
	 * 微信开发者验证
	 * @param model
	 * @return
	 * @throws Exception
	 */
	String validate(WechatCheckModel model) throws Exception;

}
