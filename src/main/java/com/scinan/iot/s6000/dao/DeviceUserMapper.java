package com.scinan.iot.s6000.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scinan.iot.s6000.dao.domain.DeviceUser;
import com.scinan.mybatis.support.mapper.GenericMapper;

public interface DeviceUserMapper extends GenericMapper<DeviceUser, String> {
	public List<DeviceUser> fetchDeviceListByUserId(Long user_id);
	public List<DeviceUser> fetchListByUserIdCompany(DeviceUser bean);
	
	/**
	 * 获取 device-user 数据
	 * @param device_id
	 * @param user_id
	 * @return
	 */
	public DeviceUser fectchDeviceUserByPk(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);
	/**
	 * 检查 device-user 数据是否存在
	 * @param device_id
	 * @param user_id
	 * @return
	 */
	public int isDeviceUserExists(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);
	public int checkDeviceExist(String device_id);
	/**
	 * 设置 user 为设备的主授权模式
	 * @param id
	 * @param user_id
	 */
	public void update2Master(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);
	/**
	 * 设置所有 user 为设备的从授权模式
	 * @param device_id
	 */
	public void update2Slave(@Param(value="device_id") String device_id);
	
	
	/**
	 * <一句话功能简述>修改用户设备所绑定的操作密码
	 * <功能详细描述>
	 * @param device_id
	 * @param user_id
	 * @param pwd
	 * @see [类、类#方法、类#成员]
	 */
    public void updateOperPwd(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id,@Param(value="pwd") String pwd);
	
    
    /**
     * <一句话功能简述>更新设备所对应的用户操作密码
     * <功能详细描述>
     * @param device_id
     * @param pwd
     * @see [类、类#方法、类#成员]
     */
    public void updateDevicePwdIsNull(@Param(value="device_id") String device_id,@Param(value="pwd") String pwd);
    
	/**
	 * 删除分享 device-user 数据
	 * @param device_id
	 * @param user_id
	 */
	public void deleteDeviceUser(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);
	
	/**
     * 解绑 device-user及分享的数据
     * @param device_id
     * @param user_id
     */
    public void deleteUnbundlingDeviceUser(@Param(value="device_id") String device_id, @Param(value="user_id") Long target_user_id, @Param(value="m_user_id") Long user_id);
	
	
	
	/**
	 * 获取从授权设备列表
	 * @param device_id
	 * @param user_id
	 * @return
	 */
	public List<DeviceUser> fetchDeviceListShare(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);
	
	
	
	/**
	 * <一句话功能简述>查询对应的设备信息
	 * <功能详细描述>
	 * @param device_id 设备编号
	 * @param user_id  目标用户
	 * @return 
	 * @see [类、类#方法、类#成员]
	 */
	public List<DeviceUser> fetchDeviceShareInfo(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);
	
	
	/**
     * 获取 device-user 密码数据信息
     * @param device_id
     * @param user_id
     * @return
     */
    public DeviceUser fectchDeviceUserByPwd(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);
	
	
    /**
     * <一句话功能简述>查询当前设备所对应的用户信息
     * <功能详细描述>
     * @param deviceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DeviceUser> listUserIdByDevice(@Param(value="device_id") String device_id, @Param(value="user_id") Long user_id);

    public List<DeviceUser> fetchShareDeviceList(@Param(value="user_id") Long user_id);
	public List<DeviceUser> fetchBoundUserList(String device_id);
	public void deleteByDeviceid(String device_id);
	
	/**
	 * 根据主授权获取 device-user 数据列表
	 * @param mstype
	 * @param user_id
	 * @return
	 */
	public List<DeviceUser> fetchByMstype(@Param(value="user_id") Long user_id, @Param(value="mstype") Integer mstype);
    
}
