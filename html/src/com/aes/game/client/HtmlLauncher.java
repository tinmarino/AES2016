package com.aes.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.GwtClipboard;
import com.aes.game.Global;
import com.aes.game.MyAesGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                //return new GwtApplicationConfiguration(320*2, 480*2);
				Global.platformOs = new PlatformHtml();
				Global.clipboard  = new GwtClipboard();
                return new GwtApplicationConfiguration((int)(320 * 1.5f), (int)(480 * 1.5f));
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new MyAesGame();
        }
}
