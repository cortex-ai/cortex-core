package com.aurorapixel.cortexai.common.config.mybatis;

import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseRepositoryImpl<M extends BaseMapper<T>,T extends BaseEntity> extends ServiceImpl<M,T> implements BaseRepository<T> {

}
