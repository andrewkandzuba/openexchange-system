package io.openexchange;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RibbonBenchmarkApp {

    public static void main(String[] args) throws Exception {
        RibbonBenchmarkApp app = new RibbonBenchmarkApp();
        app.testLoad();
    }

    private void testLoad() throws URISyntaxException {
        RestClient client = (RestClient) ClientFactory.getNamedClient("kafka-producer");  // 2
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/next")).build(); // 3
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.out.println(lb.getLoadBalancerStats());
        ConfigurationManager.getConfigInstance().setProperty(
                "kafka-producer.ribbon.listOfServers", "http://192.168.99.100:8101,http://192.168.99.100:8102,http://192.168.99.100:8103,http://192.168.99.100:8104"); // 5
        ConfigurationManager.getConfigInstance().setProperty(
                "kafka-producer.ribbon.MaxTotalConnections", 800); // 5
        ConfigurationManager.getConfigInstance().setProperty(
                "kafka-producer.ribbon.MaxConnectionsPerHost", 200); // 5
        ConfigurationManager.getConfigInstance().setProperty(
                "kafka-producer.ribbon.MaxHttpConnectionsPerHost", 200); // 5
        ConfigurationManager.getConfigInstance().setProperty(
                "kafka-producer.ribbon.EnableConnectionPool", true); // 5
        System.out.println("changing servers ...");

        generateLoad(client, request);

        System.out.println(lb.getLoadBalancerStats()); // 7
    }

    private void generateLoad(RestClient client, HttpRequest request) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch latch = new CountDownLatch(200);

        ses.schedule(task(client, request, ses, latch), 0, TimeUnit.MICROSECONDS);
        ses.schedule(task(client, request, ses, latch), 0, TimeUnit.MICROSECONDS);

        try {
            latch.await(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            ses.shutdown();
            try {
                if (!ses.awaitTermination(60, TimeUnit.MILLISECONDS)) {
                    ses.shutdownNow();
                    if (!ses.awaitTermination(30, TimeUnit.MILLISECONDS)) {
                        System.err.println("Pool is not shutting down!!!");
                    }
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private Runnable task(final RestClient client,
                          final HttpRequest request,
                          final ScheduledExecutorService ses,
                          CountDownLatch latch) {
        return () -> {
            try {
                HttpResponse response = client.executeWithLoadBalancer(request);
                System.out.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
            } catch (Throwable t) {
                System.err.println(t.getMessage());
            } finally {
                latch.countDown();
                ses.schedule(task(client, request, ses, latch), new Random().nextInt(1000), TimeUnit.MILLISECONDS);
            }
        };
    }
}