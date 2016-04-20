package com.aes.game;

import java.util.ArrayList;
import java.util.List;

import com.aes.game.PlatformOs.OS;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.Disposable;
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

	/* System clipboard not implemented on html
	*/
	public static Clipboard			clipboard;

	/* Is debbuging boolean, to check or uncheck the debug view
	*/
	public static Boolean 			bIsDebugging = false;

	/* My main font, I don't want to pass it in all classes 
	*/
	public static BitmapFont 		font; 
	public static BitmapFont 		fontButton; 


	/* The key I will use to cipher. 
	 * Warning this must be a hava a cleared clearKey to avoid leaks
	*/
	public static KeyObject			keyObject	= null; 

	/* The ciphering type, AES? RSA TRIPLEDES or TBF, my own type laying on AES256
	*/
	public static CTYPE 			cType;

	/* Contains many parameters, the unique reference to the routine I used to cipher, set key and IV
	 * Putting that here enable me to put non static var in MyChiper class
	*/
	public static MyCipher 			myCipher; 
	
	/* Due to a problem that the newline string is diffferent according to platform 
	 * In Android i is \n and in desktop it is \r
	*/
	public static String 			sReturn; 
	public static String 			ENTER_ANDROID = "\n"; 
	public static String 			ENTER_DESKTOP = "\r"; 

	/* Th epreferences I will save and load into a json file, 
	 * Contains the Keys ... just used here 
	*/
	public static PreferenceSaved  	preferenceSaved; 

	/* A list containing all passed text in case I wanna Undo 
	 * and an INdex showing where I currently am in that list
	 */
	public static List<String> 		listUndo = new ArrayList<String>();
	public static int 				iUndo	 = 0;


	/* Init method, init all parameters, all required params must be setted here
	*/
	public static void init(){
		// INPUT 
		Global.inputMultiplexer 	= new InputMultiplexer();
		Gdx.input.setInputProcessor(Global.inputMultiplexer);
		// PREF, KEY
		Global.preferenceSaved  	= new PreferenceSaved();
		Global.readPref();
		if (preferenceSaved.keyList != null && preferenceSaved.keyList.size() > 0){
			keyObject = preferenceSaved.keyList.get(0);
		}
		// FONT 
		int fontsize = 32;
		if (OS.ANDROID == platformOs.getOs() && 500 > Gdx.graphics.getHeight() ){fontsize/=2;}
		Global.font 		= platformOs.getFont(fontsize);
		Global.fontButton 	= platformOs.getFont(8); 

		// To prevent backKey from returning
		Gdx.input.setCatchBackKey(true);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// Return Key 
		if (OS.ANDROID == platformOs.getOs()){sReturn = "\n";}
		else {sReturn = "\r";}
	}


	//
	// STYLES 
	//
	public static TextFieldStyle getTextFieldStyle(List<Disposable> disposableList){
		TextFieldStyle tfStyle 	= new TextFieldStyle(); 
		tfStyle.font 			= font;
		tfStyle.fontColor 		= GuiParameter.colTextFont;
		tfStyle.background 		= PixmapFactory.getDrawableMonocromatic(1, 1, GuiParameter.colTextBck, disposableList);
		tfStyle.cursor			= PixmapFactory.getDrawableMonocromatic(2, 16, GuiParameter.colTextCursor, disposableList);
		tfStyle.selection		= PixmapFactory.getDrawableMonocromatic(2, 16, GuiParameter.colTextSelection, disposableList);
		//tfStyle.cursor.setMinWidth(2f);
		return tfStyle;
	}

	public static ScrollPaneStyle getScrollPaneStyle(List<Disposable> disposableList){
		ScrollPaneStyle spStyle = new ScrollPaneStyle();
		Texture tScroll  = new Texture(PixmapFactory.circle(GuiParameter.iScroll ,GuiParameter.colScroll));
		Texture tKnob  = new Texture(PixmapFactory.circle(GuiParameter.iKnob ,GuiParameter.colKnob));
		disposableList.add(tScroll);
		disposableList.add(tKnob);
		spStyle.vScroll 		= PixmapFactory.ninePatchFromTexture(tScroll) ;
		spStyle.vScrollKnob 	= PixmapFactory.ninePatchFromTexture(tKnob) ;
		return spStyle;
	}

	public static LabelStyle getTLabelStyle(){
		LabelStyle lStyle 	= new LabelStyle();
		lStyle.font 		= Global.font; 
		lStyle.fontColor 	= GuiParameter.colTLabelFont;
		return lStyle;
	}

	public static TextButtonStyle getTextButtonStyle(List<Disposable> disposableList){

		TextButtonStyle tbStyle = new TextButtonStyle();
		tbStyle.font 			= Global.font; 
		tbStyle.fontColor 		= GuiParameter.colPButtonFont;
		Texture t1 				= new Texture(PixmapFactory.circle(32, GuiParameter.colPButton));
		Texture t2 				= new Texture(PixmapFactory.circle(32, GuiParameter.colPButtonDown));
		tbStyle.up 				= PixmapFactory.ninePatchFromTexture(t1); 
		tbStyle.down			= PixmapFactory.ninePatchFromTexture(t2); 
		disposableList.add(t1);
		disposableList.add(t2);
		return tbStyle;
	}


	//
	// PREFERENCES
	//
	public static void writePref(){
		Json json = new Json();
		String sTosave = json.toJson(preferenceSaved);
		Gdx.app.log("TBF", "Global.writePref : " + json.prettyPrint(preferenceSaved));
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
			Gdx.app.log("TBF", "Global.readPref : " + json.prettyPrint(preferenceSaved));
		}
		else{
			Gdx.app.log("TBF", "Global.readPref : NULL !!! ");
		}
	}

	public void clearPref(){
	}

	public enum CTYPE{AES, TBF, RSA, TRIPLEDES}
}
