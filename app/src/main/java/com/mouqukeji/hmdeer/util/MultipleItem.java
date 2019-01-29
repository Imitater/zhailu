package com.mouqukeji.hmdeer.util;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mouqukeji.hmdeer.bean.AllOrderBean;

public class MultipleItem implements MultiItemEntity {
    public static final int Generation_Payment = 1;
    public static final int Generation_Order = 2;
    public static final int Ongoing = 3;
    public static final int Complete = 4;
    public static final int To_Evaluate = 5;
    public static final int Cancelled = 6;
    public static final int HaveRefund = 7;
    public static final int Sending = 8;
    public static final int Sended = 9;
    private int itemType = 1;
    private AllOrderBean.TasksBean bean;

    public AllOrderBean.TasksBean getBean() {
        return bean;
    }

    public void setBean(AllOrderBean.TasksBean bean) {
        this.bean = bean;
    }

    public MultipleItem(int itemType,AllOrderBean.TasksBean bean) {
        this.itemType = itemType;
        this.bean=bean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}


