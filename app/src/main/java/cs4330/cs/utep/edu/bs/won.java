package cs4330.cs.utep.edu.bs;
/*
Adrian Gomez Rodriguez and Jesus Ramos
CS4330 Mobile apps
Dr. Cheon
 */

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class won extends Activity {
    TextView last;
    Button b4;
    MediaPlayer w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.won);
        last = (TextView) findViewById(R.id.congo);
        Bundle b = getIntent().getExtras();
        int y = b.getInt("score");
        last.setText("FINAL SCORE:" + y);
        w = MediaPlayer.create(won.this, R.raw.fanfarrias);
        b4 = (Button) findViewById(R.id.exit);
        w.start();
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setData(Uri.parse(""));
                setResult(Activity.RESULT_OK, i);
                finish();
                w.release();
            }
        });
    }
}