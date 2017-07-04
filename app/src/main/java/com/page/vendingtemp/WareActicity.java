package com.page.vendingtemp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.page.vendingtemp.CommonUtil.WareAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.page.vendingtemp.CommonUtil.WareInfoTest.WARE_INFO;

public class WareActicity extends AppCompatActivity {

    private final static String TAG = WareActicity.class.getSimpleName();
    GridView grid;
    String wareInfo = WARE_INFO;
    JSONArray jsarray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware);
        grid = (GridView) findViewById(R.id.wareGrid);
        try {
            jsarray = new JSONArray(wareInfo);
        } catch (JSONException e) {
            Log.d("WareActivity", "转Json格式失败");
        }
        grid.setAdapter(new WareAdapter(this, jsarray));
        //点击事件
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    JSONObject temp = (JSONObject) jsarray.get(position);
                    String imagePath = temp.getString("imagePath");
                    String wareName = temp.getString("wareName");
                    int channelId = temp.getInt("channelId");
                    int channelNo = temp.getInt("channelNo");
                    String wareDesc = temp.getString("wareDesc");
                    String price = String.valueOf(temp.getInt("price") / 100.00);
                    Integer stockNumNow = temp.getInt("stockNumNow");

                    if (stockNumNow <= 0) {
                        /**商品已售罄，提示*/
                        Toast.makeText(getApplicationContext(), "此货道商品已售罄，请选择其他货道商品", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "商品已售罄");
                    } else {
                        /**选货成功，进入选货成功界面*/
                        Intent intent = new Intent(WareActicity.this, SelectedWareActivity.class);
                        intent.putExtra("imagePath", imagePath);
                        intent.putExtra("wareName", wareName);
                        intent.putExtra("wareDesc", wareDesc);
                        intent.putExtra("price", price);
                        intent.putExtra("channelId", String.valueOf(channelId));
                        intent.putExtra("channelNo", String.valueOf(channelNo));
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.d(TAG, "点击图片失败");
                }

            }
        });
    }
}

