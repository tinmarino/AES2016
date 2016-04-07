package com.aes.game;


import com.aes.game.PlatformOs.OS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
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
	ScrollPane scrollPane;


	Table leftTable; 
	Table rightTable; 

	// BUTTON params 
	//ibWidth = ibWidth + 1;
	//if (Global.platformOs.getOs() == OS.ANDROID){
	//	ibWidth = ibWidth / 2;
	//}


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
    	
		int ibWidth  = 150;
		if (Global.platformOs.getOs() == OS.ANDROID){ibWidth /= 2;}
		int ibHeight = 50;


		TextButton bParam 	= CreateTextButton("param", 	RTYPE.PARAM);
		TextButton bCipher 	= CreateTextButton("Cipher", 	RTYPE.CIPHER); 
		TextButton bCopy  	= CreateTextButton("copy",		RTYPE.COPY); 
		TextButton bPaste 	= CreateTextButton("paste",		RTYPE.PASTE); 
		TextButton bDbg		= CreateTextButton("dbg",		RTYPE.DBG); 



		// TEXT AREA
		TextFieldStyle tfStyle = new TextFieldStyle(); 
		switch (Global.platformOs.getOs()){
		case ANDROID : 
			tfStyle.font = skin.getFont("android");
			break;
		default: 
			tfStyle.font = skin.getFont("default");
			break;

		}

		tfStyle.cursor= PixmapFactory.getDrawableMonocromatic(2, 16, Color.BLACK);
		tfStyle.selection= PixmapFactory.getDrawableMonocromatic(2, 16, Color.BLUE);
		tfStyle.fontColor = Color.BLACK ;  
		tfStyle.cursor.setMinWidth(2f);
		//tfStyle.background = skin.getDrawable("white31") ; 
		

		textArea 	= new TextArea("TextAreza this ssssis \n zaeazeazeaz very long \n\n\n\n\n\n\n\nn\n\nsetring\n\n\n\n\nn\n\n\n\nnEND", tfStyle); 
		textArea.setPrefRows(3); 
		textArea.setTextFieldListener(new TextFieldListener()
		{
			@Override
			public void keyTyped(TextField textField, char c)
			{
				// Handle a newline properly. If not handled here, the TextField
				// will advance to the next field.
					if (c == '\n')
					{
						textArea.appendText("\n");
						stage.setKeyboardFocus(textField);
						textArea.getOnscreenKeyboard().show(true);
						scrollPane.layout();
					}
				}
			});

			// TODO Auto-generated method stub
			//textArea.cursor = PixmapFactory.drawableSelection();
//skin.getFont("default").setScale(0.5f, 0.5f);	
		//textArea.cursor = skin.newDrawable("up",0,0,1,1 ); 
		textArea.setPrefRows(50);
		

	

    	
		// TABLE 
    	table = new Table(); 
		leftTable = new Table(); 
		rightTable = new Table(); 
		
		leftTable.add(textArea).expand().fill().center().row();
		//leftTable.setFillParent(true); 
		//leftTable.row();
		//leftTable.add(textFieldCoded).expand().fill();
		
		//SCROLLPANE 
		ScrollPaneStyle spStyle = new ScrollPaneStyle();
		spStyle.vScroll = PixmapFactory.getDrawableMonocromatic(5, 25, Color.GRAY) ;
		spStyle.vScrollKnob	= PixmapFactory.getDrawableMonocromatic(10, 10, Color.BLUE) ;
		scrollPane = new ScrollPane(leftTable);
		scrollPane.setScrollingDisabled(true, false);
	    scrollPane.setForceScroll(true, false);
		scrollPane.setOverscroll(false, false);
		

		

		// RIGHT 
    	rightTable.add(bParam	).width(ibWidth).height(ibHeight).pad(10).expand().fill().row();
    	rightTable.add(bCipher	).width(ibWidth).height(ibHeight).pad(10).expand().fill().row();
    	rightTable.add(bCopy	).width(ibWidth).height(ibHeight).pad(10).expand().fill().row(); 
    	rightTable.add(bPaste	).width(ibWidth).height(ibHeight).pad(10).expand().fill().row(); 
    	rightTable.add(bDbg		).width(ibWidth).height(ibHeight).pad(10).expand().fill().row(); 
		//rightTable.setFillParent(true);

		int w1 = Math.round(Gdx.graphics.getWidth() * 0.7f);
		int w2 = Math.round(Gdx.graphics.getWidth() * 0.3f);

		
		table.add(scrollPane).width(w1).expand().fill(); 
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
		stage.act(); 
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


		public TextButton CreateTextButton(String label, RTYPE routineType){
		Color cButton = new Color(0.5f, 0.5f, 0.5f, 1); 
				// BUTTON PARAM  TODO replace wirth a image 
				final RTYPE crType = routineType;
				TextButton bRes = new TextButton(label, skin); 
				bRes.setColor(cButton);
    			bRes.addListener(
						new ClickListener(){
    						@Override
    						public void clicked(InputEvent event, float x, float y) {
    						    super.clicked(event,x,y); 
				    			Gdx.app.log("MARTIN Stage", "button click"); 
								routineDispatch(crType); 
    						} 
    					}
				);
				return bRes;
		}

	public enum RTYPE{PARAM, CIPHER, CLEAR, PASTE, COPY, DBG}
	public void routineDispatch(RTYPE routineType){
		switch (routineType){
			case PARAM: 	routineParam(); 	break; 
			case CIPHER:	routineCipher(); 	break; 
			case CLEAR: 	routineClear(); 	break;
			case COPY:		routineCopy();		break;
			case PASTE:		routinePaste();		break;
			case DBG:		routineDbg();		break;
		}

	}

	public void routineParam(){
		this.setNextScreen(new ParamScreen(skin)); 
		this.goFoward = true; 
	}


	public void routineCipher(){
		String sIn = textArea.getText(); 
		String sOut = MyCipher.CipherMain(sIn);
		textArea.setText( sOut );
		return; 
	}

		//this.setNextScreen(new ScrollPaneTest());
		//this.goFoward = true;


	public void routineClear(){
		textArea.setText("");
	}

	public void routinePaste(){
		textArea.appendText(Global.clipboard.getContents());
	}

	public void routineCopy(){
		Global.clipboard.setContents(textArea.getText());
	}

	public void routineDbg(){
		Gdx.app.log("TBF", "scrollPane" +   scrollPane.getScrollPercentX() );
		Gdx.app.log("TBF", "text height " +   textArea.getHeight() );
		Gdx.app.log("TBF", "line number  " +   textArea.getLines() );
		Gdx.app.log("TBF", "the content of the clipboard is " + Global.clipboard.getContents());
		//textArea.setHeight(1000);
		//scrollPane.layout();
		scrollPane.setScrollPercentX( scrollPane.getScrollPercentX() + 10 );
	}

}


