package com.easycolor.mapper;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum RouteColor {

    RED(255, 0, 0, "red"), GREEN(0, 255, 0, "green"), BLUE(0, 0, 255, "blue"), ORANGE(255, 102, 0, "orange");

    final Integer red;
    final Integer green;
    final Integer blue;
    final String name;

    RouteColor(int red, int green, int blue, String name) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public static List<Integer> getColors(String routeColorString) {
        final Optional<RouteColor> routeColor = Arrays.stream(RouteColor.values())
                .filter(color -> color.name.equals(routeColorString))
                .findAny();
        return routeColor.map(routeColor1 -> Arrays.asList(routeColor1.red, routeColor1.green, routeColor1.blue))
                .orElseGet(Collections::emptyList);
    }

}
