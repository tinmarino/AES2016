package com.aes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class TextAreaX extends TextArea {
	MainRenderer p; 

	public TextAreaX(String text, TextFieldStyle style) {
		super(text, style);
	}

	public void setParent(MainRenderer p){
		this.p = p;
	}


	public void paste(String to){
		if (to.length() == 0) {return;}
		String text = this.getText();
		int iPos = this.getCursorPosition();
		Gdx.app.log("TBF", "cursor posiriont " + iPos );

		String res = text.substring(0, iPos );
		res += to;
		res += text.substring(iPos, text.length());
		this.setCursorPosition(iPos+2);
		
		this.setText(res); 
	}


	public class TextAreaXListener extends TextAreaListener{

    		@Override
    		public boolean keyTyped(InputEvent event, char c) {
				// Handle a newline properly. If not handled here, the TextField
				// will advance to the next field.
					Gdx.app.log("TBF", "Key pressed in TextAreaX.this :" + c +  "+ " +((int) c) +"+"+ ((int)'\n')  );
					if (c == '\n' || c == '\r' || 8 == c || 127 == c)
					{
						int i = TextAreaX.this.getCursorPosition();
						p.stage.setKeyboardFocus(TextAreaX.this);
						TextAreaX.this.getOnscreenKeyboard().show(true);
						if (c == '\n' || c == '\r'){
							//if (Global.platformOs.getOs() != OS.DESKTOP)
							//{
								paste("\n");
							//}
						}
						else{paste("\b");}
						TextAreaX.this.setPrefRows(TextAreaX.this.getLines() +1);
						p.scrollPane.layout();
						TextAreaX.this.setCursorPosition(i);
						Gdx.app.log("TBF", "Enter pressed in TextAreaX.this" + TextAreaX.this.getHeight() + "+" + TextAreaX.this.getY());
						return true;
					}
					else{
						return super.keyTyped(event, c);
					}
			}
	}
}

