package com.theironyard.androidcontacts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemLongClickListener  {

    ArrayAdapter<String> contacts;

    ListView list;
    EditText name;
    EditText number;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);
        name = (EditText) findViewById(R.id.editName);
        number = (EditText) findViewById(R.id.editNumber);
        addButton = (Button) findViewById(R.id.button2);

        contacts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(contacts);

        addButton.setOnClickListener(this);
        list.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String contactName = name.getText().toString();
        String contactNumber = number.getText().toString();

        contacts.add(contactName + " (" + contactNumber + ")"); //add & combine edit fields

        name.setText("");
        number.setText("");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        String contact = contacts.getItem(position);
        contacts.remove(contact);
        return true;
    }



}//end MainActivity