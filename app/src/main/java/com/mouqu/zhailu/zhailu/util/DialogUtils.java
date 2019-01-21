package com.mouqu.zhailu.zhailu.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.bean.ItemsCategoryBean;
import com.mouqu.zhailu.zhailu.bean.ProvinceBean;
import com.mouqu.zhailu.zhailu.ui.activity.BigImageActivity;
import com.mouqu.zhailu.zhailu.ui.adapter.CodeRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.adapter.DialogProcressRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.adapter.ItemsRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.widget.ButtomDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.CenterDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.PickerScrollView;
import com.mouqu.zhailu.zhailu.ui.widget.Pickers;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.ArrayList;
import java.util.List;

public class DialogUtils {
    //设置 flag 判断数据是否已经填充
    private static boolean flag=false;
    private static PickerScrollView pickerScrollView;
    private static PickerScrollView pickerScrollView1;
    private static PickerScrollView pickerScrollView2;
    private static String sex;
    private static String address;
    private static String send;
    private static int addressId = 0;
    private static int timeId;
    private static int sexId;
    private static TextView dialogSeekbarProgress;
    private static String category="其他";
    private static int progress;
    private static OptionsPickerView build;
    private static String payway;
    private static int paywayId;
    private static String gtydId;


    //快递点框
    public static String  showButtomAddressDialog(Context context, View view, boolean isCancelable, boolean isBackCancelable, final TextView helpTakeAddressTv) {

        final ButtomDialogView buttomDialogView = new ButtomDialogView(context, view, isCancelable, isBackCancelable);
        buttomDialogView.show();


        pickerScrollView = buttomDialogView.findViewById(R.id.dialog_pickerscrollview);
        TextView dialogDissmiss = buttomDialogView.findViewById(R.id.dialog_dissmiss);
        TextView dialogEnter = buttomDialogView.findViewById(R.id.dialog_enter);

        //設置item數據
        List list = new ArrayList<Pickers>();
        String[] id = new String[]{"0", "1", "2", "3", "4", "5"};
        String[] name = new String[]{"圆通快递", "中通快递", "申通快递", "汇通快递", "韵达快递", "顺丰快递"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerScrollView.setData(list);
        pickerScrollView.setSelected(addressId);


        //设置item滚动监听
        pickerScrollView.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(Pickers pickers) {
                addressId = Integer.parseInt(pickers.getShowId());
                address = pickers.getShowConetnt();
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
                if (TextUtils.isEmpty(address)) {
                    address = "圆通快递";
                } else {
                    helpTakeAddressTv.setText(address);
                }
                buttomDialogView.dismiss();
            }
        });
        return address;
    }

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

    //物品类型框
    public static String[] showButtomItemsDialog(Context context, View view, boolean isCancelable, boolean isBackCancelable, final TextView textView, ItemsCategoryBean bean) {
        final ButtomDialogView buttomDialogView = new ButtomDialogView(context, view, isCancelable, isBackCancelable);
        buttomDialogView.show();
        final String[] strings = new String[3];
        IndicatorSeekBar dialogSeekbar = buttomDialogView.findViewById(R.id.dialog_seekbar);
        dialogSeekbarProgress = buttomDialogView.findViewById(R.id.dialog_seekbar_progress);
        TextView dialogDissmiss = buttomDialogView.findViewById(R.id.dialog_dissmiss);
        TextView dialogEnter = buttomDialogView.findViewById(R.id.dialog_enter);
        RecyclerView dialogItemsRecyclerview = buttomDialogView.findViewById(R.id.dialog_items_recyclerview);

        //设置Recyclerview 列表
        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        //设置布局管理器
        dialogItemsRecyclerview.setLayoutManager(layoutManager);
          //设置Adapter
        final List<ItemsCategoryBean.TypeBean> categoryBeans = new ArrayList<>();
        for (int i = 0; i <bean.getType().size() ; i++) {
            categoryBeans.add(bean.getType().get(i));
        }
        final ItemsRecyclerviewAdapter itemsRecyclerViewAdapter = new ItemsRecyclerviewAdapter(R.layout.adapter_items_category_layout,categoryBeans );
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
            @Override
            public void onClick(View v) {
                category=itemsRecyclerViewAdapter.getCategoryName();
                gtydId=itemsRecyclerViewAdapter.getGtypeId();
                if (!TextUtils.isEmpty(category))
                    textView.setText(category + progress + "kg");
                    strings[0]=gtydId;
                    strings[1]=progress+"";
                 buttomDialogView.dismiss();
            }
        });
        return strings;
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


    //进程显示框
    public static void processDialog(final Context context, View view, final boolean isCancelable, boolean isBackCancelable) {
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

        DialogProcressRecyclerviewAdapter dialogProcressRecyclerviewAdapter = new DialogProcressRecyclerviewAdapter(context);
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


    private static String time;
    private static String item1;
    //配送时间框
    public static void timeDialog(final Context context, final TextView textView, final String type) {
        time="";
        if (!flag) {
            getOptionData(type);
        }

        //设置三级联动
        build = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textView.setText(type);
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {//监听滚轮变化
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
                item1 = options1Items.get(options1).getPickerViewText();
                String item2=options2Items.get(options1).get(options2);
                String item3=options3Items.get(options1).get(options2).get(options3);
                time= item1 +item2+item3;
            }
        }).setLayoutRes(R.layout.dialog_time, new CustomListener() {
            @Override
            public void customLayout(View v) {
                TextView dialogDissmiss=v.findViewById(R.id.dialog_dissmiss);
                TextView dialogEnter=v.findViewById(R.id.dialog_enter);
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
                        if ("".equals(time)){
                            textView.setText(type);
                        }else {
                            if ("今天".equals(item1)) {
                                textView.setText(type);
                            } else {
                                textView.setText(time);
                            }
                        }
                        build.dismiss();
                    }
                });
            }
        }).build();
        build.setPicker(options1Items, options2Items,options3Items);
        build.show();


    }

    private static ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    //设置事件数据
    private static void getOptionData(String type) {
        //设置 flag;
        flag=true;
        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        //选项1
        options1Items.add(new ProvinceBean(0, "今天", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "明天", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add(type);
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("1点");
        options2Items_02.add("2点");
        options2Items_02.add("3点");
        options2Items_02.add("4点");
        options2Items_02.add("5点");
        options2Items_02.add("6点");
        options2Items_02.add("7点");
        options2Items_02.add("8点");
        options2Items_02.add("9点");
        options2Items_02.add("10点");
        options2Items_02.add("11点");
        options2Items_02.add("12点");
        options2Items_02.add("13点");
        options2Items_02.add("14点");
        options2Items_02.add("15点");
        options2Items_02.add("16点");
        options2Items_02.add("17点");
        options2Items_02.add("18点");
        options2Items_02.add("19点");
        options2Items_02.add("20点");
        options2Items_02.add("21点");
        options2Items_02.add("22点");
        options2Items_02.add("23点");
        options2Items_02.add("24点");

        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);

        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
        ArrayList<String> options3Items_01_01=new ArrayList<String>();
        options3Items_01_01.add(" ");
        options3Items_01.add(options3Items_01_01);

        ArrayList<String> options3Items_01_02=new ArrayList<String>();
        options3Items_01_02.add("00分");
        options3Items_01_02.add("10分");
        options3Items_01_02.add("20分");
        options3Items_01_02.add("30分");
        options3Items_01_02.add("40分");
        options3Items_01_02.add("50分");

        for (int i = 0; i < 24; i++) {
            options3Items_02.add(options3Items_01_02);
        }

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);

    }

    //凭证框
    public static CenterDialogView checkDialog(final Activity activity, final Context context, View view, final boolean isCancelable, boolean isBackCancelable) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogCheckClose = centerDialogView.findViewById(R.id.dialog_check_close);
        final TextView dialogCheckImage=centerDialogView.findViewById(R.id.dialog_check_image);
        TextView dialogCheckMoney=centerDialogView.findViewById(R.id.dialog_check_money);
        TextView dialogCheckLeftBt=centerDialogView.findViewById(R.id.dialog_check_left_bt);
        TextView dialogCheckRightBt=centerDialogView.findViewById(R.id.dialog_check_right_bt);

        //设置关闭按键监听
        dialogCheckClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });
        //返回菜单按键
        dialogCheckLeftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //点击查看大图
        dialogCheckImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BigImageActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,dialogCheckImage,"image").toBundle();
                context.startActivity(intent,bundle);
            }
        });
        return centerDialogView;
    }

    //付款方式
    public static void payWayDialog(Context context, View view, boolean isCancelable, boolean isBackCancelable, final TextView textView) {
        final ButtomDialogView buttomDialogView = new ButtomDialogView(context, view, isCancelable, isBackCancelable);
        buttomDialogView.show();

        pickerScrollView2 = buttomDialogView.findViewById(R.id.dialog_pickerscrollview);
        TextView dialogDissmiss = buttomDialogView.findViewById(R.id.dialog_dissmiss);
        TextView dialogEnter = buttomDialogView.findViewById(R.id.dialog_enter);

        //設置item數據
        List list = new ArrayList<Pickers>();
        String[] id = new String[]{"0", "1"};
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
    }



}
