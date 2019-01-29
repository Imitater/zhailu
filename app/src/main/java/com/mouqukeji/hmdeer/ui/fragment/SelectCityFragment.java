package com.mouqukeji.hmdeer.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.bean.UserEntity;
import com.mouqukeji.hmdeer.contract.fragment.SelectCityContract;
import com.mouqukeji.hmdeer.modle.fragment.SelectCityModel;
import com.mouqukeji.hmdeer.presenter.fragment.SelectCityPresenter;
import com.mouqukeji.hmdeer.ui.adapter.BannerHeaderAdapter;
import com.mouqukeji.hmdeer.ui.adapter.ContactAdapter;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class SelectCityFragment extends BaseFragment<SelectCityPresenter, SelectCityModel> implements SelectCityContract.View {

    @BindView(R.id.pic_contact_back)
    ImageView picContactBack;
    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;
    Unbinder unbinder;
    private BannerHeaderAdapter mBannerHeaderAdapter;
    private ContactAdapter mAdapter;


    @Override
    protected void initViewAndEvents() {

    }
    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_select_city;
    }

    @Override
    protected void setUpView() {
        indexableLayout.setLayoutManager(new LinearLayoutManager(getMContext()));
        initAdapter();
        onlisten();
    }

    @Override
    protected void setUpData() {

    }


    public void initAdapter() {
        mAdapter = new ContactAdapter(getMContext());
        indexableLayout.setAdapter(mAdapter);
        //设置字母提示框为仿os居中
        indexableLayout.setOverlayStyle_Center();
        mAdapter.setDatas(initDatas());
        // 全字母排序。  排序规则设置为：每个字母都会进行比较排序；速度较慢
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        // 这里BannerView只有一个Item, 添加一个长度为1的任意List作为第三个参数
        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        mBannerHeaderAdapter = new BannerHeaderAdapter(getMContext(), "↑", null, bannerList);
        indexableLayout.addHeaderAdapter(mBannerHeaderAdapter);


        mBannerHeaderAdapter.setOnItemClickListener(new BannerHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, List list) {
                //item点击事件
                //发送消息
                EventMessage eventMessage = new EventMessage(EventCode.EVENT_A, list.get(position).toString());
                post(eventMessage);
                setBack();
            }
        });
    }



    //关闭城市选择
    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment city = fragmentManager.findFragmentByTag("city");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(city);
        fragmentTransaction.commit();
    }

    public void onlisten() {

        picContactBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
                setBack();
            }
        });

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<UserEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, UserEntity entity) {
                if (originalPosition >= 0) {
                    //发送消息
                    EventMessage eventMessage = new EventMessage(EventCode.EVENT_A, entity.getNick());
                    post(eventMessage);
                    setBack();
                } else {
                    Toast.makeText(getMContext(), "选中Header/Footer:" + entity.getNick() + "  当前位置:" + currentPosition, Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private List<UserEntity> initDatas() {
        List<UserEntity> list = new ArrayList<>();
        // 初始化数据，R.array.provinces是城市资源，下面有贴出资源文件代码
        List<String> contactStrings = Arrays.asList(getMContext().getResources().getStringArray(R.array.provinces));
        List<String> mobileStrings = Arrays.asList(getMContext().getResources().getStringArray(R.array.provinces));
        for (int i = 0; i < contactStrings.size(); i++) {
            UserEntity contactEntity = new UserEntity(contactStrings.get(i), mobileStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }

}
