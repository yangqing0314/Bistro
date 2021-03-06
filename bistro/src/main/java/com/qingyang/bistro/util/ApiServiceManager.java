package com.qingyang.bistro.util;

import android.widget.Toast;
import com.qingyang.bistro.BuildConfig;
import com.qingyang.bistro.QLPApplication;
import com.qingyang.bistro.event.SignInEvent;
import com.qingyang.bistro.net.ApiService;
import com.qingyang.bistro.net.request.SignInRequest;
import com.qingyang.bistro.net.request.SignInRequest2;
import com.qingyang.bistro.net.request.UserInfoResponse;
import com.qingyang.mainlibrary.api.MainApiManager;
import com.qingyang.mainlibrary.util.LogUtil;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by QingYang on 15/9/10.
 */
public class ApiServiceManager {
    private String TAG = ApiServiceManager.class.getSimpleName();

    private static ApiServiceManager sApiServiceManager;
    private static ApiService sApiServie;
    private ApiServiceManager() {}

    public static ApiServiceManager getInstance() {
        if (sApiServiceManager == null) {
            sApiServiceManager = new ApiServiceManager();
        }
        sApiServie = (ApiService) MainApiManager.getInstance().getApiService();
        return  sApiServiceManager;
    }


    public void signInWithUserName(String uerName, String password) {
        MainApiManager.getInstance()
                .initializeApiCustomerService(ApiService.class, BuildConfig.SERVER_URL,
                        QLPApplication.getsConfiguration().getUserAgent());

        ApiService apiCustomerService =
                (ApiService) MainApiManager.getInstance().getApiCustomerService();

        //apiCustomerService.signIn(new SignInRequest(uerName, password), new Callback<UserInfoResponse>() {
        //    @Override public void success(UserInfoResponse userInfoResponse, Response response) {
        //        LogUtil.LOGD(TAG, "signIn_with_username response : " + response.getStatus());
        //                //EventBus.getDefault().post(new SignInEvent(true));
        //    }
        //
        //    @Override public void failure(RetrofitError error) {
        //        LogUtil.LOGD(TAG, "signIn_with_username response error : " + error.getMessage());
        //                //EventBus.getDefault().post(new SignInEvent(false));
        //    }
        //});

        //try {
        //    UserInfoResponse userInfoResponse = apiCustomerService.signIn(uerName, password);
        //    if (userInfoResponse != null) {
        //        LogUtil.LOGD(TAG, "signIn_with_username response : " );
        //    }
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

        sApiServie.signIn(new SignInRequest(uerName, password), new Callback<UserInfoResponse>() {
            @Override public void success(UserInfoResponse userInfoResponse, Response response) {
                LogUtil.LOGD(TAG, "signIn_with_username response : " + response.getStatus());
                EventBus.getDefault().post(new SignInEvent(true));
            }

            @Override public void failure(RetrofitError error) {
                LogUtil.LOGD(TAG, "signIn_with_username response error : " + error.getMessage());
                EventBus.getDefault().post(new SignInEvent(false));
            }
        });
    }

}
