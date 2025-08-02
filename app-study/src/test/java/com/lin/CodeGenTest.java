package com.lin;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeGenTest {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Test
    void generate() {
        // 配置数据源
        try (HikariDataSource dataSource = new HikariDataSource()) {
            System.out.println(url);
            dataSource.setJdbcUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            GlobalConfig globalConfig = createGlobalConfigUseStyle();

            // 通过 datasource 和 globalConfig 创建代码生成器
            Generator generator = new Generator(dataSource, globalConfig);

            // 生成代码
            generator.generate();
        }
    }

    private GlobalConfig createGlobalConfigUseStyle() {
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 设置根包
        globalConfig.getPackageConfig()
                    .setEntityPackage("com.lin.entities")
                    .setMapperPackage("com.lin.mappers")
                    .setMapperXmlPath(System.getProperty("user.dir") + "/src/main/resources/mappers")
                    .setServicePackage("com.lin.services")
                    .setServiceImplPackage("com.lin.services.impl")
                    .setControllerPackage("com.lin.controllers");

        // 设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
        globalConfig.getStrategyConfig()
                    // .setTablePrefix("tb_")
                    .setGenerateTable("device","owner");

        // 设置生成 entity 并启用 Lombok
        globalConfig.enableEntity()
                    .setWithLombok(true)
                    .setJdkVersion(21);

        // 设置生成 mapper
        globalConfig.enableMapper()
                    .setMapperAnnotation(true);

        // globalConfig.enableController();

        globalConfig.enableMapperXml();

        globalConfig.enableService();

        globalConfig.enableServiceImpl();

        return globalConfig;
    }
}
