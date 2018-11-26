// methods boosted from http://www.adeveloperdiary.com/java/how-to-easily-encrypt-and-decrypt-text-in-java/

package hello;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
	
	public static String encrypt(String strClearText) throws Exception{
		String strData="";
		
		try {
			System.out.println("Encoding " + strClearText);
			SecretKeySpec skeyspec=new SecretKeySpec(SecurityConstants.ENCRYPT_SECRET.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted=cipher.doFinal(strClearText.getBytes("UTF-8"));
			strData=new String(encrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	
	
	public static String decrypt(String strEncrypted) throws Exception{
		String strData="";
		
		try {
			System.out.println("Decoding " + strEncrypted);
			SecretKeySpec skeyspec=new SecretKeySpec(SecurityConstants.ENCRYPT_SECRET.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes("UTF-8"));
			strData=new String(decrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	
	public static void main(String[] args) {
		
		String inStr = "12345678910";
		
		final String template1 = "Encoding: %s => %s";
		final String template2 = "Decoding: %s => %s";
		
		try {
			String encStr = encrypt(inStr);
			String decStr = decrypt(encStr);
			System.out.println(String.format(template1, inStr, encStr));
			System.out.println(String.format(template2, encStr, decStr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
