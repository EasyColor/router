package com.easycolor.mapper;

import com.easycolor.domain.RouteEntity;
import com.easycolor.web.presentation.RoutePresentation;

public class RouteMapper {

    private RouteMapper() {
        // Do nothing
    }

    public static RoutePresentation toPresentation(RouteEntity routeEntity) {
        return RoutePresentation.builder()
                .routeOrigin(routeEntity.getRouteOrigin())
                .routeDestination(routeEntity.getRouteDestination())
                .firstColor(RouteColor.getColors(routeEntity.getFirstColor()))
                .secondColor(RouteColor.getColors(routeEntity.getSecondColor()))
                .thirdColor(RouteColor.getColors(routeEntity.getThirdColor()))
                .assigned(routeEntity.getAssigned())
                .capacity(routeEntity.getCapacity())
                .build();
    }

}
