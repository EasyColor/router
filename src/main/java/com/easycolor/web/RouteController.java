package com.easycolor.web;

import com.easycolor.web.presentation.RoutePresentation;
import com.easycolor.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Slf4j
public class RouteController {

    static final String GET_ROUTE_REQUEST = "/route/{origin}/{destination}";

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = GET_ROUTE_REQUEST, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoutePresentation>> getRoute(@PathVariable Integer origin, @PathVariable Integer destination) {
        log.debug("Entering get route({}, {})", origin, destination);
        return ResponseEntity.ok(routeService.getRoute(origin, destination));
    }

}
