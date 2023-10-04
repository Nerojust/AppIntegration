package com.company.appintegration.ViewModels;


import static com.company.appintegration.ApiClient.RetrofitClient.getUserService;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.appintegration.Models.BillerResponseModel;
import com.company.appintegration.Models.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserViewModel extends ViewModel {

    public static final String TAG = "UserViewModel";
    private MutableLiveData<Resource<BillerResponseModel>> myResponseLivedata;

    public UserViewModel() {
        this.myResponseLivedata = new MutableLiveData<>();
    }

    public MutableLiveData<Resource<BillerResponseModel>> getMyResponseLivedata() {
        return myResponseLivedata;
    }

    public void executeGetBillerList(String value) {
        Call<BillerResponseModel> myCall = getUserService().getBillerList(value);
        myCall.enqueue(new Callback<BillerResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BillerResponseModel> call, @NonNull Response<BillerResponseModel> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    myResponseLivedata.postValue(Resource.success(response.body()));
                } else {
                    String errorMessage;
                    errorMessage = response.message();

                    myResponseLivedata.postValue(Resource.error(errorMessage));
                }
            }

            @Override
            public void onFailure(@NonNull Call<BillerResponseModel> call, @NonNull Throwable t) {
                myResponseLivedata.postValue(Resource.error(t.getMessage()));
            }
        });
    }
}
