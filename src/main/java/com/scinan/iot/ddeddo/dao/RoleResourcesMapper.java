package com.scinan.iot.ddeddo.dao;

import java.util.List;

import com.scinan.iot.ddeddo.dao.domain.RoleResources;
import com.scinan.mybatis.support.mapper.GenericMapper;


/**
 * 功能说明：用户角色信息
 * @author Kim
 *
 */
public interface RoleResourcesMapper extends GenericMapper<RoleResources, Integer> {

	void deleteByRoleId(Long roleId);

	void batchSave(List<RoleResources> vtlRoleResources);

	void deleteByRoleIdList(List<String> strSplitToList);

}
