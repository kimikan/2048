package kan.number2.Screens;

import kan.number2.Helpers.Assets;
import kan.number2.views.Buttons;
import kan.number2.views.MapStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

public class GameScreen extends ScreenAdapter implements InputProcessor, Disposable {
	
	private SpriteBatch _spriteBatch = new SpriteBatch();
	
	private OrthographicCamera _camera;
	
	private MapStage _stage = new MapStage();
	
	public enum GameState {
		NotStart,
		Running,
		Pause,
		Middle,
		Stopped,
	}
	
	private GameState _gameState = GameState.NotStart;
	private int _score = 0;
	private int _level = 1;


	TextureRegion _background = null;
	
	public GameScreen() {
		_camera = new OrthographicCamera(MapStage.WIDTH, MapStage.HEIGHT);
		_camera.position.set(MapStage.WIDTH/2, MapStage.HEIGHT/2, 0);
		
		Gdx.input.setInputProcessor(this);
		
		switch(MathUtils.random(1000000) % 3)
		{
		case 0:
			_background = Assets.Background1;
			break;
		case 1:
			_background = Assets.Background2;
			break;
		default:
			_background = Assets.Background3;
			break;
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		_spriteBatch.dispose();
		_stage.dispose();
		
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void pause() {
		super.pause();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		_spriteBatch.setProjectionMatrix(_camera.combined);
		_camera.update();
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		_spriteBatch.begin();
		_spriteBatch.draw(_background, 0, 0, 480, 800);
		//_spriteBatch.draw(Assets.Logo, 10, 353, 64, 64, 128, 128, 0.7f, 0.7f, 0);
		Buttons.render(_spriteBatch, delta);
		_spriteBatch.end();
		
		_spriteBatch.begin();
		//Assets.font.draw(_spriteBatch, ("Got: " + _score), WIDTH - 150, HEIGHT - 30);
		_spriteBatch.end();
		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void resume() {
		super.resume();
	}
 
	@Override
	public void show() {
		super.show();
	}

	@Override
	public boolean keyDown(int arg0) {
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}


	@Override
	public boolean keyUp(int arg0) {
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}
	
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		Vector3 vec = new Vector3(arg0, arg1, 0);
		this._camera.unproject(vec);
		_stage.touchDown(vec.x, vec.y);
		if(_gameState == GameState.Running) {
			
		} else if(_gameState == GameState.NotStart) {
		} else if(_gameState == GameState.Middle) {
		} else if(_gameState == GameState.Stopped) {
		}
		
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		Vector3 vec = new Vector3(arg0, arg1, 0);
		this._camera.unproject(vec);
		_stage.touchUp(vec.x, vec.y);
		return true;
	}

}
