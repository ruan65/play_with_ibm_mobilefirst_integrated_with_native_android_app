package org.premiumapp.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.premiumapp.androidapp.data.JsonStoreAdapter;
import org.premiumapp.androidapp.data.model.Persona;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity {

    @Bind(R.id.et_first_name) EditText etName;
    @Bind(R.id.et_age) EditText etAge;

    private JsonStoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);

        adapter = ThisApp.get(this).getAdapter();
    }


    public void savePerson(View view) {

        adapter.savePerson(new Persona(etName.getText().toString(),
                Integer.valueOf(etAge.getText().toString())));

        etName.setText("");
        etAge.setText("");
        onBackPressed();
    }


}
