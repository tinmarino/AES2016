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
		keyObject.label = "Dbg1 on Android";
		keyObject.cipheredKey = "Dbg1android Key ".getBytes();
		keyList.add(keyObject);


		keyObject = new KeyObject();
		keyObject.label = "Dbg2 2 on Desktop";
		keyObject.cipheredKey = "Dbg2Desktop Key ".getBytes();
		keyList.add(keyObject);
	}
}
