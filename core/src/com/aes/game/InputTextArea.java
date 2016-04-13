package com.aes.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

class InputTextArea extends InputListener{
    private final InputListener l;

    public InputTextArea(InputListener l) {
        super();
        this.l = l;
    }

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
    @Override
    public boolean keyTyped(InputEvent event, char character) {
        } else if ((character == '\r' || character == '\n')){
            next(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT));
            return true;
        }
        return l.keyTyped(event, character);
    }

}
