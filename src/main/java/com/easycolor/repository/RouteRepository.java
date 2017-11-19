package com.easycolor.repository;

import com.easycolor.domain.RouteEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface RouteRepository extends CassandraRepository<RouteEntity> {

//    Iterable<RouteEntity> findAllByRouteOriginAndRouteDestination(Integer origin, Integer destination);

}
