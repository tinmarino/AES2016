package com.aes.game;

import com.badlogic.gdx.Screen;

public class AbstractScreen implements Screen
 {

	 private AbstractScreen nextScreen; 
	 private AbstractScreen previousScreen; 
	 public boolean goBack   = false; 
	 public boolean goFoward = false; 

	 public Screen getPreviousScreen(){
		 return previousScreen; 
	 }

	 public Screen getNextScreen(){
		 return nextScreen; 
	 }

	 public void setPreviousScreen(AbstractScreen abstractScreen){
		 this.previousScreen = abstractScreen;  
	 }

	 public void setNextScreen(AbstractScreen abstractScreen){
		 nextScreen = abstractScreen;  
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
		// TODO Auto-generated method stub

	}
}
