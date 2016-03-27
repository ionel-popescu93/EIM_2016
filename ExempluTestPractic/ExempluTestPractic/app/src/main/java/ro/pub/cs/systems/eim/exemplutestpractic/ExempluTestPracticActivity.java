package ro.pub.cs.systems.eim.exemplutestpractic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExempluTestPracticActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplu_test_practic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button0 = (Button)findViewById(R.id.press_button0);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text0 = (EditText)findViewById(R.id.text0);
                int value = Integer.valueOf(text0.getText().toString());
                value++;
                text0.setText(String.valueOf(value));
            }
        });

        Button button1 = (Button)findViewById(R.id.press_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text1 = (EditText)findViewById(R.id.text1);
                int value = Integer.valueOf(text1.getText().toString());
                value++;
                text1.setText(String.valueOf(value));
            }
        });

        Button nextActivity = (Button)findViewById(R.id.nevigate_button);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("ro.cs.pub.ro.android.intent.action.SUM");
                EditText text0 = (EditText)findViewById(R.id.text0);
                EditText text1 = (EditText)findViewById(R.id.text1);

                int sum = Integer.valueOf(text0.getText().toString()) + Integer.valueOf(text1.getText().toString());
                intent.putExtra("sum", sum);
                startActivityForResult(intent, 1);
            }
        });
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        EditText text0 = (EditText)findViewById(R.id.text0);
        EditText text1 = (EditText)findViewById(R.id.text1);

        savedInstanceState.putString("text0", text0.getText().toString());
        savedInstanceState.putString("text1", text1.getText().toString());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText text0 = (EditText)findViewById(R.id.text0);
        EditText text1 = (EditText)findViewById(R.id.text1);

        text0.setText(savedInstanceState.getString("text0"));
        text1.setText(savedInstanceState.getString("text1"));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "Result OK", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Result Wrong", Toast.LENGTH_LONG).show();
                }
                break;

            // process other request codes
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplu_test_practic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
