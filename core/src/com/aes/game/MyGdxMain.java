package com.aes.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class MyGdxMain extends Game {
	Screen mainScreen; 
	
	@Override
	public void create () {
		mainScreen = new MainRenderer(); 
		setScreen(mainScreen);  
	}

	@Override
	public void render () {
		super.render(); // Calling mainScreen.render()
	}
}
