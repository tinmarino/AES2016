package com.aes.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;



public class AbstractScreen implements Screen, InputProcessor
 {

	 private AbstractScreen nextScreen = null; 
	 private AbstractScreen previousScreen = null; 
	 public boolean goBack   = false; 
	 public boolean goFoward = false; 

	 public String sName = "AbstractScreen";

	 

	 public AbstractScreen getPreviousScreen(){
		 return this.previousScreen; 
	 }

	 public AbstractScreen getNextScreen(){
		 return this.nextScreen; 
	 }


	 public void setPreviousScreen(AbstractScreen abstractScreen){
		 this.previousScreen = abstractScreen;  
	 }

	 public void setNextScreen(AbstractScreen abstractScreen){
		 this.nextScreen = abstractScreen;  
	 }

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
	}

	public void onClick(){
		Gdx.app.log("MARTIN Stage", "Cipher clieck"); 
		switch(3){

		}
	}



	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log("KeyDown : ", "from class " + sName + " for key :" + keycode ); 
				this.goBack = true; 
				Gdx.app.log("Key ", "" + this.getPreviousScreen() ); 
		switch(keycode){
			// GO BACK 
			case Keys.ESCAPE:
			case Keys.BACK:
				Gdx.app.log("Key ", "I try to go back " + this.getPreviousScreen() ); 
				this.goBack = true; 
				return true;
			case Keys.F:
				Gdx.app.log("Key ", "I try to go foward " + this.getPreviousScreen() ); 
				this.goFoward = true; 
				return true;

			// GO FOWARD 
			case Keys.P: 
				Gdx.app.log("", "PreviousScreen is " + this.getPreviousScreen()); 
				return true; 

			// DEBUG KEYS
			case Keys.N: 
				Gdx.app.log("", "Nest Screen is " + this.getNextScreen()); 
				return true; 

			default: 
				Gdx.app.log("", "Default nothing haappens"); 
				return false;
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("Abstract Screen keydown pressed : ", "" + screenX +","+ screenY); 

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	///////////////////////////////////////////////:
}

