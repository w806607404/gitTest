package com.scinan.iot.ddeddo.dao;

import com.scinan.iot.ddeddo.dao.domain.BillCloseDetail;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：申请体现结算
 * @author vinson
 *
 */
public interface BillCloseDetailMapper extends GenericMapper<BillCloseDetail, Long> {
	int deleteByUser_id(Long user_id);
	 Integer countByTransfer(Long user_id);
}
