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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
	Table      	table, keyTable;
	CheckBox 	cb1, cb2, cb3, cb4;
	

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





		// KEY 
		Table tKey = new Table(); 
		tKey.add(new Label("Key:",lStyle)).align(Align.left);
		tKey.add(new TextField("", tfStyle)).align(Align.left).expandX().fill();

		table.add(tKey).expandX().fill().row();

		// IV 
		


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


		// SAVE PARAMS 

		
		// PACK 
		Table tCipherType = new Table();
		tCipherType.add(cb1).expandX().fill().row();
		tCipherType.add(cb2).expandX().fill().row();

		table.add(tCipherType).expandX().fill().row();
		table.add(isDebuggingCheckBox).expandX().fill().row();
		for(int  i= 0; i<30; i++){
			table.add(new Label("Alea jacta est",lStyle)).row();
		}


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

}

/*	
	public void show(){
            stage = new Stage() ;
	    stage.addListener(new InputListener(){
               @Override
	       public boolean keyDown(InputEvent event, int keycode) {
	           switch (keycode){
	             case Keys.BACK   : 
	             case Keys.ESCAPE : 
	             case Keys.B      : 
	             case Keys.MENU   :
		        goOut = true; 
	                return true;  
	             default : 
	                 return false; 
	           }  
               } 
	    });
	    Gdx.input.setInputProcessor(stage); 

		
	    skin = new Skin( Gdx.files.internal("img/game/setting/Setting.json") ,  
		new TextureAtlas(Gdx.files.internal("img/game/setting/Setting.atlas"))  );
		
	    Table table = new Table(); 
	    
	//GEOMETRY 
	    float sWidth = wwidth / 1.1f;  // slider width 
	    float sHeight = wwidth/5; 
	    Drawable drawable =  skin.newDrawable("Slider",HtmlColor.Red); 
	    drawable.setMinHeight(sHeight) ; 



	   InputListener stopTouchDown = new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		        event.stop();
			return false;
	        }
	   };
	    
	//CONTENT 
	  //Speed 
	    Table speedTable = new Table(); 
	    final Label speedLabel = new Label("Speed :", skin); 
	    
	    final TextField speedField = new TextField("", skin);
	    speedField.setMessageText("enterText");
	    
	    final Slider speedSlider = new Slider(0,10,1,false,skin);
	    speedSlider.getStyle().knob = skin.getDrawable("Slider"); 
	    speedSlider.getStyle().background.setMinHeight(sHeight/2);
	    speedSlider.setValue( GamePrefs.preferences.getInteger(GamePrefs.TIMESTEP)  ); 
	    
	    speedSlider.addListener(stopTouchDown); 
	    speedSlider.addListener(new DragListener() {	
	    	@Override
	    	public void drag(InputEvent event, float x, float y, int pointer) {
	    	    super.drag(event, x, y, pointer);
		    int value = (int)  speedSlider.getValue(); 
		    switch ( value ){
		    case 0 : 
		      speedField.setText("Slow"); 
		      break; 
		    case 5 : 
		      speedField.setText("Normal"); 
		      break; 
		    case 10: 
		      speedField.setText("Fast"); 
		      break; 
		    default : 
		      speedField.setText(   Integer.toString(value )  );
		      break;  
		    }
	    	}
	    	@Override
	    	public void dragStop(InputEvent event, float x, float y, int pointer) {
		    super.dragStop(event,x,y,pointer);  
                    GamePrefs.calculateTimeStep(speedSlider.getValue()); 		    
	    	}
	    });
	    speedTable.add(speedLabel).expand();
	    speedTable.add(speedField).expand();
	    speedTable.row();
	    speedTable.add(speedSlider).expand().colspan(2).fill();
	    speedTable.row();

	    
	    
	//Music 
	    Table musicTable = new Table(); 
	    final Label musicLabel = new Label("Music Volume: ", skin); 
	    musicLabel.setWrap(true);
	    musicLabel.setAlignment(Align.left);
	    
	    final TextField musicField = new TextField("", skin);
	    musicField.setMessageText(  Integer.toString(  (int)   (100*GamePrefs.preferences.getFloat(GamePrefs.VOLUME))   )   );
	    musicField.setMaxLength(4);
	    
	    System.out.println(musicField.getWidth() + ",," + musicField.getMinWidth());
	    
	    final Slider musicSlider = new Slider(0,100,1,false,skin);
	    musicSlider.getStyle().knob = drawable; 
	    musicSlider.getStyle().background.setMinHeight(sHeight/2);
	    musicSlider.setValue( (int)   (100*GamePrefs.preferences.getFloat(GamePrefs.VOLUME))  ); 
	    
	    musicSlider.addListener(stopTouchDown); 
	    musicSlider.addListener(new DragListener() {
	    	@Override
	    	public void drag(InputEvent event, float x, float y, int pointer) {
	    		super.drag(event, x, y, pointer);
	    		musicField.setText(   Integer.toString( (int)  musicSlider.getValue()  )  );
	    	}
	    	@Override
	    	public void dragStop(InputEvent event, float x, float y, int pointer) {
		        super.dragStop(event, x, y, pointer); 
	    		System.out.println("VOLUME :" + musicSlider.getValue()); 
	    		//Global.midiPlayer.setVolume(   musicSlider.getValue() /100 );
	    		GamePrefs.preferences.putFloat(GamePrefs.VOLUME, musicSlider.getValue() /100);
	    		GamePrefs.preferences.flush();
	    	}
		});
	    
	    musicTable.add(musicLabel).expand().left();
	    musicTable.add(musicField).right().width(sWidth-musicLabel.getWidth());
	    musicTable.row();
	    musicTable.add(musicSlider).expand().colspan(2).fill(); //.fill();
	    musicTable.row();
	    //musicTable.setWidth(100);
	    musicTable.debug(); 
	
	//isMute, 
	    final CheckBox isMuteCheckBox = new CheckBox("Mute ", skin); 
	    isMuteCheckBox.getStyle().fontColor = HtmlColor.Black; 
	    //if (Global.midiPlayer.isMute()) {isMuteCheckBox.setChecked(true);} 
		isMuteCheckBox.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GamePrefs.preferences.putBoolean(GamePrefs.IS_MUTE, isMuteCheckBox.isChecked());
				GamePrefs.preferences.flush();
				//Global.midiPlayer.setMute(isMuteCheckBox.isChecked());
			}
		});
		musicTable.add(isMuteCheckBox); 
	    
	    
	    
	// DEBUG 
	    final CheckBox debugCheckBox = new CheckBox("Debug ", skin); 
	    debugCheckBox.getStyle().fontColor = HtmlColor.Black; 
	    if (Global.debug) {debugCheckBox.setChecked(true);} 
	    debugCheckBox.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (debugCheckBox.isChecked()) {Global.debug = true; }
				else {Global.debug = false;}
			}
	     });
	    
	// OK
	    final TextButton okButton = new TextButton("OK", skin); 
	    okButton.getStyle().fontColor = HtmlColor.Black; 
	    okButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
			   goOut= true; 
			}
	    });

	    
	//PACK 

	    
	    scrollPane = new ScrollPane(table);
	    scrollPane.setFillParent(true); 
	    scrollPane.setScrollingDisabled(true,false); 
	 // Music 
	    table.add(musicTable).width(sWidth); 
	    table.row();
	 //Speed
	    table.add(speedTable).width(sWidth); 
	    table.row(); 
	  // Debug 
	    table.add(debugCheckBox); 
	    table.row(); 

          // ok 
	    table.add(okButton); 
	    table.row(); 

	    table.setWidth(sWidth);
	    table.debug();
	    stage.addActor(scrollPane);
	 
		
		
	}
	



	public void dispose(){
		stage.dispose();
		skin.dispose();
	}
	


}
*/
