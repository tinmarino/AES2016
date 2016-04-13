package com.aes.game;

import com.aes.game.PlatformOs.OS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

class InputTextArea extends InputListener{
	MainRenderer p; 

	public InputTextArea(MainRenderer mainRenderer) {
        super();
		this.p = mainRenderer;
    }
/*
    @Override
    public boolean handle(Event e) {
        return l.handle(e);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y,
            int pointer, int button) {
        return l.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y,
            int pointer, int button) {
        l.touchUp(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y,
            int pointer) {
        l.touchDragged(event, x, y, pointer);
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        return l.mouseMoved(event, x, y);
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer,
            Actor fromActor) {
        l.enter(event, x, y, pointer, fromActor);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer,
            Actor toActor) {
        l.exit(event, x, y, pointer, toActor);
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y,
            int amount) {
        return l.scrolled(event, x, y, amount);
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        return l.keyDown(event, keycode);
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        return l.keyUp(event, keycode);
    }
	*/


	public void paste(String to){
		if (to.length() == 0) {return;}
		String text = p.textArea.getText();
		int iPos = p.textArea.getCursorPosition();
		Gdx.app.log("TBF", "cursor posiriont " + iPos );

		String res = text.substring(0, iPos );
		res += to;
		res += text.substring(iPos, text.length());
		p.textArea.setCursorPosition(iPos+2);
		
		p.textArea.setText(res); 
	}


    @Override
    public boolean keyTyped(InputEvent event, char c) {
			{
				// Handle a newline properly. If not handled here, the TextField
				// will advance to the next field.
					Gdx.app.log("TBF", "Key pressed in p.textArea :" + c +  "+ " +((int) c) +"+"+ ((int)'\n')  );
					if (c == '\n' || c == '\r' || 8 == c || 127 == c)
					{
						int i = p.textArea.getCursorPosition();
						p.stage.setKeyboardFocus(p.textArea);
						p.textArea.getOnscreenKeyboard().show(true);
						if (c == '\n' || c == '\r'){
							//if (Global.platformOs.getOs() != OS.DESKTOP)
							//{
								paste("\n");
							//}
						}
						else{paste("\b");}
						p.textArea.setPrefRows(p.textArea.getLines() +1);
						p.scrollPane.layout();
						p.textArea.setCursorPosition(i);
						Gdx.app.log("TBF", "Enter pressed in p.textArea" + p.textArea.getHeight() + "+" + p.textArea.getY());
						return true;
					}
					else{
						return super.keyTyped(event, c);
					}
			}
    }

}
