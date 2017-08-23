	
	package com.manager;

	import com.bean.Account;
import com.bean.Register;
import com.dao.BankDao;
import com.factory.Factory;

	/**
	 * 单例助手,连接业务层和持久层
	 * @author twj
	 * */
	public class Manager{
	
		//开辟静态区空间
		private  static Manager manager = null;
		private BankDao moneyDaoImpl = null;
		private Factory factory  = null;
		
		//防止外部直接创建该对象
		private Manager(){
			if(factory == null){
				factory = Factory.getInstance();
			}	
			moneyDaoImpl = factory.getMoneyBank();
		}
		
		//创建manager对象并返回
		public synchronized static Manager getInstance(){
			//提高已经存在对象的效率,双重检查
			if(manager == null){
				 manager = new Manager();
			}
			
			return manager;
		}
		
		//验证登陆
		public int confirmLogin(Account account){
			
			return moneyDaoImpl.confirmLogin(account);
		}
		
		//注册
		public Integer register(Register register){
			
			return moneyDaoImpl.register(register);
		}
		//获取余额
		public double inquiry(int id){
			
			return moneyDaoImpl.inquiry(id); 
		} 
		
		//存款
		public int getDeposit(Double deposit,int id){
			
			return moneyDaoImpl.getDeposit(deposit,id);
		}
		
		//取款
		public int setWithdrawals(double withdrawals,int id){
			
			return moneyDaoImpl.setWithdrawals(withdrawals,id);
		}
		//转账
		public boolean transfer(String fromName,int id, double money){
			
			return moneyDaoImpl.transfer(fromName, id, money);
		}
		
		public boolean alterPassword(String password, int id){
			
			return moneyDaoImpl.alterPassword(password, id);
		}
		
		public int getIdByName(String name, String email){
			return moneyDaoImpl.getIdByName(name, email);
		}
		
	}
