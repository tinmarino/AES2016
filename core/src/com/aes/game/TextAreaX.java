package com.aes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class TextAreaX extends TextArea {
	MainRenderer p; 

	public TextAreaX(String text, TextFieldStyle style) {
		super(text, style);
	}

	public void setParent(MainRenderer p){
		this.p = p;
	}


	@Override
	protected InputListener createInputListener () {
		return new TextAreaXListener();
	}

	public void newline(){
		String text = this.getText();
		int iPos = this.getCursorPosition();
		Gdx.app.log("TBF", "cursor posiriont " + iPos +"+"+ this.getCursorLine());

		String res = text.substring(0, iPos );
		res += "\n";
		res += text.substring(iPos, text.length());
	
		
		Gdx.app.log("TBF", "cursor posiriont bis " + this.getCursorPosition() );
		this.setText(res); 
		this.setCursorPosition(iPos+1);
		//this.moveCursorLine(this.getCursorLine() +1);
	}

	public void backspace(){
		String text = this.getText();
		int iPos = this.getCursorPosition();
		Gdx.app.log("TBF", "cursor posiriont " + iPos );
		if (iPos > 0){
			String res = text.substring(0, iPos - 1 );
			res += text.substring(iPos, text.length());
			this.setText(res);
			if (0<iPos){this.setCursorPosition(iPos-1);}
		}
		else{
			this.moveCursorLine(this.getCursorLine() - 1);
		}

	}


	public class TextAreaXListener extends TextAreaListener{

    		@Override
    		public boolean keyTyped(InputEvent event, char c) {
				// Handle a newline properly. If not handled here, the TextField
				// will advance to the next field.
				// 0 if for enter in html
					Gdx.app.log("TBF", "Key pressed in TextAreaX.this :" + c +  "+ " +((int) c)  );
					if (c == '\n' || c == '\r' || 8 == c || 127 == c || 0 == c)
					{
						p.stage.setKeyboardFocus(TextAreaX.this);
						TextAreaX.this.getOnscreenKeyboard().show(true);
						if (c == '\n' || c == '\r' || c == 0){
							 	if (c==0){
									if(!Gdx.input.isKeyPressed(Keys.ENTER)){
										return super.keyTyped(event, c);
									}
								}
								newline();
						}
						else{backspace();}
						
						TextAreaX.this.setPrefRows(TextAreaX.this.getLines() +1);
						p.scrollPane.layout();
						Gdx.app.log("TBF", "Enter pressed in TextAreaX.this" + TextAreaX.this.getHeight() + "+" + TextAreaX.this.getY());
						return true;
					}
					else{
						return super.keyTyped(event, c);
					}
			}

			@Override
			public void clicked (InputEvent event, float x, float y) {
				int len = TextAreaX.this.getText().length();
				if ( len   < TextAreaX.this.letterUnderCursor(x)){
					TextAreaX.this.setCursorPosition(len);
					Gdx.app.log("TBF", "click dans les choux:"+ x + "+" + y +"+"+event);
					return ;
				}
				super.clicked(event, x, y);
				return;
			}
	}
}

