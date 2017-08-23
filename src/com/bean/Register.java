	
	package com.bean;
	
	public class Register {
		
		private String account;
		private String password;
		private String email;
		
		
		public Register(String account, String password, String email) {
			super();
			this.account = account;
			this.password = password;
			this.email = email;
		}
		@Override
		public String toString() {
			return "Register [account=" + account + ", password=" + password
					+ ", email=" + email + "]";
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Register() {
			super();
		}
		public String getAccount() {
			return account;
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
