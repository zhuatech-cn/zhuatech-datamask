-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/
CREATE TABLE IF NOT EXISTS masking_rule(id BIGINT PRIMARY KEY AUTO_INCREMENT,field_type VARCHAR(32) NOT NULL,rule_name VARCHAR(80) NOT NULL,enabled BOOLEAN DEFAULT TRUE,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
