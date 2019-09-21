package com.tool;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表字段信息
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Column implements Serializable {

    /**
     * 索引
     */
    private Integer colIndex;

    /**
     * 列名
     */
    private String colName;

    /**
     * 类型
     */
    private String typeName;

    /**
     * 长度
     */
    private Integer colSize;

    /**
     * 精度
     */
    private Integer accuracy;

    /**
     * 默认值
     */
    private String colDef;

    /**
     * 允许为空
     */
    private String nullAble;

    /**
     * 备注
     */
    private String remarks;

}
