package com.aes.game;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;





public class MainRenderer extends AbstractScreen
 {
	Stage stage; 
	Skin skin;
	private Table table;
	TextField textFieldClear; 
	TextField textFieldCoded; 
	TextArea  textArea;
	ScrollPane scrollPane;
	ScrollPane scrollPaneRight;
	BitmapFont font; 
	BitmapFont fontButton; 
	Boolean bAreToolsVisible = false;
	ImageTextButton bList;
	
	int w0, w1, w2, ibWidth, ibHeight;
	Table leftTable; 
	Table rightTable; 


	/**
	 *
	 */
	public MainRenderer() {
		sName = "MainRenderer";
	}

	@Override
	public void show() {
		super.show(); 

		switch (Global.platformOs.getOs()){
		case ANDROID : 
			font = new BitmapFont(Gdx.files.internal("font/Ubuntu16White.fnt"));
			fontButton = new BitmapFont(Gdx.files.internal("font/Ubuntu8White.fnt"));
			break;
		default: 
			font = new BitmapFont(Gdx.files.internal("font/Ubuntu32Black.fnt"));
			fontButton = new BitmapFont(Gdx.files.internal("font/Ubuntu16White.fnt"));
			break;
		}
		Global.font = font;

		// Input 
		stage = new Stage(); 
		Global.inputMultiplexer.addProcessor(stage);



		ibWidth  = Gdx.graphics.getWidth() / 8;
		ibHeight = ibWidth;


		bList					= CreateTextButton("", 			RTYPE.LIST);
		ImageTextButton bNull 	= CreateTextButton("", 			RTYPE.NULL);
		ImageTextButton bParam 	= CreateTextButton("param", 	RTYPE.PARAM);
		ImageTextButton bCipher = CreateTextButton("Cipher", 	RTYPE.CIPHER); 
		ImageTextButton bCopy  	= CreateTextButton("copy",		RTYPE.COPY); 
		ImageTextButton bPaste 	= CreateTextButton("paste",		RTYPE.PASTE); 
		ImageTextButton bClear 	= CreateTextButton("clear",		RTYPE.CLEAR); 
		ImageTextButton bDbg	= CreateTextButton("dbg",		RTYPE.DBG); 


		// TEXT AREA
		TextFieldStyle tfStyle = new TextFieldStyle(); 
		tfStyle.font = font;
		tfStyle.cursor= PixmapFactory.getDrawableMonocromatic(2, 16, Color.BLACK);
		tfStyle.selection= PixmapFactory.getDrawableMonocromatic(2, 16, Color.BLUE);
		tfStyle.fontColor = Color.BLACK ;  
		tfStyle.cursor.setMinWidth(2f);
		Global.tfStyle = tfStyle;
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
		
		// LEFT 
		leftTable.add(textArea).expand().fill().center();
		ScrollPaneStyle spStyle = new ScrollPaneStyle();
		spStyle.vScroll = PixmapFactory.getDrawableMonocromatic(5, 25, Color.GRAY) ;
		spStyle.vScrollKnob	= PixmapFactory.getDrawableMonocromatic(10, 10, Color.BLUE) ;
		scrollPane = new ScrollPane(leftTable);
		scrollPane.setScrollingDisabled(true, false);
	    scrollPane.setForceScroll(true, false);
		scrollPane.setOverscroll(false, false);
		
		

		// RIGHT 
    	rightTable.add(bNull	).width(ibWidth).height(ibHeight).pad(2).align(Align.top).fill().row();
    	rightTable.add(bParam	).width(ibWidth).height(ibHeight).pad(2).align(Align.top).fill().row();
    	rightTable.add(bCipher	).width(ibWidth).height(ibHeight).pad(2).align(Align.top).fill().row();
    	rightTable.add(bCopy	).width(ibWidth).height(ibHeight).pad(2).align(Align.top).fill().row(); 
    	rightTable.add(bPaste	).width(ibWidth).height(ibHeight).pad(2).align(Align.top).fill().row(); 
    	rightTable.add(bClear	).width(ibWidth).height(ibHeight).pad(2).align(Align.top).fill().row(); 
    	rightTable.add(bDbg		).width(ibWidth).height(ibHeight).pad(2).align(Align.top).fill().row(); 
    	rightTable.add(bNull	).width(ibWidth).height(Gdx.graphics.getHeight() - 7*(ibHeight+4)).pad(2).align(Align.top).fill().row(); 
		//scrollPaneRight = new ScrollPane(rightTable)	;
	    //scrollPaneRight.setScrollingDisabled(true, false);
		//scrollPaneRight.setFillParent(true);


		// PACK 
		w0 = Math.round(Gdx.graphics.getWidth());
		w1 = Math.round(Gdx.graphics.getWidth() - ibWidth - 4);
		w2 = Math.round(ibWidth + 4);
		

		table.add(scrollPane).width(w0).expand().fill(); 
    	table.setFillParent(true);
    	stage.addActor(table);
		stage.addActor(bList);

		if (Global.bIsDebugging){
			leftTable.debug(); 
			rightTable.debug(); 
			table.debug();
		}

		bList.setX(stage.getWidth() - bList.getWidth());
		bList.setY(stage.getHeight()- bList.getHeight());

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


	public ImageTextButton CreateTextButton(String label, RTYPE routineType){

		//Color cButton = HtmlColor.MidnightBlue; 
		ImageTextButtonStyle itbStyle = new ImageTextButtonStyle(); 
		// NINE PATCH 
		switch(routineType){
			case LIST:
				new Color();
				itbStyle.up = PixmapFactory.getDrawableMonocromatic(1,1,new Color(1,1,1f,0.01f));
				itbStyle.down = PixmapFactory.getDrawableMonocromatic(1,1,new Color(1,1,1,0.5f));
				break;
			case NULL:
				break;
			default:
			 	Texture t = new Texture(PixmapFactory.circleDrawPixel(64, HtmlColor.SkyBlue));
				itbStyle.up   = PixmapFactory.ninePatchFromTexture(t);
				itbStyle.down = PixmapFactory.getDrawableMonocromatic(1,1,HtmlColor.MidnightBlue);
				break;
		}
		itbStyle.font = fontButton; 
		itbStyle.fontColor = Color.BLACK;
		switch (routineType){
			case PARAM: 	
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/tool128.png");
				break; 
			case CIPHER:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/key128.png");
				break; 
			case CLEAR:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/trash128.png");
				break;
			case COPY:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/copy128.png");
				break;
			case PASTE:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/paste128.png");
				break;
			case DBG:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/tool128.png");
				break;
			case LIST:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/list128.png");
				break;
			case NULL:
				break;
		}
		
		// IMAGETEXTBUTTON
		//optionla public Drawable imageUp, imageDown, imageOver, imageChecked, imageCheckedOver, imageDisabled;
		// FROM TEXTBUTTON 
		//public BitmapFont font;
		/** Optional. */
		//dpublic Color fontColor, downFontColor, overFontColor, checkedFontColor, checkedOverFontColor, disabledFontColor;
		
			// Form button style 
		/** Optional. */
		//public Drawable up, down, over, checked, checkedOver, disabled;
		/** Optional. */
		//public float pressedOffsetX, pressedOffsetY, unpressedOffsetX, unpressedOffsetY;
		
		// BUTTON PARAM  TODO replace wirth a image 
		final RTYPE crType = routineType;
		ImageTextButton bRes = new ImageTextButton(label, itbStyle); 
		bRes.setWidth(ibWidth - 4);
		bRes.setHeight(ibHeight -4);
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

		// ReJUSTIFY 
		bRes.clearChildren();
		bRes.add(bRes.getImage()).row();
		bRes.add(bRes.getLabel());
		if (RTYPE.NULL == routineType){bRes.setDisabled(true);}
		return bRes;
	}

	public enum RTYPE{PARAM, CIPHER, CLEAR, PASTE, COPY, DBG, LIST, NULL}
	public void routineDispatch(RTYPE routineType){
		switch (routineType){
			case LIST: 		routineList(); 		break; 
			case PARAM: 	routineParam(); 	break; 
			case CIPHER:	routineCipher(); 	break; 
			case CLEAR: 	routineClear(); 	break;
			case COPY:		routineCopy();		break;
			case PASTE:		routinePaste();		break;
			case DBG:		routineDbg();		break;
			case NULL:		routineDbg();		break;
		}

	}

	public void routineList(){
		// DELETE TOOL scrollpane
		if (bAreToolsVisible){
			table.reset();
			table.add(scrollPane).width(w0).expand().fill(); 
			bList.remove();
			stage.addActor(bList);
			
		}
		// ADD TOOL ScrollPane
		else{
			table.reset();
			table.add(scrollPane).width(w1).expandY().fill(); 
			table.add(rightTable).width(w2).height(Gdx.graphics.getHeight()).expandY().align(Align.top).top().fill(); 
			bList.remove();
			stage.addActor(bList);
		}
		bAreToolsVisible = !bAreToolsVisible;

		//bList.setHeight(ibHeight);
		//bList.setWidth(ibHeight);
		//bList.setX(stage.getWidth() - bList.getWidth());
		//bList.setY(stage.getHeight()- bList.getHeight());
	}

	public void routineParam(){
		this.setNextScreen(new ParamScreen()); 
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
		Gdx.app.log("TBF", "Pixel DEnsity  " +   Gdx.graphics.getDensity());
		//textArea.setHeight(1000);
		//scrollPane.layout();
		scrollPane.setScrollPercentX( scrollPane.getScrollPercentX() + 10 );
	}

}


