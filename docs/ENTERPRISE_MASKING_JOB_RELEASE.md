# 企业级数据脱敏任务发布

`POST /api/enterprise/datamask/masking-job-release` 检查源数据授权、数据分类、规则覆盖、不可逆性、关联一致性、输出权限、行数核对、预演、抽样验证、审计和审批，返回 `EXECUTE / PILOT / BLOCKED`。

真实项目应在隔离环境运行，禁止使用生产明文数据进行无审批复制，并对脱敏规则、输入输出位置及访问行为完整审计。
