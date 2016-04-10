package com.aes.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public class MyAesGame extends Game {
	public  AbstractScreen 		currentScreen; 

	
	
	@Override
	public void create () {

		Global.inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(Global.inputMultiplexer);
		Gdx.input.setInputProcessor(Global.inputMultiplexer);


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
				// Create Future screen 
				AbstractScreen previousScreen = currentScreen.getPreviousScreen();
				previousScreen.setNextScreen(currentScreen);
				Global.inputMultiplexer.addProcessor(previousScreen);
				previousScreen.show(); 

				// Delete Old Scren 
				currentScreen.dispose();
				Global.inputMultiplexer.removeProcessor(currentScreen);

				// Set new screen 
				currentScreen = previousScreen;
				
			}
    	} 

		else if (currentScreen.goFoward) {
			currentScreen.goFoward  = false; 

			if (null != currentScreen.getNextScreen())
			{
				// Create future screen 
				AbstractScreen nextScreen = currentScreen.getNextScreen();
				nextScreen.setPreviousScreen(currentScreen);
				Global.inputMultiplexer.addProcessor(nextScreen);
				nextScreen.show();

				// Remove old screen 
				currentScreen.dispose();
				Global.inputMultiplexer.removeProcessor(currentScreen);

				// Set the new screen 
				currentScreen = nextScreen;
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
