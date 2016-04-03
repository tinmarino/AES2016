package com.aes.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGdxMain extends Game {
	public  AbstractScreen currentScreen; 

	
	
	@Override
	public void create () {
		currentScreen = new MainRenderer(); 
		currentScreen.show();
	}

	@Override
	public void render () {
		currentScreen.render(Gdx.graphics.getDeltaTime()); // Calling mainScreen.render()

    	if (currentScreen.goBack) {
			currentScreen.goBack = false; 
			
			Gdx.app.log("MARTIN MyGdx", " try go Back "); 
			if (null != currentScreen.getPreviousScreen())
			{
				AbstractScreen previousScreen = currentScreen.getPreviousScreen();
				previousScreen.setNextScreen(currentScreen);
				previousScreen.show(); 
				currentScreen = previousScreen;
			}
    	} 

		else if (currentScreen.goFoward) {
			currentScreen.goFoward  = false; 

			if (null != currentScreen.getNextScreen())
			{
				AbstractScreen nextScreen = currentScreen.getNextScreen();
				nextScreen.setPreviousScreen(currentScreen);
				nextScreen.show();
				currentScreen = nextScreen;
			}
    	}

	}


}
