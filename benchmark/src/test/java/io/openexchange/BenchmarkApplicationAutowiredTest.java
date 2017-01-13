package io.openexchange;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.export.Exporter;
import org.springframework.boot.actuate.metrics.rich.RichGaugeRepository;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootApplication
@TestPropertySource(locations = "classpath:test.properties")
public class BenchmarkApplicationAutowiredTest {
    @Autowired
    private MetricWriter metricWriter;
    @Autowired
    private Exporter exporter;

    @Test
    public void testBoot() throws Exception {
        Assert.assertTrue(metricWriter instanceof RichGaugeRepository);
    }
}
