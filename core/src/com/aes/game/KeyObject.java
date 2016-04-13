package com.aes.game;

import com.badlogic.gdx.Gdx;

public class KeyObject{
		public String label; 
		public byte[] cipheredKey;
		public String clearKey; 




		public void cipherKey(){
			if (null == clearKey || "".equals(clearKey)){
				Gdx.app.log("TBF", "Cannot cipher null clearKey");
			}

			byte[] bClear = clearKey.getBytes(); 
			cipheredKey = new byte[bClear.length];
			for (int i = 0; i<bClear.length; i++){
				cipheredKey[i] = bClear[bClear.length- 1 - i];  
			}
			clearKey = "";
		}
}
