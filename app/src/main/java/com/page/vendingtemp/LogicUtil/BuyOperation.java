package com.page.vendingtemp.LogicUtil;

/**
 * 购物操作
 * Created by Miley_Ren on 2017/7/4.
 */

public class BuyOperation {
    /**
     * 发送出货信息
     *
     * @param channelNo  售货机编号
     * @param deliverNum 出货数量
     */
    public static boolean deliverWare(int channelNo, int deliverNum) {
        boolean result =true;
        //1、根据货道编号和出货量进行出货
        return result;
    }

    /**
     * 更新数据库、货道、订单信息
     *
     * @param channelId 货道ID
     * @param sellNum   卖出商品数量
     * @return 返回更新信息
     **/
    public static boolean updateDB(int channelId, int sellNum) {
        boolean result = true;
        //1、根据货道ID及卖出商品数量，跟新后台货道信息
        //2、根据售卖信息，在订单列表里增加一条信息
        return result;
    }

}
