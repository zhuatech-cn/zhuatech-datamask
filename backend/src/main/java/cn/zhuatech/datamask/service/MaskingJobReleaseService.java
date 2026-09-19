/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.datamask.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class MaskingJobReleaseService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.sourceAccessApproved()) blockers.add("源数据访问未批准");
        if (!request.dataCatalogClassified()) blockers.add("敏感数据目录分类不完整");
        if (!request.ruleCoverageComplete()) blockers.add("脱敏规则未覆盖全部敏感字段");
        if (!request.irreversibleForDirectIdentifiers()) blockers.add("直接标识符仍可逆");
        if (!request.referentialIntegrityPreserved()) blockers.add("跨表关联一致性未验证");
        if (!request.outputAccessRestricted()) blockers.add("脱敏输出访问权限未限制");
        if (!request.rowCountReconciled()) blockers.add("源表与输出行数未核对");
        if (!request.auditTrailEnabled()) blockers.add("脱敏任务审计未启用");
        if (!request.finalApprovalComplete()) blockers.add("脱敏任务发布审批未完成");
        if (!blockers.isEmpty()) {
            actions.add("阻断脱敏任务并完成数据、安全和质量整改");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (!request.dryRunPassed() || !request.sampleValidationPassed()) {
            if (!request.dryRunPassed()) actions.add("在隔离环境完成全量预演");
            if (!request.sampleValidationPassed()) actions.add("由数据责任人完成抽样验证");
            return new Assessment(Decision.PILOT, blockers, actions);
        }
        actions.add("批准脱敏任务执行并归档规则、版本、统计和审批证据");
        return new Assessment(Decision.EXECUTE, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String jobId, boolean sourceAccessApproved,
                          boolean dataCatalogClassified, boolean ruleCoverageComplete,
                          boolean irreversibleForDirectIdentifiers, boolean referentialIntegrityPreserved,
                          boolean outputAccessRestricted, boolean rowCountReconciled,
                          boolean dryRunPassed, boolean sampleValidationPassed,
                          boolean auditTrailEnabled, boolean finalApprovalComplete) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { EXECUTE, PILOT, BLOCKED }
}
