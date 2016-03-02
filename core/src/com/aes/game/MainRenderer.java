package com.aes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class MainRenderer implements Screen
 {

	SpriteBatch batch;
	Texture img;
	Stage stage; 
	Skin skin;
	private TextButton myButton;
	private Table table;

	@Override
	public void show() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		// TODO Auto-generated method stub

		stage = new Stage(); 
		Gdx.input.setInputProcessor(stage);


    	skin = new Skin(Gdx.files.internal("json/main.json") , new TextureAtlas("img/ui/white32_pa.atlas") ); 
    	
		// BUTTON 1
    	myButton = new TextButton("Salut les loulou",skin);
		myButton.setColor(0,0,0,1);
    	myButton.addListener(
				new ClickListener(){
    				@Override
    				public void clicked(InputEvent event, float x, float y) {
    				    super.clicked(event,x,y); 
		    			Gdx.app.log("MARTIN Stage", "button click"); 
    				}
    			}
		);

    	
		// TABLE 
    	table = new Table(); 
    	table.setFillParent(true);
    	
    	table.add(myButton);

    	stage.addActor(table);
	}


	@Override
	public void render(float delta) {
		// CLEAR 
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// BATCH 
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		//STAGE 
		stage.act(delta); 

		stage.draw(); 
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose(); 

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
