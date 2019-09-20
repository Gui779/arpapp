package com.example.erpqpp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.AlreadyDetailsAdapter;
import com.lbb.mvplibrary.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 已  收货详情
 */
public class AlreadyDetailsFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.kehu_name)
    TextView kehuName;
    @BindView(R.id.order_id)
    TextView orderId;
    Unbinder unbinder;
    @BindView(R.id.alreadyDetails_rc)
    RecyclerView alreadyDetailsRc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_alreadydetails, container, false);
        Bundle arguments = getArguments();
        String type = arguments.getString("type");
        unbinder = ButterKnife.bind(this, inflate);

        initdata();
        return inflate;
    }

    private void initdata() {

        AlreadyDetailsAdapter alreadyDetailsAdapter = new AlreadyDetailsAdapter(getContext());
        alreadyDetailsRc.setAdapter(alreadyDetailsAdapter);
        alreadyDetailsRc.setLayoutManager(new LinearLayoutManager(getActivity()));
        alreadyDetailsRc.setNestedScrollingEnabled(false);//禁止滑动
        ivSearchDelete.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_delete:
                etSearchContent.setText("");
                break;
        }
    }
}
