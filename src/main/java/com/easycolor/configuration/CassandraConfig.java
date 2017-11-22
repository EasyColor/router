package com.easycolor.configuration;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.policies.ExponentialReconnectionPolicy;
import com.datastax.driver.core.policies.ReconnectionPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource("classpath:cassandra.properties")
@Slf4j
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${cassandra.contactpoints}")
    private String contactPoints;

    @Value("${cassandra.username}")
    private String username;

    @Value("${cassandra.password}")
    private String password;

    @Value("${cassandra.port}")
    private int port;

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Override
    public CassandraAdminTemplate cassandraTemplate() throws Exception {
        return new CassandraAdminTemplate(session().getObject(), cassandraConverter());
    }

    @PostConstruct
    public void logCassandraConfig() {
        log.info("Creating cluster with following parameters : ");
        log.info("cassandra.contactpoints : " + contactPoints);
        log.info("cassandra.port : " + port);
        log.info("cassandra.keyspace : " + keyspace);
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected AuthProvider getAuthProvider() {
        return new PlainTextAuthProvider(username, password);
    }

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected PoolingOptions getPoolingOptions() {
        return new PoolingOptions()
                .setCoreConnectionsPerHost(HostDistance.LOCAL, 4)
                .setMaxConnectionsPerHost(HostDistance.LOCAL, 10)
                .setCoreConnectionsPerHost(HostDistance.REMOTE, 2)
                .setMaxConnectionsPerHost(HostDistance.REMOTE, 4);
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        final List<CreateKeyspaceSpecification> specifications = new ArrayList<>();

        final CreateKeyspaceSpecification spec =
                CreateKeyspaceSpecification.createKeyspace(keyspace)
                        .withSimpleReplication(1)
                        .ifNotExists();
        specifications.add(spec);
        return specifications;
    }

    @Override
    protected ReconnectionPolicy getReconnectionPolicy() {
        return new ExponentialReconnectionPolicy(1000, 30000);
    }

}
