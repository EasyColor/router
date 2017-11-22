package com.easycolor.repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.easycolor.domain.RouteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RouteRepository {

    @Autowired
    private CassandraOperations cassandraTemplate;

    public RouteEntity findByRouteOriginAndRouteDestination(Integer origin, Integer destination) {
        Statement select = QueryBuilder.select()
                .from(RouteEntity.TABLE_NAME)
                .where(QueryBuilder.eq( RouteEntity.ROUTE_ORIGIN_COLUMN, origin))
                .and(QueryBuilder.eq( RouteEntity.ROUTE_DESTINATION_COLUMN, destination))
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        final List<RouteEntity> routeEntities = cassandraTemplate.select(select.toString(), RouteEntity.class);
        RouteEntity routeEntity = null;
        if (!routeEntities.isEmpty()) {
            final Optional<RouteEntity> routeEntityOptional = routeEntities
                    .stream()
                    .filter(route -> route.getAssigned() < route.getCapacity())
                    .findFirst();
            if (routeEntityOptional.isPresent()) {
                routeEntity = routeEntityOptional.get();
            }
            else {
                final Optional<RouteEntity> routeMaxOptional = routeEntities.stream()
                        .max(Comparator.comparing(RouteEntity::getCapacity));
                if (routeMaxOptional.isPresent()){
                    routeEntity = routeMaxOptional
                            .get();
                }
            }
        }
        if (routeEntity != null) {
            routeEntity.setAssigned(routeEntity.getAssigned() + 1);
            cassandraTemplate.update(routeEntity);
        }
        return routeEntity;
    }
}
