package com.SIDevelopers.noticiasdehonduras;




import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity{
	private static int SPLASH_TIME_OUT = 1400;
	
	@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startAnimation();
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent i = new Intent(SplashActivityNew.this, MainActivity.class);
                //startActivity(i);
                startActivity(new Intent("com.SIDevelopers.noticiasdehonduras.MAIN"));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

	private void startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.reset();
        ImageView imageLogo = (ImageView) findViewById(R.id.imgSplash);
        TextView textInfo = (TextView) findViewById(R.id.txtInfo);
        imageLogo.clearAnimation();
        textInfo.clearAnimation();
        imageLogo.startAnimation(animation);
        textInfo.startAnimation(animation);
    }
	
}
