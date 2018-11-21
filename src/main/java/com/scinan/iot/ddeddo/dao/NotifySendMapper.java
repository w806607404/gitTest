package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.NotifySend;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：发货通知
 * @author vinson
 *
 */
public interface NotifySendMapper extends GenericMapper<NotifySend, Long> {
    
    List<NotifySend> fetchBySubsidyAmountPage (Map<String,Object>  map);
    
    Integer countByTransfer(Long user_id);
    
    int deleteByUser_id(Long user_id);
 }
