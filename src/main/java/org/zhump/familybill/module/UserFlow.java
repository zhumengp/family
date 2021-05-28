package org.zhump.familybill.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *@Title UserFlow
 *@Description: 用户流水实体
 *
 *@author zhump
 *@date 2021/4/10 20:59
 */
@Data
public class UserFlow {

    /**
     * 主键
     */
    private long id;
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 分类ID
     */
    private long categoryId;
    /**
     * 消费金额
     */
    private BigDecimal price;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    /**
     * 消费时间，具体哪个时间消费的
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp comsumeTime;
    /**
     * 备注
     */
    private String remark;
}
