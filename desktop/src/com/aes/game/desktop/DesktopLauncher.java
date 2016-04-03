package com.aes.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aes.game.MyAesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title  = "AES encrypotor"; 
		config.width  = 240; 
		config.height = 320; 
		new LwjglApplication(new MyAesGame(), config);
	}
}
