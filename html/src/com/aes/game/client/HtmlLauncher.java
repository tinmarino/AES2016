package com.aes.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.aes.game.Global;
import com.aes.game.MyAesGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                //return new GwtApplicationConfiguration(320*2, 480*2);
				Global.platformOs = new PlatformHtml();
                return new GwtApplicationConfiguration(320 * 2, 480 * 2);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new MyAesGame();
        }
}
