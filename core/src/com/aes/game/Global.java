package com.aes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Clipboard;

/*	Here I declare some global variables. They are accesible from anywhere. 
    They correspond to settings, parameters... 
	Or to some variables I am lazy to pass betwenn classes
*/
public class Global{
	/* The input multiplexer, to be able to listen to different listener at the same time,
	 * Don't forget to dispose it
	*/
	public static InputMultiplexer 	inputMultiplexer; 

	/* An interface implemented differently on desktop android or html 
	*/
	public static PlatformOs 		platformOs;

	/* System clipboard, not implemented yet, not working on html 
	*/
	public static Clipboard 		clipboard;

	/* Is debbuging boolean, to check or uncheck the debug view
	*/
	public static Boolean 			bIsDebugging = true;

	/* My main font, I don't want to pass it in all classes 
	*/
	public static BitmapFont 		font; 

	/* To be removed maybe 
	*/
	public static TextFieldStyle 	tfStyle;

	/* My keyList, thiese are saved in the game preferences
	*/
	public static KeyElt[]			keyList; 

	/* The ciphering type, AES? RSA TRIPLEDES or TBF, my own type laying on AES256
	*/
	public static CTYPE 			cType;

	/* Contains many parameters, the unique reference to the routine I used to cipher, set key and IV
	 * Putting that here enable me to put non static var in MyChiper class
	*/
	public static MyCipher 			myCipher; 


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
	public enum CTYPE{AES, TBF, RSA, TRIPLEDES}
}
