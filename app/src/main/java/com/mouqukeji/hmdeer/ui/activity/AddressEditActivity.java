package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.modle.activity.AddressEditModel;
import com.mouqukeji.hmdeer.presenter.activity.AddressEditPresenter;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;

public class AddressEditActivity extends BaseActivity<AddressEditPresenter, AddressEditModel> implements AddressEditContract.View, View.OnClickListener{
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.address_edit_name)
    EditText addressEditName;
    @BindView(R.id.address_edit_number)
    EditText addressEditNumber;
    @BindView(R.id.address_tv_address)
    TextView addressTvAddress;
    @BindView(R.id.address_edit_address_info)
    EditText addressEditAddressInfo;
    private String select_point_lat;
    private String select_point_lon;
    private String defaul;
     private String name;
    private String number;
    private String location;
    private String locationInfo;
    private String endId;
    private String userid;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_address_edit;
    }

    @Override
    protected void setUpView() {
        View actionBack = findViewById(R.id.action_back);
        Intent intent = getIntent();
        userid = intent.getStringExtra("userId");//获取传递参数 user_id
        endId = intent.getStringExtra("editId");//获取传递参数 id
        name = intent.getStringExtra("editName");//获取传递参数 name
        number = intent.getStringExtra("editNumber");//获取传递参数 number
        location = intent.getStringExtra("editLocation");//获取传递参数 address
        locationInfo = intent.getStringExtra("editLocationInfo");//获取传递参数 detial
        defaul = intent.getStringExtra("editisDefaul");//获取传递参数 isdefaul
        addressEditName.setText(name);
        addressEditNumber.setText(number);
        addressTvAddress.setText(location);
        addressEditAddressInfo.setText(locationInfo);
        //设置按钮事件监听
        actionBack.setOnClickListener(this);
        addressTvAddress.setOnClickListener(this);
        actionSave.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
               setBack();
                break;
            case R.id.address_tv_address:
                 startActivityForResult(new Intent(AddressEditActivity.this, SelectAddressActivity.class), 5);
                break;
            case R.id.action_save:
                //修改地址接口
                String spUserID = new GetSPData().getSPUserID(this);
                if (TextUtils.isEmpty(addressEditName.getText().toString())){
                    Toast.makeText(AddressEditActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(addressEditNumber.getText().toString())){
                    Toast.makeText(AddressEditActivity.this,"电话不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(location)){
                    Toast.makeText(AddressEditActivity.this,"地址不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(addressEditAddressInfo.getText().toString())){
                    Toast.makeText(AddressEditActivity.this,"详细地址不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    mMvpPresenter.editAddress(spUserID, userid, addressEditName.getText().toString(), addressEditNumber.getText().toString(),
                            location, addressEditAddressInfo.getText().toString(),
                            defaul, select_point_lat, select_point_lon, mMultipleStateView);
                }
                setBack();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!TextUtils.isEmpty(data.getStringExtra("select_address"))) {
            //获取地址
            location = data.getStringExtra("select_address");
            addressTvAddress.setText(location);
            //经度
            select_point_lat = data.getStringExtra("select_point_lat");
            //纬度
            select_point_lon = data.getStringExtra("select_point_lon");
        }
     }

    @Override
    public void editAddress(EditAddressBean bean) {
        Toast.makeText(this,"修改地址成功",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        setBack();
        super.onBackPressed();
    }

    private void setBack() {
        Intent intent = new Intent();
        intent.putExtra("afterId", userid);
        intent.putExtra("afterName", addressEditName.getText().toString());
        intent.putExtra("afterNumber", addressEditNumber.getText().toString());
        intent.putExtra("afterLocation", location);
        intent.putExtra("afterLocationInfo", addressEditAddressInfo.getText().toString());
        intent.putExtra("afterIsDefaul", defaul);
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }
}
