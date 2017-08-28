package kan.number2.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IDrawable {

	void onRender(SpriteBatch sb);
	
	void onUpdate(float delta);
}
