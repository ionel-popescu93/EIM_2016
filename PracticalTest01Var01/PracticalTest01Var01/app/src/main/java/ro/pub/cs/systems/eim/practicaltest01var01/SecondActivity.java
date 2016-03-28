package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.StringTokenizer;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra("result");
            StringTokenizer tokenizer = new StringTokenizer(text, "+");

            int sum = 0;
            while (tokenizer.hasMoreElements()) {
                //Log.d("lala", (String)tokenizer.nextElement());
                sum += Integer.valueOf((String)tokenizer.nextElement());
            }

            Intent result = new Intent();

            result.putExtra("sum", sum);

            setResult(RESULT_OK, result);
            finish();
        }

    }
}
