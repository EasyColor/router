package com.easycolor.service.impl;

import com.easycolor.domain.RouteEntity;
import com.easycolor.mapper.RouteMapper;
import com.easycolor.repository.RouteRepository;
import com.easycolor.service.RouteService;
import com.easycolor.web.presentation.RoutePresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public RoutePresentation getRoute(Integer origin, Integer destination) {
        RouteEntity routeEntity = routeRepository.findByRouteOriginAndRouteDestination(origin, destination);
        return RouteMapper.toPresentation(routeEntity);
    }

}
