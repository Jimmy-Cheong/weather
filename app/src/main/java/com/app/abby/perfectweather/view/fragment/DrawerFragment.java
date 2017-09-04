package com.app.abby.perfectweather.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.activity.SelectCityActivity;
import com.app.abby.perfectweather.base.BaseFragment;
import com.app.abby.perfectweather.base.WeatherApplication;
import com.app.abby.perfectweather.contract.DrawerContract;
import com.app.abby.perfectweather.model.api.ApiClient;
import com.app.abby.perfectweather.model.api.WeatherBean;
import com.app.abby.perfectweather.model.database.DrawerItemORM;
import com.app.abby.perfectweather.model.database.OrmLite;
import com.app.abby.perfectweather.util.RxUtil;
import com.app.abby.perfectweather.view.adapter.DrawerItemAdapter;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Abby on 8/29/2017.
 */

public class DrawerFragment extends BaseFragment implements DrawerContract.View {

    private DrawerContract.Presenter mPrsenter;
    private Unbinder unbinder;
    private OnDrawerItemClick onDrawerItemClick;
    private Subscription subscription;
    public DrawerFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @BindView(R.id.drawer_item)
    RecyclerView mDrawerRecy;

    @BindView(R.id.add_city_btn)
    Button add_btn;

    private DrawerItemAdapter mDraweritemAdapter;
    private List<DrawerItemORM> mDraweritemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        unbinder = ButterKnife.bind(this, view);


        mDrawerRecy.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mDraweritemList = new ArrayList<>();
        mDraweritemAdapter = new DrawerItemAdapter(mDraweritemList);
        mDraweritemAdapter.setOnHolderItemClickListener(new DrawerItemAdapter.OnHolderItemClickListener() {
            @Override
            public void onHolderItemClick(int position) {
                List<DrawerItemORM> item=OrmLite.getInstance().query(new QueryBuilder<>(DrawerItemORM.class).where("mCity=?",mDraweritemList.get(position).getCity()));
                subscription=ApiClient.getInstance().fetchWeather(mDraweritemList.get(position).getCity())
                        .compose(RxUtil.rxSchedulerHelper())
                        .subscribe(new Subscriber<WeatherBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(WeatherBean weatherBean) {

                                item.get(0).setCity(weatherBean.getHeWeather5().get(0).getBasic().getCity()+"市");
                                item.get(0).setTemp(weatherBean.getHeWeather5().get(0).getNow().getTmp()+"℃");
                                item.get(0).setCode(weatherBean.getHeWeather5().get(0).getNow().getCond().getCode());
                                OrmLite.getInstance().update(item.get(0));
                            }
                        });

                onDrawerItemClick.onDrawerItemClick(mDraweritemList.get(position).getCity());
            }
        });

        mDraweritemAdapter.setOnDeleteBtnClickListener(position -> {

            new AlertDialog.Builder(getContext())
                    .setTitle("提示")
                    .setMessage("您确定将"+mDraweritemList.get(position).getCity()+"删除吗？")
                    .setPositiveButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .setNegativeButton("确定", (dialog, which) -> {

                        OrmLite.getInstance().delete(new WhereBuilder(DrawerItemORM.class).where("mCity=?",mDraweritemList.get(position).getCity()));
                        mDraweritemList.remove(position);

                        mDraweritemAdapter.notifyDataSetChanged();
                    }).show();


        });


        mDrawerRecy.setAdapter(mDraweritemAdapter);


        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnDrawerItemClick){
            onDrawerItemClick=(OnDrawerItemClick)context;
        }
    }
    @Override
    public void lazyload() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPrsenter.loadItem();
    }


    @Override
    public void setPresenter(DrawerContract.Presenter presenter) {
        mPrsenter = presenter;
    }


    @OnClick(R.id.add_city_btn)
    void onAddClick() {
        Intent intent = new Intent(getActivity(), SelectCityActivity.class);
        startActivity(intent);
    }


    @Override
    public void showItem(List<DrawerItemORM> data) {

        if(mDraweritemList!=null){
            mDraweritemList.clear();
        }

        mDraweritemList.addAll(data);
        mDraweritemAdapter.notifyDataSetChanged();
    }


    public static DrawerFragment newInstance() {
        return new DrawerFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPrsenter.onunsubscribe();
        unbinder.unbind();
        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

    }


    public interface OnDrawerItemClick{
        void onDrawerItemClick(String city);
    }

}