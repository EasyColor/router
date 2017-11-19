package com.easycolor.mapper;

import java.util.Arrays;
import java.util.List;

public enum RouteColor {

    RED(255, 0, 0), BLUE(0, 0, 255);

    final Integer red;
    final Integer green;
    final Integer blue;

    RouteColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public List<Integer> getColors() {
        return Arrays.asList(red, green, blue);
    }
}
