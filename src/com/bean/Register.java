	
	package com.bean;
	
	public class Register {
		
		private String account;
		private String password;
		
		public Register() {
			super();
		}
		public String getAccount() {
			return account;
		}
		public Register(String account, String password) {
			super();
			this.account = account;
			this.password = password;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
