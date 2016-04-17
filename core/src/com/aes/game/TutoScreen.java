package com.aes.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TutoScreen extends AbstractScreen{
	ScrollPane 	scrollPane;
	Table 		table;
	Label 		label; 
	TextButton  tbBack; 
	String 		sText;
	List<Disposable> disposableList = new ArrayList<Disposable>();


	@Override
	public void show(){


		// READ 
		FileHandle handle = Gdx.files.internal("text/tuto.txt");
		sText 	= handle.readString();


		// CREATE 
		stage 	= new Stage(new StretchViewport(
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Global.inputMultiplexer.addProcessor(stage);
		disposableList.add(stage);
		label 	= new Label(sText, Global.getTLabelStyle());
		label.setWrap(true);
		tbBack 	= new TextButton("OK", Global.getTextButtonStyle(disposableList));
		tbBack.setColor(GuiParameter.colTButton);
		tbBack.addListener(
				new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event,x,y); 
						(TutoScreen.this).goBack = true; 	
					} 
				}
		);

		// PACK 
		table = new Table();
		table.add(label).expand().fill().row();
		table.add(tbBack).row();
		scrollPane = new ScrollPane(table);
		scrollPane.setFillParent(true);
		stage.addActor(scrollPane);
	}

	@Override 
	public void render(float delta){
		// CLEAR 
		Gdx.gl.glClearColor(GuiParameter.colTClear.r, GuiParameter.colTClear.g, GuiParameter.colTClear.b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// DRAW
		stage.act(delta); 
		stage.draw();
	}

	@Override
	public void resize(int width, int height){
		stage.getViewport().update(width,height);
	}

	@Override
	public void dispose(){
		for (Disposable d : disposableList){
			if (null != d){
				d.dispose();
			}
		}
	}
	
}
