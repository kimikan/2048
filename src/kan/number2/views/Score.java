package kan.number2.views;

import kan.number2.Helpers.Assets;
import kan.number2.Helpers.Helper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Score extends Actor {

	private int _score = 0;
	
	private int _best = 0;
	
	public Score() {
		_best = Helper.getScore();
	}

	public int getScore() {
		return _score;
	}
	
	class PlusScore extends Actor {
		String _str;
		
		int x = 120;
		int y = 680;
		
		public PlusScore(String str) {
			this.setBounds(x, y, 50, 20);
			_str = str;
			Action action = Actions.parallel(Actions.moveTo(x, y + 65, 0.5f)
					, Actions.sequence(Actions.fadeIn(0.25f), Actions.fadeOut(0.25f), Actions.run(new Runnable() {

						@Override
						public void run() {
							getStage().getActors().removeValue(PlusScore.this, true);
						}
						
					})));
			this.addAction(action);
		}

		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			Assets.font.draw(batch, _str, getX()+5, getY() + 4);
		}
		
	}
	
	public void setScore(int score) {
		if(score > _score) {
			String str = "+" + (score - _score);
			this.getStage().addActor(new PlusScore(str));
		}
		_score = score;
		if(_score > _best) {
			_best = _score;
			Helper.setScore(_best);
		}
	}
	
	@Override
	public void act(float arg0) {
		super.act(arg0);
	}

	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		batch.draw(Assets.Best, -2, 15, 120, 30, 240, 60, 1, 1, 0);
		Assets.font.draw(batch, " " + _best, 130, 60);
		batch.draw(Assets.Score, -2, 680, 120, 30, 240, 60, 1, 1, 0);
		Assets.font.draw(batch, " " + _score, 130, 720);
	}
	
}
