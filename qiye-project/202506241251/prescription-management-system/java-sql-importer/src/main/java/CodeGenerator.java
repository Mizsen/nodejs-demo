import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        // 数据库连接信息（替换为实际路径）
        String dbUrl = "jdbc:sqlite:todo.db";
        String username = ""; // SQLite无用户名
        String password = ""; // SQLite无密码
        
        // 配置生成器
        FastAutoGenerator.create(dbUrl, username, password)
            // 全局配置
            .globalConfig(builder -> {
                builder.author("你的姓名")          // 作者信息
                       .enableSwagger()            // 生成Swagger注解
                       .outputDir("src/main/java"); // 代码输出目录
            })
            // 包配置
            .packageConfig(builder -> {
                builder.parent("com.example.prescription")  // 父包名
                       .moduleName("")                       // 模块名（空表示直接使用父包）
                       .entity("entity")                     // 实体类包名
                       .mapper("mapper")                     // Mapper接口包名
                       .service("service")                   // 服务接口包名
                       .serviceImpl("service.impl")          // 服务实现类包名
                       .controller("controller");            // 控制器包名
            })
            // 策略配置
            .strategyConfig(builder -> {
                builder.addInclude(
                    "user", "authority", "prescription", 
                    "drug", "prescription_drug", "prescription_image", "drug_image"
                )  // 需要生成的表名列表
                .addTablePrefix("t_", "c_")  // 表名前缀过滤（如有）
                .entityBuilder()
                    .enableLombok()             // 启用Lombok注解
                    .enableTableFieldAnnotation() // 启用字段注解
                    .naming(NamingStrategy.underline_to_camel) // 表名转驼峰策略
                    .formatFileName("%sEntity")  // 实体类命名格式
                .mapperBuilder()
                    .enableBaseResultMap()      // 生成BaseResultMap
                    .enableBaseColumnList()     // 生成BaseColumnList
                    .formatMapperFileName("%sMapper") // Mapper接口命名格式
                .serviceBuilder()
                    .formatServiceFileName("%sService") // 服务接口命名格式
                    .formatServiceImplFileName("%sServiceImpl") // 服务实现类命名
                .controllerBuilder()
                    .formatFileName("%sController") // 控制器命名格式
                    .enableRestStyle();           // 启用Rest风格注解
            })
            // 模板引擎配置（使用Freemarker）
            .templateEngine(new FreemarkerTemplateEngine())
            // 执行生成
            .execute();
    }
}