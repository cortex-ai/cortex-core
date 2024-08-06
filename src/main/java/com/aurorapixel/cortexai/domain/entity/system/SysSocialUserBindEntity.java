package com.aurorapixel.cortexai.domain.entity.system;


import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("mrk_social_user_bind")
@EqualsAndHashCode(callSuper = true)
public class SysSocialUserBindEntity extends BaseEntity {
    /**
     * 关联的用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 社交平台的用户编号
     *
     */
    private Long socialUserId;

    /**
     * 社交平台的类型
     *
     */
    private Integer socialType;
}
