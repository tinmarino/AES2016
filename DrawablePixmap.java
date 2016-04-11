	/**
	 * Nested (static) class to provide a nice abstraction over Pixmap, exposing
	 * only the draw calls we want and handling some of the logic for smoothed
	 * (linear interpolated, aka 'lerped') drawing. This will become the 'owner'
	 * of the underlying pixmap, so it will need to be disposed.
	 */
	private static class DrawablePixmap implements Disposable {

		private final int brushSize = 10;
		private final Color clearColor = new Color(0, 0, 0, 0);
		private final Color drawColor = new Color(1, 1, 1, 1);

		private Pixmap pixmap;
		private Texture texture;
		private boolean dirty;

		public DrawablePixmap(Pixmap pixmap, int textureBinding) {
			this.pixmap = pixmap;
			pixmap.setColor(drawColor);

			/* Create a texture which we'll update from the pixmap. */
			this.texture = new Texture(pixmap);
			this.dirty = false;

			/* Bind the mask texture to TEXTURE<N> (TEXTURE1 for our purposes),
			 * which also sets the currently active texture unit. */
			this.texture.bind(textureBinding);

			/* However SpriteBatch will auto-bind to the current active texture,
			 * so we must now reset it to TEXTURE0 or else our mask will be
			 * overwritten. */
			Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
		}

		/** Write the pixmap onto the texture if the pixmap has changed. */
		public void update() {
			if (dirty) {
				texture.draw(pixmap, 0, 0);
				dirty = false;
			}
		}

		public void clear() {
			pixmap.setColor(clearColor);
			pixmap.fill();
			pixmap.setColor(drawColor);
			dirty = true;
		}

		private void drawDot(Vector2 spot) {
			pixmap.fillCircle((int) spot.x, (int) spot.y, brushSize);
		}

		public void draw(Vector2 spot) {
			drawDot(spot);
			dirty = true;
		}

		public void drawLerped(Vector2 from, Vector2 to) {
			float dist = to.dst(from);
			/* Calc an alpha step to put one dot roughly every 1/8 of the brush
			 * radius. 1/8 is arbitrary, but the results are fairly nice. */
			float alphaStep = brushSize / (8f * dist);

			for (float a = 0; a < 1f; a += alphaStep) {
				Vector2 lerped = from.lerp(to, a);
				drawDot(lerped);
			}

			drawDot(to);
			dirty = true;
		}

		@Override
		public void dispose() {
			texture.dispose();
			pixmap.dispose();
		}
	}
