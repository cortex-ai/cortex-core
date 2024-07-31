package com.aurorapixel.cortexai.infrastructure.config.mybatis;

import cn.hutool.core.util.ObjectUtil;
import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

public class DefaultInsertHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
            if(ObjectUtil.isNull(baseEntity.getCreateTime())){
                baseEntity.setCreateTime(LocalDateTime.now());
            }
            if(ObjectUtil.isNull(baseEntity.getUpdateTime())){
                baseEntity.setUpdateTime(LocalDateTime.now());
            }
            //TODO 获取当前登录用户
//            if(ObjectUtil.isNull(baseEntity.getCreateUser())&&ObjectUtil.isNotNull(user)){
//                baseEntity.setCreateUser(user.getUserId());
//            }
//            if(ObjectUtil.isNull(baseEntity.getUpdateUser())&&ObjectUtil.isNotNull(user)){
//                baseEntity.setUpdateUser(user.getUserId());
//            }
//            if(ObjectUtil.isNull(baseEntity.getDeleteFlag())){
//                baseEntity.setDeleteFlag(0);
//            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(ObjectUtil.isNotNull(metaObject)&& metaObject.getOriginalObject() instanceof BaseEntity baseEntity){
            if(ObjectUtil.isNull(baseEntity.getUpdateTime())){
                baseEntity.setUpdateTime(LocalDateTime.now());
            }
            //TODO 获取当前登录用户
//            if(ObjectUtil.isNull(baseEntity.getUpdateUser())&&ObjectUtil.isNotNull(user)){
//                baseEntity.setUpdateUser(user.getUserId());
//            }
        }
    }
}
