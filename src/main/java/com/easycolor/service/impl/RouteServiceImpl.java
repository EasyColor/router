package com.easycolor.service.impl;

import com.easycolor.domain.RouteEntity;
import com.easycolor.mapper.RouteMapper;
import com.easycolor.repository.RouteRepository;
import com.easycolor.web.presentation.RoutePresentation;
import com.easycolor.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<RoutePresentation> getRoute(Integer origin, Integer destination) {
        List<RoutePresentation> list = new ArrayList<>();
//        for (RouteEntity routeEntity : routeRepository.findAllByRouteOriginAndRouteDestination(origin, destination)) {
        for (RouteEntity routeEntity : routeRepository.findAll()) {
            RoutePresentation routePresentation = RouteMapper.toPresentation(routeEntity);
            list.add(routePresentation);
        }
        return list;
    }

}
