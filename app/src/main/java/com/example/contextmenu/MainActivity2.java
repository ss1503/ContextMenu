package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity2 extends AppCompatActivity implements View.OnCreateContextMenuListener {

    ListView serieLv;
    TextView resultTxt;

    Intent gi;

    int pos;
    boolean type;
    double mul, firstValue;
    double valuePlace, sum;

    String[] serie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gi = getIntent();

        serieLv = (ListView) findViewById(R.id.serieLv);
        resultTxt = (TextView) findViewById(R.id.resultTxt);


        mul = gi.getDoubleExtra("mul", -19);
        type = gi.getBooleanExtra("type", false);
        firstValue = gi.getDoubleExtra("firstValue", -19);

        serie = new String[20];
        if(!type)
        {
            for(int i = 0; i < 20; i++)
            {
                double num = firstValue + (i) * mul;
                String str = String.valueOf(num);

                if(str.contains("E"))
                {
                    NumberFormat numberFormat = new DecimalFormat();
                    numberFormat = new DecimalFormat("0.####E0");
                    serie[i] = numberFormat.format(num);
                }
                else
                {
                    serie[i] = String.valueOf(num);
                }
            }
        }
        else
        {
            for(int i = 0; i < 20; i++)
            {
                double num = firstValue * Math.pow(mul, i);
                String str = String.valueOf(num);

                if(str.contains("E"))
                {
                    NumberFormat numberFormat = new DecimalFormat();
                    numberFormat = new DecimalFormat("0.####E0");
                    serie[i] = numberFormat.format(num);
                }
                else
                {
                    serie[i] = String.valueOf(num);
                }

            }
        }

        serieLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                return false;
            }
        });
        serieLv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, serie);

        serieLv.setAdapter(adp);
        serieLv.setOnCreateContextMenuListener(this);
    }

    public void goBack(View view)
    {
        finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Pleace choose one:");
        menu.add("Place");
        menu.add("Sum");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        valuePlace = pos + 1;
        String itemStr = item.getTitle().toString();
        if (itemStr.equals("Sum"))
        {
            if(!type)
            {
                sum = (valuePlace * (firstValue + Double.parseDouble(serie[pos]))) / 2;
            }
            else
            {
                sum = (firstValue * (Math.pow(mul, valuePlace) - 1)) / (mul - 1);
            }

            resultTxt.setText("Sn = " + sum);
        }
        else if(itemStr.equals("Place"))
        {
            resultTxt.setText("n = " + (int)valuePlace);
        }

        return super.onContextItemSelected(item);
    }
}