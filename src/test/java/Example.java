import me.nikosgram.omg.web.WebClient;

import java.io.IOException;

public class Example {
    public static void main(String[] args) throws Exception {
        final long delay = System.currentTimeMillis();

        WebClient client = new WebClient();

        for (int i = 0; i < 10; i++) {
            final long post = System.currentTimeMillis();
            final int finalI = i;
            client.send("http://localhost/", (response) -> {
                long now = System.currentTimeMillis();

                System.out.println("Task [" + (finalI + 1) + "]: Code:[" + response.getResponseCode() + "] Delay:[" + (now - delay) + "ms], PostDelay:[" + (now - post) + "ms], DownloadDelay:[" + response.getDelay() + "ms]");
            });
        }
        final long post = System.currentTimeMillis();
        client.send("http://localhost/", (response) -> {
            long now = System.currentTimeMillis();

            System.out.println("Task [Final]: Code:[" + response.getResponseCode() + "] Delay:[" + (now - delay) + "ms], PostDelay:[" + (now - post) + "ms], DownloadDelay:[" + response.getDelay() + "ms]");

            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
