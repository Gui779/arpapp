package com.lbb.mvplibrary.retrofit;


import com.lbb.mvplibrary.utils.LoggingInterceptor;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ApiHttp {

    /**
     *  默认超时时间 单位/秒
     */
    public static final int DEFAULT_TIME_OUT = 20;

    private int mTimeout = DEFAULT_TIME_OUT;

    private String mBaseUrl;

    private OkHttpClient mOkHttpClient;

    private Retrofit mRetrofit;


    public ApiHttp(String baseUrl){
        this(baseUrl,DEFAULT_TIME_OUT);
    }

    /**
     *
     * @param baseUrl
     * @param timeout  超时时间 单位/秒
     */
    public ApiHttp(String baseUrl, int timeout){
        this.mBaseUrl = baseUrl;
        this.mTimeout = timeout;
    }

    public Retrofit getRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory( GsonConverterFactory.create())
                    .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient(){

        if(mOkHttpClient == null) {
         // HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
           LoggingInterceptor loggingInterceptor=new LoggingInterceptor();
        // loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(mTimeout, TimeUnit.SECONDS)
                    .readTimeout(mTimeout, TimeUnit.SECONDS)
                    .writeTimeout(mTimeout, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .hostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .build();
        }

        return mOkHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }
}
