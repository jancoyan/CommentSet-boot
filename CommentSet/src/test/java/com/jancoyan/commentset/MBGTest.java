/**
 * @Author: Yan Jingcun
 * @Date: 2021/8/22
 * @Description:
 * @Version: 1.0
 */

package com.jancoyan.commentset;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;


public class MBGTest {


//    @Test
    public void testGenerator(){
//        全局配置
        GlobalConfig config = new GlobalConfig();
        config.setAuthor("Jancoyan")
                .setOutputDir("R:\\GITHUB\\CommentSet-boot\\CommentSet\\src\\main\\java")
                .setFileOverride(true)
                .setServiceName("%sService")
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setActiveRecord(true);

//        数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql:///commentSet?serverTimezone=UTC&characterEncoding=utf-8")
                .setUsername("root")
                .setPassword("333");

//        策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setTablePrefix("tbl_")
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setInclude("tbl_collection")
                .setInclude("tbl_collection_comment")
                .setInclude("tbl_comment")
                .setInclude("tbl_user");

//        包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.jancoyan.commentset")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("pojo")
                .setXml("mapper");

//        整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

//        执行
        autoGenerator.execute();
    }


}
