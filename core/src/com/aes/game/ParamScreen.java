package com.aes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ParamScreen extends AbstractScreen {

	Stage stage; 

	ParamScreen(Skin skin){
		super(); 
	}


	@Override
	public void show() {
		stage = new Stage(); 
		Table table = new Table(); 

		
		
    	stage.addActor(table);

	}

	@Override
	public void render(float delta) {
		// CLEAR
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// STAGE act 
		stage.act(delta); 
		stage.draw(); 

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
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}



}
