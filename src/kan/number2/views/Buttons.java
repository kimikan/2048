package kan.number2.views;

import kan.number2.MyApplication;
import kan.number2.Helpers.Assets;
import android.content.Intent;
import android.net.Uri;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Buttons {

	private static float width = Assets.Retry.getRegionWidth() * 2;
	
	private static float height = Assets.Retry.getRegionHeight() * 2;
	
	public static Rectangle startRect, shareRect;
	
	final static int YY = 520;
	
	static {
		float centerX = (MapStage.WIDTH/2 - width/2 - 30) + width/ 2;
		float centerY = YY + height/2;
		startRect = new Rectangle(centerX - width/4, centerY-height/4, width/2, height/2);
		float centerX2 = (MapStage.WIDTH/2 + 10) + width / 2;
		shareRect = new Rectangle(centerX2-width/4, centerY-height/4, width/2, height/2);
	}
	
	static float scale = 0.5f;
	public static void render(SpriteBatch batch, float delta) {
		
		batch.draw(retryDown ? Assets.RetryPressed : Assets.Retry, MapStage.WIDTH/2 - width/2 - 30, YY, width/2
				, height/2, width, height, scale, scale, 0);
		batch.draw(shareDown ? Assets.SharePressed : Assets.Share, MapStage.WIDTH/2 + 10, YY, width/2
				, height/2, width, height, scale, scale, 0);
	}
	
	static boolean retryDown = false;
	static boolean shareDown = false; 
	
	public static void touchDown(MapStage stage, float x, float y) {
		if(startRect.contains(x, y)) {
			retryDown = true;
			stage.reset();
		} else if(shareRect.contains(x, y)) {
			shareDown = true;
		}
	}
	
	public static boolean touchUp(float x, float y) {
		if(retryDown || shareDown) {
			if(shareDown) {
				Intent sendIntent = new Intent(); 
	            sendIntent.setAction(Intent.ACTION_SEND); 
	            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=kan.number2"); 
	            sendIntent.setType("text/plain"); 
	            MyApplication.context.startActivity(sendIntent);
			}
			
			retryDown = false;
			shareDown = false;
			return true;
		}
		
		return false;
	}
}
