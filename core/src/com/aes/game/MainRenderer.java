package com.aes.game;


import java.util.ArrayList;
import java.util.List;

import com.aes.game.PlatformOs.OS;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;





public class MainRenderer extends AbstractScreen
 {
	Stage 			stage; 
	TextArea  		textArea;
	ScrollPane 		scrollPane, scrollPaneRight;
	Table 			table, leftTable, rightTable, rightTopTable, rightBottomTable; 
	BitmapFont 		font, fontButton; 
	Boolean 		bAreToolsVisible = false;
	ImageTextButton bList;
	List<Disposable> disposableList = new ArrayList<Disposable>();

	
	int w0, w1, w2, h0, ibWidth;


	/**
	 *
	 */
	public MainRenderer() {
		sName = "MainRenderer";
	}

	@Override
	public void show() {
		super.show(); 


		// PARAMS 
		ibWidth  	= Math.max(40, Math.round(Gdx.graphics.getWidth() / 8));

		w0 			= Math.round(Gdx.graphics.getWidth());
		w1 			= Math.round(Gdx.graphics.getWidth() - ibWidth - 4);
		w2 			= Math.round(ibWidth + 4);
		h0 			= Math.round(Gdx.graphics.getHeight());

		font 		= Global.font;
		fontButton 	= Global.fontButton;
		// TABLE 
    	table 		= new Table(); 
		leftTable 	= new Table(); 
		rightTable 	= new Table(); 
		rightBottomTable = new Table(); 
		rightTopTable = new Table(); 


		// STAGE 
		stage = new Stage(); 
		stage.setViewport(new StretchViewport(w0, h0));
		Global.inputMultiplexer.addProcessor(stage);
		disposableList.add(stage);

		// BUTTONS
		bList					= CreateTextButton("", 			RTYPE.LIST);
		ImageTextButton bNull 	= CreateTextButton("", 			RTYPE.NULL);
		ImageTextButton bParam 	= CreateTextButton("param", 	RTYPE.PARAM);
		ImageTextButton bCipher = CreateTextButton("Cipher", 	RTYPE.CIPHER); 
		ImageTextButton bCopy  	= CreateTextButton("copy",		RTYPE.COPY); 
		ImageTextButton bPaste 	= CreateTextButton("paste",		RTYPE.PASTE); 
		ImageTextButton bClear 	= CreateTextButton("clear",		RTYPE.CLEAR); 
		ImageTextButton bDbg	= CreateTextButton("dbg",		RTYPE.DBG); 
		ImageTextButton bExit	= CreateTextButton("exit",		RTYPE.EXIT); 


		// TEXT AREA
		TextFieldStyle tfStyle = Global.getTextFieldStyle(disposableList);

		textArea 	= new TextArea("TextAreza this ssssis \n zaeazeazeaz very long \n\n\n\n\n\n\n\nn\n\nsetring\n\n\n\n\nn\n\n\n\nnEND", tfStyle); 
		textArea.setPrefRows(textArea.getLines());
		textArea.setTextFieldListener(new TextFieldListener()
		{
			@Override
			public void keyTyped(TextField textArea, char c)
			{
				// Handle a newline properly. If not handled here, the TextField
				// will advance to the next field.
					Gdx.app.log("TBF", "Key pressed in textArea :" + c +  "+ " +((int) c) +"+"+ ((int)'\n')  );
					if (c == '\n' || c == '\r' || 8 == c || 127 == c)
					{
						int i = textArea.getCursorPosition();
						stage.setKeyboardFocus(textArea);
						textArea.getOnscreenKeyboard().show(true);
						if (c == '\n' || c == '\r'){textArea.appendText("\n");} // otherwise, adding 2 lines
						else{textArea.appendText("\b");}
						((TextArea)textArea).setPrefRows(((TextArea)textArea).getLines() +1);
						scrollPane.layout();
						textArea.setCursorPosition(i);
						Gdx.app.log("TBF", "Enter pressed in textArea" + textArea.getHeight() + "+" + textArea.getY());
					}
			}
		});


		
		// PACK LEFT TABLE
		ScrollPaneStyle spStyle = Global.getScrollPaneStyle(disposableList);
		scrollPane = new ScrollPane(textArea, spStyle);
		scrollPane.setScrollingDisabled(true, false);
		leftTable.add(scrollPane).expand().fill().center();
		
		

		// PACK RIGHT TABLE
    	rightBottomTable.add(bParam		).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row();
    	rightBottomTable.add(bCipher	).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row();
    	rightBottomTable.add(bCopy		).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row(); 
    	rightBottomTable.add(bPaste		).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row(); 
    	rightBottomTable.add(bClear		).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row(); 
    	rightBottomTable.add(bDbg		).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row(); 
    	rightBottomTable.add(bExit		).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row(); 
    	rightBottomTable.add(bNull	).width(ibWidth).height(Gdx.graphics.getHeight() - 8*(ibWidth+4)).pad(2).align(Align.top).fill().row(); 
		scrollPaneRight = new ScrollPane(rightBottomTable)	;
	    scrollPaneRight.setScrollingDisabled(true, false);
    	rightTable.add(bNull		).width(ibWidth).height(ibWidth).pad(2).align(Align.top).fill().row();
		rightTable.add(scrollPaneRight);


		
		// Main Table packed in routineList
    	table.setFillParent(true);
    	stage.addActor(table);

		if (Global.bIsDebugging){
			leftTable.debug(); 
			rightTable.debug(); 
			rightBottomTable.debug(); 
			rightTopTable.debug(); 
			table.debug();
		}

		bList.setX(stage.getWidth() - bList.getWidth());
		bList.setY(stage.getHeight()- bList.getHeight());
		routineList();

	}


	@Override
	public void render(float delta) {
		// CLEAR 
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// DRAW
		stage.act(); 
		stage.draw(); 
	}

	@Override
	public void dispose() {
		Gdx.app.log("Main Renderer :", "Dispose()"); 
		if (null != stage){
			Global.inputMultiplexer.removeProcessor(stage);
			stage.dispose(); 
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



	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		w0 = Math.round(stage.getWidth());
		w1 = Math.round(stage.getWidth() - ibWidth - 4);
		w2 = Math.round(ibWidth + 4);
		h0 = height;
		bList.setX(stage.getWidth() - bList.getWidth());
		bList.setY(stage.getHeight()- bList.getHeight());
		scrollPane.setBounds(0, 0, w1, h0);
		rightBottomTable.setBounds(w1, 0, w0, h0-3*ibWidth);

		bAreToolsVisible = !bAreToolsVisible;
		routineList();
	}


	public ImageTextButton CreateTextButton(String label, RTYPE routineType){

		// 1/ STYLE 
		ImageTextButtonStyle itbStyle = new ImageTextButtonStyle(); 
		itbStyle.font = fontButton; 
		itbStyle.fontColor = Color.BLACK;
		switch(routineType){
			case LIST:
				new Color();
				itbStyle.up = PixmapFactory.getDrawableMonocromatic(1,1,new Color(1,1,1f,0.01f), disposableList);
				itbStyle.down = PixmapFactory.getDrawableMonocromatic(1,1,new Color(1,1,1,0.5f), disposableList);
				break;
			case NULL:
				break;
			default:
			 	Texture t = new Texture(PixmapFactory.circle(16, HtmlColor.SkyBlue));
				disposableList.add(t);
				itbStyle.up   = PixmapFactory.ninePatchFromTexture(t);
				itbStyle.down = PixmapFactory.getDrawableMonocromatic(1,1,HtmlColor.MidnightBlue, disposableList);
				break;
		}
		String suffix = "128.png";
		if (Global.platformOs.getOs() == OS.ANDROID){suffix = "32.png";}
		Gdx.app.log("TBF", "size suffix" + suffix);
		switch (routineType){
			case PARAM: 	
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/tool" + suffix, disposableList);
				break; 
			case CIPHER:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/key" + suffix, disposableList);
				break; 
			case CLEAR:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/trash" + suffix, disposableList);
				break;
			case COPY:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/copy" + suffix, disposableList);
				break;
			case PASTE:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/paste" + suffix, disposableList);
				break;
			case DBG:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/tool" + suffix, disposableList);
				break;
			case LIST:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/list" + suffix, disposableList);
				break;
			case EXIT:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/exit" + suffix, disposableList);
				break;
			case NULL:
				break;
		}
		
		// 2/ BUTTON PARAM  
		final RTYPE crType = routineType;
		ImageTextButton bRes = new ImageTextButton(label, itbStyle); 
		bRes.setWidth(ibWidth - 4);
		bRes.setHeight(ibWidth -4);
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

	public enum RTYPE{PARAM, CIPHER, CLEAR, PASTE, COPY, DBG, LIST, NULL, EXIT}
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
			case EXIT:		routineExit();		break;
		}

	}

	public void routineList(){
		// SET Button postion 
		Gdx.app.log("RESIZE", textArea.getLines()+ ", " + textArea.getHeight() +", "+  scrollPane.getHeight());
		
		// DELETE TOOL scrollpane
		if (bAreToolsVisible){
			table.reset();
			table.add(scrollPane).width(w0).expandY().fill(); 
		}

		// ADD TOOL ScrollPane
		else{
			table.reset();
			rightTable.reset();
			rightTable.add(rightTopTable).width(w2).height(ibWidth+4).fill().row(); 
			rightTable.add(rightBottomTable).width(w2).height(h0-ibWidth-4).fill().row(); 
			table.add(leftTable).width(w1).expandY().fill(); 
			table.add(rightTable).width(w2).height(h0- ibWidth-4).expandY().align(Align.top).top().fill(); 
		}
		bList.remove();
		stage.addActor(bList);
		bAreToolsVisible = !bAreToolsVisible;
	}

	public void routineExit(){
		Gdx.app.exit();
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

		//Gdx.app.log("TBF", "scrollPane" +   scrollPane.getScrollPercentX() );
		Gdx.app.log("TBF", "text height " +   textArea.getHeight() );
		Gdx.app.log("TBF", "line number  " +   textArea.getLines() );
		Gdx.app.log("TBF", "Pixel DEnsity  " +   Gdx.graphics.getDensity());
		////textArea.setHeight(1000);
		////scrollPane.layout();
		//scrollPane.setScrollPercentX( scrollPane.getScrollPercentX() + 10 );

		//if (null == Global.preferenceSaved){
		//	Global.preferenceSaved = new PreferenceSaved();	
		//	Gdx.app.log("TBF", "PreferencesSaved is null");
		//}

		//KeyObject toAdd = new KeyObject();
		//toAdd.cipheredKey = new byte[10]; 
		//toAdd.label = "This is the label I want to see";
		//Global.preferenceSaved.keyList.add(toAdd);
		//Global.writePref();
	
	}

}


