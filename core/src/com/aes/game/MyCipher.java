package com.aes.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



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


	/*   Called with the textArea.getText(), 
	 *   This is the main exported routine of this class
	 */
	public static String CipherMain(String sIn){
		String sOut = "";
		Boolean bol = false; 

		

		// TEST if I have a key  
		bol  = (null != Global.keyObject);
		if (bol){bol &= (null != Global.keyObject.savedKey);}
		if (!bol){
			Gdx.app.log("TBF","ERROR I don't have any walid key to cipher");
			return "Error no valid key to cipher";
		}

		// TEST if starting by IV
		bol = doCipher(sIn);

		// CIPHER KEY 
		key = CipherKey(Global.keyObject.savedKey, Global.keyObject.savedKey);
		

		// UNCIPHER
		if (!bol){
			Gdx.app.log("MainRendered ", "Unciphereing"); 
			sOut = UncipherText(sIn);
		}

		// CIPHER 
		else{
			Gdx.app.log("MainRendered ", "Ciphering"); 
			sOut = MyCipher.CipherText(sIn);
		}

		// DELETE KEY 
		key = null;

		return sOut;

	}

	public static Boolean doCipher(String sIn){
		String[] listChapter = sIn.split("--+",10);
		if ( 2 > listChapter.length){
			Gdx.app.log("TBF", "doCipher, split trop courtin");
			return true;
		}

		String header 		 = listChapter[1];
		if (3 > header.length()) {
			Gdx.app.log("TBF", "doCipher, header trop courtin" + header);
			return true;
		}

		header 		 		 = header.substring(1,header.length());
		if (!"IV".equals(header.substring(0,2))){
			Gdx.app.log("TBF", "doCipher, header not starttin by IV" + header);
			return true;
		}
		return false;
	}

	/*  Main uncipher
	 */
	public static  String UncipherText(String text){
		String 	res 			= ""; 
		String 	cipheredText 	= ""; 
		String 	header  		= "";
		Boolean bol 			= false ; 

		// DIVIDE TEXT 
		String[] listChapter = text.split("--+",10);
		if ( 3 > listChapter.length){
			res = "Not good unciphered Text, must be divided by -------";
			Gdx.app.log("TBF", res);
			return res; 
		}
		header 		 = listChapter[1];
		header 		 = header.substring(1,header.length());
		cipheredText = listChapter[2];
		cipheredText = cipheredText.substring(1, cipheredText.length()-1); // Delete the /n and /n at begining and end


		// HEADER 
		// TEST if starting by IV (again)
		bol  = 2 < header.length() ;
		if (bol){bol &= "IV".equals(header.substring(0,2)) ;}
		if (!bol){
			Gdx.app.log("MyCipher", "No IV, I cannot uncipher :" + text);
			return "No IV I cannot uncipher !!";
		}
		// GET IV 
		String ivString = header.split("=")[1].split("-")[0]; // between = and - 
		byte[] IVshowed  = Base64.decode(ivString);
		IV = new byte[iBlockSize];
		for (int i=0; (IVshowed.length > i) & (IV.length > i); i++){
			IV[i] = IVshowed[i];
		}

		// Uncipher text
		res = (MyAesCipher(cipheredText, false));

		return res; 
	}


	/*  Main CIpher
	 */
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

		// Create String to put in textArea 
		res += "------------------------------------" ;
		res +=  Global.sReturn + "IV1=" + IV64 + "-" + Global.sReturn;
		res += "------------------------------------" ;
		res +=  Global.sReturn + MyAesCipher(text, true) + Global.sReturn;
		res += "------------------------------------" + Global.sReturn;

		return res; 
	}



	/*  Clear a byte[]
	 */
	public static void ClearByte(byte[] bytearray){
		for (int i=0; i<bytearray.length; i ++)
		{
			bytearray[i]=0; 
		}
	}


	/*  AES cipher and uncipher a bytearray, 
	 *  <- Key and IV  static vars 
	 */ 
	public static String MyAesCipher(String sClear, Boolean bCipher){
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

	/* The sizes of the input must be good, expecially key4Key!!!
	 */ 
	public static byte[] CipherKey(byte[] key4Key, byte[] clear4Key){
		byte[] cipheredKey = new byte[iBlockSize];


		CipherParameters paramKey = new KeyParameter(key4Key);
    	PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
			new CBCBlockCipher(new AESEngine()));
		aes.init(true, paramKey);
		try {
			cipheredKey = cipherData(aes, clear4Key);
		} catch (Exception e) {
			Gdx.app.log("TBF", "ERROR at CipherKey "  + e);
			e.printStackTrace();
		}
		return cipheredKey; 
	}

	/*  Internal Util function :
	 *  Cipher byte array with a PaddedBlcok cipher. 
	 */
	private static byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data) throws Exception{
	    int minSize = cipher.getOutputSize(data.length);
	    byte[] outBuf = new byte[minSize];
	    int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
	    int length2 = cipher.doFinal(outBuf, length1);
	    int actualLength = length1 + length2;
	    byte[] result = new byte[actualLength];
	    System.arraycopy(outBuf, 0, result, 0, result.length);
	    return result;
	}


	/* Convert a Byte list to Byte array 
	*/
	public static byte[] toByteArray(List<Byte> in) {
	    final int n = in.size();
	    byte ret[] = new byte[n];
	    for (int i = 0; i < n; i++) {
	        ret[i] = in.get(i);
	    }
	    return ret;
	}

	/* Convert a Byte array to byte List 
	*/
	public static List<Byte> toByteList(byte[] in){
	    final int n = in.length;
	    List<Byte> ret  = new ArrayList<Byte>();
	    for (int i = 0; i < n; i++) {
	        ret.add(in[i]);
	    }
	    return ret;
	}

	
}
