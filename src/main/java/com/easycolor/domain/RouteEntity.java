package com.easycolor.domain;

import com.datastax.driver.mapping.EnumType;
import com.datastax.driver.mapping.annotations.Enumerated;
import com.easycolor.mapper.RouteColor;
import lombok.Builder;
import lombok.Data;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Builder
@Data
@Table(value = RouteEntity.TABLE_NAME)
public class RouteEntity {

    public static final String TABLE_NAME = "route";
    public static final String ROUTE_ORIGIN_COLUMN = "route_origin";
    public static final String ROUTE_DESTINATION_COLUMN = "route_destination_column";
    public static final String COLOR_FIRST_COLUMN = "first_color_column";
    public static final String COLOR_SECOND_COLUMN = "second_color_column";
    public static final String COLOR_THIRD_COLUMN = "third_color_column";
    public static final String ASSIGNED_COLUMN = "assigned_column";
    public static final String CAPACITY_COLUMN = "capacity_column";

    @PrimaryKeyColumn(
            name = ROUTE_ORIGIN_COLUMN,
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED
    )
    private Integer routeOrigin;

    @PrimaryKeyColumn(
            name = ROUTE_DESTINATION_COLUMN,
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED
    )
    private Integer routeDestination;

    @PrimaryKeyColumn(
            name = COLOR_FIRST_COLUMN,
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED
    )
    @Enumerated(EnumType.STRING)
    private RouteColor firstColor;

    @PrimaryKeyColumn(
            name = COLOR_SECOND_COLUMN,
            ordinal = 3,
            type = PrimaryKeyType.CLUSTERED
    )
    @Enumerated(EnumType.STRING)
    private RouteColor secondColor;

    @PrimaryKeyColumn(
            name = COLOR_THIRD_COLUMN,
            ordinal = 4,
            type = PrimaryKeyType.CLUSTERED
    )
    @Enumerated(EnumType.STRING)
    private RouteColor thirdColor;

    @Column(value = ASSIGNED_COLUMN)
    private Integer assigned;

    @Column(value = CAPACITY_COLUMN)
    private Integer capacity;

}
