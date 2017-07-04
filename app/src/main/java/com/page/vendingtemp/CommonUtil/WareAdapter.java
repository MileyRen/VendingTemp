package com.page.vendingtemp.CommonUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.page.vendingtemp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.page.vendingtemp.R.id.warePrice;

/**
 * 自定义商品Adapter,继承自BaseAdapter
 * Created by Miley_Ren on 2017/7/3.
 */

public class WareAdapter extends BaseAdapter {

    private final static String TAG = WareAdapter.class.getSimpleName();
    //上下文
    private Context context;
    //资源
    private JSONArray jdata;
    //视图容器
    private LayoutInflater gridContainer;

    //构造方法
    public WareAdapter(Context context, JSONArray jdata) {
        this.context = context;
        this.jdata = jdata;
        gridContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
    }

    /**
     * 返回的数据源对象的个数
     */
    @Override
    public int getCount() {
        return jdata.length();
    }

    /**
     * 返回指定位置position上的列表
     */
    @Override
    public Object getItem(int position) {
        // return imagesPath[position];
        Integer channelId = null;
        try {
            JSONObject object = (JSONObject) jdata.get(position);
            channelId = object.getInt("channelId");
        } catch (JSONException e) {
            Log.w(TAG, "json格式错误" + e.getMessage());
        }
        return channelId;
    }

    /**
     * 返回指定位置处的行id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回列表项对应的视图
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //view = getLayoutInflater().inflate(R.layout.ware_grid, null);
            //gridContainer = LayoutInflater.from(context);
            //获取ware_grid布局文件的视图
            view = gridContainer.inflate(R.layout.ware_grid, null);
        }
        ImageView iv = view.findViewById(R.id.wareImage);
        ImageView sellout = view.findViewById(R.id.wareSellout);
        TextView tv = view.findViewById(R.id.wareName);
        TextView tv2 = view.findViewById(warePrice);
        final Bitmap[] bitmap = new Bitmap[jdata.length()];
        JSONObject wareObject = null;
        try {
            wareObject = (JSONObject) jdata.get(i);
            bitmap[i] = BitmapHelper.getBitmapTask(wareObject.getString("imagePath"));
            iv.setImageBitmap(bitmap[i]);
            String num = "(有货)";
            if (wareObject.getInt("stockNumNow") == 0) {
                num = "(无货)";
                sellout.setImageResource(R.drawable.sellout);
            }
            String price = String.valueOf(wareObject.getInt("price")/100.00);
            tv.setText(wareObject.getString("wareName") + num);
            tv2.setText(price);
        } catch (Exception e) {
            Log.w(TAG, "设置图片失败");
        }
        return view;
    }
}
