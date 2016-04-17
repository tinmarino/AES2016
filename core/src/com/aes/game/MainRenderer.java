package com.aes.game;


import java.util.ArrayList;
import java.util.List;


import com.aes.game.PlatformOs.OS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;


/*  MainRenderer : This is the main Screen, everything important should happen here. 
 */



public class MainRenderer extends AbstractScreen
 {
	TextAreaX  		textArea;
	ScrollPane 		scrollPane, scrollPaneRight;
	Table 			table, leftTable, rightTable, rightTopTable, rightBottomTable; 
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
		ImageTextButton bParam 	= CreateTextButton("Param", 	RTYPE.PARAM);
		ImageTextButton bCipher = CreateTextButton("Cipher", 	RTYPE.CIPHER); 
		ImageTextButton bCopy  	= CreateTextButton("Copy",		RTYPE.COPY); 
		ImageTextButton bPaste 	= CreateTextButton("Paste",		RTYPE.PASTE); 
		ImageTextButton bClear 	= CreateTextButton("Clear",		RTYPE.CLEAR); 
		//ImageTextButton bDbg	= CreateTextButton("Dbg",		RTYPE.DBG); 
		ImageTextButton bTuto	= CreateTextButton("Tuto",		RTYPE.TUTO); 
		ImageTextButton bUndo	= CreateTextButton("Undo",		RTYPE.UNDO); 
		ImageTextButton bRedo	= CreateTextButton("Redo",		RTYPE.REDO); 
		ImageTextButton bExit	= CreateTextButton("Exit",		RTYPE.EXIT); 


		// TEXT AREA
		TextFieldStyle tfStyle = Global.getTextFieldStyle(disposableList);
		textArea 	= new TextAreaX("TextAreza  azeazethis sss", tfStyle); 
		textArea.setParent(this);
		textArea.setPrefRows(textArea.getLines());
		textArea.setMaxLength(1000);
		textArea.setClipboard(Global.clipboard);

		
		// PACK LEFT TABLE
		ScrollPaneStyle spStyle = Global.getScrollPaneStyle(disposableList);
		scrollPane = new ScrollPane(textArea, spStyle);
		scrollPane.setScrollingDisabled(true, false);
		leftTable.add(scrollPane).expand().fill();
		
		
		// PACK RIGHT TABLE
		Table tButton = new Table();
    	tButton.add(bParam		).width(ibWidth).height(ibWidth).pad(2).fill().row();
    	tButton.add(bCipher		).width(ibWidth).height(ibWidth).pad(2).fill().row();
    	tButton.add(bCopy		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
    	tButton.add(bPaste		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
    	tButton.add(bClear		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
    	//tButton.add(bDbg		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
    	tButton.add(bTuto		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
    	tButton.add(bUndo		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
    	tButton.add(bRedo		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
    	tButton.add(bExit		).width(ibWidth).height(ibWidth).pad(2).fill().row(); 
		ScrollPaneStyle spStyleButton = new ScrollPaneStyle();
		spStyleButton.background = PixmapFactory.getDrawableMonocromatic(1,1, GuiParameter.colSpButtonBck, disposableList);
		scrollPaneRight = new ScrollPane(tButton, spStyleButton)	;
	    scrollPaneRight.setScrollingDisabled(true, false);
    	rightTopTable.add(bNull		).fill().expand().row();
		rightTopTable.setBackground(PixmapFactory.getDrawableMonocromatic(1, 1, GuiParameter.colSpButtonBck, disposableList));
		rightBottomTable.add(scrollPaneRight).fill().expand();


		
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
		Gdx.gl.glClearColor(GuiParameter.colClear.r, GuiParameter.colClear.g, GuiParameter.colClear.b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// DRAW
		stage.act(delta); 
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
		itbStyle.font 		= Global.fontButton; 
		itbStyle.fontColor 	= GuiParameter.colFontButton;
		switch(routineType){
			case LIST:
				itbStyle.up = PixmapFactory.getDrawableMonocromatic(1,1,new Color(1,1,1, 0.01f), disposableList);
				itbStyle.down = PixmapFactory.getDrawableMonocromatic(1,1,new Color(1,1,1,0.5f), disposableList);
				break;
			
			case NULL:
				itbStyle.up = PixmapFactory.getDrawableMonocromatic(1,1,GuiParameter.colSpButtonBck, disposableList);
				break;
			default:
			 	Texture t = new Texture(PixmapFactory.circle(16, GuiParameter.colButton));
				disposableList.add(t);
				itbStyle.up   = PixmapFactory.ninePatchFromTexture(t);
				itbStyle.down = PixmapFactory.getDrawableMonocromatic(1,1,GuiParameter.colButtonDown, disposableList);
				break;
		}
		String suffix = "128.png";
		if (Global.platformOs.getOs() == OS.ANDROID){suffix = "32.png";}
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
			case TUTO:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/book" + suffix, disposableList);
				break;
			case UNDO:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/undo" + suffix, disposableList);
				break;
			case REDO:
				itbStyle.imageUp = PixmapFactory.drawableFromFile("img/ui/redo" + suffix, disposableList);
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

	public enum RTYPE{PARAM, CIPHER, CLEAR, PASTE, COPY, DBG, LIST, NULL, EXIT, TUTO, UNDO, REDO}

	public void routineDispatch(RTYPE routineType){
		Gdx.app.log("TBF", "Rgith button presses :" + routineType);
		Global.iUndo +=1; 
		Global.listUndo.add(textArea.getText());
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
			case UNDO:		routineUndo();		break;
			case REDO:		routineRedo();		break;
			case TUTO:		routineTuto();		break;
		}

	}



	public void routineList(){
		// SET Button postion 
		Gdx.app.log("RESIZE", textArea.getLines()+ ", " + textArea.getHeight() +", "+  scrollPane.getHeight());
		
		// DELETE TOOL scrollpane
		if (bAreToolsVisible){
			// LEFT 
			leftTable.reset();
			leftTable.add(scrollPane).expand().fill();
			// MAIN
			table.reset();
			table.add(leftTable).width(w0).expand().fill(); 
		}

		// ADD TOOL ScrollPane
		else{
			// RIGHT
			rightTable.reset();
			rightTable.add(rightTopTable).width(w2).height(ibWidth+4).expand().fill().row(); 
			rightTable.add(rightBottomTable).width(w2).expand().fill(); 
			// LEFT 
			leftTable.reset();
			leftTable.add(scrollPane).expand().fill();
			// MAIN 
			table.reset();
			table.add(leftTable).width(w1).expandY().fill(); 
			table.add(rightTable).width(w2).expandY().fill(); 
		}
		bList.remove();
		stage.addActor(bList);
		bAreToolsVisible = !bAreToolsVisible;
	}

	public void routineUndo(){
		if( 1 >= Global.iUndo){
			Gdx.app.log("TBF", "Error : No more Undo");
			return;
		}
		Global.iUndo -= 2; 
		textArea.setText(Global.listUndo.get(Global.iUndo));
	}

	public void routineRedo(){
		if( Global.listUndo.size() <= Global.iUndo+ 2){
			Gdx.app.log("TBF", "Error : No more Redo");
			return;
		}
		Global.iUndo += 2; 
		textArea.setText(Global.listUndo.get(Global.iUndo));
	}


	public void routineTuto(){
		this.setNextScreen(new TutoScreen()); 
		this.goFoward = true; 
		
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
		Gdx.app.postRunnable(new Runnable() {
				 @Override
				 public void run() {
					String sPaste = Global.clipboard.getContents();
					if (OS.ANDROID == Global.platformOs.getOs()){
						sPaste = sPaste.replaceAll(Global.ENTER_DESKTOP, Global.ENTER_ANDROID);
					}
					else{
						sPaste = sPaste.replaceAll(Global.ENTER_ANDROID, Global.ENTER_DESKTOP);
					}
					Gdx.app.log("TBF", "I will paste "+ sPaste );
					textArea.appendText(sPaste);
				 }
			  });
	}

	public void routineCopy(){
		Global.clipboard.setContents(textArea.getText());
	}

	public void routineDbg(){
	}

}


