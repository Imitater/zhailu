package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.UserInfoBean;
import com.mouqukeji.hmdeer.bean.UserInfoUpBean;
import com.mouqukeji.hmdeer.contract.activity.MyInformationContract;
import com.mouqukeji.hmdeer.modle.activity.MyInformationModel;
import com.mouqukeji.hmdeer.presenter.activity.MyInformationPresenter;
import com.mouqukeji.hmdeer.util.DateUtils;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.TokenHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInformationActivity extends BaseActivity<MyInformationPresenter, MyInformationModel> implements MyInformationContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.info_head)
    CircleImageView infoHead;
    @BindView(R.id.info_name)
    EditText infoName;
    @BindView(R.id.info_sex)
    TextView infoSex;
    @BindView(R.id.info_sex_item)
    LinearLayout infoSexItem;
    @BindView(R.id.info_age)
    TextView infoAge;
    @BindView(R.id.info_age_item)
    LinearLayout infoAgeItem;
    @BindView(R.id.info_school)
    TextView infoSchool;
    @BindView(R.id.info_school_item)
    LinearLayout infoSchoolItem;
    List<LocalMedia> list = new ArrayList<>();
    @BindView(R.id.L8_progressbar2)
    ProgressBar L8Progressbar2;
    @BindView(R.id.info_progress)
    LinearLayout infoProgress;
    private String url;
    private String sex ;
    private String spUserID;
    private String address;
    private String age;
    private String name;
    private boolean flag = false;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getUserInfo(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_myinformation;
    }

    @Override
    protected void setUpView() {
        actionSave.setVisibility(View.VISIBLE);
        actionSave.setText("保存");
        actionTitle.setText("我的资料");
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        infoSexItem.setOnClickListener(this);
        infoSchoolItem.setOnClickListener(this);
        infoHead.setOnClickListener(this);
        actionSave.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                finish();
                break;
            case R.id.info_sex_item://性别选择
                View inflate_info_sex = getLayoutInflater().inflate(R.layout.dialog_info_sex, null);
                sex = DialogUtils.infoSexDialog(MyInformationActivity.this, inflate_info_sex, true, true, infoSex);
                break;
            case R.id.info_school_item:
                //进入地址选择页面
                startActivityForResult(new Intent(this, SelectAddressActivity.class), 99);
                break;
            case R.id.info_head:
                setHead();//设置头像
                break;
            case R.id.action_save:
                //保存修改
                if (TextUtils.isEmpty(infoName.getText().toString())) {
                    Toast.makeText(MyInformationActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(infoAge.getText().toString())) {
                    Toast.makeText(MyInformationActivity.this, "年龄不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(infoSchool.getText().toString())) {
                    Toast.makeText(MyInformationActivity.this, "请选择学校地址", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(url)) {
                    Toast.makeText(MyInformationActivity.this, "请选择上传图片", Toast.LENGTH_SHORT).show();
                } else {
                    if (flag) {
                        mMvpPresenter.putUserInfo(spUserID, infoName.getText().toString(), url, sex, infoAge.getText().toString(), address, mMultipleStateView);
                    } else {
                        Toast.makeText(MyInformationActivity.this, "图片加载中，请稍等", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }


    private void setHead() {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(MyInformationActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .cropCompressQuality(50)
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
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
                    uploadImgSignQiNiu(list.get(0).getCompressPath());
                    infoProgress.setVisibility(View.VISIBLE);
                    break;
                case 99:
                    if (!TextUtils.isEmpty(data.getStringExtra("select_address"))) {
                        //获取地址
                        address = data.getStringExtra("select_address");
                        infoSchool.setText(address);
                        //经度
                        String buyAddressLat = data.getStringExtra("select_point_lat");
                        //纬度
                        String buyAddressLng = data.getStringExtra("select_point_lon");
                    }
                    break;

            }

        }
    }

    public void uploadImgSignQiNiu(final String path) {
        flag = false;
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        String key = "icon_" + num + DateUtils.getData();
        TokenHelper tokenHelper = TokenHelper.create("Nwz4XdKR-G777FoMf-DrjaySeCWvjiwv7gd4sIm1", "aZkyjMBELmPthFf-60rwJQKR0eXYazHydDG8uF4H");
        String token = tokenHelper.getToken("mouqukeji");
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(path, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                //res包含hash、key等信息，具体字段取决于上传策略的设置
                if (info.isOK()) {
                    flag = true;
                    url = "http://picture.mouqukeji.com/" + key;
                    Glide.with(MyInformationActivity.this).load(list.get(0).getCompressPath()).into(infoHead);
                    infoProgress.setVisibility(View.GONE);
                } else {
                    Log.i("picture", "Upload Fail");
                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("picture", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);
    }

    @Override
    public void getUserInfo(UserInfoBean bean) {
        Glide.with(this).load(bean.getAvatar()).into(infoHead);//设置头像
        name = bean.getNickname();
        sex = bean.getGender();
        age = bean.getAge();
        url = bean.getAvatar();
        address = bean.getSchool_name();
        infoName.setText(bean.getNickname());
        if (bean.getGender().equals("1")) {
            infoSex.setText("男");
        } else if (bean.getGender().equals("2")) {
            infoSex.setText("女");
        } else {
            infoSex.setText("保密");
        }
        sex = bean.getGender();
        infoAge.setText(bean.getAge());
        infoSchool.setText(bean.getSchool_name());
    }

    @Override
    public void putUserInfo(UserInfoUpBean bean) {
        PictureFileUtils.deleteCacheDirFile(MyInformationActivity.this);
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }


}
