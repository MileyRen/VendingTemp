package com.page.vendingtemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.page.vendingtemp.CommonUtil.BitmapHelper;
import com.page.vendingtemp.LogicUtil.BuyOperation;

public class SelectedWareActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = SelectedWareActivity.class.getSimpleName();
    ImageView selectedWareImage;
    TextView selectedWareDes, selectedWareName, selectedWarePrice;
    Button pay_btn;
    String imagePath, wareName, wareDesc, price;
    Integer channelId, channelNo, sellNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_ware);
        selectedWareImage = (ImageView) findViewById(R.id.selectedWareImage);
        selectedWareDes = (TextView) findViewById(R.id.selectedWareDes);
        selectedWareName = (TextView) findViewById(R.id.selectedWareName);
        selectedWarePrice = (TextView) findViewById(R.id.selectedWarePrice);
        pay_btn = (Button) findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            imagePath = intent.getStringExtra("imagePath");
            wareName = intent.getStringExtra("wareName");
            wareDesc = intent.getStringExtra("wareDesc");
            price = intent.getStringExtra("price");
            channelId = Integer.valueOf(intent.getStringExtra("channelId"));
            channelNo = Integer.valueOf(intent.getStringExtra("channelNo"));
            try {
                selectedWareImage.setImageBitmap(BitmapHelper.getBitmapTask(imagePath));
                selectedWareName.setText("商品名：" + wareName);
                selectedWareDes.setText(wareDesc);
                selectedWarePrice.setText(price);
            } catch (Exception e) {
                Log.d(TAG, "选货失败\n" + e.getMessage());
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_btn:
                //出货
                boolean deliverResult = BuyOperation.deliverWare(channelNo, sellNum);

                //更新数据库信息
                boolean updatDBResult = BuyOperation.updateDB(channelId,sellNum);

                break;
            default:
                break;
        }
    }
}
