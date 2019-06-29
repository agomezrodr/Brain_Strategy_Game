package cs4330.cs.utep.edu.bs;
/*
Adrian Gomez Rodriguez and Jesus Ramos
CS4330 Mobile apps
Dr. Cheon
 */
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuOptions extends Activity {
    Button b1, b2, b3, b4;
    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_menu);
        b1 = (Button) findViewById(R.id.btnsimple);
        b2 = (Button) findViewById(R.id.about);
        b3 = (Button) findViewById(R.id.instructions);
        b4 = (Button) findViewById(R.id.exit);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuOptions.this, QuestionActivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MenuOptions.this, About.class);
                startActivity(intent2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MenuOptions.this, Instructions.class);
                startActivity(intent3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setData(Uri.parse(""));
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}