package com.aes.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglClipboard;
import com.aes.game.Global;
import com.aes.game.MyAesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title  = "AES encryptor"; 
		config.vSyncEnabled = true;
		config.width  = 240 * 2; 
		config.height = 320 * 2; 

		config.addIcon("img/icon/crypto256.png", FileType.Internal);
		config.addIcon("img/icon/crypto128.png", FileType.Internal);
		config.addIcon("img/icon/crypto64.png", FileType.Internal);
		config.addIcon("img/icon/crypto32.png", FileType.Internal);

		Global.platformOs = new PlatformDesktop();
		Global.clipboard  = new LwjglClipboard();
		//cfg.fullscreen = false;
		new LwjglApplication(new MyAesGame(), config);
	}
}
