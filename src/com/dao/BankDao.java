	
	package com.dao;

	import com.bean.Account;
import com.bean.Register;


	
	public interface BankDao {
	
		
			//验证登陆
			public int confirmLogin(Account account);
			//注册
			public Integer register(Register register);
			//获取当前余额
			public double inquiry(int id);
			//存款
			public int getDeposit(Double deposit , int id);
			//取款
			public int setWithdrawals(double withdrawals, int id);
			//转账
			public boolean transfer(String fromName,int id, double money);
		
			//修改密码
			public boolean alterPassword(String pass, int id);
			//通过名字查询id
			public int getIdByName(String name, String email);
	}
