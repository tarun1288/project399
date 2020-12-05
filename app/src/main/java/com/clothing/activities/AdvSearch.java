package com.clothing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.clothing.R;

public class AdvSearch extends AppCompatActivity {
    TextView tv_price_range,tv_price;
    Button btn_submit;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_search);

        getSupportActionBar().setTitle("Advanced Search");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn_submit=(Button)findViewById(R.id.btn_submit);
        et_name=(EditText)findViewById(R.id.et_name);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        seekBar.incrementProgressBy(10);
        seekBar.setMax(100);
        tv_price_range=(TextView)findViewById(R.id.tv_price_range);
        tv_price=(TextView)findViewById(R.id.tv_price);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10;
                progress = progress * 10;
                //Toast.makeText(getContext(), ""+progress, Toast.LENGTH_SHORT).show();
                tv_price_range.setText(String.valueOf(progress));
                tv_price.setText(String.valueOf(progress));

                tv_price_range.setText("Selected Range is In Between 0$ - "+String.valueOf(progress)+"$");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(AdvSearch.this,AdvancedSearchActivity.class);
                intent.putExtra("price",tv_price.getText().toString());
                intent.putExtra("pname",et_name.getText().toString());
                startActivity(intent);

                //serverData();

            }
        });
    }
}