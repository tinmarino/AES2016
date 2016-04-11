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
	 public STATE state = STATE.UNINITIALISED;

	 public enum STATE{PAUSE, UNINITIALISED, RUN}



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
		state = STATE.UNINITIALISED;
	}


	@Override
	public void pause() {
		this.state = STATE.PAUSE;

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
		state = STATE.RUN;

	}

	@Override
	public void show() {
		state = STATE.RUN;
	}

	public void onClick(){
		Gdx.app.log("MARTIN Stage", "Cipher clieck"); 
		switch(3){

		}
	}



	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log("TBF", "Key down from class " + sName + " for key :" + keycode ); 
		switch(keycode){
			// GO BACK 
			case Keys.BACK:
			case Keys.ESCAPE:
				this.goBack = true; 
				return true;
			case Keys.F:
				this.goFoward = true; 
				return true;

			// GO FOWARD 
			case Keys.P: 
				Gdx.app.log("TBF", "PreviousScreen is " + this.getPreviousScreen()); 
				return true; 

			// DEBUG KEYS
			case Keys.N: 
				Gdx.app.log("TBF", "Nest Screen is " + this.getNextScreen()); 
				return true; 

			default: 
				Gdx.app.log("TBF", "Default nothing haappens"); 
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

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	///////////////////////////////////////////////:
}

