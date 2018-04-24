package com.magicrecipe.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.magicrecipe.Adapter.DataAdapter;
import com.magicrecipe.Helper.MyDialog_Progress;
import com.magicrecipe.R;
import com.magicrecipe.Model.Recepies;
import com.magicrecipe.Interface.RequestInterface;
import com.magicrecipe.Model.SubDetails;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Recepie extends AppCompatActivity {
    public static final String BASE_URL = "http://www.recipepuppy.com/";

    private RecyclerView mRecyclerView;

    private CompositeDisposable mCompositeDisposable;

    private DataAdapter mAdapter;
    private TextView no_recipe;
    private ArrayList<SubDetails> mAndroidArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepie);

        mCompositeDisposable = new CompositeDisposable();
        no_recipe=findViewById(R.id.no_recipe);
        initRecyclerView();
        loadJSON();
    }

    private void initRecyclerView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        MyDialog_Progress.open(Recepie.this);
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);
        Intent intent = getIntent();
        String user=intent.getStringExtra("user");

        mCompositeDisposable.add(requestInterface.register(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));

    }

    private void handleResponse(Recepies androidList) {
        List<SubDetails> repo = androidList.getReceipes();
        mAndroidArrayList = new ArrayList<>(repo);
        if(mAndroidArrayList.size()==0){
               no_recipe.setVisibility(View.VISIBLE);
            MyDialog_Progress.close(Recepie.this);
        }else {
            no_recipe.setVisibility(View.GONE);
            mAdapter = new DataAdapter(this, mAndroidArrayList);
            mRecyclerView.setAdapter(mAdapter);
            MyDialog_Progress.close(Recepie.this);
        }
    }

    private void handleError(Throwable error) {
        MyDialog_Progress.close(Recepie.this);
        Toast.makeText(this, "Enter a valid user & repository", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

}
