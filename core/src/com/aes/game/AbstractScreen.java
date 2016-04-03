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
		Gdx.app.log("Abstract Screen keydown mother fucker : ", "" + keycode ); 
				this.goBack = true; 
				Gdx.app.log("Key ", "" + this.getPreviousScreen() ); 
		switch(keycode){
			case Keys.ESCAPE:
				this.goBack = true; 
				Gdx.app.log("Key ", "" + this.getPreviousScreen() ); 
				return true;
			case Keys.F:
				this.goFoward = true; 
				return true;
			default: 
				this.goBack = true; 
				Gdx.app.log("Key aaaaaaaaas", "" + this.getPreviousScreen() ); 
				return true;
				
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

