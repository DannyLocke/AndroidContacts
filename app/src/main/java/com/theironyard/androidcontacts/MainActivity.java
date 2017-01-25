package com.theironyard.androidcontacts;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener  {

    ArrayAdapter<String> contacts;

    ListView list;
    EditText name;
    EditText number;
    Button addButton;

    static final int CONTACT_REQUEST = 1;
    static final String CONTACT_CARD = "com.theironyard.androidcontacts.contact";
    final String FILENAME = "contact.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);
        name = (EditText) findViewById(R.id.editName);
        number = (EditText) findViewById(R.id.editNumber);
        addButton = (Button) findViewById(R.id.button2);

        contacts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        loadContacts();
        list.setAdapter(contacts);

        addButton.setOnClickListener(this);
        list.setOnItemLongClickListener(this);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String contactName = name.getText().toString();
        String contactNumber = number.getText().toString();

        contacts.add(contactName + " (" + contactNumber + ")"); //add & combine edit fields

        name.setText("");
        number.setText("");
        saveContacts();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        final String contact = contacts.getItem(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contact");
        builder.setMessage("Are you sure you want to remove this item?");
        builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                contacts.remove(contact);
                saveContacts();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Main2Activity.class);
        String contact = contacts.getItem(position);
        intent.putExtra(CONTACT_CARD, contact);
        intent.putExtra("position", position);
        startActivityForResult(intent, CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CONTACT_REQUEST) {
            if(resultCode == RESULT_OK) {
                int position = data.getIntExtra("position", 0);
                String newNam = data.getStringExtra("newName");
                String newNum = data.getStringExtra("newNumber");
                String contact = contacts.getItem(position);
                contacts.remove(contact);
                contacts.add(newNam + "  ( " + newNum + "  )");
                saveContacts();
            }
        }
    }

    private void saveContacts() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < contacts.getCount(); i++) {
                String contact = contacts.getItem(i);
                sb.append(contact + "\n");
            }
            fos.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while(br.ready()) {
                String contactLine = br.readLine();
                contacts.add(contactLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}//end MainActivity