package io.openexchange;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootApplication(exclude = BenchmarkApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class BenchmarkApplicationTest {
    @Test
    public void testBoot() throws Exception {
    }
}
