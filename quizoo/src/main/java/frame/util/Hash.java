package frame.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	public static byte[] getHashedByteArray(String pass) {
		byte[] result = null;
		
		try {
			
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			
			result = md.digest(pass.getBytes());
			
		}catch(NoSuchAlgorithmException e) {
			System.out.println("AlgorithmException");
			e.printStackTrace();
		}
		
		return result;
	}
} 