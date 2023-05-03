//package com.app.dodamdodam;
//
//import okhttp3.*;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import java.io.IOException;
//
//
//public class KakaoCalendarExample {
//    private static final String API_KEY = "c4295049a5b123f27adae92ac07abeb1";
//    private static final String CLIENT_SECRET = "GyfKhVQFtFDkpPPj3YBaPx63BlZnhK5O";
//
//    public static void main(String[] args) {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://kapi.kakao.com/v1/calendar/event")
//                .addHeader("Authorization", "KakaoAK " + API_KEY)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                System.out.println(response.body().string());
//            } else {
//                System.out.println("API request failed: " + response.code());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
