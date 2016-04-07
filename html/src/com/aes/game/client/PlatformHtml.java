package com.aes.game.client;

import com.aes.game.PlatformOs;

public class PlatformHtml implements PlatformOs {

	@Override
	public OS getOs() {
		return OS.HTML;
	}

	@Override
	public void setOrientation(Orientation orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public Orientation getOrientation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getSize() {
		// TODO Auto-generated method stub
		return null;
	}
}
