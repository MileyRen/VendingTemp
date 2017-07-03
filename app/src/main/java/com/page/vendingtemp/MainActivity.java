package com.page.vendingtemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button wares_btn, exchange_bth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wares_btn = (Button) findViewById(R.id.wares_btn);
        exchange_bth = (Button) findViewById(R.id.exchange_bth);
        wares_btn.setOnClickListener(this);
        exchange_bth.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wares_btn:
                Intent intent = new Intent(this,WareActicity.class);
                startActivity(intent);
                break;
            case R.id.exchange_bth:
                break;
            default:
                break;
        }
    }
}
