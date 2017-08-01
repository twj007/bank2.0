	
	package com.util;

	
	import java.security.MessageDigest;
	import java.security.NoSuchAlgorithmException;


	
	
	/**
	 * MD5对密码加密和验证的类
	 * */
	public class MD5Util {
	
		
		/**
		 * 获取密码和指定算法名称的信息摘要，对其进行加密
		 * @param String str 密码
		 * @param String algorithm 算法名称的信息摘要
		 * @throws NoSuchAlgorithmException 
		 * */
		public String getAlgorithmString(String str,String algorithm) {
			
			return getString(str ,algorithm);
		}
		
		
		/**
		 * 调用MessageDigest提供的加密算法
		 * @param String str加密前密码
		 * @return String 加密后密码
		 * @throws NoSuchAlgorithmException 
		 * */
		public String getString(String str ,String algorithm) {
			
			//获取加密方式为MD5
			MessageDigest md=null;
			byte[] before=null;
			try {
				md = MessageDigest.getInstance(algorithm);
				md.update(str.getBytes());
				
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			}
			try{
				//把加密前密码变成字节
				
				
					before = md.digest();
					//1.使用sun包下的BASE64Encoder进行加密(也可以使用16进制转换)
//					BASE64Encoder base = new BASE64Encoder();
//					String after=base.encode(md.digest(before));
					//2.使用16进制
					
					return byteArrayToHexStr(before);
				
				
			
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			
			return null;
		}
		
		/**
		 * 转码将二进制数组转换成16进制字符串
		 * @param byte[] 
		 * @return String 转码后字符串
		 * */
		private String byteArrayToHexStr(byte[] before) {
			
			StringBuffer after = new StringBuffer(before.length*2);
			//进行16进制与转换
			for(int i=0;i<before.length-1;i++){
				if(((int)before[i]&0xff)<0x10)//小于10前面补0
					after.append("0");
				//转换16进制
				after.append(Long.toString((int) before[i] & 0xff, 16));
			}
			return after.toString();
		}


		/**
		 * 判断密码是否一致
		 * 
		 * */
		public boolean judgePassword(String password ,String str){
			if(password.equals(getAlgorithmString(str,"MD5")))
				return true;
			return false;
			
		}
		
//		public static void main(String[] args) {
//			MD5Util mu = new MD5Util();
//			String s ="root";
//			String after=mu.getAlgorithmString("zsmj10","MD5");
//			System.out.println(after);
//			System.out.println(mu.getAlgorithmString("root","MD5"));
//			System.out.println("比较"+mu.judgePassword(after, s));
//		}
	}
