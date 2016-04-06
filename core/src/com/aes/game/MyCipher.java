package com.aes.game;

import java.util.Random;

import com.aes.game.base64.Base64;
import com.badlogic.gdx.Gdx;

/*
 * 32 bytes encryption is 256 bits 
http://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum
 */



public class MyCipher{
	static int 		iBlockSize 			= 32;
	static int  	iBase64BlockSize 	= 43;
	static byte[] 	IV					= new byte[iBlockSize];  				
	static byte[]	IV64				= new byte[iBase64BlockSize];


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

}
