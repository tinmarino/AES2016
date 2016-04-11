package com.aes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.Json;

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
	public static Boolean 			bIsDebugging = false;

	/* My main font, I don't want to pass it in all classes 
	*/
	public static BitmapFont 		font; 

	/* To be removed maybe 
	*/
	public static TextFieldStyle 	tfStyle;

	/* My keyList, thiese are saved in the game preferences
	*/
	public static List<KeyObject>			keyList; 

	/* The ciphering type, AES? RSA TRIPLEDES or TBF, my own type laying on AES256
	*/
	public static CTYPE 			cType;

	/* Contains many parameters, the unique reference to the routine I used to cipher, set key and IV
	 * Putting that here enable me to put non static var in MyChiper class
	*/
	public static MyCipher 			myCipher; 


	public static PreferenceSaved  	preferenceSaved; 


	public static void writePref(){
		Json json = new Json();
		String sTosave = json.toJson(preferenceSaved);
		Gdx.app.log("TBF", "Global.writePref : " + sTosave);
     	Preferences preferences = Gdx.app.getPreferences("v1"); // v1 for version 1 
		preferences.putString("jsonKey1", sTosave);
		preferences.flush();
	}

	public static void readPref(){
		Json json = new Json(); 
     	Preferences preferences = Gdx.app.getPreferences("v1"); // v1 for version 1 
		if (preferences.get().containsKey("jsonKey1")) {
			String sToLoad = preferences.getString("jsonKey1");
			preferenceSaved = json.fromJson(PreferenceSaved.class, sToLoad );
			Gdx.app.log("TBF", "Global.readPref : " + sToLoad);
		}
		else{
			Gdx.app.log("TBF", "Global.readPref : NULL !!! ");
		}
	}

	public void clearPref(){
	}

	public enum CTYPE{AES, TBF, RSA, TRIPLEDES}
}
