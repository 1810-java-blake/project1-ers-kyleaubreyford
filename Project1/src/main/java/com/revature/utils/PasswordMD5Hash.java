package com.revature.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordMD5Hash {
	
	public static String hashPassword(String password) {
		String generatedPassword = null;
		try {
			//Create message digest instance for MD5
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			
			//Add Password bytes to Digest
			mDigest.update(password.getBytes());
			
			//Get the hash bytes
			byte[] bytes = mDigest.digest();
			
			//Bytes[] has bytes in decimal format
			//Convert it to hexadecimal format.
			
			StringBuilder sb = new StringBuilder();
			for(int i=0;i < bytes.length;i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
			}
			generatedPassword = sb.toString();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}