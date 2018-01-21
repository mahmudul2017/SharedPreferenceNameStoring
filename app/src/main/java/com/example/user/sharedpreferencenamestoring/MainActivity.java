package com.example.user.sharedpreferencenamestoring;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView1, textView2;
    EditText editText1, editText2;
    Button button1, button2;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting all view
        View();

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        //show current date and time
        Calendar_Time();
    }

    private void View() {
        textView1 = findViewById(R.id.textview1);
        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView2 = findViewById(R.id.textview2);
    }

    public void Calendar_Time(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        // Now we display formattedDate value in TextView
        textView2.setText(" Date : "+formattedDate);
        textView2.setGravity(Gravity.CENTER);
        textView2.setTextSize(20);
        //setContentView(textView2);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {

            String username = editText1.getText().toString();
            String password = editText2.getText().toString();

            if(username.equals("") && password.equals("")){

                Toast.makeText(getApplicationContext(), "Please Enter Username or Password!", Toast.LENGTH_LONG).show();
            }

            else {
                //creating method for storing all data
                sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
                //use for edit
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("Name", username);
                editor.putString("Pass", password);
                editor.commit();

                Toast.makeText(getApplicationContext(),"Successfully Saved", Toast.LENGTH_LONG).show();

                editText1.setText("");
                editText2.setText("");

            }


        } else if (v.getId() == R.id.button2) {

            sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);

            if (sharedPreferences.contains("Name") && sharedPreferences.contains("Pass")) {

                String user = sharedPreferences.getString("Name", "Name not Found!");
                String pass = sharedPreferences.getString("Pass", "Password not Found!");

                textView1.setText(user + "\n" + pass);
            }
        }
    }
}
