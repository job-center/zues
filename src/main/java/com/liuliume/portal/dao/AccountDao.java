package com.liuliume.portal.dao;

import java.util.List;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.mybatis.Parameter;

public interface AccountDao {
	
	public int count(Parameter parameter);
	
	public List<Account> list(Parameter parameter);
	
	public void delete(Account account);
	
	public Account findAccountById(Integer account_id);

    public Account findAccountByMobile(String mobile);
	
	public void createAccount(Account account);
	
	public void updateAccount(Account account);
	
	public List<Account> listAllAccount();
}
