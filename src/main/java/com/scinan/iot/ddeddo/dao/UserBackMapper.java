package com.scinan.iot.ddeddo.dao;

import com.scinan.iot.ddeddo.dao.domain.UserBack;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：用户账户分成比例
 * @author vinson
 *
 */
public interface UserBackMapper extends GenericMapper<UserBack, Long> {
    
	int deleteByUser_id(Long user_id);
}
