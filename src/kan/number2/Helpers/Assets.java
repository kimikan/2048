package kan.number2.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static BitmapFont font;
	
	public static Sound moveSound;
	public static Sound noMoveSound;
	public static Sound winSound;
	
	public static TextureRegion NEmpty;
	public static TextureRegion NumberSelected;
	public static TextureRegion N2;
	public static TextureRegion N4;
	public static TextureRegion N8;
	public static TextureRegion N16;
	public static TextureRegion N32;
	public static TextureRegion N64;
	public static TextureRegion N128;
	public static TextureRegion N256;
	public static TextureRegion N512;
	public static TextureRegion N1024;
	public static TextureRegion N2048;
	public static TextureRegion N4096;
	
	public static TextureRegion Start;
	public static TextureRegion Pause;
	public static TextureRegion Retry;
	public static TextureRegion Share;
	public static TextureRegion StartPressed;
	public static TextureRegion PausePressed;
	public static TextureRegion RetryPressed;
	public static TextureRegion SharePressed;
	
	public static TextureRegion Best;
	public static TextureRegion Score;
	public static TextureRegion Background1;
	public static TextureRegion Background2;
	public static TextureRegion Background3;
	public static TextureRegion Logo;
	
	static TextureAtlas localTextureAtlas;
	static AssetManager assetManager;
	
	private static void loadSound() {
		//ScoreSound = Gdx.audio.newSound(Gdx.files.internal("music/score.ogg"));
		moveSound = Gdx.audio.newSound(Gdx.files.internal("move.mp3"));
		noMoveSound = Gdx.audio.newSound(Gdx.files.internal("no_move.mp3"));
		winSound = Gdx.audio.newSound(Gdx.files.internal("sound_win.mp3"));
	}
	
	public static void load() {
		
		assetManager = new AssetManager();
		Texture.setAssetManager(assetManager);
		TextureLoader.TextureParameter localTextureParameter = new TextureLoader.TextureParameter();
		localTextureParameter.genMipMaps = true;
		localTextureParameter.minFilter = Texture.TextureFilter.Linear;
		localTextureParameter.magFilter = Texture.TextureFilter.Linear;
		
		localTextureAtlas = new TextureAtlas(
				Gdx.files.internal("_2048.pack"));
		NEmpty = localTextureAtlas.findRegion("n_empty");
		NumberSelected = localTextureAtlas.findRegion("map");
		N2 = localTextureAtlas.findRegion("n2");
		N4 = localTextureAtlas.findRegion("n4");
		N8 = localTextureAtlas.findRegion("n8");
		N16 = localTextureAtlas.findRegion("n16");
		N32 = localTextureAtlas.findRegion("n32");
		N64 = localTextureAtlas.findRegion("n64");
		N128 = localTextureAtlas.findRegion("n128");
		N256 = localTextureAtlas.findRegion("n256");
		N512 = localTextureAtlas.findRegion("n512");
		N1024 = localTextureAtlas.findRegion("n1024");
		N2048 = localTextureAtlas.findRegion("n2048");
		N4096 = localTextureAtlas.findRegion("n4096");
		Best = localTextureAtlas.findRegion("best");
		Score = localTextureAtlas.findRegion("score");
		Background1 = localTextureAtlas.findRegion("bg-1");
		Background2 = localTextureAtlas.findRegion("bg-2");
		Background3 = localTextureAtlas.findRegion("bg-3");
		Logo = localTextureAtlas.findRegion("copa_oro");
		Retry = localTextureAtlas.findRegion("restart_button");
		Share = localTextureAtlas.findRegion("share_button");
		RetryPressed = localTextureAtlas.findRegion("restart_button_pressed");
		SharePressed = localTextureAtlas.findRegion("restart_button_pressed");
		//Lose = localTextureAtlas.findRegion("lose");
		//Guns = localTextureAtlas.findRegion("pao5");
		
		loadFont();
		loadSound();
	}
	
	public static void loadFont() {
		assetManager.load("font.fnt", BitmapFont.class);
		assetManager.finishLoading();
		
		font = (BitmapFont) assetManager.get("font.fnt", BitmapFont.class);
		font.setUseIntegerPositions(false);
	}
	
	public static void dispose() {
		if(localTextureAtlas != null) {
			localTextureAtlas.dispose();
			localTextureAtlas = null;
		}
		if(font != null) {
			font.dispose();
			font = null;	
		}
		
		if(assetManager != null) {
			assetManager.dispose();
			assetManager = null;
		}
		if(moveSound != null) {
			moveSound.dispose();moveSound = null;
		}
		if(noMoveSound != null) {
			noMoveSound.dispose(); noMoveSound = null;
		}
		if(winSound != null) {
			winSound.dispose(); winSound = null;
		}
	}
	
}
