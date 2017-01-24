package com.theironyard.androidcontacts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
        //implements View.OnClickListener
        {

//    TextView contact;
//    EditText contactNotes;
//
//    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String contact = intent.getStringExtra(MainActivity.CONTACT_CARD);
        TextView textView = new TextView(this);
        textView.setTextSize(25);
        textView.setText(contact);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main2);
        layout.addView(textView);
    }

//    @Override
//    public void onClick(View view) {
//
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("contactNotes", contactNotes.getText().toString());
//        returnIntent.putExtra("position", position);
//        setResult(Activity.RESULT_OK, returnIntent);
//        finish();
//    }
}
