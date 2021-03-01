package com.microservices.model;

import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user_token")
public class JwtToken {
    @PrimaryKeyColumn(name ="id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.UUID)
    private Uuids id;
    private String token;

    public Uuids getId() {
        return id;
    }

    public void setId(Uuids id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
