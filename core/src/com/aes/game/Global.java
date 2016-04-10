package com.aes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Clipboard;

public class Global{
	public static InputMultiplexer 	inputMultiplexer; 

	public static PlatformOs 		platformOs;

	public static Clipboard 		clipboard;

	public static Boolean 			bIsDebugging = true;

	public static BitmapFont 		font; 

	public static TextFieldStyle 	tfStyle;

	public static KeyElt[]			keyList; 


	public void safePref(){
     	Preferences preferences = Gdx.app.getPreferences("v1"); 
     //if (!preferences.get().containsKey(VOLUME))     {preferences.putFloat  (VOLUME  , 0.5f);}
     //if (!preferences.get().containsKey(IS_MUTE))    {preferences.putBoolean(IS_MUTE ,false);}
     //if (!preferences.get().containsKey(DEBUG))      {preferences.putBoolean(DEBUG   ,false);}
     //if (!preferences.get().containsKey(TIMESTEP))   {preferences.putInteger(TIMESTEP,5    );}
	}

	public void loadPref(){
	}

	public class KeyElt{
		public String label; 
		public byte[] cipheredKey;
	}
}
