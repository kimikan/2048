package kan.number2;

import kan.number2.Helpers.Assets;
import kan.number2.Screens.GameScreen;

import com.badlogic.gdx.Game;

public class RapidCalcGame extends Game {

	@Override
	public void create() {
		Assets.load();
		this.setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.dispose();
	}

	@Override
	public void pause() {
		super.pause();
		//Assets.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void resume() {
		super.resume();
		Assets.loadFont();
	}
	
}
