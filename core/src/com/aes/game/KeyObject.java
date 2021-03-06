package com.aes.game;

import com.badlogic.gdx.Gdx;


/* Key Object : Keep the key
 */
public class KeyObject{
		public String label; 
		public byte[] savedKey;
		public byte[] aesKey;
		public String clearKey; 



		public KeyObject(){
			label = "NoKey";
		}

		/* To cipher the key to save
		*/ 
		public void cipherKey(){
			byte[] key4Save;
			savedKey = new byte[MyCipher.iBlockSize];

			if (null == clearKey || "".equals(clearKey)){
				Gdx.app.log("TBF", "ERROR in keyObject: Cannot cipher null clearKey");
				return;
			}

			byte[] key4Key 		= new byte[MyCipher.iBlockSize];
			byte[] clear4Key 	= clearKey.getBytes(); 

			key4Save = MyCipher.CipherKey(key4Key, clear4Key);

			for (int i = 0; i<MyCipher.iBlockSize; i++){
				savedKey[i] = key4Save[i];
			}
			clearKey = "";
			return;
		}
}
