/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.datamask.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MaskingJobReleaseServiceTest {
    private final MaskingJobReleaseService service = new MaskingJobReleaseService();
    @Test void executesControlledMaskingJob() {
        var r = service.assess(new MaskingJobReleaseService.Request("M1", true, true, true, true,
                true, true, true, true, true, true, true));
        assertThat(r.decision()).isEqualTo(MaskingJobReleaseService.Decision.EXECUTE);
    }
    @Test void pilotsUnvalidatedJob() {
        var r = service.assess(new MaskingJobReleaseService.Request("M2", true, true, true, true,
                true, true, true, false, false, true, true));
        assertThat(r.actions()).hasSize(2);
    }
    @Test void blocksUnsafeJob() {
        var r = service.assess(new MaskingJobReleaseService.Request("M3", false, false, false, false,
                false, false, false, true, true, false, false));
        assertThat(r.blockers()).hasSize(9);
    }
}
