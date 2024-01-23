package com.sharif111.fingerprintathentication;

import static com.sharif111.fingerprintathentication.R.id.titleeditinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class noteUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_update);

                EditText titleInput = findViewById(R.id.titleeditinput);
                EditText descriptionInput = findViewById(R.id.descriptioneditinput);
                MaterialButton saveBtn = findViewById(R.id.saveeditbtn);


                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();


                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = titleInput.getText().toString();
                        String description = descriptionInput.getText().toString();
                        long createdTime = System.currentTimeMillis();

                        realm.beginTransaction();

                        Notes notes = realm.createObject(Notes.class);
                        notes.setTitle(title);
                        notes.setDescription(description);
                        notes.setCreatedTime(createdTime);

                        realm.commitTransaction();

                        Toast.makeText(getApplicationContext(), "Note Saved successfully", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });


    }
}