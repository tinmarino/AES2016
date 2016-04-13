package com.aes.game;

import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class TextAreaX extends TextArea {

	public TextAreaX(String text, TextFieldStyle style) {
		super(text, style);
	}

		//final TextFieldListener tfListener = textArea.listener;
		//textArea.setTextFieldListener(new TextFieldListener()
		//{
		//	@Override
		//	public void keyTyped(TextField textArea, char c)
		//	{
		//		// Handle a newline properly. If not handled here, the TextField
		//		// will advance to the next field.
		//			Gdx.app.log("TBF", "Key pressed in textArea :" + c +  "+ " +((int) c) +"+"+ ((int)'\n')  );
		//			if (c == '\n' || c == '\r' || 8 == c || 127 == c)
		//			{
		//				int i = textArea.getCursorPosition();
		//				stage.setKeyboardFocus(textArea);;
		//				textArea.getOnscreenKeyboard().show(true);
		//				if (c == '\n' || c == '\r'){
		//					if (Global.platformOs.getOs() != OS.DESKTOP){
		//						textArea.appendText("\n");
		//					} // otherwise, adding 2 lines
		//				}
		//				else{textArea.appendText("\b");}
		//				((TextArea)textArea).setPrefRows(((TextArea)textArea).getLines() +1);
		//				scrollPane.layout();
		//				textArea.setCursorPosition(i);
		//				Gdx.app.log("TBF", "Enter pressed in textArea" + textArea.getHeight() + "+" + textArea.getY());
		//			}
		//			else {
		//	 			tfListener.keyTyped(textArea, c);
		//			}
		//	}
		//});

}
