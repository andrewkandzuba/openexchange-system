package io.openexchange;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BenchmarkMockController {
    @RequestMapping("/test")
    public String test(){
        return "test message";
    }
}