package com.company.appintegration.Views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.appintegration.Adapters.UserAdapter;
import com.company.appintegration.Models.BillerResponseModel;
import com.company.appintegration.Models.Resource;
import com.company.appintegration.Models.Result;
import com.company.appintegration.R;
import com.company.appintegration.ViewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel myUserViewModel;
    private UserAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private RecyclerView myRecyclerView;
    private List<Result> myResponse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initSubscribers();
    }


    private void initViews() {
        myRecyclerView = findViewById(R.id.recycler_view);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(myLayoutManager);

        myUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        myUserViewModel.executeGetBillerList("2s");
    }

    private void initSubscribers() {
        myUserViewModel.getMyResponseLivedata().observe(this, new Observer<Resource<BillerResponseModel>>() {
            @Override
            public void onChanged(Resource<BillerResponseModel> billerResponseModelResource) {
                switch (billerResponseModelResource.status){
                    case LOADING:
                        //show loading here
                        break;
                    case SUCCESS:
                        //dismiss loading here and in error block
                        List<Result> billerList = null;
                        if (billerResponseModelResource.data != null) {
                            billerList = billerResponseModelResource.data.getResult();
                        }

                        myAdapter = new UserAdapter(MainActivity.this, billerList);
                        myRecyclerView.setAdapter(myAdapter);
                        myAdapter.notifyDataSetChanged();

                        break;
                    case ERROR:
                        break;
                }
            }
        });
    }
}