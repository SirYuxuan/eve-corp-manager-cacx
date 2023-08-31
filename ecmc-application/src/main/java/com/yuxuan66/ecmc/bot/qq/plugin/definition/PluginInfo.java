package com.yuxuan66.ecmc.bot.qq.plugin.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Discord插件信息
 * @author Sir丶雨轩
 * @since 2023/1/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PluginInfo {

    /**
     * 插件名称
     */
    private String name;
    /**
     * 插件排序, 越小执行优先级越高
     */
    private int sort;
}
