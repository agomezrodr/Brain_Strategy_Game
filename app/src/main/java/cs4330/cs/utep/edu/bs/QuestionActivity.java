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
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuestionActivity extends Activity {
    MediaPlayer mp;
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    Question currentQ;
    TextView txtQuestion, times, scored;
    Button button1, button2, button3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(QuestionActivity.this, R.raw.mus);
        mp.start();
        DataBaseHelper db = new DataBaseHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.exit);
        scored = (TextView) findViewById(R.id.score);
        times = (TextView) findViewById(R.id.timers);

        setQuestionView();
        times.setText("00:02:00");

        CounterClass timer = new CounterClass(120000, 1000);
        timer.start();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button1.getText().toString());
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button2.getText().toString());
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button3.getText().toString());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setData(Uri.parse(""));
                setResult(Activity.RESULT_OK, i);
                finish();
                mp.release();
            }
        });
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {
            score++;
            scored.setText("Score : " + score);
        } else {
            Intent intent = new Intent(QuestionActivity.this,
                    ResultActivity.class);

            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
            mp.release();
        }
        if (qid < 22) {
            currentQ = quesList.get(qid);
            setQuestionView();
        } else {
            Intent intent = new Intent(QuestionActivity.this,won.class);
            Bundle b = new Bundle();
            b.putInt("score",score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
            mp.release();

        }
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            times.setText("Time is up");
        }

      @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }
    }

    private void setQuestionView() {
        // the method which will put everything together
        txtQuestion.setText(quesList.get(qid).getQUESTION());
        button1.setText(quesList.get(qid).getOPTA());
        button2.setText(quesList.get(qid).getOPTB());
        button3.setText(quesList.get(qid).getOPTC());
        qid++;
    }
}