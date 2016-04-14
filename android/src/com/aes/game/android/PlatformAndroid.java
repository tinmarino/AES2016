
package com.aes.game.android;

import com.aes.game.PlatformOs;
import com.aes.game.PlatformOs.OS;
import com.badlogic.gdx.Gdx;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.inputmethod.InputMethodManager;


public class PlatformAndroid implements PlatformOs {
	public  Activity activity;
	public Orientation orientation;

	public PlatformAndroid(){
	}

	@Override
	public OS getOs(){
		return OS.ANDROID;
	}

	public void showKeyboard(){
		InputMethodManager imm = (InputMethodManager)
		                                 activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm != null){
		        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
		}
    }
	public void hideKeyboard(){
		InputMethodManager imm = (InputMethodManager)
										  activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm != null){
				imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
		}
	}


	@Override
	public void setOrientation(Orientation orientation) {
	   this.orientation = orientation; 
	   if (orientation == Orientation.PAYSAGE){
		   activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	       //Gdx.graphics.setDisplayMode(480,320,false); //boolean for fullscreen yes or no 
	   }
	   if (orientation == Orientation.PORTRAIT){
		   activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	       //Gdx.graphics.setDisplayMode(320,480,false); //boolean for fullscreen yes or no 
	   }
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public int[] getSize() {
	        if (orientation == Orientation.PAYSAGE){
		  return new int[]{Gdx.graphics.getHeight(),Gdx.graphics.getWidth()}; 
		}
		else if (orientation == Orientation.PORTRAIT){
		  return new int[]{Gdx.graphics.getWidth(),Gdx.graphics.getHeight()}; 
		}
		else return null; 
	}

}

	   /*
           try {
               Thread.sleep(1000);                 //1000 milliseconds is one second.
           } catch(InterruptedException ex) {
           }	
	   */
