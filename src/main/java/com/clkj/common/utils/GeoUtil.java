package com.clkj.common.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * 经纬度计算 工具类
 * Created by Jojo on 2017/12/21 17:03.
 */
public class GeoUtil {

    /**
     * 对比百度地图，计算结果和Sphere坐标系计算结果一致，
     * WGS84坐标系的计算结果存在几十米的误差。
     * 不同坐标系精度不同，结果不一样
     *
     * @param sourceLat 源纬度
     * @param sourceLng 源经度
     * @param targetLat 目标纬度
     * @param targetLng 目标经度
     * @return
     */
    public static double getDistance(double sourceLat, double sourceLng, double targetLat, double targetLng) {
        GlobalCoordinates gpsFrom = new GlobalCoordinates(sourceLat, sourceLng);
        GlobalCoordinates gpsTo = new GlobalCoordinates(targetLat, targetLng);
        //坐标系：Ellipsoid.Sphere Ellipsoid.WGS84
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }

}
