package org.education.ade.controller;

import com.alibaba.fastjson.JSONObject;
import org.education.ade.resp.BaseResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author fbin
 * @since 2023-05-11
 */
@RestController
public class HealthCheckController {
    @GetMapping("health/check")
    public BaseResp<Object> execute() {
        JSONObject data = new JSONObject();
        data.put("timestamp", System.currentTimeMillis());
        return BaseResp.ok(data);
    }
}
