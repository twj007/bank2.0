	
	package com.bean;
	
	/**
	 * 用于封装账号数据
	 * 
	 * */
	public class Account {
	
		private String name;//账户名
		private String password;//密码
		
		public Account() {
			super();
		}
		public Account(String name, String password) {
			super();
			this.name = name;
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
