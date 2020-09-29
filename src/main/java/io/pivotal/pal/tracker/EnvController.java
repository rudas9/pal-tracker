package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController{

    private HashMap<String, String> envMap;

    public EnvController(@Value("${PORT :NOT_SET}") String port, @Value("${MEMORY_LIMIT :NOT_SET}") String memoryLimit,
                         @Value("${CF_INSTANCE_INDEX :NOT_SET}") String instanceIndex,
                         @Value("${CF_INSTACNE_ADDR :NOT_SET}") String instanceAddr){

        envMap = new HashMap<String, String>();
        envMap.put("PORT", port);
        envMap.put("MEMORY_LIMIT", memoryLimit);
        envMap.put("CF_INSTANCE_INDEX", instanceIndex);
        envMap.put("CF_INSTANCE_ADDR", instanceAddr);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        return this.envMap;
    }
}
