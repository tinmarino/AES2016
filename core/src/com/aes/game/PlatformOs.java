
package com.aes.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/* PlatforOs : Interface to Abstactize Platform dependant operations
 *     Supported platforms : android (apk), desktop (jar), html (gwt)
 */

public interface PlatformOs {
	
  public OS getOs();
  public void setOrientation(Orientation orientation);
  public Orientation getOrientation(); 
  public int[] getSize(); 
  
  public void runOnUiThread(Runnable runnable);
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
