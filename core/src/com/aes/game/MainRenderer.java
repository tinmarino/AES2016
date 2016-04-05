package com.aes.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class MainRenderer extends AbstractScreen
 {

	
	SpriteBatch batch;
	Texture img;
	Stage stage; 
	Skin skin;
	private Table table;
	TextField textFieldClear; 
	TextField textFieldCoded; 
	TextArea  textArea;


	Table leftTable; 
	Table rightTable; 

	// BUTTON params 
	int ibWidth  = 150;
	int ibHeight = 50;
	Color cButton = new Color(0.5f, 0.5f, 0.5f, 1); 

	/**
	 *
	 */
	public MainRenderer() {
		sName = "MainRenderer";
	}

	@Override
	public void show() {
		super.show(); 
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		// TODO Auto-generated method stub

		// Input 
		stage = new Stage(); 

		Global.inputMultiplexer.addProcessor(stage);



    	skin = new Skin(Gdx.files.internal("json/main.json") , new TextureAtlas("img/ui/white32_pa.atlas") ); 
    	


		// BUTTON PARAM  TODO replace wirth a image 
		TextButton bParam = new TextButton("params", skin); 
		bParam.setColor(cButton);
    	bParam.addListener(
				new ClickListener(){
    				@Override
    				public void clicked(InputEvent event, float x, float y) {
    				    super.clicked(event,x,y); 
		    			Gdx.app.log("MARTIN Stage", "button click"); 
						routineParam(); 
    				} 
    			}
		);
		//bParam.setFillParent(true);




		// Cippher
		TextButton bCipher = new TextButton("Cipher", skin); 
		bCipher.setColor(cButton);
    	bCipher.addListener(
				new ClickListener(){
    				@Override
    				public void clicked(InputEvent event, float x, float y) {
    				    super.clicked(event,x,y); 
		    			Gdx.app.log("MARTIN Stage", "Cipher clieck"); 
    				} 
    			}
		);
		//bCipher.setFillParent(true); 


		// ToClipboard button to copy to clipboard 
		TextButton bToClipboard = new TextButton("copy", skin); 
		bToClipboard.setColor(cButton);
    	bToClipboard.addListener(
				new ClickListener(){
    				@Override
    				public void clicked(InputEvent event, float x, float y) {
    				    super.clicked(event,x,y); 
		    			Gdx.app.log("MARTIN Stage", "click copy "); 
    				} 
    			}
		);
		//bToClipboard.setFillParent(true); 



		// TextField1
		textFieldClear = new TextField("This is a raw input ", skin);

		textFieldCoded = new TextField("This will be the crippted text", skin);

		TextFieldStyle tfStyle = new TextFieldStyle(); 
		tfStyle.font = skin.getFont("default");
		tfStyle.fontColor = Color.BLACK ;  
		//tfStyle.background = skin.getDrawable("white31") ; 
		//tfStyle.cursor = skin.newDrawable("white32", Color.BLACK);
		tfStyle.cursor = PixmapFactory.getCursorDrawable();
		tfStyle.cursor.setMinWidth(2f);
		
		tfStyle.selection = PixmapFactory.drawableSelection();
//skin.newDrawable("dialogDim", 0, 0, 1, 0.5f); //skin.newDrawable("white32", 0.5f, 0.5f, 0.5f, 0.5f);


		textArea 	= new TextArea("TextAreza", tfStyle); 
		textArea.setPrefRows(3); 
		//textArea.cursor = PixmapFactory.drawableSelection();
//skin.getFont("default").setScale(0.5f, 0.5f);	
		//textArea.cursor = skin.newDrawable("up",0,0,1,1 ); 
		

	

    	
		// TABLE 
    	table = new Table(); 
		leftTable = new Table(); 
		rightTable = new Table(); 
		
		leftTable.add(textArea).expand().fill();
		//leftTable.setFillParent(true); 
		//leftTable.row();
		//leftTable.add(textFieldCoded).expand().fill();
		

		// RIGHT 
    	rightTable.add(bParam		).width(ibWidth).height(ibHeight).pad(10).expand().fill().row();
    	rightTable.add(bCipher		).width(ibWidth).height(ibHeight).pad(10).expand().fill().row();
    	rightTable.add(bToClipboard	).width(ibWidth).height(ibHeight).pad(10).expand().fill().row(); 
		//rightTable.setFillParent(true);

		int w1 = Math.round(Gdx.graphics.getWidth() * 0.7f);
		int w2 = Math.round(Gdx.graphics.getWidth() * 0.3f);

		
		table.add(leftTable ).width(w1).expand().fill(); 
		table.add(rightTable).width(w2).expand().fill(); 
    	table.setFillParent(true);
    	stage.addActor(table);
		leftTable.debug(); 
		rightTable.debug(); 
		table.debug();

	}


	@Override
	public void render(float delta) {
		// CLEAR 
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//STAGE 
		//stage.act(delta); 
		stage.draw(); 
		//Table.drawDebug( stage);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.log("Main Renderer :", "Dispose()"); 
		if (null != stage){
			Global.inputMultiplexer.addProcessor(stage);
			stage.dispose(); 
		}
		if (null != skin){
			skin.dispose();
		}

	}
	@Override
	public void hide() {
		dispose();
	}



	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		// TODO Auto-generated method stub

	}



	public void routineParam(){
		this.setNextScreen(new ParamScreen(skin)); 
		this.goFoward = true; 
	}
}
