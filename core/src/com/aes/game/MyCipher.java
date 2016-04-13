package com.aes.game;

import java.util.ArrayList;
import java.util.List;
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
import java.util.Random;


//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.SecretKeySpec;

//import com.aes.game.base64.Base64;
import com.badlogic.gdx.Gdx;
import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.bouncycastle.engines.AESEngine;
import com.googlecode.gwt.crypto.bouncycastle.modes.CBCBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.paddings.PaddedBufferedBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.params.KeyParameter;
import com.googlecode.gwt.crypto.bouncycastle.params.ParametersWithIV;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;

/*
 * 32 bytes encryption is 256 bits 
http://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum
 */



public class MyCipher{

	static int 		iBlockSize 			= 16;
	static int 		iShowedIvSize		= 6;    // 6 bytes so 8 chars in base64
	static byte[] 	IV					= null;  				
	static byte[]   key 				= null;


	public static String CipherMain(String sIn){
		String sOut = "";
		Boolean bol = false; 

		

		// TEST if I have a key  
		bol  = (null != Global.keyObject);
		if (bol){bol &= (null != Global.keyObject.cipheredKey);}
		if (!bol){
			Gdx.app.log("TBF","ERROR I don't have any walid key to cipher");
			return "Error no valid key to cipher";
		}
		key = Global.keyObject.cipheredKey;

		// TEST if starting by IV
		bol  = 2 < sIn.length() ;
		if (bol){bol &= "IV".equals(sIn.substring(0,2)) ;}

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

		// TEST if starting by IV (again)
		bol  = 2 < text.length() ;
		if (bol){bol &= "IV".equals(text.substring(0,2)) ;}
		if (!bol){
			Gdx.app.log("MyCipher", "No IV, I cannot uncipher :" + text);
			return "No IV I cannot uncipher !!";
		}


		// GET IV 
		String ivString = text.split("=")[1].split("\n")[0].split("\r")[0]; 
		byte[] IVshowed  = Base64.decode(ivString);
		IV = new byte[iBlockSize];
		for (int i=0; IVshowed.length > i; i++){
			IV[i] = IVshowed[i];
		}

		Gdx.app.log("CipherMain", "tetrieving :iv  " + ivString);
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

		res = (MyAesCipher(res, false));


		return res; 
	}




	public static String CipherText(String text){
		String res = ""; 
		Random rn  = new Random();

		// Generate IV 
		IV = new byte[iBlockSize];
	 	byte[] bShowedIV = new byte[6];	
		for (int i=0; 6 > i; i++){
			IV[i] 		 = (byte) rn.nextInt(); 
			bShowedIV[i] = IV[i]; 
		}


		String IV64 = new String(Base64.encode(bShowedIV));
		res  = "IV1=";
		res	+=  IV64;
		res += "\n------------------------------------";
		res += "\n";
		
		res += MyAesCipher(text, true);

		res += "\n------------------------------------\n";

		Gdx.app.log("CipherMain", "generated  IV : " + IV64 );
		Gdx.app.log("MyCipher ", "int size " + Integer.SIZE);
		Gdx.app.log("-----------------------", "Base 64 len is" + IV64.length() + " " + IV64);
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


	public static String MyAesCipher(String sClear, Boolean bCipher)
	{
		Gdx.app.log("TBF", "AES ______________________________________________" + IV.length +","+ key.length);
		String res="";
		byte[] stream;
		

		// Create Ciphering engine 
		CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), IV);
    	PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
			new CBCBlockCipher(new AESEngine()));
		Gdx.app.log("TBF", "uinput len " + sClear.length());
        try {
			if (bCipher){
				aes.init(true, ivAndKey);
				stream = cipherData(aes, sClear.getBytes());
				res = new String(Base64.encode(stream)); 
			}
			else{
				stream = Base64.decode(sClear);
				aes.init(false, ivAndKey);
				res = new String(cipherData(aes, stream));
				//Gdx.app.log("TBF", "Clear : " + (sClear.length()));
				//Gdx.app.log("TBF", "hex decode  " + stream.length  );
				//sTmp = new String(Hex.encode(stream));
				//Gdx.app.log("TBF", "encrypt size sTmp : " + sTmp.length()  );
				//Gdx.app.log("TBF", "decrypt size " + res.length()  );
			}
				Gdx.app.log("TBF", "mid len " + res.length());
				Gdx.app.log("TBF", "out len " + res.length());
        } 
		catch (InvalidCipherTextException e) {
            //throw new RuntimeException(e);
			Gdx.app.log("TBF", "Pad, cipher exception 1: " + e);
			res = "Apparently not the good key :Error java " +e;
		} catch (Exception e) {
			Gdx.app.log("TBF", "Pad, cipher exception 2: " + e);
			e.printStackTrace();
			res = "Apparently not the good key :Error java " +e;
		}

		return res;
	}


	private static byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data)
	        throws Exception
	{
	    int minSize = cipher.getOutputSize(data.length);
	    byte[] outBuf = new byte[minSize];
	    int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
	    int length2 = cipher.doFinal(outBuf, length1);
	    int actualLength = length1 + length2;
	    byte[] result = new byte[actualLength];
	    System.arraycopy(outBuf, 0, result, 0, result.length);
	    return result;
	}


	public static byte[] toByteArray(List<Byte> in) {
	    final int n = in.size();
	    byte ret[] = new byte[n];
	    for (int i = 0; i < n; i++) {
	        ret[i] = in.get(i);
	    }
	    return ret;
	}

	public static List<Byte> toByteList(byte[] in){
	    final int n = in.length;
	    List<Byte> ret  = new ArrayList<Byte>();
	    for (int i = 0; i < n; i++) {
	        ret.add(in[i]);
	    }
	    return ret;
	}

	
}
