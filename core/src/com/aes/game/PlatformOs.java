
package com.aes.game;

public interface PlatformOs {

  public void setOrientation(Orientation orientation);
  public Orientation getOrientation(); 
  public int[] getSize(); 

  public static enum Orientation{
    PORTRAIT,
    PAYSAGE
  }
}
