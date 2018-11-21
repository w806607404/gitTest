package com.scinan.map;
/**
 * 中国国家安全坐标体系（GCJ-02）火星系坐标
 * @author admin
 *
 */
public class GCJPointer extends GeoPointer {

  public GCJPointer() {}

  public GCJPointer(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  /**
   * GCJ --> WGS 坐标转换
   * @return
   */
  public WGSPointer toWGSPointer() {
    if (TransformUtil.outOfChina(this.latitude, this.longitude)) {
      return new WGSPointer(this.latitude, this.longitude);
    }
    double[] delta = TransformUtil.delta(this.latitude, this.longitude);
    return new WGSPointer(this.latitude - delta[0], this.longitude - delta[1]);
  }
  
  /**
   * GCJ --> BD 坐标转换
   * @return
   */
  public BDPointer toBDPointer() {
	double x = this.longitude, y = this.latitude;  
	double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * BDPointer.BD_PI);  
	double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * BDPointer.BD_PI);  
	double bd_lon = z * Math.cos(theta) + 0.0065;  
	double bd_lat = z * Math.sin(theta) + 0.006;  
	return new BDPointer(bd_lat, bd_lon);
  }
  
  /**
   * GCJ坐标精确的转换为WGS坐标
   * @return
   */
  public WGSPointer toExactWGSPointer() {
    final double initDelta = 0.01;
    final double threshold = 0.000001;
    double dLat = initDelta, dLng = initDelta;
    double mLat = this.latitude - dLat, mLng = this.longitude - dLng;
    double pLat = this.latitude + dLat, pLng = this.longitude + dLng;
    double wgsLat, wgsLng;
    WGSPointer currentWGSPointer = null;
    for (int i = 0; i < 30; i++) {
      wgsLat = (mLat + pLat) / 2;
      wgsLng = (mLng + pLng) / 2;
      currentWGSPointer = new WGSPointer(wgsLat, wgsLng);
      GCJPointer tmp = currentWGSPointer.toGCJPointer();
      dLat = tmp.getLatitude() - this.getLatitude();
      dLng = tmp.getLongitude() - this.getLongitude();
      if ((Math.abs(dLat) < threshold) && (Math.abs(dLng) < threshold)) {
        return currentWGSPointer;
      } else {
//        System.out.println(dLat + ":" + dLng);
      }
      if (dLat > 0) {
        pLat = wgsLat;
      } else {
        mLat = wgsLat;
      }
      if (dLng > 0) {
        pLng = wgsLng;
      } else {
        mLng = wgsLng;
      }
    }
    return currentWGSPointer;
  }
}
