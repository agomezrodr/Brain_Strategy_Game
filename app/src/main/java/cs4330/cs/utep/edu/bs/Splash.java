package cs4330.cs.utep.edu.bs;
/*
Adrian Gomez Rodriguez and Jesus Ramos
CS4330 Mobile apps
Dr. Cheon
 */
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends Activity {
    TextView title;
    MediaPlayer st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        st = MediaPlayer.create(Splash.this, R.raw.latigo);
        st.start();
        title = (TextView) findViewById(R.id.textView);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotatefade);
        title.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent intent = new Intent(Splash.this, MenuOptions.class);
                startActivity(intent);
                st.release();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}