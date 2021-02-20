package com.ting.activity.config;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * uuid生成策略
 *
 * @author ting
 * @version 1.0
 * @date 2021/01/28
 */
public class UUIDGenerator implements IdGenerator {
    @Override
    public String getNextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
