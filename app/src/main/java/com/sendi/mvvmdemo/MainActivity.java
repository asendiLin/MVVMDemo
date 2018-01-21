package com.sendi.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sendi.mvvmdemo.adapter.MyAdapter;
import com.sendi.mvvmdemo.databinding.ActivityMainBinding;
import com.sendi.mvvmdemo.viewmodel.IViewModel;
import com.sendi.mvvmdemo.viewmodel.MainViewModelImp;

public class MainActivity extends AppCompatActivity implements IMainView{

   private ActivityMainBinding mBinding;
   private IViewModel mViewModel;
   private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mBinding=DataBindingUtil.setContentView(this,R.layout.activity_main);
       mBinding.setMainActivity(MainActivity.this);

        initView();
        mViewModel=new MainViewModelImp(mBinding,mAdapter);

        initData();
    }

    public void onChangeAge(View view){
        mViewModel.changeAge();
    }

    private void initData() {
        mViewModel.initUser();
        mViewModel.loadUsersData();
    }

    private void initView() {
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL ,false));
        mAdapter=new MyAdapter();
        mBinding.recyclerView.setAdapter(mAdapter);
    }

}
