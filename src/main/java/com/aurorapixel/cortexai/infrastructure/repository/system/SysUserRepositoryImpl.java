package com.aurorapixel.cortexai.infrastructure.repository.system;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.system.SysUserEntity;
import com.aurorapixel.cortexai.domain.repository.system.SysUserRepository;
import com.aurorapixel.cortexai.infrastructure.mapper.system.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SysUserRepositoryImpl extends BaseRepositoryImpl<SysUserMapper, SysUserEntity> implements SysUserRepository {
    @Override
    public Optional<SysUserEntity> findByAccount(String account) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getAccount, account);
        return Optional.ofNullable(this.getOne(queryWrapper));
    }

    @Override
    public Optional<SysUserEntity> findByEmail(String email) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getEmail, email);
        return Optional.ofNullable(this.getOne(queryWrapper));
    }
}
