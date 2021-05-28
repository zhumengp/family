package org.zhump.familybill.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

/**
 *@Title UserFlowListRsponse
 *@Description: 用户流水响应类
 *
 *@author zhump
 *@date 2021/4/11 21:55
 */
@Data
public class UserFlowListRsponse {

    /**
     * 主键
     */
    private long id;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户ID
     */
    private long userId;

    /**
     * 消费备注，例如公交，充话费
     */
    private String categoryName;
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
     * 跟新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    
    /**
     * 消费时间，具体哪个时间消费的
     */
    private String comsumeTime;

    /**
     * 备注
     */
    private String remark;
}
