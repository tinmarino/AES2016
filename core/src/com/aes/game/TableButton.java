package com.aes.game;

import java.util.List;

import com.aes.game.PlatformOs.OS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

public class TableButton extends Table{

	Table 		tButton, rightBottomTable, rightTopTable;
	TextArea 	textArea;
	ImageTextButton bList, bNull;
	int ibWidth;
	ScrollPane scrollPane;
	List<Disposable> disposableList;
	MainRenderer parent;

	public TableButton(MainRenderer parent,TextArea textArea, int ibWidth, List<Disposable> disposableList){
		super();
		this.textArea = textArea;
		this.ibWidth  = ibWidth;
		this.disposableList = disposableList;
		this.parent = parent;


		// BUTTONS
		bNull 					= CreateTextButton("", 			RTYPE.NULL);
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


		tButton = new Table();
    	tButton.add(bParam		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row();
    	tButton.add(bCipher		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row();
    	tButton.add(bCopy		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 
    	tButton.add(bPaste		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 
    	tButton.add(bClear		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 
    	//tButton.add(bDbg		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 
    	tButton.add(bTuto		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 
    	tButton.add(bUndo		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 
    	tButton.add(bRedo		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 
    	tButton.add(bExit		).width(ibWidth).height(ibWidth).pad(2).fill().expand().row(); 



		// SCROLLPANE 
		ScrollPaneStyle spStyleButton = new ScrollPaneStyle();
		spStyleButton.background = PixmapFactory.getDrawableMonocromatic(1,1, GuiParameter.colSpButtonBck, disposableList);
		scrollPane = new ScrollPane(tButton, spStyleButton)	;
	    scrollPane.setScrollingDisabled(true, false);


    	this.add(bNull		).fill().expand().row();
		this.add(scrollPane).fill().expand();
	}




	public void resize(int w2, int h0){
		this.reset();
		this.add(bNull).prefWidth(w2).prefHeight(ibWidth+4).expand().fill().row(); 
		this.add(scrollPane).prefWidth(w2).expand().fill(); 
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
			case LIST: 		parent.routineList(); 		break; 
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
			default:		routineDbg(); 		break;
		}

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
		parent.setNextScreen(new TutoScreen()); 
		parent.goFoward = true; 
	}

	public void routineExit(){
		Gdx.app.exit();
	}

	public void routineParam(){
		parent.setNextScreen(new ParamScreen()); 
		parent.goFoward = true; 
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
		Runnable runnable = new Runnable() {
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
		  };
		Global.platformOs.runOnUiThread(runnable);
	}

	public void routineCopy(){
		Global.clipboard.setContents(textArea.getText());
	}

	public void routineDbg(){
	}

}
