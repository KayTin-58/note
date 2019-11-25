package com.tool;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * description
 * </p>
 *
 * @author BO.ZHANG04@hand-china.com 2018/06/07 13:48
 */
public class Main {

    public static final String CREATE_TABLE = "CREATE TABLE %s";
    public static final String DOUBLE_QUOTES = "\"";
    public static final String APO_STROPHE = "\'";
    public static final String MYSQL_COLUMN_BASE = "%s %s default %s %s comment %s";
    public static final String HIVE_COLUMN_BASE = "%s %s comment %s";
    public static final String CREATE_PK_SQL = "id int primary key auto_increment,";
    public static final String PARTITIONED_BY = "PARTITIONED BY (";
    public static final String ROW_FORMAT = "ROW FORMAT %s";
    public static final String SPACE = " ";
    public static final String INSERT_INTO = "insert into %s";
    public static final String INSERT_OVERWRITE = "insert overwrite %s";
    public static final String AIM_TABLE = "%s.%s";
    public static final String VALUES = "VALUES";
    public static final String TYPE_OF_DATA = "%s (%s)";
    public static final String STORED_AS = "stored as %s";

    public static void main(String[] args) {
        List<Tuple<String, String>> values = new ArrayList<>();
        Tuple tuple = new Tuple();
        tuple.setFirst("name");
        tuple.setSecond("张三");
        values.add(tuple);
        Tuple tuple1 = new Tuple();
        tuple1.setFirst("adder");
        tuple1.setSecond("广州");
        values.add(tuple1);

        System.out.println(SqlUtils.structureInsertSql(DataBaseType.HIVE, "TEST", "test", values));

        System.out.println(String.format(TYPE_OF_DATA, "int", 3));

        String a = null;
        System.out.println(String.valueOf(a));


        StringUtils.isEmpty(a);

        List<Column> columns = new ArrayList<>();

        Column column = new Column();
        column.setColName("name");
        column.setTypeName(SqlDataType.BIG_INT.getType());
        column.setColSize(3);
        column.setNullAble("not null");
        column.setRemarks("姓名");
        columns.add(column);

        Column column1 = new Column();
        column1.setColName("addre");
        column1.setTypeName(SqlDataType.VARCHAR.getType());
        column1.setColSize(64);
        column1.setNullAble("null");
        column1.setRemarks("北京");
        columns.add(column1);


        List<Column> par = new ArrayList<>();
        Column column2 = new Column();
        column2.setColName("year");
        column2.setTypeName(SqlDataType.STRING.getType());
        par.add(column2);


        System.out.println(SqlUtils.structureCreateSql(DataBaseType.HIVE, "Person", columns, true, par, null));
        System.out.println(structureCreateSql("test", columns));

    }


    /**
     * 字段串为空时替换
     *
     * @param source 源字符串
     * @return 替换值
     */
    public static String replaceIfNull(String source) {
        return StringUtils.isEmpty(source) ? "-99999" : source;
    }

    public static String structureCreateSql(String tableName, List<Column> columns) {
        StringBuilder sql = null;
        if (!columns.isEmpty()) {
            sql = new StringBuilder();
            sql.append(CREATE_PK_SQL);
            sql.append(String.format(CREATE_TABLE, tableName)).append("(");
            int size = columns.size();
            Column column;
            for (int i = 0; i < size; i++) {
                column = columns.get(i);
                String typeOfData = column.getTypeName();
                // varchar和char需要指定长度
                if (SqlDataType.VARCHAR.getType().equals(typeOfData) || SqlDataType.CHAR.getType().equals(typeOfData)) {
                    typeOfData = String.format(TYPE_OF_DATA, column.getTypeName(), column.getColSize());
                }
                sql.append(String.format(MYSQL_COLUMN_BASE, column.getColName(), typeOfData,
                                DOUBLE_QUOTES + replaceIfNull(column.getColDef()) + DOUBLE_QUOTES, column.getNullAble(),
                                APO_STROPHE + column.getRemarks() + APO_STROPHE));
                if (i != size - 1) {
                    sql.append(",");
                }
            }
            sql.append(")");
            return sql.toString();
        }
        return null;
    }
}
