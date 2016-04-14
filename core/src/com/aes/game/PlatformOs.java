
package com.aes.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public interface PlatformOs {
	
  public OS getOs();
  public void setOrientation(Orientation orientation);
  public Orientation getOrientation(); 
  public int[] getSize(); 
  
  public BitmapFont getFont(int fontsize);

  public static enum Orientation{
    PORTRAIT,
    PAYSAGE
  }
  public static enum OS{
	DESKTOP,
	ANDROID,
	HTML,
	UNKNOWN
  }


}
