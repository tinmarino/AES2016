package com.aes.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public class MyAesGame extends Game {
	public  AbstractScreen 		currentScreen; 

	
	
	@Override
	public void create () {

		Global.inputMultiplexer = new InputMultiplexer();
		// To prevent backKey from returning
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(Global.inputMultiplexer);


		currentScreen = new MainRenderer(); 
		currentScreen.show();
	}

	@Override
	public void render () {
		currentScreen.render(Gdx.graphics.getDeltaTime()); // Calling mainScreen.render()

    	if (currentScreen.goBack) {
			currentScreen.goBack = false; 
			
			Gdx.app.log("TBF MyGdx", " try go Back "+ currentScreen.getPreviousScreen()); 
			if (null != currentScreen.getPreviousScreen())
			{
				// Create Future screen 
				AbstractScreen previousScreen = currentScreen.getPreviousScreen();
				previousScreen.setNextScreen(currentScreen);
				Global.inputMultiplexer.addProcessor(previousScreen);

				// Delete Old Scren 
				currentScreen.pause();
				Global.inputMultiplexer.removeProcessor(currentScreen);

				// Set new screen 
				currentScreen = previousScreen;
				if (currentScreen.state == AbstractScreen.STATE.PAUSE){
					currentScreen.resume();
				}
				else{
					currentScreen.show();
				}
			}
    	} 

		else if (currentScreen.goFoward) {
			currentScreen.goFoward  = false; 

			Gdx.app.log("TBF MyGdx", " try go foward"+ currentScreen.getNextScreen()); 
			if (null != currentScreen.getNextScreen())
			{
				// Create future screen 
				AbstractScreen nextScreen = currentScreen.getNextScreen();
				nextScreen.setPreviousScreen(currentScreen);
				Global.inputMultiplexer.addProcessor(nextScreen);

				// Remove old screen 
				currentScreen.pause();
				Global.inputMultiplexer.removeProcessor(currentScreen);

				// Set the new screen 
				currentScreen = nextScreen;
				currentScreen.show();
			}
    	}

	}

	@Override
	public void resize (int width, int height) {
		if (currentScreen != null) {currentScreen.resize(width, height);}
	}


	@Override
	public void dispose () {
		if (currentScreen != null) currentScreen.hide();
	}

	@Override
	public void pause () {
		if (currentScreen != null) currentScreen.pause();
	}

	@Override
	public void resume () {
		if (currentScreen != null) currentScreen.resume();
	}

}
