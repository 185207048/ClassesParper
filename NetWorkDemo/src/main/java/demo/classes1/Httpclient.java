package demo.classes1;


import okhttp3.*;

import javax.management.timer.Timer;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Httpclient {
    public void sendPost(String url, String json)   {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response != null && response.isSuccessful()){
                    System.out.println(response.body().string());
                }
            }
        });

    }

    public static void main(String[] args) throws InterruptedException {
        while (true){
            sleep(Timer.ONE_SECOND*5);
            new Httpclient().sendPost("http://10.50.101.80/Iast/engine/agent_heartbeat","{\"agent_id\": \"60a92a51-0ceb-593b-557a-d51be764f3d9\"}");
        }
    }
}
