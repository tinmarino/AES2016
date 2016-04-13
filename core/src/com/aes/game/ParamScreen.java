package com.aes.game;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

public class ParamScreen extends AbstractScreen {

	// DISPOSABLE 
	Stage stage; 
	List<Disposable> disposableList = new ArrayList<Disposable>();

	ScrollPane 	scrollPane, keyScrollPane;  
	Table      	table;
	CheckBox 	cb1, cb2, cb3, cb4;
	TextField 	tfNewKey, tfNewLabel;
	

    float sWidth ;  // slider width 
	float sHeight;

	/**
	 *
	 */
	public ParamScreen() {
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show(){
		stage = new Stage(); 
		Global.inputMultiplexer.addProcessor(stage);
		table = new Table();

		// STYLES 
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = Global.font; 
		lStyle.fontColor = Color.BLACK;

		LabelStyle lStyleChapter = new LabelStyle();
		lStyleChapter.font = Global.font; 
		lStyleChapter.fontColor = Color.BLUE;

		TextFieldStyle tfStyle = new TextFieldStyle(); 
		tfStyle.font = Global.font;
		tfStyle.cursor= PixmapFactory.getDrawableMonocromatic(2, 16, Color.BLACK, disposableList);
		tfStyle.selection= PixmapFactory.getDrawableMonocromatic(2, 16, Color.BLUE, disposableList);
		tfStyle.fontColor = Color.BLACK ;  
		Texture t = new Texture( PixmapFactory.circle(16,Color.GRAY));
		disposableList.add(t);
		tfStyle.background = PixmapFactory.ninePatchFromTexture(t);
		tfStyle.cursor.setMinWidth(2f);

		CheckBoxStyle cbStyle = new CheckBoxStyle();
		cbStyle.font = Global.font; 
		cbStyle.fontColor = Color.BLACK;
		cbStyle.checkboxOff = PixmapFactory.drawableCheckBoxOff(disposableList);
		cbStyle.checkboxOn =  PixmapFactory.drawableCheckBoxOn(disposableList);

		TextButtonStyle tbStyle = new TextButtonStyle();
		tbStyle.font 			= Global.font; 
		tbStyle.fontColor 		= Color.BLACK;
		Texture t1 				= new Texture(PixmapFactory.circle(32, Color.GRAY));
		tbStyle.up 				= PixmapFactory.ninePatchFromTexture(t1); 




		// KEY  : Saved keys 
		Table tKey = new Table(); 
		for (KeyObject keyObject : Global.preferenceSaved.keyList){
			final KeyObject keyObjectSaved = keyObject;
			TextButton  lKey = new TextButton(keyObject.label, tbStyle);
			lKey.addListener(
					new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event,x,y); 
							routineLabelKey(keyObjectSaved); 
						} 
					}
			);
			tKey.add(lKey).expandX().fill().row();
		}
	
		// NEW KEY : tNewKey 
		// New Key : key field 
		Table 		tNewKey 	= new Table();
		Label 		lNewKey 	= new Label("Key:",lStyle);
					tfNewKey 	= new TextField("", tfStyle);
		tNewKey.add(lNewKey).align(Align.left);
		tNewKey.add(tfNewKey).align(Align.left).expandX().fill().row();
		
		// New Key : Label field 
		Label 		lNewLabel 	= new Label("Label:",lStyle);
					tfNewLabel 	= new TextField("", tfStyle);
		tNewKey.add(lNewLabel).align(Align.left);
		tNewKey.add(tfNewLabel).align(Align.left).expandX().fill().row();
		// New Key : TExtbuttn Saved new Key 
		TextButton tbSaveNewKey = new TextButton("SaveKey", tbStyle);
		tbSaveNewKey.addListener(
					new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event,x,y); 
							final KeyObject keyObject = new KeyObject();
							keyObject.clearKey 	= tfNewKey.getText();
							keyObject.label 	= tfNewLabel.getText();
							routineSaveNewKey(keyObject); 
						} 
					}
		);
		// New Key : TExtbuttn Use new Key 
		TextButton tbUseNewKey = new TextButton("UseKey", tbStyle);
		tbUseNewKey.addListener(
					new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event,x,y); 
							final KeyObject keyObject = new KeyObject();
							keyObject.clearKey 	= tfNewKey.getText();
							keyObject.label 	= tfNewLabel.getText();
							routineUseNewKey(keyObject); 
						} 
					}
		);
		Table tUseSave = new Table();
		tUseSave.add(tbUseNewKey);
		tUseSave.add(tbSaveNewKey);
		tNewKey.add(tUseSave).colspan(2).expandX().row();
		tKey.add(tNewKey).expandX().fill().row();
		

		ScrollPaneStyle spStyle = Global.getScrollPaneStyle(disposableList);
		keyScrollPane = new ScrollPane(tKey, spStyle);
		keyScrollPane.setHeight(lNewKey.getHeight()*3);


		// CIPHER type 
	    cb1 = new CheckBox(" AES ", cbStyle); 
		cb1.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				routineCipherType(Global.CTYPE.AES);
			}
		});
	    cb2 = new CheckBox(" TBF ", cbStyle); 
		cb1.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				routineCipherType(Global.CTYPE.TBF);
			}
		});

		// IS DEBUGGING 
	    CheckBox isDebuggingCheckBox = new CheckBox(" Debug ", cbStyle); 
		isDebuggingCheckBox.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("TBF", "CheckBox IsDebuggin");
			}
		});


		// BACK and EXIT Button 
		TextButton tbBack = new TextButton("Back", tbStyle);
		tbBack.addListener(
					new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							(ParamScreen.this).goBack = true; 	
						} 
					}
		);
		TextButton tbExit = new TextButton("Exit", tbStyle);
		tbExit.addListener(
					new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							Gdx.app.exit();
						} 
					}
		);
		Table tBackExit = new Table(); 
		tBackExit.add(tbBack).pad(4);
		tBackExit.add(tbExit).pad(4);



		// SAVE PARAMS 

		
		// PACK 
		Table tCipherType = new Table();
		tCipherType.add(cb1).expandX().fill().row();
		tCipherType.add(cb2).expandX().fill().row();

		int iKeyScrollPaneHeight = (int) Math.max(Gdx.graphics.getWidth()/4, lNewKey.getHeight()*5) ;
		table.add(new Label("Key---------------------------------", lStyleChapter) ).expandX().fill().row();
		table.add(keyScrollPane).height(iKeyScrollPaneHeight).expandX().fill().row();
		table.add(new Label("Misc--------------------------------", lStyleChapter) ).expandX().fill().row();
		table.add(tCipherType).expandX().fill().row();
		table.add(isDebuggingCheckBox).expandX().fill().row();
		table.add(new Label("Return------------------------------", lStyleChapter) ).expandX().fill().row();
		table.add(tBackExit).row();
		table.add(new Label("------", lStyleChapter) ).expandX().fill().row();
		//for(int  i= 0; i<30; i++){
		//	table.add(new Label("Alea jacta est",lStyle)).row();
		//}


		scrollPane = new ScrollPane(table); 
		scrollPane.setFillParent(true);
		stage.addActor(scrollPane);
	}

	@Override
	public void dispose(){
		super.dispose();
		if (null != stage){
			stage.dispose(); 
			Global.inputMultiplexer.removeProcessor(stage);
		}
		for (Disposable d : disposableList){
			if (null != d){
				d.dispose();
			}
		}
	}



	@Override
	public void pause(){
		super.pause();
		if (null != stage){
			Global.inputMultiplexer.removeProcessor(stage);
		}
	}

	@Override
	public void resume(){
		super.resume(); 
		if (null != stage){
			Global.inputMultiplexer.addProcessor(stage);
		}
	}

	public void routineCipherType(Global.CTYPE cType){
		Gdx.app.log("TBF", "routine cihper type");
	}

	public void routineLabelKey(KeyObject keyObject){
		Gdx.app.log("TBF", "You choosed key:" + keyObject.label);
		Global.keyObject = keyObject;
	}

	public void routineSaveNewKey(KeyObject keyObject){
		keyObject.cipherKey();		
		Global.preferenceSaved.keyList.add(keyObject);
		Global.writePref();
		Global.keyObject = keyObject;
	}

	public void routineUseNewKey(KeyObject keyObject){
		keyObject.cipherKey();
		Global.keyObject = keyObject;
	}

}
