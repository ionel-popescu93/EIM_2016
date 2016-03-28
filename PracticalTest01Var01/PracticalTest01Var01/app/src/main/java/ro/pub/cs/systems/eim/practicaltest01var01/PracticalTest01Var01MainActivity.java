package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {

    Button addButton = null;
    EditText resultText = null;
    EditText getText = null;
    Button compute = null;
    String previousComputed = null;
    int previousSum = 0;
    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), intent.getStringExtra("message") , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);
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

        resultText = (EditText)findViewById(R.id.result_text);
        getText = (EditText)findViewById(R.id.get_text);
        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = getText.getText().toString();
                String result = resultText.getText().toString();

                if (result != null && result.length() > 0) {
                    resultText.setText(result + "+" + text);
                } else {
                    resultText.setText(text);
                }
            }
        });
        compute = (Button)findViewById(R.id.compute_button);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousComputed != null && previousComputed.compareTo(resultText.getText().toString()) == 0) {
                    Toast.makeText(getApplicationContext(), "Sum already computed " + previousSum, Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent("ro.cs.android.intent.action.SECOND");
                intent.putExtra("result", resultText.getText().toString());
                startActivityForResult(intent, 1);

            }
        });

        intentFilter.addAction("lala");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    int sum = intent.getIntExtra("sum", 0);
                    previousSum = sum;
                    previousComputed = resultText.getText().toString();
                    Toast.makeText(getApplicationContext(), "Sum is " + sum, Toast.LENGTH_LONG).show();
                    if (sum > 10) {
                        Log.d("lala", "trimit in service");
                        Intent intent2 = new Intent(getApplicationContext(), StartService.class);
                        intent2.putExtra("sum", sum);
                        getApplicationContext().startService(intent2);
                    }
                }
                break;

            // process other request codes
        }
    }

    protected void onDestroy() {
        Intent intent = new Intent(this, StartService.class);
        stopService(intent);
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("previousString", previousComputed);
        savedInstanceState.putInt("previousSum", previousSum);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        previousComputed = savedInstanceState.getString("previousString");
        previousSum = savedInstanceState.getInt("previousSum");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var01_main, menu);
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
