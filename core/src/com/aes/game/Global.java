package com.aes.game;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Clipboard;

public class Global{
	public static InputMultiplexer 	inputMultiplexer; 

	public static PlatformOs 		platformOs;

	public static Clipboard 		clipboard;

	public static Boolean 			bIsDebugging = true;

	public static BitmapFont 		font; 

	public static TextFieldStyle 	tfStyle;
}
