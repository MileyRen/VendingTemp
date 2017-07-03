package com.page.vendingtemp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.page.vendingtemp.CommonUtil.BitmapHelper;
import com.page.vendingtemp.CommonUtil.Constanst;
import com.page.vendingtemp.CommonUtil.WareAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WareActicity extends AppCompatActivity {

    private final static String TAG = WareActicity.class.getSimpleName();
    GridView grid;
    ImageView testImage;
    String wareInfo = "[\n" +
            "    {\n" +
            "        \"channelId\": 49,\n" +
            "        \"isDiscount\": 0,\n" +
            "        \"mOperaterId\": 26,\n" +
            "        \"price\": 2.5,\n" +
            "        \"stockNumNow\": 2,\n" +
            "        \"wareDesc\": \"百事可乐\",\n" +
            "        \"wareName\": \"百事可乐300ml\",\n" +
            "        \"imagePath\": \"http://49.52.10.197:8080/vendingfile/kele.jpg\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"channelId\": 50,\n" +
            "        \"isDiscount\": 0,\n" +
            "        \"mOperaterId\": 26,\n" +
            "        \"price\": 3,\n" +
            "        \"stockNumNow\": 2,\n" +
            "        \"wareDesc\": \"茉莉清茶，去火\",\n" +
            "        \"wareName\": \"茉莉清茶\",\n" +
            "        \"imagePath\": \"http://49.52.10.197:8080/vendingfile/moli.jpg\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"channelId\": 51,\n" +
            "        \"isDiscount\": 0,\n" +
            "        \"mOperaterId\": 26,\n" +
            "        \"price\": 3,\n" +
            "        \"stockNumNow\": 2,\n" +
            "        \"wareDesc\": \"无描述信息\",\n" +
            "        \"wareName\": \"七喜\",\n" +
            "        \"imagePath\": \"http://49.52.10.197:8080/vendingfile/qixi.jpg\"\n" +
            "    }\n" +
            "]";
    JSONArray jsarray = null;

    public final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == Constanst.CLICK_IMAGE) {
                Bundle bundle = msg.getData();
                String ImagePath = bundle.getString("clickImage");
                try {
                    testImage.setImageBitmap(BitmapHelper.getBitmapTask(ImagePath));
                    Toast.makeText(WareActicity.this, "选货成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d(TAG, "选货失败" + e.getMessage());
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ware_acticity);
        grid = (GridView) findViewById(R.id.wareGrid);
        testImage = (ImageView) findViewById(R.id.testImage);
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
                    testImage.setImageBitmap(BitmapHelper.getBitmapTask(imagePath));
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("clickImage", imagePath);
                    msg.what = Constanst.CLICK_IMAGE;
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    Log.d(TAG, "图片" + imagePath + "被选中");
                } catch (Exception e) {
                    Log.d(TAG, "点击图片失败");
                }
            }
        });
    }
}

