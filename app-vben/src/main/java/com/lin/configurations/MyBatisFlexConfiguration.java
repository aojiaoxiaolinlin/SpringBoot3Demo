package com.lin.configurations;

import com.lin.utils.ULIDGenerate;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisFlexConfiguration {
    private static final Logger logger = LoggerFactory
            .getLogger("mybatis-flex-sql");

    public MyBatisFlexConfiguration() {
        KeyGeneratorFactory.register("ULID", new ULIDGenerate());
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage ->
                logger.info("{}; 耗时:{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime()));
    }
}
