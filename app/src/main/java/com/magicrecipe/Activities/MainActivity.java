package com.magicrecipe.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.magicrecipe.R;

public class MainActivity extends AppCompatActivity {

    EditText input_name;
    Button btn_sbmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn_sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Recepie.class);
                i.putExtra("user", input_name.getText().toString());
                startActivity(i);
                /*if (validate()) {

                }*/
            }
        });
    }

    private void init() {
        input_name=findViewById(R.id.input_name);
        btn_sbmit=findViewById(R.id.submit_btn_next);
    }
    public boolean validate() {
        boolean valid = true;
        String name = input_name.getText().toString().trim();
        if (name.isEmpty()) {
            input_name.setError("enter a valid ingredients");
            valid = false;
        } else {
            input_name.setError(null);
        }
        return valid;
    }
}
