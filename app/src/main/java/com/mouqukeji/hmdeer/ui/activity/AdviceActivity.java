package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.FeedBackBean;
import com.mouqukeji.hmdeer.contract.activity.AdviceContract;
import com.mouqukeji.hmdeer.modle.activity.AdviceModel;
import com.mouqukeji.hmdeer.presenter.activity.AdvicePresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DateUtils;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.KeyUtils;
import com.mouqukeji.hmdeer.util.TokenHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdviceActivity extends BaseActivity<AdvicePresenter, AdviceModel> implements AdviceContract.View, View.OnClickListener {
    private static final long MAX_COUNT = 300;
    @BindView(R.id.advice_actionbar)
    MyActionBar adviceActionbar;
    @BindView(R.id.dialog_bt_1)
    RadioButton dialogBt1;
    @BindView(R.id.dialog_bt_2)
    RadioButton dialogBt2;
    @BindView(R.id.dialog_bt_3)
    RadioButton dialogBt3;
    @BindView(R.id.dialog_bt_4)
    RadioButton dialogBt4;
    @BindView(R.id.advice_et)
    EditText adviceEt;
    @BindView(R.id.text_count)
    TextView textCount;
    @BindView(R.id.advice_add_image)
    LinearLayout adviceAddImage;
    @BindView(R.id.advice_bt)
    Button adviceBt;
    @BindView(R.id.advice_bt_1)
    LinearLayout adviceBt1;
    @BindView(R.id.advice_bt_2)
    LinearLayout adviceBt2;
    @BindView(R.id.advice_bt_3)
    LinearLayout adviceBt3;
    @BindView(R.id.advice_bt_4)
    LinearLayout adviceBt4;
    @BindView(R.id.advice_pic_count)
    TextView advicePicCount;
    @BindView(R.id.advice_progress)
    LinearLayout adviceProgress;
    private int type = 1;
    List<LocalMedia> list = new ArrayList<>();
    private String url = "";
    private String spUserID;
    private boolean flag=false;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_advice;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(this);
        adviceActionbar.setTitle("意见反馈");
        adviceEt.setSelection(adviceEt.length()); // 将光标移动最后一个字符后面
        setListener();

    }

    private void getPic() {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(AdviceActivity.this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(4)// 最大图片选择数量 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
				.withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMedia(list)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    list = PictureSelector.obtainMultipleResult(data);
                    advicePicCount.setText(list.size() + "");
                    adviceProgress.setVisibility(View.VISIBLE);
                    for (int i = 0; i < list.size(); i++) {
                        uploadImg2QiNiu(list.get(i).getCompressPath(),i);
                    }
                    break;
            }
        }
    }

    public void uploadImg2QiNiu(String path, final int i) {
        flag = false;
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        String key = "icon_" + num + DateUtils.getData();
        TokenHelper tokenHelper = TokenHelper.create(KeyUtils.Access_Key, KeyUtils.Secret_Key);
        String token = tokenHelper.getToken(KeyUtils.Bucket);
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(path, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                //res包含hash、key等信息，具体字段取决于上传策略的设置
                if (info.isOK()) {
                    url = url + KeyUtils.Base_Url + key + ";";
                    Log.i("picture", "Upload Success");
                    if (i==list.size()-1){
                        flag=true;
                        adviceProgress.setVisibility(View.GONE);
                    }
                 } else {
                    Log.i("picture", "Upload Fail");
                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("picture", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);
    }


    private void setListener() {
        adviceBt1.setOnClickListener(this);
        adviceBt2.setOnClickListener(this);
        adviceBt3.setOnClickListener(this);
        adviceBt4.setOnClickListener(this);
        adviceBt.setOnClickListener(this);

        dialogBt1.setOnClickListener(this);
        dialogBt2.setOnClickListener(this);
        dialogBt3.setOnClickListener(this);
        dialogBt4.setOnClickListener(this);
        adviceAddImage.setOnClickListener(this);
        adviceEt.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.advice_bt_1:
                dialogBt1.setChecked(true);
                dialogBt2.setChecked(false);
                dialogBt3.setChecked(false);
                dialogBt4.setChecked(false);

                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                type = 1;
                break;
            case R.id.advice_bt_2:
                dialogBt1.setChecked(false);
                dialogBt2.setChecked(true);
                dialogBt3.setChecked(false);
                dialogBt4.setChecked(false);


                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                type = 2;
                break;
            case R.id.advice_bt_3:
                dialogBt1.setChecked(false);
                dialogBt2.setChecked(false);
                dialogBt3.setChecked(true);
                dialogBt4.setChecked(false);


                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                type = 3;
                break;
            case R.id.advice_bt_4:
                dialogBt1.setChecked(false);
                dialogBt2.setChecked(false);
                dialogBt3.setChecked(false);
                dialogBt4.setChecked(true);

                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                type = 4;
                break;
            case R.id.dialog_bt_1:
                dialogBt1.setChecked(true);
                dialogBt2.setChecked(false);
                dialogBt3.setChecked(false);
                dialogBt4.setChecked(false);

                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                type = 1;
                break;
            case R.id.dialog_bt_2:
                dialogBt1.setChecked(false);
                dialogBt2.setChecked(true);
                dialogBt3.setChecked(false);
                dialogBt4.setChecked(false);


                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                type = 2;
                break;
            case R.id.dialog_bt_3:
                dialogBt1.setChecked(false);
                dialogBt2.setChecked(false);
                dialogBt3.setChecked(true);
                dialogBt4.setChecked(false);


                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                type = 3;
                break;
            case R.id.dialog_bt_4:
                dialogBt1.setChecked(false);
                dialogBt2.setChecked(false);
                dialogBt3.setChecked(false);
                dialogBt4.setChecked(true);

                dialogBt1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt3.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                dialogBt4.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                type = 4;
                break;
            case R.id.advice_bt:
                //意见反馈
                if (TextUtils.isEmpty(adviceEt.getText().toString())) {
                    Toast.makeText(AdviceActivity.this, "请填写建议", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(url)) {
                    Toast.makeText(AdviceActivity.this, "请提供相关问题图片", Toast.LENGTH_SHORT).show();
                } else {
                    mMvpPresenter.feedBack(url, adviceEt.getText().toString(), type + "", spUserID, mMultipleStateView);
                }
                break;
            case R.id.advice_add_image:
                //选择图片
                getPic();
                break;
        }
    }

    @Override
    public void feedBack(FeedBackBean backBean) {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    //textview 监听
    private TextWatcher mTextWatcher = new TextWatcher() {

        private int editStart;

        private int editEnd;

        public void afterTextChanged(Editable s) {
            editStart = adviceEt.getSelectionStart();
            editEnd = adviceEt.getSelectionEnd();

            // 先去掉监听器，否则会出现栈溢出
            adviceEt.removeTextChangedListener(mTextWatcher);

            // 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
            // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
            while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
                s.delete(editStart - 1, editEnd);
                editStart--;
                editEnd--;
            }
            // mEditText.setText(s);将这行代码注释掉就不会出现后面所说的输入法在数字界面自动跳转回主界面的问题了，多谢@ainiyidiandian的提醒
            adviceEt.setSelection(editStart);

            // 恢复监听器
            adviceEt.addTextChangedListener(mTextWatcher);

            setLeftCount();
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

    };


    private long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }


    private void setLeftCount() {
        textCount.setText(String.valueOf((getInputCount())) + "/" + MAX_COUNT);
    }


    private long getInputCount() {
        return calculateLength(adviceEt.getText().toString());
    }



}
