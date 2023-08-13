package com.example.exceldemo.main;

import java.sql.Types;
import java.util.Collections;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGen {
    public static void main(String[] args) {
        final String outputDir = "";

        FastAutoGenerator.create("jdbc:", "", "")
            .globalConfig(builder -> builder.author("xxoo").enableSwagger().outputDir(outputDir).disableOpenDir())
            .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                if (typeCode == Types.SMALLINT) {
                    return DbColumnType.INTEGER;
                }
                return typeRegistry.getColumnType(metaInfo);
            }))
            .packageConfig(builder -> builder.parent("com.seefly.xx").moduleName("xx")
                .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir)))
            .strategyConfig(builder -> builder.addInclude("project_operation_record", "project_operation_user_role")
                .addTablePrefix("t_x", "c_x").entityBuilder().enableFileOverride().enableLombok().enableChainModel()
                .enableColumnConstant().enableTableFieldAnnotation().mapperBuilder().enableFileOverride()
                .mapperAnnotation(Mapper.class).enableBaseColumnList().enableBaseResultMap().serviceBuilder()
                .enableFileOverride().formatServiceFileName("%sService").controllerBuilder().enableFileOverride()
                .enableRestStyle().enableHyphenStyle())
            .templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}
