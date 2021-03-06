package com.ighub.sqliteclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ighub.sqliteclass.dbfiles.ContactModel;
import com.ighub.sqliteclass.dbfiles.DatabaseHandler;

public class MainActivity extends AppCompatActivity {
    
    private EditText etFname, etLname, etEmail, etPhone, etId;
    private Button btnLogin;
    private String fName, lName, email, phone, id;

    DatabaseHandler db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(getApplicationContext());
        
        etEmail = findViewById(R.id.etEmail);
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etlname);
        etPhone = findViewById(R.id.etPhone);
        etId = findViewById(R.id.etId);
        btnLogin = findViewById(R.id.btnLogin);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName = etFname.getText().toString().trim();
                lName = etLname.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                id = etId.getText().toString().trim();
                
                proceed();
            }
        });
    }

    private void proceed() {
        ContactModel model = new ContactModel();
        db.addContact(model);
        Toast.makeText(this, "Saved successfully...", Toast.LENGTH_SHORT).show();
    }
}
