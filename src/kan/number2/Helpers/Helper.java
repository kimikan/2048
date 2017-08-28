package kan.number2.Helpers;

import kan.number2.MyApplication;
import kan.number2.R;
import kan.number2.views.MapStage;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Helper {
	
	public static final String SCORE = "score";
	
	public static Preferences save = Gdx.app.getPreferences("My preferences");
	
	static {
		if (save.contains(SCORE))
			save.putInteger(SCORE, 0);
	}
	
	
	
	public static int getScore() {
		return save.getInteger(SCORE, 0);
	}
	
	public static void setScore(int score) {
		save.putInteger(SCORE, score);
		save.flush();
	}
	
	public static void showSuccessDialog(final MapStage stage) {
		MyApplication.context.setAdVisible(true);
		MyApplication.context.handler.post(new Runnable() {
			
			@Override
			public void run() {
				final AlertDialog dialog = new AlertDialog.Builder(MyApplication.context)  
		        .setTitle(R.string.success_title)  
		        .setIcon(R.drawable.about)
		        .setMessage(R.string.success_text)
		        .setPositiveButton(android.R.string.yes, new Dialog.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface arg0, int arg1) {
		            	stage.reset();
		                arg0.dismiss();
		            }
		        }).setNegativeButton(android.R.string.no, new Dialog.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface arg0, int arg1) {
		                arg0.dismiss();
		                System.exit(0);
		            }
		        }).create();
				
				dialog.show(); 
			}  
	         
		});
	}
	
	public static void showFailedDialog(final MapStage stage) {
		MyApplication.context.setAdVisible(true);
		
		MyApplication.context.handler.post(new Runnable() {
			
			@Override
			public void run() {
				final AlertDialog dialog = new AlertDialog.Builder(MyApplication.context)  
		        .setTitle(R.string.failed_title)  
		        .setIcon(R.drawable.about)
		        .setMessage(R.string.failed_text)
		        .setPositiveButton(android.R.string.yes, new Dialog.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface arg0, int arg1) {
		            	stage.reset();
		                arg0.dismiss();
		            }
		        }).setNegativeButton(android.R.string.no, new Dialog.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface arg0, int arg1) {
		                arg0.dismiss();
		            }
		        }).create();
				
				dialog.show(); 
			}  
	         
		});
	}
}
