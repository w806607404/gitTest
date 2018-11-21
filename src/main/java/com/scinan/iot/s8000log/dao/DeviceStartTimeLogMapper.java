package com.scinan.iot.s8000log.dao;

import com.scinan.iot.s8000log.dao.domain.DeviceStartTimeLog;
import com.scinan.mybatis.support.mapper.GenericMapper;

import java.util.Map;

/**
 * DeviceOnlineTimeLogDao接口类
 * @author vinson
 *
 */
public interface DeviceStartTimeLogMapper extends GenericMapper<DeviceStartTimeLog, Long> {

    Integer countByMouth(Map<String, Object> params);

}
