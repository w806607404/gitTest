package com.scinan.iot.ddeddo.dao;

import java.util.List;
import java.util.Map;

import com.scinan.iot.ddeddo.dao.domain.Purchase;
import com.scinan.iot.ddeddo.dao.domain.PurchaseBean;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明:购货操作
 * @author vinson
 *
 */
public interface PurchaseMapper extends GenericMapper<PurchaseBean, Long> {

    List<PurchaseBean> fetchByPurchasePage(Map<String,Object>  map);
    
    int updateStatus(Purchase purchase);
    
    Integer countByTransfer(Long user_id);
    
    int deleteByUser_id(Long user_id);
    
}
