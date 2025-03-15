package com.BookYourCab.CarBookingApp.Utils;

import com.BookYourCab.CarBookingApp.Dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    public static Point createPoint(PointDto pointDto){
        if (pointDto == null || pointDto.getCoordinates() == null || pointDto.getCoordinates().length != 2) {
            throw new IllegalArgumentException("Invalid PointDto: " + pointDto);
        }
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0], pointDto.getCoordinates()[1]);

        Point point = geometryFactory.createPoint(coordinate);
        point.setSRID(4326);
        return point;
    }
}

