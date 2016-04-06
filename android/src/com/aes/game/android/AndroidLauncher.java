package com.aes.game.android;

import android.os.Bundle;

//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.aes.game.MyAesGame;

//import com.aes.game.Global;
//import android.content.pm.ActivityInfo;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Gdx.app.log("TBFAndroidLauncher","Starting");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		//PlatformAndroid platformAndroid = new PlatformAndroid();
		//platformAndroid.activity = this; 
		//Global.platformOs = platformAndroid; 
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    //Gdx.graphics.setDisplayMode(320,480,false); //boolean for fullscreen yes or no 

		initialize(new MyAesGame(), config);
	}
}
