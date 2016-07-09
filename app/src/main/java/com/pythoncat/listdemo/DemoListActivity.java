package com.pythoncat.listdemo;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.listdemo.adapter.RvAdapter;
import com.pythoncat.listdemo.service.LoadApi;
import com.pythoncat.listdemo.utils.RxJavaUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class DemoListActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshView;
    private RecyclerView mRecyclerView;
    private RvAdapter adapter;
    private Subscription refreshSub;
    private Subscription loadMoreSub;

    private Map<Integer, View> layoutMap = new HashMap<>();

    private static final int showError = 0;
    private static final int showEmpty = 1;
    private static final int showLoading = 2;
    private static final int showContent = 3;

    @IntDef({showContent, showEmpty, showError, showLoading})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowView {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);
        LogUtils.e(" demo list activity create.........");
        init();
    }


    private void loadMore(List<Bean> data) {
        if (data == null || data.size() == 0) {
            adapter.notifyDataChangedAfterLoadMore(true);
            return;
        }
        List<Bean> newlyData = Observable.from(data)
                .filter(newly -> !adapter.getData().contains(newly))
                .toList()
                .toBlocking()
                .single();
        adapter.notifyDataChangedAfterLoadMore(newlyData, true);
    }

    @NonNull
    private Action1<Throwable> onError() {
        return e -> {
            LogUtils.e(e);
            showSelf(layoutMap.get(showError));
        };
    }

    private void showSelf(View self) {
        Observable.from(layoutMap.keySet())
                .subscribe(key -> {
                    View view = layoutMap.get(key);
                    int visibility = view.equals(self) ? View.VISIBLE : View.GONE;
                    view.setVisibility(visibility);
                }, LogUtils::e);
    }

    private void hideAll() {
        Observable.from(layoutMap.keySet())
                .map(key -> layoutMap.get(key))
                .subscribe(v -> v.setVisibility(View.VISIBLE),
                        LogUtils::e);
    }

    private void init() {
        View emptyLayout = findViewById(R.id.layout_empty);
        View errorLayout = findViewById(R.id.layout_error);
        View loadingLayout = findViewById(R.id.layout_loading);
        refreshView = (SwipeRefreshLayout) findViewById(R.id.demo_refresh_view);
        refreshView.setColorSchemeColors(R.color.colorPrimary, R.color.colorAccent);
        refreshView.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        layoutMap.put(showContent, refreshView);
        layoutMap.put(showEmpty, emptyLayout);
        layoutMap.put(showError, errorLayout);
        layoutMap.put(showLoading, loadingLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.demo_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RvAdapter(new ArrayList<>());
        adapter.openLoadMore(0, true); //一进来就加载更多了！！！
//        adapter.openLoadAnimation(); //开启默认动画
        adapter.setOnLoadMoreListener
                (() -> {
                            LogUtils.e("加载更多。。。。");
                            RxJavaUtil.close(loadMoreSub);
                            loadMoreSub = LoadApi.loadMoreApi()
                                    .subscribe(this::loadMore,
                                            onError(),
                                            () -> onComplete());
                        }
                );
        mRecyclerView.setAdapter(adapter);
        refreshView.setOnRefreshListener(this::refresh);
        assert errorLayout != null;
        assert emptyLayout != null;
        errorLayout.setOnClickListener(v -> refresh());
        emptyLayout.setOnClickListener(v -> refresh());
        assert loadingLayout != null;
        loadingLayout.setVisibility(View.VISIBLE);
        refreshView.setVisibility(View.VISIBLE);
        emptyLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
    }

    private void onComplete() {
        if (adapter.getData() == null) {
            onError();
        } else if (adapter.getData().size() == 0) {
            showSelf(layoutMap.get(showEmpty));
        } else {
            showSelf(layoutMap.get(showContent));
        }
    }

    private void refresh() {
        RxJavaUtil.close(refreshSub);
        refreshSub = LoadApi.refreshApi()
                .subscribe(data -> {
                            if (adapter == null) throw new NullPointerException("adapter is null!");
                            if (data.size() == 0)
                                showSelf(layoutMap.get(showEmpty));
                            else {
                                showSelf(layoutMap.get(showContent));
                                adapter.setNewData(data);
                            }
                        }, onError(),
                        () -> {
                            refreshView.setRefreshing(false);
                            onComplete();
                        });
    }

}
