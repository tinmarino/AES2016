package com.aes.game.client;

import com.aes.game.PlatformOs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PlatformHtml implements PlatformOs {

	@Override
	public OS getOs() {
		return OS.HTML;
	}

	@Override
	public BitmapFont getFont(int fontsize){
		BitmapFont font;
		if (16 == fontsize){
			font = new BitmapFont(Gdx.files.internal("font/Ubuntu16White.fnt"), false);
		}
		else{
			font = new BitmapFont(Gdx.files.internal("font/Ubuntu32White.fnt"), false);
		}
		return font; 
	}

	@Override
	public void setOrientation(Orientation orientation) {
	}

	@Override
	public Orientation getOrientation() {
		return null;
	}

	@Override
	public int[] getSize() {
		return null;
	}
}
