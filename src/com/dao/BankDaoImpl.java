	
	package com.dao;

	import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bean.Account;
import com.bean.Register;
import com.util.JdbcUtil;
	
	public class BankDaoImpl implements BankDao{

		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		@Override
		public int confirmLogin(Account account) {
			String sql      = "SELECT id FROM user WHERE account = ? AND password = ?";
			String name     = account.getName();
			String password = account.getPassword();
			Object[] params = {name, password};
			Integer flag    = null;
			try {
				flag = (Integer)qr.query(sql, new ScalarHandler(), params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(flag != null){
				return flag;
			}
			return 0;
		}

		@SuppressWarnings("deprecation")
		@Override
		public Integer register(Register register) {
			String account = register.getAccount();
			String password = register.getPassword();
			String sql = "SELECT account FROM user WHERE account = ? ";
			String count = null;
		  
			try {
				count = (String)qr.query(sql, account, new ScalarHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count != null)
				return null;
			sql = "INSERT INTO user(account, password, money) VALUES (?, ?, 0)";
			Object[] params = {account, password};
			try {
				qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "SELECT id FROM user WHERE 1 = 1 AND account = ?";
			Integer ifSuccess = null;
			try {
				ifSuccess = (Integer)qr.query(sql, account, new ScalarHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(ifSuccess != null){
				return ifSuccess;
			}
			return null;
		}

		@Override
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
		@Override
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
		@Override
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
		@Override
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
	
	}
