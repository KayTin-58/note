package com.tool;


/**
 * <p>
 *  sql数据库的数据类型
 * </p>
 *
 * @author BO.ZHANG04@hand-china.com 2018/06/07 13:48
 */
public enum SqlDataType {

    /**
     * 整数类型：BIT、BOOL、TINY INT、SMALL INT、MEDIUM INT、 INT、 BIG INT
     *
     * 浮点数类型：FLOAT、DOUBLE、DECIMAL
     *
     * 字符串类型：CHAR、VARCHAR、TINY TEXT、TEXT、MEDIUM TEXT、LONGTEXT、TINY BLOB、BLOB、MEDIUM BLOB、LONG BLOB
     *
     * 日期类型：Date、DateTime、TimeStamp、Time、Year
     *
     * 其他数据类型：BINARY、VARBINARY、ENUM、SET、Geometry、Point、MultiPoint、LineString、MultiLineString、Polygon、GeometryCollection等
     *
     *
     *BitMap
     */
    BIG_INT("bigint"),
    FLOAT("float"),
    DOUBLE("double"),
    DECIMAL("decimal"),
    CHAR("char"),
    VARCHAR("varchar"),
    DATETIME("DATETIME"),
    TIMESTAMP("timestamp"),
    STRING("string");

    SqlDataType(String type) {
        this.type = type;
    }
    private final String type;

    public String getType() {
        return type;
    }
}
