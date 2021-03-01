package com.microservices.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("test_table")
public class TestTable {

    @PrimaryKeyColumn(name = "col0", ordinal =  0, type = PrimaryKeyType.PARTITIONED)
    private String col0;
    @PrimaryKeyColumn(name = "col1", ordinal =  0, type = PrimaryKeyType.PARTITIONED)
    private String col1;
    @Column("col2")
    private String col2;
    @Column("col3")
    private String col3;

    public String getCol0() {
        return col0;
    }

    public void setCol0(String col0) {
        this.col0 = col0;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }
}
