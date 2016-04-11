package com.aes.game;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;

/*
 * WARNING, pixmaps must be disposed
 * */

public class PixmapFactory{
	

	public static NinePatchDrawable ninePatchDrawableMonochromatci(int width, int height, Color color){
		final Texture t = new Texture(monocromaticPixmap(width, height, color));
		return ninePatchFromTexture(t);
	}

	public static NinePatchDrawable ninePatchDrawableFromFile(String path){
		final Texture t = new Texture(path);
		return ninePatchFromTexture(t);
	}

	public static NinePatchDrawable ninePatchFromTexture(Texture t){
		final int width = t.getWidth() / 2;
		final int height = t.getHeight() /2 ;
		// ------>width1    width2<--------- i define the stretchable region
		NinePatch np = new NinePatch(t, width-1, width-1, height-1,height-1);
		return new NinePatchDrawable(np);
	}

	public static Drawable drawableFromFile(String path, List<Disposable> disposableList){
		Texture t = new Texture(path);
		disposableList.add(t);
		return new SpriteDrawable(new Sprite(t));
	}

	public static Drawable drawableSelection(){
		Pixmap pixmap = monocromaticPixmap(2, 16, Color.BLUE);
		return new SpriteDrawable(new Sprite(new Texture(pixmap)));

	}

	public static Drawable drawableOneBlue(){
		Pixmap pixmap = monocromaticPixmap(1, 1, Color.BLUE);
		for (int i = 0; i<32; i++){
			for (int j=-1; j<=1; j++){
				pixmap.drawPixel(i,i+j);
				pixmap.drawPixel(i,32-i-j);
			}
		}
		return new SpriteDrawable(new Sprite(new Texture(pixmap)));
	}

	public static Drawable drawableCheckBoxOn(List<Disposable> disposableList){
		Pixmap pixmap = pixmapCheckBox();
		// Cross
		pixmap.setColor(Color.RED);
		for (int i = 0; i<32; i++){
			for (int j=-1; j<=1; j++){
				pixmap.drawPixel(i,i+j);
				pixmap.drawPixel(i,32-i-j);
			}
		}
		disposableList.add(pixmap);
		return new SpriteDrawable(new Sprite(new Texture(pixmap)));
	}

	public static Drawable drawableCheckBoxOff(List<Disposable> disposableList){
		Pixmap pixmap = pixmapCheckBox();
		disposableList.add(pixmap);
		return new SpriteDrawable(new Sprite(new Texture(pixmap)));
	}

	public static Pixmap pixmapCheckBox(){
		Pixmap pixmap = monocromaticPixmap(32, 32, Color.WHITE);
		pixmap.setColor(Color.BLACK);
		// square
		for (int i = 0; i<32; i++){
			for (int j=0; j<=4; j++){
				pixmap.drawPixel(i,j);				// BOT
				pixmap.drawPixel(i,32-j);			// TOP
				pixmap.drawPixel(j,i);				// LEFT 
				pixmap.drawPixel(32-j,i);				// RIGHT 
			}
		}
		return pixmap;
	}


	public static Drawable getDrawableMonocromatic(int x, int y, Color color, List<Disposable> disposableList){
		Pixmap pixmap = monocromaticPixmap(x, y, color);
		disposableList.add(pixmap);
		return new SpriteDrawable(new Sprite(new Texture(pixmap)));
	}


	public static Pixmap pixmapRandom(int x, int y, Color color, int random){
		Random ranGen = new Random(); 
		Pixmap pixmap = new Pixmap(x, y, Pixmap.Format.RGBA8888); 
		for (int i = 0; i<pixmap.getWidth(); i++){
			for (int j = 0; j<pixmap.getHeight(); j++){
					pixmap.setColor( new Color(
							color.r + (-random + ranGen.nextInt(2*random) )/255f,
							color.g + (0   -random + ranGen.nextInt(2*random) )/255f,
							color.b + (0   -random + ranGen.nextInt(2*random) )/255f,
							1));
					pixmap.drawPixel(i, j );
			}
			System.out.println("Texture Generation lin :" + i + "," +(-random +ranGen.nextInt(2*random)) ); 
		}
		return pixmap; 
	}
	
	public static Texture texturePixel(Color color){
		Pixmap pixmap = monocromaticPixmap(1, 1, color); 
		Texture texture = new Texture(pixmap); 
		pixmap.dispose();
		return texture; 
	}


	
	public static Texture gridPixmap(Pixmap pixmap, int xRepeat,int yRepeat){ // size is the number of bricks
		/* we repeat a texture in a grid to imitate the textute of a wall:
		 */
		Format format = Format.RGBA8888;
		if (xRepeat<0){pixmap = flipPixmapX(pixmap) ; xRepeat *= -1;}
		if (yRepeat<0){pixmap = flipPixmapY(pixmap) ; yRepeat *= -1;}
		
		
		Pixmap out = new Pixmap(xRepeat*pixmap.getWidth(), yRepeat*pixmap.getHeight(), format);
		
	// join the images	
		for (int i= 0 ; i < xRepeat; i++){
			for (int j=0 ; j < yRepeat; j++){
				out.drawPixmap(pixmap, i*pixmap.getWidth(), j*pixmap.getHeight(), 0, 0 , pixmap.getWidth() , pixmap.getHeight());
			}
		}

				
		Texture res = new Texture(out);
		out.dispose();
		return res;

	}
	
	
	public static Texture alphaTexture(FileHandle file , float alpha){
		Pixmap fg= new Pixmap(file); 
		
		// Initially, the mask should have an alpha of 1
		Pixmap mask = new Pixmap(fg.getWidth(), fg.getHeight(), Pixmap.Format.Alpha);

		// Cut a rectangle of alpha value 0
		Pixmap.setBlending(Pixmap.Blending.None);
		mask.setColor(new Color(0f, 0f, 0f, 0.2f));
		mask.fillRectangle(0, 0,fg.getWidth(), fg.getHeight());

		fg.drawPixmap(mask, fg.getWidth(), fg.getHeight());
		Pixmap.setBlending(Pixmap.Blending.SourceOver);
		
		Texture res = new Texture(fg); 
		fg.dispose();
		mask.dispose();
		
		return res; 
	}


	public static Pixmap monocromaticPixmap(int width, int height, Color color){
		Pixmap res = new Pixmap(width, height, Format.RGBA8888);
		
		res.setColor(color);
		res.drawRectangle(0, 0, width, height);
		
		return res; 
	}
	
	public static Pixmap polycromaticPixmap(Color[][] colorArray){ // gives lines and column 
		Pixmap res = new Pixmap(colorArray[0].length, colorArray.length, Format.RGBA8888);
		
		for (int x = 0 ; x < colorArray[0].length ; x ++){
			for (int y=0 ; y < colorArray.length ; y++){

				res.setColor(colorArray[y][x]) ; 
				res.drawPixel(x, y);
			}
		}
		return res; 
	}
	
	
	public static Pixmap circle(int radius,Color color) {
		Pixmap res = new Pixmap( radius , radius , Format.RGBA8888);
		
		res.setColor(color);
		res.fillCircle(radius/2, radius/2, radius/2);
		
		return res; 
	}

	public static Pixmap circleDrawPixel(int diameter, Color color){
		Pixmap res = new Pixmap( diameter , diameter , Format.RGBA8888);
		int r1 = (diameter/2);
		int r2 = r1*r1;

		res.setColor(color);
		for (int i=0; i<diameter; i++){
			for (int j=0; j<diameter; j++){
				if( (i-r1)*(i-r1) + (j-r1)*(j-r1)  < r2){
					res.drawPixel(i,j);
				}
			}
		}

		
		return res; 
	}
	
	
	public static Pixmap flipPixmapY( Pixmap src ) {
	    final int width = src.getWidth();
	    final int height = src.getHeight();
	    Pixmap flipped = new Pixmap(width, height, src.getFormat());

	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            flipped.drawPixel(x, y, src.getPixel(x, height -y-1));
	        }
	    }
	    return flipped;
	}
	
	
	/** Cut this pixmap to a sub array .
	 * @param src, the pixmap to be cutted
	 * @param x0 the top left starting point
	 * @param y0 the top left starting point
	 * @param width the width in pixel 
	 * @param height the height in pixel  */
	public static Pixmap cutPixmap(Pixmap src, int x0, int y0, int width, int height){
		Pixmap res = new Pixmap(width, height,src.getFormat());
	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            res.drawPixel(x, y, src.getPixel(x0+x,y0+y));
	        }
	    }
	    return res;
	}
	
	public static Pixmap flipPixmapX(Pixmap src) {
	    final int width = src.getWidth();
	    final int height = src.getHeight();
	    Pixmap flipped = new Pixmap(width, height, src.getFormat());

	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            flipped.drawPixel(x, y, src.getPixel(width-x-1,y));
	        }
	    }
	    return flipped;
	}

}

