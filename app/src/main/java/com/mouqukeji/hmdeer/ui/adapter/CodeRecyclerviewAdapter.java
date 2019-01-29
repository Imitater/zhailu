package com.mouqukeji.hmdeer.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;

import java.util.ArrayList;
import java.util.List;

public class CodeRecyclerviewAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {


    private final List<Object> data;
    private List<String> list ;
    public CodeRecyclerviewAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
        this.data = data;
        list = new ArrayList();
        list.add("");
        if (data.size() == 0) {
            addData(0, "");
            notifyDataSetChanged();
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Object item) {
        if (helper.getPosition() == 0) {
            //显示添加按钮
            helper.setVisible(R.id.code_add, true);
            //隐藏删除按钮
            helper.setVisible(R.id.code_close, false);
        } else {
            //隐藏添加按钮
            helper.setVisible(R.id.code_add, false);
            //显示删除按钮
            helper.setVisible(R.id.code_close, true);
        }
        //添加取件码
        helper.setOnClickListener(R.id.code_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(helper.getPosition() + 1);
            }
        });
        //删除取件码
        helper.setOnClickListener(R.id.code_close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(helper.getPosition());
            }
        });
        EditText editText = helper.getView(R.id.code_et);
        if( editText.getTag() instanceof TextWatcher){
            editText.removeTextChangedListener(((TextWatcher)editText.getTag()));
        }
        editText.setText( list.get(helper.getLayoutPosition()) );

        TextWatcher brandWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if( list.get(helper.getLayoutPosition()) != null){
                    list.remove(helper.getLayoutPosition());
                }
                list.add(helper.getLayoutPosition(), s.toString() );
            }
        };

        editText.addTextChangedListener( brandWatcher );
        editText.setTag( brandWatcher );

    }

    public List<String> getList() {
        return list;
    }

    //  添加数据
    public void addData(int position) {
//      在list中添加数据，并通知条目加入一条
        data.add(position);
        list.add(position,"");
        //添加动画
        notifyItemInserted(position);
        notifyDataSetChanged();
        notifyItemRemoved( position+1 );
    }

    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
        notifyItemRemoved( position );
    }


}
