package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.contextmenu.R;

import java.net.NoRouteToHostException;

public class MainActivity extends AppCompatActivity {

    ToggleButton changeTbtn;
    EditText firstValueEdt;
    EditText mulOrDifEdt;

    boolean type;
    double firstValue;
    double mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeTbtn = (ToggleButton) findViewById(R.id.changeTbtn);
        firstValueEdt = (EditText) findViewById(R.id.firstValueEdt);
        mulOrDifEdt = (EditText) findViewById(R.id.mulOrDifEdt);

        type = false;
    }

    public void SeeSerie(View view)
    {
        if(changeTbtn.isChecked())
        {
            type = true;
        }
        else
        {
            type = false;
        }

        String temp = firstValueEdt.getText().toString();
        firstValue = Double.parseDouble(temp);

        temp = mulOrDifEdt.getText().toString();
        mul = Double.parseDouble(temp);

        Intent si = new Intent(this, com.example.contextmenu.MainActivity2.class);
        si.putExtra("type",type);
        si.putExtra("firstValue",firstValue);
        si.putExtra("mul", mul);
        startActivity(si);
    }
}