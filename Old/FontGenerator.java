package com.aes.game;

public class FontGenerator{

FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/unbom.ttf"));

FreeTypeFontParameter parameter = new FreeTypeFontParameter();
parameter.size = 18;
parameter.characters = "한국어/조선�?";

BitmapFont koreanFont = generator.generateFont(parameter);
generator.dispose();

parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
generator = new FreeTypeFontGenerator(Gdx.files.internal("data/russkij.ttf"));
BitmapFont cyrillicFont = generator.generateFont(parameter);
generator.dispose();
}
