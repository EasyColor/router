package com.easycolor.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

@Component
public class CassandraHealthIndicator extends AbstractHealthIndicator {

	@Autowired
	private CassandraOperations cassandra;

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		try {
			this.cassandra.execute("SELECT key FROM system.local");
			builder.up().build();
		} catch (DataAccessException ex) {
			builder.down(ex).build();
		}
	}

}
