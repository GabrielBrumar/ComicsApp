package com.marvel.comicsapp.comicsapp.remote;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String PUBLIC_KEY = "0a2e19e81a5dc2c38b5e540d415098f4";
    private static final String PRIVATE_KEY = "bb2fa350bd5c9873a3698ae54cdc19e1add906eb";

    private static final String TS = "ts";
    private static final String APIKEY = "apikey";
    private static final String HASH = "hash";

    @Override
    public Response intercept(Chain chain) throws IOException {

        String ts = String.valueOf(System.currentTimeMillis());
        String digestMessage = ts + PRIVATE_KEY + PUBLIC_KEY;
        String md5Message = extractMD5(digestMessage);
        Request currentRequest = chain.request();

        HttpUrl url = currentRequest.url().newBuilder()
                .addQueryParameter(TS, ts)
                .addQueryParameter(APIKEY, PUBLIC_KEY)
                .addQueryParameter(HASH, md5Message).build();
        Request newRequest = currentRequest.newBuilder().url(url).build();

        return chain.proceed(newRequest);
    }

    private String extractMD5(String digestMessage) {
        StringBuffer sb = new StringBuffer();
        try {
            byte[] bytesOfMessage = digestMessage.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytesMD5 = md.digest(bytesOfMessage);
            for (int i = 0; i < bytesMD5.length; ++i) {
                sb.append(Integer.toHexString((bytesMD5[i] & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
