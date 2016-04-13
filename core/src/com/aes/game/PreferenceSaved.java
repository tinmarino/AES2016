package com.aes.game;

import java.util.ArrayList;
import java.util.List;

/*
 * This object will be used for reflection even with gwt
 *	https://github.com/libgdx/libgdx/wiki/Reflection
 *	https://github.com/libgdx/libgdx/wiki/Reading-&-writing-JSON
 */
public class PreferenceSaved{
	public List<KeyObject> keyList = new ArrayList<KeyObject>(); 



	public void initDebug(){
		KeyObject keyObject = new KeyObject();
		keyObject.label = "Label1 on Android";
		keyObject.cipheredKey = "Any String you want".getBytes();
		keyList.add(keyObject);


		keyObject = new KeyObject();
		keyObject.label = "Label2 on Android";
		keyObject.cipheredKey = "Any String 2 you want".getBytes();
		keyList.add(keyObject);

		keyObject = new KeyObject();
		keyObject.label = "Label3 on Android";
		keyObject.cipheredKey = "Any String 3 you want".getBytes();
		keyList.add(keyObject);


		keyObject = new KeyObject();
		keyObject.label = "Label4 on Android";
		keyObject.cipheredKey = "Any String 3 you want".getBytes();
		keyList.add(keyObject);

		keyObject = new KeyObject();
		keyObject.label = "Label5 on Android";
		keyObject.cipheredKey = "Any String 3 you want".getBytes();
		keyList.add(keyObject);
	}
}
