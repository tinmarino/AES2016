
package com.aes.game.desktop;


import com.aes.game.PlatformOs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class PlatformDesktop implements PlatformOs {
		
    public Orientation orientation; 


	@Override
	public OS getOs(){
		return OS.DESKTOP;
	}

	@Override
	public void setOrientation(Orientation orientation) {
	    this.orientation = orientation; 
		if (orientation == Orientation.PAYSAGE){
			Gdx.app.log("PlatformDesktop","I change to desktop paysage");
			Gdx.graphics.setWindowedMode(480,320); 
		}
		if (orientation == Orientation.PORTRAIT){
			Gdx.app.log("PlatformDesktop","I change to desktop portrait");
			Gdx.graphics.setWindowedMode(320,480); 
		}	

	}

	public BitmapFont getFont(int fontsize){

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/UbuntuMono-R.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	 		
		parameter.size 	= fontsize;
		BitmapFont font  			= generator.generateFont(parameter);
		generator.dispose();
		return font; 
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public int[] getSize() {
	     return new int[]{ Gdx.graphics.getWidth(),Gdx.graphics.getHeight()};
	}
	


}
