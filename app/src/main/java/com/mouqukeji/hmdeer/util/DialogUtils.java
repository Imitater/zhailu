package com.mouqukeji.hmdeer.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.HelpUniversalInfoBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.ProvinceBean;
import com.mouqukeji.hmdeer.presenter.activity.DiscountPresenter;
import com.mouqukeji.hmdeer.presenter.activity.ExperienceCardPresenter;
import com.mouqukeji.hmdeer.presenter.fragment.AllOrderPresenter;
import com.mouqukeji.hmdeer.presenter.fragment.CancelledOrderPresenter;
import com.mouqukeji.hmdeer.presenter.fragment.CompleteOrderPresenter;
import com.mouqukeji.hmdeer.presenter.fragment.ToPayPresenter;
import com.mouqukeji.hmdeer.presenter.fragment.WaitListPresenter;
import com.mouqukeji.hmdeer.ui.activity.BigImageActivity;
import com.mouqukeji.hmdeer.ui.adapter.DialogProcressRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.adapter.ItemsRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.CenterDialogView;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;
import com.mouqukeji.hmdeer.ui.widget.PickerScrollView;
import com.mouqukeji.hmdeer.ui.widget.Pickers;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.ArrayList;
import java.util.List;

public class DialogUtils {
    private static PickerScrollView pickerScrollView;
    private static PickerScrollView pickerScrollView1;
    private static PickerScrollView pickerScrollView2;
    private static String sex;
    private static String address;
    private static String send;
    private static int addressId = 0;
    private static int timeId;
    private static int sexId = 0;
    private static TextView dialogSeekbarProgress;
    private static String category = "其他";
    private static int progress;
    private static OptionsPickerView build;
    private static String payway;
    private static int paywayId = 2;
    private static String gtydId;
    private static String item3;
    private static String item2;
    private static String item1;
    private static String day;
    private static String hour;
    private static String minute;


    //送达时间框
    public static int showButtomSendDialog(Context context, View view, boolean isCancelable, boolean isBackCancelable, final TextView helpTakeTimeTv) {
        final ButtomDialogView buttomDialogView = new ButtomDialogView(context, view, isCancelable, isBackCancelable);
        buttomDialogView.show();

        pickerScrollView1 = buttomDialogView.findViewById(R.id.dialog_pickerscrollview);
        TextView dialogDissmiss = buttomDialogView.findViewById(R.id.dialog_dissmiss);
        TextView dialogEnter = buttomDialogView.findViewById(R.id.dialog_enter);

        //設置item數據
        List list = new ArrayList<Pickers>();
        String[] id = new String[]{"0", "1"};
        String[] name = new String[]{"12:00～14:00", "18:00～22:00"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerScrollView1.setData(list);
        pickerScrollView1.setSelected(timeId);


        // 滚动选择器选中事件
        pickerScrollView1.setOnSelectListener(new PickerScrollView.onSelectListener() {

            @Override
            public void onSelect(Pickers pickers) {
                timeId = Integer.parseInt(pickers.getShowId());
                send = pickers.getShowConetnt();
            }
        });
        dialogDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //關閉dialog
                buttomDialogView.dismiss();
            }
        });
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //確定并關閉
                if (TextUtils.isEmpty(send)) {
                    send = "12:00～14:00";
                } else {
                    helpTakeTimeTv.setText(send);
                }
                buttomDialogView.dismiss();
            }
        });
        return timeId;
    }

    //设置性别
    public static int showButtomSexDialog(Context context, View view, boolean isCancelable, boolean isBackCancelable, final TextView textView) {
        final ButtomDialogView buttomDialogView = new ButtomDialogView(context, view, isCancelable, isBackCancelable);
        buttomDialogView.show();

        pickerScrollView2 = buttomDialogView.findViewById(R.id.dialog_pickerscrollview);
        TextView dialogDissmiss = buttomDialogView.findViewById(R.id.dialog_dissmiss);
        TextView dialogEnter = buttomDialogView.findViewById(R.id.dialog_enter);

        //設置item數據
        List list = new ArrayList<Pickers>();
        String[] id = new String[]{"0", "1", "2"};
        String[] name = new String[]{"男女不限", "男", "女"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerScrollView2.setData(list);
        pickerScrollView2.setSelected(sexId);

        // 滚动选择器选中事件
        pickerScrollView2.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                sexId = Integer.parseInt(pickers.getShowId());
                sex = pickers.getShowConetnt();
            }
        });
        dialogDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //關閉dialog
                buttomDialogView.dismiss();
            }
        });
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //確定并關閉
                if (TextUtils.isEmpty(sex)) {
                    sex = "男女不限";
                } else {
                    textView.setText(sex);
                }
                buttomDialogView.dismiss();
            }
        });
        return sexId;
    }


    //支付框
    public static ButtomDialogView payDialog(final Activity activity, View view, final boolean isCancelable, boolean isBackCancelable) {
        final ButtomDialogView buttomDialogView = new ButtomDialogView(activity, view, isCancelable, isBackCancelable);
        buttomDialogView.show();


        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeixin = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhifubao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);

        dialogPayYue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogPayYue.setChecked(true);
                    dialogPayWeixin.setChecked(false);
                    dialogPayZhifubao.setChecked(false);

                    dialogPayYue.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue)));
                    dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                    dialogPayZhifubao.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                }
            }
        });

        dialogPayWeixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogPayYue.setChecked(false);
                    dialogPayWeixin.setChecked(true);
                    dialogPayZhifubao.setChecked(false);

                    dialogPayYue.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                    dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue)));
                    dialogPayZhifubao.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                }
            }
        });

        dialogPayZhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogPayYue.setChecked(false);
                    dialogPayWeixin.setChecked(false);
                    dialogPayZhifubao.setChecked(true);

                    dialogPayYue.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                    dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                    dialogPayZhifubao.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue)));
                }
            }
        });

        return buttomDialogView;
    }

    //支付框
    public static ButtomDialogView payMemberDialog(final Activity activity, View view, final boolean isCancelable, boolean isBackCancelable) {
        final ButtomDialogView buttomDialogView = new ButtomDialogView(activity, view, isCancelable, isBackCancelable);
        buttomDialogView.show();
        final RadioButton dialogPayWeixin = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhifubao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        dialogPayWeixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogPayWeixin.setChecked(true);
                    dialogPayZhifubao.setChecked(false);
                    dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue)));
                    dialogPayZhifubao.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                }
            }
        });
        dialogPayZhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogPayWeixin.setChecked(false);
                    dialogPayZhifubao.setChecked(true);
                    dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.order_code_gray)));
                    dialogPayZhifubao.setButtonTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue)));
                }
            }
        });

        return buttomDialogView;
    }


    //进程显示框
    public static void processDialog(final Context context, View view, final boolean isCancelable, boolean isBackCancelable, HelpTakeInfoBean takeInfoBean) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogProcessClose = centerDialogView.findViewById(R.id.dialog_procress_close);
        RecyclerView dialogProcressRecyclerview = centerDialogView.findViewById(R.id.adapter_dialog_procress_recyclerview);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        dialogProcressRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        List list = new ArrayList<String>();
        List listTime = new ArrayList<String>();
        if (takeInfoBean.getDetail().getProgress().equals("1")) {
            list.add("订单提交成功");
            list.add("待支付");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("2")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("待接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("3")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("4")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("5")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");
            list.add("待评价");


            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("6")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("7")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");
            list.add("已退款");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("8")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getPakeup_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("9")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");
            list.add("已送达");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
        }

        DialogProcressRecyclerviewAdapter dialogProcressRecyclerviewAdapter = new DialogProcressRecyclerviewAdapter(context, takeInfoBean.getDetail().getProgress(), list, listTime);
        dialogProcressRecyclerview.setAdapter(dialogProcressRecyclerviewAdapter);

        //设置关闭按键监听
        dialogProcessClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });
    }

    //进程显示框
    public static void processDialog(final Context context, View view, final boolean isCancelable, boolean isBackCancelable, HelpSendInfoBean takeInfoBean) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogProcessClose = centerDialogView.findViewById(R.id.dialog_procress_close);
        RecyclerView dialogProcressRecyclerview = centerDialogView.findViewById(R.id.adapter_dialog_procress_recyclerview);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        dialogProcressRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        List list = new ArrayList<String>();
        List listTime = new ArrayList<String>();
        if (takeInfoBean.getDetail().getProgress().equals("1")) {
            list.add("订单提交成功");
            list.add("待支付");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("2")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("待接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("3")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("4")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("5")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");
            list.add("待评价");


            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("6")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("7")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");
            list.add("已退款");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("8")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getPakeup_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("9")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");
            list.add("已送达");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
        }

        DialogProcressRecyclerviewAdapter dialogProcressRecyclerviewAdapter = new DialogProcressRecyclerviewAdapter(context, takeInfoBean.getDetail().getProgress(), list, listTime);
        dialogProcressRecyclerview.setAdapter(dialogProcressRecyclerviewAdapter);

        //设置关闭按键监听
        dialogProcessClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });
    }

    //进程显示框
    public static void processDialog(final Context context, View view, final boolean isCancelable, boolean isBackCancelable, HelpBuyInfoBean takeInfoBean) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogProcessClose = centerDialogView.findViewById(R.id.dialog_procress_close);
        RecyclerView dialogProcressRecyclerview = centerDialogView.findViewById(R.id.adapter_dialog_procress_recyclerview);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        dialogProcressRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        List list = new ArrayList<String>();
        List listTime = new ArrayList<String>();
        if (takeInfoBean.getDetail().getProgress().equals("1")) {
            list.add("订单提交成功");
            list.add("待支付");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("2")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("待接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("3")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("4")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("5")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");
            list.add("待评价");


            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("6")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("7")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");
            list.add("已退款");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("8")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getPakeup_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("9")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");
            list.add("已送达");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
        }

        DialogProcressRecyclerviewAdapter dialogProcressRecyclerviewAdapter = new DialogProcressRecyclerviewAdapter(context, takeInfoBean.getDetail().getProgress(), list, listTime);
        dialogProcressRecyclerview.setAdapter(dialogProcressRecyclerviewAdapter);

        //设置关闭按键监听
        dialogProcessClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });
    }

    //进程显示框
    public static void processDialog(final Context context, View view, final boolean isCancelable, boolean isBackCancelable, HelpDeliverInfoBean takeInfoBean) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogProcessClose = centerDialogView.findViewById(R.id.dialog_procress_close);
        RecyclerView dialogProcressRecyclerview = centerDialogView.findViewById(R.id.adapter_dialog_procress_recyclerview);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        dialogProcressRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        List list = new ArrayList<String>();
        List listTime = new ArrayList<String>();
        if (takeInfoBean.getDetail().getProgress().equals("1")) {
            list.add("订单提交成功");
            list.add("待支付");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("2")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("待接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("3")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("4")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("5")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");
            list.add("待评价");


            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("6")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("7")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");
            list.add("已退款");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("8")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getPakeup_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("9")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");
            list.add("已送达");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
        }

        DialogProcressRecyclerviewAdapter dialogProcressRecyclerviewAdapter = new DialogProcressRecyclerviewAdapter(context, takeInfoBean.getDetail().getProgress(), list, listTime);
        dialogProcressRecyclerview.setAdapter(dialogProcressRecyclerviewAdapter);

        //设置关闭按键监听
        dialogProcessClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });
    }

    //进程显示框
    public static void processDialog(final Context context, View view, final boolean isCancelable, boolean isBackCancelable, HelpUniversalInfoBean takeInfoBean) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogProcessClose = centerDialogView.findViewById(R.id.dialog_procress_close);
        RecyclerView dialogProcressRecyclerview = centerDialogView.findViewById(R.id.adapter_dialog_procress_recyclerview);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        dialogProcressRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        List list = new ArrayList<String>();
        List listTime = new ArrayList<String>();
        if (takeInfoBean.getDetail().getProgress().equals("1")) {
            list.add("订单提交成功");
            list.add("待支付");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("2")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("待接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("3")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("4")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("5")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("已送达");
            list.add("已完成");
            list.add("待评价");


            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
            listTime.add(takeInfoBean.getDetail().getFinish_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("6")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("7")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("订单已取消");
            list.add("已退款");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getCancel_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("8")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getPakeup_time());
        } else if (takeInfoBean.getDetail().getProgress().equals("9")) {
            list.add("订单提交成功");
            list.add("支付成功");
            list.add("已接单");
            list.add("送货中");
            list.add("已送达");

            listTime.add(takeInfoBean.getDetail().getCreate_time());
            listTime.add(takeInfoBean.getDetail().getPay_time());
            listTime.add(takeInfoBean.getDetail().getTaking_time());
            listTime.add(takeInfoBean.getDetail().getService_time());
        }

        DialogProcressRecyclerviewAdapter dialogProcressRecyclerviewAdapter = new DialogProcressRecyclerviewAdapter(context, takeInfoBean.getDetail().getProgress(), list, listTime);
        dialogProcressRecyclerview.setAdapter(dialogProcressRecyclerviewAdapter);

        //设置关闭按键监听
        dialogProcessClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });
    }


    //配送时间框
    public static void timeDialog(final Context context, final TextView textView, final String type) {
        item1 = "";
        item2 = "";
        item3 = "";
        getOptionData();
        //设置三级联动
        build = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {//监听滚轮变化
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
                item1 = options1Items.get(options1).getPickerViewText();
                item2 = options2Items.get(options1).get(options2);
                item3 = options3Items.get(options1).get(options2).get(options3);
            }
        }).setLayoutRes(R.layout.dialog_time, new CustomListener() {
            @Override
            public void customLayout(View v) {
                TextView dialogDissmiss = v.findViewById(R.id.dialog_dissmiss);
                TextView dialogEnter = v.findViewById(R.id.dialog_enter);
                //关闭界面
                dialogDissmiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        build.dismiss();
                    }
                });
                dialogEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(item1)) {
                            //设置默认时间
                            setDefaulDate(textView);
                        } else {
                            if (item1.equals("今天")) {
                                day = DateUtils.getData();
                                hour = item2.substring(0, item2.indexOf("点"));
                                minute = item3.substring(0, item3.indexOf("分"));
                            } else {
                                day = DateUtils.getTomoData();
                                hour = item2.substring(0, item2.indexOf("点"));
                                minute = item3.substring(0, item3.indexOf("分"));
                            }
                            textView.setText(day +" "+ hour +":"+ minute);
                        }

                        build.dismiss();
                    }
                });
            }
        }).build();
        build.setPicker(options1Items, options2Items, options3Items);
        build.show();
     }

    private static void setDefaulDate(TextView textView) {
        day = DateUtils.getData();
        String m = DateUtils.getMinute();
        if (Integer.parseInt(m) > 50) {
            minute = m;
        } else if (Integer.parseInt(m) > 40) {
            minute = "50";
        } else if (Integer.parseInt(m) > 30) {
            minute = "40";
        } else if (Integer.parseInt(m) > 20) {
            minute = "30";
        } else if (Integer.parseInt(m) > 10) {
            minute = "20";
        } else {
            minute = "10";
        }
        String h = DateUtils.getHour();
        for (int i = 1; i < 25; i++) {
            if (i >= Integer.parseInt(h)) {
                hour = i + "";
                break;
            }
        }
        textView.setText(day +" "+ hour + ":" + minute );
    }

    private static ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    //设置事件数据
    private static void getOptionData() {
        options1Items.clear();
        options2Items.clear();
        options3Items.clear();
        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        //选项1
        options1Items.add(new ProvinceBean(0, "今天", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "明天", "描述部分", "其他数据"));
        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        ArrayList<String> options2Items_01_02 = new ArrayList<String>();
        ArrayList<String> options2Items_01_01 = new ArrayList<String>();
        String hour = DateUtils.getHour();
        String minute = DateUtils.getMinute();
        for (int i = 1; i < 25; i++) {
            if (i >= Integer.parseInt(hour)) {
                options2Items_01.add(i + "点");
            }
        }

        ArrayList<String> options2Items_02 = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            options2Items_02.add(i + "点");
        }


        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);

        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();


        ArrayList<String> options3Items_01_02 = new ArrayList<String>();
        options3Items_01_02.add("00分");
        options3Items_01_02.add("10分");
        options3Items_01_02.add("20分");
        options3Items_01_02.add("30分");
        options3Items_01_02.add("40分");
        options3Items_01_02.add("50分");

        ArrayList<String> options3Items_01_03 = new ArrayList<String>();
        options3Items_01_03.add("00分");

        for (int i = 0; i < 24; i++) {
            if (i == 23) {
                options3Items_02.add(options3Items_01_03);
            } else {
                options3Items_02.add(options3Items_01_02);
            }
        }

        List<Integer> list = new ArrayList();
        list.add(0);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        if (Integer.parseInt(minute) > list.get(5)) {
            options2Items_01_02.add(minute + "分");
        } else if (Integer.parseInt(minute) > list.get(4)) {
            options2Items_01_02.add("50分");
        } else if (Integer.parseInt(minute) > list.get(3)) {
            options2Items_01_02.add("40分");
            options2Items_01_02.add("50分");
        } else if (Integer.parseInt(minute) > list.get(2)) {
            options2Items_01_02.add("30分");
            options2Items_01_02.add("40分");
            options2Items_01_02.add("50分");
        } else if (Integer.parseInt(minute) > list.get(1)) {
            options2Items_01_02.add("20分");
            options2Items_01_02.add("30分");
            options2Items_01_02.add("40分");
            options2Items_01_02.add("50分");
        } else {
            options2Items_01_02.add("10分");
            options2Items_01_02.add("20分");
            options2Items_01_02.add("30分");
            options2Items_01_02.add("40分");
            options2Items_01_02.add("50分");
        }


        options2Items_01_01.add("00分");
        options2Items_01_01.add("10分");
        options2Items_01_01.add("20分");
        options2Items_01_01.add("30分");
        options2Items_01_01.add("40分");
        options2Items_01_01.add("50分");

        for (int i = 0; i < options2Items_01.size(); i++) {
            if (i == 0) {
                options3Items_01.add(options2Items_01_02);
            } else {
                options3Items_01.add(options2Items_01_01);
            }
        }

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
    }

    //凭证框
    public static CenterDialogView checkDialog(final Activity activity, final Context context, View view, final boolean isCancelable, boolean isBackCancelable, final String picture) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogCheckClose = centerDialogView.findViewById(R.id.dialog_check_close);
        final TextView dialogCheckImage = centerDialogView.findViewById(R.id.dialog_check_image);
        TextView dialogCheckMoney = centerDialogView.findViewById(R.id.dialog_check_money);
        TextView dialogCheckRightBt = centerDialogView.findViewById(R.id.dialog_check_right_bt);

        //设置关闭按键监听
        dialogCheckClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });

        //点击查看大图
        dialogCheckImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BigImageActivity.class);
                intent.putExtra("pic", picture);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, dialogCheckImage, "image").toBundle();
                context.startActivity(intent, bundle);
            }
        });
        return centerDialogView;
    }

    //付款方式
    public static int payWayDialog(Context context, View view, boolean isCancelable, boolean isBackCancelable, final TextView textView) {
        final ButtomDialogView buttomDialogView = new ButtomDialogView(context, view, isCancelable, isBackCancelable);
        buttomDialogView.show();

        pickerScrollView2 = buttomDialogView.findViewById(R.id.dialog_pickerscrollview);
        TextView dialogDissmiss = buttomDialogView.findViewById(R.id.dialog_dissmiss);
        TextView dialogEnter = buttomDialogView.findViewById(R.id.dialog_enter);
        //設置item數據
        List list = new ArrayList<Pickers>();
        String[] id = new String[]{"2", "1"};
        String[] name = new String[]{"寄付现结", "快递到付"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerScrollView2.setData(list);
        pickerScrollView2.setSelected(paywayId);

        // 滚动选择器选中事件
        pickerScrollView2.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                paywayId = Integer.parseInt(pickers.getShowId());
                payway = pickers.getShowConetnt();
            }
        });
        dialogDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //關閉dialog
                buttomDialogView.dismiss();
            }
        });
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //確定并關閉
                if (TextUtils.isEmpty(payway)) {
                    payway = "寄付现结";
                } else {
                    textView.setText(payway);
                }
                buttomDialogView.dismiss();
            }
        });
        return paywayId;
    }


    //物品类型框
    public static String[] showCategoryDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable,
                                              final TextView textView, ItemsCategoryBean bean, final int basekg,
                                              final double kg_price, final double money, final double kmPrice, final TextView helptakeMoney, final int num,
                                              final TextView helpsendPassWeight, final TextView helpsendPassWeightTv) {
        final String[] strings = new String[3];
        category = "";
        gtydId = "";
        final ButtomDialogView buttomDialogView = new ButtomDialogView(context, view, isCancelable, isBackCancelable);
        buttomDialogView.show();
        IndicatorSeekBar dialogSeekbar = buttomDialogView.findViewById(R.id.dialog_seekbar);
        dialogSeekbarProgress = buttomDialogView.findViewById(R.id.dialog_seekbar_progress);
        TextView dialogDissmiss = buttomDialogView.findViewById(R.id.dialog_dissmiss);
        TextView dialogEnter = buttomDialogView.findViewById(R.id.dialog_enter);
        RecyclerView dialogItemsRecyclerview = buttomDialogView.findViewById(R.id.dialog_items_recyclerview);

        //设置Recyclerview 列表
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        //设置布局管理器
        dialogItemsRecyclerview.setLayoutManager(layoutManager);
        //设置Adapter
        final List<ItemsCategoryBean.TypeBean> categoryBeans = new ArrayList<>();
        for (int i = 0; i < bean.getType().size(); i++) {
            categoryBeans.add(bean.getType().get(i));
        }
        final ItemsRecyclerviewAdapter itemsRecyclerViewAdapter = new ItemsRecyclerviewAdapter(R.layout.adapter_items_category_layout, categoryBeans);
        dialogItemsRecyclerview.setAdapter(itemsRecyclerViewAdapter);

        progress = 1;
        //设置seekbar 监听器
        dialogSeekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                if (seekParams.progress != 1) {
                    dialogSeekbarProgress.setText(seekParams.progress + "kg");
                } else {
                    dialogSeekbarProgress.setText("小于1kg");
                }
                progress = seekParams.progress;
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
            }
        });

        dialogDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //關閉dialog
                buttomDialogView.dismiss();
            }
        });
        dialogEnter.setOnClickListener(new View.OnClickListener() {

            private int kgPrice;

            @Override
            public void onClick(View v) {
                category = itemsRecyclerViewAdapter.getCategoryName();
                gtydId = itemsRecyclerViewAdapter.getGtypeId();
                if (!TextUtils.isEmpty(category)) {
                    textView.setText(category + progress + "kg");
                    strings[0] = gtydId;//设置物品类型
                    strings[1] = progress + "";//设置重量
                } else {
                    Toast.makeText(context, "请设置物品类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                //超重  计价
                if (progress > basekg) {
                    kgPrice = (int) ((progress - basekg) * kg_price);
                } else {
                    kgPrice = 0;
                }
                helpsendPassWeight.setText(kgPrice + "元");//超重
                helpsendPassWeightTv.setText("(" + kgPrice + "kg)");
                helptakeMoney.setText((kgPrice + money + kmPrice - num) + "");//显示设置物品类型和重量之后的价格变化
                helptakeMoney.setTextColor(context.getResources().getColor(R.color.black));
                strings[2] = kgPrice + "";//设置付款
                buttomDialogView.dismiss();
            }
        });
        return strings;
    }


    //拨打电话
    public static void callDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogCall = dialogView.findViewById(R.id.dialog_call);
        dialogCall.setOnClickListener(new View.OnClickListener() {//拨打
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "400-179-0720");
                intent.setData(data);
                context.startActivity(intent);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }

    //取消订单

    public static void cancellDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final AllOrderPresenter mMvpPresenter, final MultipleStatusView multipleStatusView, final String userId, final String task_id) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.cancelOrder(task_id, userId, multipleStatusView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }

    //取消订单
    public static void cancellDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final WaitListPresenter mMvpPresenter, final MultipleStatusView multipleStatusView, final String userId, final String task_id) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.cancelOrder(task_id, userId, multipleStatusView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }

    //取消订单
    public static void cancellDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final ToPayPresenter mMvpPresenter, final MultipleStatusView multipleStatusView, final String userId, final String task_id) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.cancelOrder(task_id, userId, multipleStatusView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }


    //删除订单
    public static void deleteDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final AllOrderPresenter mMvpPresenter, final MultipleStatusView mMultipleStatusView, final String task_id) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.deleteOrder(task_id, mMultipleStatusView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }

    //删除订单
    public static void deleteDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final CompleteOrderPresenter mMvpPresenter, final MultipleStatusView mMultipleStatusView, final String task_id) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.deleteOrder(task_id, mMultipleStatusView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }

    //删除订单
    public static void deleteDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final CancelledOrderPresenter mMvpPresenter, final MultipleStatusView mMultipleStatusView, final String task_id) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.deleteOrder(task_id, mMultipleStatusView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }


    //确认领取优惠券
    public static void discountDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final DiscountPresenter mMvpPresenter, final MultipleStatusView mMultipleStateView, final String good_id, final String spUserID) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.convertGoods(good_id, spUserID, mMultipleStateView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }

    //确认领取vip卡
    public static void discountDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final ExperienceCardPresenter mMvpPresenter, final MultipleStatusView mMultipleStateView, final String good_id, final String spUserID) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogEnter = dialogView.findViewById(R.id.dialog_enter);
        dialogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.convertGoods(good_id, spUserID, mMultipleStateView);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss = dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }
}
