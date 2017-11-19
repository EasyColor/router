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
                .firstColor(routeEntity.getFirstColor().getColors())
                .secondColor(routeEntity.getSecondColor().getColors())
                .thirdColor(routeEntity.getThirdColor().getColors())
                .assigned(routeEntity.getAssigned())
                .capacity(routeEntity.getCapacity())
                .build();
    }

}
