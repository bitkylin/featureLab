package cc.bitky.test.idea.integration.service.util;

import okhttp3.*;

/**
 * @author limingliang
 */
public class TradeTestHttpClient {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static String postJson(String url, String jsonContent) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, jsonContent);
        return doPost(url, body);
    }

    public static String doGet(String url) {
        //创建一个Request
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Response response;
        try {
            response = CLIENT.newCall(request).execute();
            ResponseBody responseBody = response.body();
            return responseBody.string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String doPost(String url, RequestBody body) {
        Request requestOk = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response;
        try {
            response = CLIENT.newCall(requestOk).execute();
            ResponseBody responseBody = response.body();
            return responseBody.string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
