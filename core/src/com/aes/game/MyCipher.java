package com.aes.game;

//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
import java.util.Random;


//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.SecretKeySpec;

import com.aes.game.base64.Base64;
import com.badlogic.gdx.Gdx;
import com.googlecode.gwt.crypto.client.AESCipher;

/*
 * 32 bytes encryption is 256 bits 
http://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum
 */



public class MyCipher{

	static int 		iBlockSize 			= 32;
	static int  	iBase64BlockSize 	= 43;
	static byte[] 	IV					= new byte[iBlockSize];  				
	static byte[]	IV64				= new byte[iBase64BlockSize];
	static byte[]   key 				= new byte[iBlockSize];


	public static String CipherMain(String sIn){
		String sOut = "";
		Boolean bol = false; 

		Gdx.app.log("MainRendered ", "Unciphereing"+ sIn.substring(0,2) ); 
		
		// TEST 
		bol  = 2 < sIn.length() ;
		bol &= "IV".equals(sIn.substring(0,2)) ;

		// UNCIPHER
		if (bol){
			Gdx.app.log("MainRendered ", "Unciphereing"); 
			sOut = UncipherText(sIn);
		}

		// CIPHER 
		else{
			Gdx.app.log("MainRendered ", "Ciphering"); 
			sOut = MyCipher.CipherText(sIn);
		}

		return sOut;

	}

	public static  String UncipherText(String text){
		String 	res = ""; 
		Boolean bol = false ; 

		// TEST INPUT 
		bol  = 2 < text.length() ;
		bol &=  !"IV".equals(text.substring(0,2)) ;
		if (bol){
			Gdx.app.log("MyCipher", "No IV, I cannot uncipher");
			return "No IV I cannot uncipher !!";
		}


		IV = null; 
		String ivString 	= text.substring(4,4 + iBase64BlockSize);
		Gdx.app.log("CipherMain", "tetrieving : " + ivString);
		byte [] tmp0 = ivString.getBytes();
		Gdx.app.log("CipherMain", "retrieving Iv tmp  : " + tmp0.toString());
		//IV = Base64.decode(tmp0); 
		//IV = Base64.decode(ivString).getBytes(); 

		String[] tmp = text.split("-+",10);
		
		for (int i=0; i<tmp.length; i++){
			Gdx.app.log("MyCIpher split", ""+ i + ":" + tmp[i] );
		}
		res = tmp[1];
		res = res.substring(1, res.length()-1); // Delete the /n and /n at begining and end


		return res; 
	}




	public static String CipherText(String text){
		String res = ""; 

		// Generate IV 
		IV = new byte[iBlockSize];
   		Random rn = new Random();
		for (int i=0; 32 > i; i++){
			IV[i] = (byte) rn.nextInt(); 
		}

		

		IV64 = Base64.encode(IV);
		res  = "IV1=";
		res	+=  IV64.toString();
		res += "\n------------------------------------";
		res += "\n";
		
		res += text;

		res += "\n------------------------------------\n";

		Gdx.app.log("CipherMain", "generated  IV : " + IV.toString() );
		Gdx.app.log("MyCipher ", "int size " + Integer.SIZE);
		Gdx.app.log("-----------------------", "Base 64 len is" + IV64.length + " " + IV64.toString());
		return res; 
	}


	public static void ClearIV()
	{
		Gdx.app.log("IV lenght aaaa", IV.length + "" );
		for (int i=0; i<IV.length; i++)
		{
			IV[i]=0; 
		}
	}

	public static void ClearByte(byte[] bytearray)
	{
		for (int i=0; i<bytearray.length; i ++)
		{
			bytearray[i]=0; 
		}
	}


	public static byte[] AesCipher(byte[] sClear, Boolean bCipher)
	{
		byte res[] = new byte[sClear.length] ;
		AESCipher aesChiper = new AESCipher();
		


		return res;
	}
}





/*
 *


		SecretKeySpec aesKeySpec = new SecretKeySpec(key, "AES");

		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, aesKeySpec);

		res  = aesCipher.doFinal(sClear);

		AESCipher cipher = new AESCipher();

        cipher.setKey(key);
		String string = new String(sClear);
        try {
            res = cipher.encrypt(string).getBytes();
        } 
		catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }

 *
 *
 * */

	//	byte[] encryptedData = new byte[10];
	//	try{
	//		Cipher c = Cipher.getInstance("AES");
	//		SecretKeySpec k = new SecretKeySpec(key, "AES");
	//		if (bCipher){c.init(Cipher.ENCRYPT_MODE, k);}
	//		else {c.init(Cipher.DECRYPT_MODE, k);}
	//		encryptedData = c.doFinal(sClear);
	//	}
	//	catch (NoSuchAlgorithmException e){
	//		Gdx.app.log("TBF", "Errror, no AES algo");
	//	}
	//	catch (NoSuchPaddingException e){
	//		Gdx.app.log("TBF", "Errror, no suh padding");
	//	}
	//	catch (InvalidKeyException e){
	//		Gdx.app.log("TBF", "Errror, AES invalid key");
	//	}
	//	catch (IllegalBlockSizeException e){
	//		Gdx.app.log("TBF", "Errror, AES illegal block size ");
	//	}
	//	catch (BadPaddingException e){
	//		Gdx.app.log("TBF", "Errror, badh padding");
	//	}

	//	return encryptedData;

