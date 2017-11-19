package com.easycolor.service;

import com.easycolor.web.presentation.RoutePresentation;

import java.util.List;

public interface RouteService {

    List<RoutePresentation> getRoute(Integer origin, Integer destination);

}
