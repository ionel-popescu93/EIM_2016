package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PhoneDialerActivity extends AppCompatActivity {

    class CodeNumberListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText number = (EditText) findViewById(R.id.number);
            number.setText(number.getText().append(((Button) v).getText()));
        }
    }

    class ContactButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            EditText phone = (EditText)findViewById(R.id.number);
            String phoneNumber = phone.getText().toString();
            Log.d("lala", "phone number is " + phoneNumber);

            if (phoneNumber.length() > 0) {
                Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                startActivityForResult(intent, Constants.CONTACTS_MANAGER_REQUEST_CODE);
            } else {
                Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
            }
        }
    }

    class DeleteNumberListener implements View.OnClickListener {
        public void onClick(View v) {
            EditText number = (EditText) findViewById(R.id.number);
            if (number.length() == 0)
                return;
            number.setText(number.getText().delete(number.length() - 1, number.length()));
        }
    }

    class CallNumberListener implements View.OnClickListener {
        public void onClick(View v) {
            EditText number = (EditText) findViewById(R.id.number);

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number.getText().toString()));
            startActivity(intent);
        }
    }

    class CloseNumberListener implements View.OnClickListener {
        public void onClick(View v) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CodeNumberListener codeNumberListener = new CodeNumberListener();
        Button b0 = (Button) findViewById(R.id.button0);
        b0.setOnClickListener(codeNumberListener);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(codeNumberListener);
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(codeNumberListener);
        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(codeNumberListener);
        Button b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(codeNumberListener);
        Button b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(codeNumberListener);
        Button b6 = (Button) findViewById(R.id.button6);
        b6.setOnClickListener(codeNumberListener);
        Button b7 = (Button) findViewById(R.id.button7);
        b7.setOnClickListener(codeNumberListener);
        Button b8 = (Button) findViewById(R.id.button8);
        b8.setOnClickListener(codeNumberListener);
        Button b9 = (Button) findViewById(R.id.button9);
        b9.setOnClickListener(codeNumberListener);
        Button b_star = (Button) findViewById(R.id.button_star);
        b_star.setOnClickListener(codeNumberListener);
        Button b_diez = (Button) findViewById(R.id.button_diez);
        b_diez.setOnClickListener(codeNumberListener);

        ImageButton img1 = (ImageButton) findViewById(R.id.imageButton);
        img1.setOnClickListener(new DeleteNumberListener());

        ImageButton img2 = (ImageButton) findViewById(R.id.imageButton2);
        img2.setOnClickListener(new CallNumberListener());

        ImageButton img3 = (ImageButton) findViewById(R.id.imageButton3);
        img3.setOnClickListener(new CloseNumberListener());

        ImageButton img4 = (ImageButton) findViewById(R.id.imageButton4);
        img4.setOnClickListener(new ContactButtonListener());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone_dialer, menu);
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
