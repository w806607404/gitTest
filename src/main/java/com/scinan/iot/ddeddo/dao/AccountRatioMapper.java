package com.scinan.iot.ddeddo.dao;

import com.scinan.iot.ddeddo.dao.domain.AccountRatio;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：用户账户分成比例
 * @author vinson
 *
 */
public interface AccountRatioMapper extends GenericMapper<AccountRatio, Long> {

	
	AccountRatio fetchByDevice_type(Long device_type);
    
    
    
    
    
}
