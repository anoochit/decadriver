package net.redlinesoft.app.decadriver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class DecadriverActivity extends Activity {
	/** Called when the activity is first created. */
	public boolean henshin_finish = false;
	public Integer card_id = 10;
	private MediaPlayer mMediaPlayer = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClickChangeCard(View v) {
		final CharSequence[] items = { "Kuuga", "Agito", "Ryuki", "Faiz",
				"Blade", "Hibiki", "Kabuto", "Den-O", "Kiva", "Decade" };		
		// check reset status		
		if (henshin_finish==false) {		
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Pick a card");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				// onclick dialog to select card
				public void onClick(DialogInterface dialog, int item) {
					// set card id
					card_id = item;
					// play sound insert card
					playSound(R.raw.instert_card);
				}
			}).show();
		}
 
	}

	public void onClickHenshin(View v) {
		
		ImageView imageDecaLeft = (ImageView) findViewById(R.id.imageView1);
		ImageView imageDecaRight = (ImageView) findViewById(R.id.imageView2);
		final View imageDeca = (View) findViewById(R.id.imageView3);
		
		if (this.henshin_finish == false) {
			// Rotate Deca
			Animation rotation = AnimationUtils.loadAnimation(this,
					R.anim.rotate_deca);
			imageDeca.startAnimation(rotation);
			
			// Push Deca
			Animation left_push_deca = AnimationUtils.loadAnimation(this,R.anim.push_left_deca);
			imageDecaLeft.startAnimation(left_push_deca);
			Animation right_push_deca = AnimationUtils.loadAnimation(this,R.anim.push_right_deca);
			imageDecaRight.startAnimation(right_push_deca);
			
			// play henshin sound
			switch (card_id) {
			case 0:
				playSound(R.raw.decade_kuuga_form);
				break;
			case 1:
				playSound(R.raw.decade_agito_form);
				break;
			case 2:
				playSound(R.raw.decade_ryuki_form);
				break;
			case 3:
				playSound(R.raw.decade_faiz_555_form);
				break;
			case 4:
				playSound(R.raw.decade_blade_form);
				break;
			case 5:
				playSound(R.raw.decade_hibiki_form);
				break;
			case 6:
				playSound(R.raw.decade_kabuto_form);
				break;
			case 7:
				playSound(R.raw.decade_den_o_form);
				break;
			case 8:
				playSound(R.raw.decade_kiva_form);
				break;
			case 9:
				playSound(R.raw.decade_form);
				break;
			default :
				playSound(R.raw.remove_card);
				break;
			}
			henshin_finish = true;
		} else {
			// Reset Deca
			Animation rotation = AnimationUtils.loadAnimation(this,
					R.anim.rotate_deca_reset);
			imageDeca.startAnimation(rotation);
			// Pull Deca
			Animation left_pull_deca = AnimationUtils.loadAnimation(this,R.anim.pull_left_deca);
			imageDecaLeft.startAnimation(left_pull_deca);
			Animation right_pull_deca = AnimationUtils.loadAnimation(this,R.anim.pull_right_deca);
			imageDecaRight.startAnimation(right_pull_deca);
			// playsound remove card
			playSound(R.raw.remove_card);
			henshin_finish = false;
		}

	}

	/*
	 * Playsound
	 */
	public void playSound(int resources) {
		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
		}
		mMediaPlayer = MediaPlayer.create(this, resources);
		mMediaPlayer.start();
	}

}