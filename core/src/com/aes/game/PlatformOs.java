
package com.aes.game;

public interface PlatformOs {
	
  public OS getOs();
  public void setOrientation(Orientation orientation);
  public Orientation getOrientation(); 
  public int[] getSize(); 

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
