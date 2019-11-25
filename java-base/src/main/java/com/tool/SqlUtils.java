package com.tool;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 构建SQL语句
 * </p>
 *
 * @author BO.ZHANG04@hand-china.com 2018/06/07 13:48
 */
public final class SqlUtils {

    private static final String CREATE_TABLE = "CREATE TABLE %s";
    private static final String DOUBLE_QUOTES = "\"";
    private static final String APO_STROPHE = "\'";
    private static final String MYSQL_COLUMN_BASE = "%s %s default %s %s comment %s";
    private static final String HIVE_COLUMN_BASE = "%s %s comment %s";
    private static final String CREATE_PK_SQL = "id int primary key auto_increment,";
    private static final String PARTITIONED_BY = "PARTITIONED BY (";
    private static final String ROW_FORMAT = "ROW FORMAT %s";
    private static final String SPACE = " ";
    private static final String INSERT_INTO = "insert into %s";
    private static final String INSERT_OVERWRITE = "insert overwrite %s";
    private static final String AIM_TABLE = "%s.%s";
    private static final String VALUES = "VALUES";
    private static final String TYPE_OF_DATA = "%s(%s)";

    /**
     * 构建创表语句
     *
     * @param dataType 数据库类型 （Mysql/Hive）
     * @param tableName 表名
     * @param columns 列
     * @param ifPartition 是否分区
     * @param partitions 分区键
     * @param rowFormat 分隔符（Mysql时传 null）
     * @return sql
     */
    public static String structureCreateSql(DataBaseType dataType, String tableName, List<Column> columns,
                    Boolean ifPartition, List<Column> partitions, String rowFormat) {
        StringBuilder sql = new StringBuilder();
        sql.append(String.format(CREATE_TABLE, tableName)).append("(");
        // 为Mysql添加默认主键
        if (dataType.getType().equals(DataBaseType.MYSQL.getType())) {
            sql.append(CREATE_PK_SQL);
            rowFormat = null;
        }
        int size = columns.size();
        Column column;
        for (int i = 0; i < size; i++) {
            column = columns.get(i);
            String typeOfData = column.getTypeName();
            // varchar和char需要指定长度
            if (SqlDataType.VARCHAR.getType().equals(typeOfData) || SqlDataType.CHAR.getType().equals(typeOfData)) {
                typeOfData = String.format(TYPE_OF_DATA, column.getTypeName(), column.getColSize());
            }
            switch (dataType) {
                case HIVE:
                    sql.append(String.format(HIVE_COLUMN_BASE, column.getColName(), typeOfData,
                                    APO_STROPHE + column.getRemarks() + APO_STROPHE));
                    break;
                case MYSQL:
                    sql.append(String.format(MYSQL_COLUMN_BASE, column.getColName(), typeOfData,
                                    DOUBLE_QUOTES + replaceIfNull(column.getColDef()) + DOUBLE_QUOTES,
                                    column.getNullAble(), APO_STROPHE + column.getRemarks() + APO_STROPHE));
                    break;
                default:
                    break;
            }
            if (i != size - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        // 是否分区
        if (ifPartition) {
            sql.append(SPACE).append(PARTITIONED_BY);
            partitions.forEach(s -> sql.append(s.getColName()).append(SPACE).append(s.getTypeName()));
            sql.append(")").append(SPACE);
        }
        // 是否指定了分隔符
        if (!StringUtils.isEmpty(rowFormat)) {
            sql.append(String.format(ROW_FORMAT, APO_STROPHE + rowFormat + APO_STROPHE));
        }
        return sql.toString();
    }

    /**
     * 构建插值sql
     *
     * @param dataBaseType 数据库类型
     * @param schema 数据库
     * @param tableName 表名
     * @param values 值元组
     * @return sql
     */
    public static String structureInsertSql(DataBaseType dataBaseType, String schema, String tableName,
                    List<Tuple<String, String>> values) {
        StringBuilder sql = new StringBuilder();
        String aimTable = String.format(AIM_TABLE, schema, tableName);
        sql.append(String.format(INSERT_INTO, aimTable));

        sql.append("(");
        // 值sql
        StringBuilder valuesSql = new StringBuilder();
        valuesSql.append("(");
        int size = values.size();
        Tuple<String, String> tuple;
        for (int i = 0; i < size; i++) {
            tuple = values.get(i);
            // 列名
            String key = tuple.getFirst();
            // 列值
            String value = tuple.getSecond();
            sql.append(key);
            valuesSql.append(APO_STROPHE + value + APO_STROPHE);
            if (i != size - 1) {
                sql.append(",");
                valuesSql.append(",");
            }
        }
        sql.append(")");
        valuesSql.append(")");
        sql.append(SPACE).append(VALUES).append(SPACE).append(valuesSql);
        return sql.toString();
    }

    /**
     * 字段串为空时替换
     *
     * @param source 源字符串
     * @return 替换值
     */
    private static String replaceIfNull(String source) {
        return StringUtils.isEmpty(source) ? "0" : source;
    }



}
