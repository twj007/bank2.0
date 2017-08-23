	
	package com.dao;

	import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bean.Account;
import com.bean.Online;
import com.bean.Register;
import com.util.JdbcUtil;
import com.util.MD5Util;
	
	/**
	 * 持久层，处理银行业务的增删查改
	 * 
	 * */
	public class BankDaoImpl implements BankDao{

		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		MD5Util md5 = new MD5Util();

		/**
		 * 验证登陆
		 * @param Account account 账号
		 * @return int 返回登陆用户的id
		 * */
		public int confirmLogin(Account account) {
			String sql      = "SELECT id, state FROM user WHERE account = ? AND password = ?";
			String name     = md5.getAlgorithmString(account.getName(), "MD5");
			String password = md5.getAlgorithmString(account.getPassword(), "MD5");
			Object[] params = {name, password};
			Online online   = null;
			try {
				online = (Online)qr.query(sql, new BeanHandler<Online>(Online.class), params);
				System.out.println(online);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(online != null){
				return online.getId();
			}
			return 0;
		}

		@SuppressWarnings("deprecation")
		/**
		 * 注册
		 * @prama Register resgister 注册bean
		 * @return Integer 返回注册者id
		 * */
		public Integer register(Register register) {
			String MD5Account = md5.getAlgorithmString(register.getAccount(), "MD5");
			String MD5Password = md5.getAlgorithmString(register.getPassword(), "MD5");
			String email = register.getEmail();
			
			String sql = "SELECT account FROM user WHERE account = ? ";
			String count = null;
			
			try {
				count = (String)qr.query(sql, MD5Account, new ScalarHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count != null)
				return null;
			sql = "INSERT INTO user(account, password, money, state, email) VALUES (?, ?, 0, 1, ?)";
			Object[] params = {MD5Account, MD5Password, email};
			try {
				qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "SELECT id FROM user WHERE 1 = 1 AND account = ?";
			Integer ifSuccess = null;
			try {
				ifSuccess = (Integer)qr.query(sql, MD5Account, new ScalarHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(ifSuccess != null){
				return ifSuccess;
			}
			return null;
		}

		/**
		 * 查询银行业务
		 * @param int id 
		 * @return double 返回余额
		 * 
		 * */
		public double inquiry(int id) {
			String sql = "SELECT money FROM user WHERE id = ?";
			Object param = new Integer(id);
			double money = -1;
			try {
				money = (Double)qr.query(sql, new ScalarHandler(), param);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(money != -1)
				return money;
			return -1;
		}

		@SuppressWarnings("deprecation")
		/**
		 * 存款业务
		 * @param Double deposit 存款金额
		 * @prama int id
		 * @return int 返回是否存款成功
		 * */
		public int getDeposit(Double deposit, int id) {
			String sql = "SELECT money FROM user WHERE id = ?";
			double before = -1;
			int result = -1;
			try {
				before = (Double)qr.query(sql, id, new ScalarHandler());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(before != -1){
				deposit = deposit + before;
				sql = "UPDATE user SET money = ? WHERE id = ?";
				Object[] params = {deposit, id};
				
				try {
					result = qr.update(sql, params);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
			return result;
		}

		@SuppressWarnings("deprecation")
		/**
		 * 取款
		 * @param double withdrawals 取款金额
		 * @param int id
		 * @return int
		 * */
		public int setWithdrawals(double withdrawals, int id) {
			String sql = "SELECT money FROM user WHERE id = ?";
			double before = -1;
			int result = -1;
			try {
				before = (Double)qr.query(sql, id, new ScalarHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			withdrawals = before - withdrawals; 
			if(withdrawals < 0)
				return result;
			else{
				sql = "UPDATE user SET money = ? WHERE id = ?";
				Object[] params = {withdrawals, id};
				try {
					result = qr.update(sql, params);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		@SuppressWarnings("deprecation")
		/**
		 * 转账功能
		 * @param String fromName
		 * @param int id
		 * @param double money
		 * @return boolean
		 * */
		public boolean transfer(String fromName, int id, double money) {
			String sql = "SELECT money FROM user WHERE id = ? ";
			double total = 0;
			double toAfter = 0;
			try {
				total = (Double)qr.query(sql, id, new ScalarHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			double fromAfter = total - money;
			
			if(fromAfter < 0)
				return false;
			 
			sql = "SELECT money FROM user WHERE account = ?";
			fromName = md5.getAlgorithmString(fromName, "MD5");
			try {
				toAfter = total + (Double)qr.query(sql, fromName, new ScalarHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "UPDATE user SET money = ? WHERE id = ?";
			Object[] params = {fromAfter, id};
			try {
				qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "UPDATE user SET money = ? WHERE account = ?";
			Object[] afterParams = {toAfter, fromName};
			try {
				qr.update(sql, afterParams);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}

		@Override
		public boolean alterPassword(String pass, int id) {
			
			
			String sql = "UPDATE user SET password = ? WHERE id = ?";
			int flag = -1;
			pass = md5.getAlgorithmString(pass, "MD5");
			Object[] params = {pass, id};
			try {
				flag = qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(flag != -1)
				return true;
			return false;
		}
		
		
		public int getIdByName(String name, String email){
			String sql = "SELECT id FROM user WHERE account = ? AND email = ?";
			name = md5.getAlgorithmString(name, "MD5");
			Object[] params = {name, email};
			int id = 0;
			try {
				id = (Integer)qr.query(sql, new ScalarHandler(), params);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id;
		}
	
	}
