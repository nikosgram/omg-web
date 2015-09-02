package me.nikosgram.omg.web;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebClient implements Closeable {
    private final ExecutorService service;

    public WebClient() {
        service = Executors.newFixedThreadPool(10, Thread::new);
    }

    public WebClient send(String url, ResponseHandler handler) {
        return send(url, RequestMethod.GET, handler);
    }

    public WebClient send(String url, RequestMethod method, ResponseHandler handler) {
        service.execute(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setDefaultUseCaches(true);
                connection.setRequestMethod(method.getType());
                connection.setUseCaches(true);
                connection.setDoOutput(false);
                connection.setDoInput(true);
                connection.connect();

                long delay = System.currentTimeMillis();

                try (InputStream reader = connection.getInputStream()) {
                    //StringBuilder
//                    StringBuilder builder = new StringBuilder();

//                    int character;
//                    while ((character = reader.read()) != -1) {
//                        builder.append((char) character);
//                    }

//                    handler.execute(ResponseBuilder.create()
//                                    .response(builder.toString())
//                                    .delay(System.currentTimeMillis() - delay)
//                                    .responseCode(connection.getResponseCode())
//                                    .responseMethod(connection.getRequestMethod())
//                                    .build()
//                    );

                    //StringWriter
//                    StringWriter writer = new StringWriter();
//                    int character;
//                    while ((character = reader.read()) != -1) {
//                        writer.append((char) character);
//                    }
//
//                    handler.execute(ResponseBuilder.create()
//                                    .response(writer.toString())
//                                    .delay(System.currentTimeMillis() - delay)
//                                    .responseCode(connection.getResponseCode())
//                                    .responseMethod(connection.getRequestMethod())
//                                    .build()
//                    );

                    //Scanner
//                    Scanner scanner = new Scanner(reader, "UTF-8").useDelimiter("A");
//
//                    handler.execute(ResponseBuilder.create()
//                                    .response(scanner.hasNext() ? scanner.next() : "")
//                                    .delay(System.currentTimeMillis() - delay)
//                                    .responseCode(connection.getResponseCode())
//                                    .responseMethod(connection.getRequestMethod())
//                                    .build()
//                    );

                    //String The best way.
                    byte[] buffer = new byte[reader.available()];
                    while (reader.read(buffer) > -1) {
                        buffer = new byte[reader.available() + 1];
                    }

                    handler.execute(ResponseBuilder.create()
                                    .response(new String(buffer))
                                    .delay(System.currentTimeMillis() - delay)
                                    .responseCode(connection.getResponseCode())
                                    .responseMethod(connection.getRequestMethod())
                                    .build()
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return this;
    }

    @Override
    public void close() throws IOException {
        service.shutdown();
    }
}