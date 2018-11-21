package com.scinan.map;
/**
 *  百度坐标
 * @author admin
 *
 */
public class BDPointer extends GeoPointer {
  public final static double BD_PI = 3.14159265358979324 * 3000.0 / 180.0; 
  public BDPointer() {}
  
  /**
   * @param latitude 维度
   * @param longitude 经度
   */
  public BDPointer(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  /**
   * BD------>GCJ坐标转换
   * @return
   */
  public GCJPointer toGCJPointer() {
	  double x = this.longitude - 0.0065, y = this.latitude - 0.006;  
	  double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * BD_PI);  
	  double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * BD_PI);  
	  double gcj_lon = z * Math.cos(theta);  
	  double gcj_lat = z * Math.sin(theta);  
	  return new GCJPointer(gcj_lat, gcj_lon);
  }
  
  /**
   * BD------>GCJ坐标转换
   * @return
   */
  public WGSPointer toWGSPointer() {
	  double x = this.longitude - 0.0065, y = this.latitude - 0.006;  
	  double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * BD_PI);  
	  double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * BD_PI);  
	  double gcj_lon = z * Math.cos(theta);  
	  double gcj_lat = z * Math.sin(theta);  
	  return new GCJPointer(gcj_lat, gcj_lon).toWGSPointer();
  }
  
  /**
   * BD------>GCJ坐标转换
   * @return
   */
  public WGSPointer toExactWGSPointer() {
	  double x = this.longitude - 0.0065, y = this.latitude - 0.006;  
	  double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * BD_PI);  
	  double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * BD_PI);  
	  double gcj_lon = z * Math.cos(theta);  
	  double gcj_lat = z * Math.sin(theta);  
	  return new GCJPointer(gcj_lat, gcj_lon).toExactWGSPointer();
  }
}
