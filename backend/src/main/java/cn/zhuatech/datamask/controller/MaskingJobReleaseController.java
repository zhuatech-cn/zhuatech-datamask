/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.datamask.controller;

import cn.zhuatech.datamask.service.MaskingJobReleaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/datamask")
public class MaskingJobReleaseController {
    private final MaskingJobReleaseService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public MaskingJobReleaseController(MaskingJobReleaseService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/masking-job-release")
    public MaskingJobReleaseService.Assessment assess(
            @Valid @RequestBody MaskingJobReleaseService.Request request) {
        return service.assess(request);
    }
}
