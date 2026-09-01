/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.datamask.controller;

import cn.zhuatech.datamask.service.MaskingJobReleaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/datamask")
public class MaskingJobReleaseController {
    private final MaskingJobReleaseService service;
    public MaskingJobReleaseController(MaskingJobReleaseService service) { this.service = service; }
    @PostMapping("/masking-job-release")
    public MaskingJobReleaseService.Assessment assess(
            @Valid @RequestBody MaskingJobReleaseService.Request request) {
        return service.assess(request);
    }
}
