package com.gdpu.backend.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**注解明确表示这是一个配置类，Spring 会识别并处理该类，将其作为配置源，按照其中定义的逻辑来创建和配置相关的 Bean 对象，构建项目所需的运行环境。
 *
 */
@Configuration
@MapperScan("com.gdpu.backend.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseGeneratedKeys(false);
        /**属性设置为 false，是为了遵循 MyBatis 的相关规则。
         *
         */
    }
    /**对 MyBatis Plus 进行基础配置，使其具备分页功能并对 MyBatis 的部分配置属性进行了定制化设置
     *
     */
}