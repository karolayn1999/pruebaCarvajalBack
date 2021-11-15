package com.prueba.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

	public String encryptText(String menssage) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		byte[] hash = md.digest(menssage.getBytes());
		StringBuffer sb = new StringBuffer();
		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
}