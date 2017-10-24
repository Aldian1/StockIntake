import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiAccessor {

    private final String BASE_URL = "http://demo2529119.mockable.io/sendmessage";
    OkHttpClient client = new OkHttpClient();

    public ApiAccessor() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        client = builder.build();

    }


    protected String getTargetBody(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    protected void sendPostRequest(String data) {
        RequestBody formBody = new FormBody.Builder()
                .add("msf", data)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
