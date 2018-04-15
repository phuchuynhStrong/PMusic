package phucht.com.pmusic.Networking;

import okhttp3.OkHttpClient;

/**
 * Created by oldmen on 4/3/18.
 */

public class NetworkClient {
    private static OkHttpClient client;

    public static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }
}
