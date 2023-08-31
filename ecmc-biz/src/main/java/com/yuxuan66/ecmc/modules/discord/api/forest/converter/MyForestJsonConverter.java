package com.yuxuan66.ecmc.modules.discord.api.forest.converter;

import com.dtflys.forest.converter.json.ForestJacksonConverter;
import com.dtflys.forest.converter.json.ForestJsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Configuration
public class MyForestJsonConverter {
    @Bean
    public ForestJsonConverter forestFastjsonConverter() {
        ForestJacksonConverter converter = new ForestJacksonConverter();
        // 设置日期格式
        converter.setDateFormat("yyyy-MM-dd hh:mm:ss");
        // 获取 Jackson 的 ObjectMapper 对象
        ObjectMapper mapper = converter.getMapper();
        // 通过 ObjectMapper 对象可以对 Jackson 转换器做更细致的设置
        return converter;
    }
}
