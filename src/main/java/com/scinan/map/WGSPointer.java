package com.scinan.map;
/**
 *  原始GPS信息（WGS-84）
 * @author admin
 *
 */
public class WGSPointer extends GeoPointer {

  public WGSPointer() {}
  /**
   * @param latitude 维度
   * @param longitude 经度
   */
  public WGSPointer(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  /**
   * WGS------->GCJ 坐标转换
   * @return
   */
  public GCJPointer toGCJPointer() {
    if (TransformUtil.outOfChina(this.latitude, this.longitude)) {
      return new GCJPointer(this.latitude, this.longitude);
    }
    double[] delta = TransformUtil.delta(this.latitude, this.longitude);
    return new GCJPointer(this.latitude + delta[0], this.longitude + delta[1]);
  }
}
