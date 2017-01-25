package com.theironyard.androidcontacts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
        implements View.OnClickListener
{
    EditText newName;
    EditText newNumber;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String contact = intent.getStringExtra(MainActivity.CONTACT_CARD);
        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setTextSize(25);
        textView.setText(contact);

        newName = (EditText) findViewById(R.id.editName);
        newName.setText(getIntent().getExtras().getString("newName", ""));
        newNumber = (EditText) findViewById(R.id.editNumber);
        newNumber.setText(getIntent().getExtras().getString("newNumber", ""));

        position = getIntent().getExtras().getInt("position", 0);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("newName", newName.getText().toString());
        returnIntent.putExtra("newNumber", newNumber.getText().toString());
        returnIntent.putExtra("position", position);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
