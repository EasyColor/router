package com.easycolor.store;

import com.easycolor.domain.RouteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class RouteStore {

    @Autowired
    private CassandraAdminTemplate cassandraTemplate;

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @PostConstruct
    public void cassandraConfig() {
        cassandraTemplate.createTable(true, CqlIdentifier.cqlId(RouteEntity.TABLE_NAME), RouteEntity.class, new HashMap<>());
    }
}
