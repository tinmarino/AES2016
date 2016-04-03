package com.aes.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aes.game.MyAesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title  = "AES encryptor"; 
		config.width  = 240 * 2; 
		config.height = 320 * 2; 
		//cfg.fullscreen = false;
		new LwjglApplication(new MyAesGame(), config);
	}
}
