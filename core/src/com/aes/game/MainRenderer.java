package com.aes.game;


import java.util.ArrayList;
import java.util.List;

import com.aes.game.TableButton.RTYPE;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/*  MainRenderer : This is the main Screen, everything important should happen here. 
 */



public class MainRenderer extends AbstractScreen
 {
	TextAreaX  		textArea;
	ScrollPane 		scrollPane, scrollPaneRight;
	Table 			table, leftTable, rightTopTable, rightBottomTable, tButton; 
	TableButton 	rightTable;
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
    	table 				= new Table(); 
		table.setFillParent(true);
		leftTable 			= new Table(); 


		// STAGE 
		stage = new Stage(); 
		stage.setViewport(new ScreenViewport());
		Global.inputMultiplexer.addProcessor(stage);
		disposableList.add(stage);



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
		rightTable 			= new TableButton(this, textArea, ibWidth, disposableList);
		

		
		table.add(leftTable).width(w1).expand().fill(); 
		table.add(rightTable).width(w2).expand().fill(); 

		bList					= rightTable.CreateTextButton("", 			RTYPE.LIST);
		bList.setX(stage.getWidth() - bList.getWidth());
		bList.setY(stage.getHeight()- bList.getHeight());
		routineList();

    	stage.addActor(table);
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
		//ibWidth  	= Math.max(40, Math.round(stage.getWidth() / 8));
		w1 = Math.round(stage.getWidth() - ibWidth - 4);
		w2 = Math.round(ibWidth + 4);
		h0 = height;

		bList.setX(stage.getWidth() - bList.getWidth());
		bList.setY(stage.getHeight()- bList.getHeight());
		//scrollPane.setBounds(0, 0, w1, h0);

		bAreToolsVisible = !bAreToolsVisible;
		routineList();
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
			rightTable.resize(w2, h0);
			// LEFT 
			leftTable.reset();
			leftTable.add(scrollPane).expand().fill();
			// MAIN 
			table.reset();
			table.add(leftTable).width(w1).expandY().fill(); 
			table.add(rightTable).width(w2).expandY().fill(); 
		}

		// REPACK bList 
		bList.remove();
		stage.addActor(bList);
		bAreToolsVisible = !bAreToolsVisible;
	}

}


