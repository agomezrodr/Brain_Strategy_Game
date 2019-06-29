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

public class ResultActivity extends Activity {
    Button b4;
    MediaPlayer l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        l = MediaPlayer.create(ResultActivity.this, R.raw.trompetaperdedor);
        l.start();
        TextView textResult = (TextView) findViewById(R.id.textResult);
        TextView textResult2 = (TextView) findViewById(R.id.textResult2);
        TextView textResult3 = (TextView) findViewById(R.id.textResult3);
        b4 = (Button) findViewById(R.id.exit);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        textResult.setText("Wrong answer!!");
        textResult2.setText("Your have " + " " + score + " questions correct! ");
        textResult3.setText("Better lucky next time!");

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setData(Uri.parse(""));
                setResult(Activity.RESULT_OK, i);
                finish();
                l.release();
            }
        });
    }

    public void playagain(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
        l.release();
    }
}