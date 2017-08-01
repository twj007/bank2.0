	
	package com.factory;

	import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.dao.BankDao;
		

	/**
	 *  工厂模式
	 *  单例+同步
	 *  @author Administrator
	 *  @version 2017/7/17
	 * */
	public class Factory {

		private BankDao bd;//持久层接口
		private static Factory factory = null;//工厂对象
		
		//在创建工厂时，创建对应的持久层对象
		private Factory(){
			
			createUserDao();
			
		}
		/**
		 *  获取工厂对象
		 * 	@return Factory
		 * */
		public static synchronized Factory getInstance() {
			
			if(factory == null){
				factory = new Factory();
			}
			
			return factory;
			
		}
		
		/**
		 * 	加载配置文件中的创建持久层对象
		 * 	@return void
		 * */
		private <T> void createUserDao(){
			//加载配置文件
			Properties prop = new Properties();
			String className ;
			try {
				//获取当前类所在目录下的classInfo.properties
				InputStream is = Factory.class.getResourceAsStream("classInfo.properties");
				prop.load(is);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
			//获取持久层对象的位置，根据魔板创建该类映射对像
			className = prop.getProperty("className");
			Class<?> c;
			try {
				c = Class.forName(className);
				Object o = c.newInstance();
				bd = (BankDao)o;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		/**获取持久层对象
		 * 
		 * @return MoneyBank
		 */
		public BankDao getMoneyBank(){
			
			return this.bd;
		}
		
		public static void main(String[] args) {
			
			Factory f = Factory.getInstance();
			BankDao bd = f.getMoneyBank();
			System.out.println(bd);
		}
	}
