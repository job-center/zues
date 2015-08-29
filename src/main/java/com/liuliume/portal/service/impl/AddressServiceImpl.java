package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.AddressDao;
import com.liuliume.portal.dao.cond.AddressQueryCond;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

	private Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private AddressDao addressDao;

	@Override
	public List<Address> list(Seed<Address> seed) throws Exception{
		logger.info("Account Service list bengins.");
		List<Address> result = new ArrayList<Address>();
		
		Parameter parameter = MBox.convertParameter(seed);
		
		AddressQueryCond cond = new AddressQueryCond();
		Address address = new Address();
		
		if(seed.getFilter().containsKey("nameQ")){
			String name = seed.getFilter().get("nameQ");
			address.setName(name);
		}
		//add other query condition here
		
		cond.setAddress(address);
		parameter.setCond(cond);
		
		int count = addressDao.count(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = addressDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

}
