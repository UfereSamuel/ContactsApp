package com.ighub.sqliteclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ighub.sqliteclass.dbfiles.ContactModel;
import com.ighub.sqliteclass.dbfiles.DatabaseHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerContacts;
    private DatabaseHandler databaseHandler;
    private ArrayList<ContactModel> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerContacts = findViewById(R.id.recycler_contact);
        recyclerContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerContacts.setHasFixedSize(true);

        databaseHandler = new DatabaseHandler(this);
        contactList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        contactList = databaseHandler.listContact();

        ContactsRecyclerAdapter recyclerAdapter = new ContactsRecyclerAdapter(this, contactList);
        recyclerContacts.setAdapter(recyclerAdapter);
    }

    public void openEditActivity(View view) {
        startActivity(new Intent(this, EditActivity.class));
    }
}
