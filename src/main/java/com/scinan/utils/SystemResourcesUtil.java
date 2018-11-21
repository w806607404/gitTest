package com.scinan.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.scinan.iot.ddeddo.dao.domain.Resource;

/**
 * 封装菜单资源工具类
 * 
 * @project datacenter
 * @class com.scinan.utils.TreeUtil
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月11日
 * @description
 */
public class SystemResourcesUtil {
	
	List<Resource> returnList = new ArrayList<Resource>();
	
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list 分类表
	 * @param typeId 传入的父节点ID
	 * @return String
	 */
	public List<Resource> getChildTreeObjects(List<Resource> list,int praentId) {
		List<Resource> returnList = new ArrayList<Resource>();
		for (Iterator<Resource> iterator = list.iterator(); iterator.hasNext();) {
			Resource t = (Resource) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParent_id()==praentId) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * 递归列表
	 * @param list
	 * @param t
	 */
	private void  recursionFn(List<Resource> list, Resource t) {
		List<Resource> childList = getChildList(list, t);// 得到子节点列表
		t.setNodes(childList);
		List<String> list1 = new ArrayList<String>();
		list1.add(childList.size() + "");
		t.setTags(list1);
		for (Resource tChild : childList) {
			if (hasChild(list, tChild)) {// 判断是否有子节点
				//returnList.add(TreeObject);
				Iterator<Resource> it = childList.iterator();
				while (it.hasNext()) {
					Resource n = (Resource) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	/**
	 * 得到子节点列表
	 * @param list 所有菜单
	 * @param t 父菜单
	 * @return
	 */
	private List<Resource> getChildList(List<Resource> list, Resource t) {
		
		List<Resource> tlist = new ArrayList<Resource>();
		Iterator<Resource> it = list.iterator();
		while (it.hasNext()) {
			Resource n = (Resource) it.next();
			if (n.getParent_id().longValue() == t.getRes_id().longValue()) {
				tlist.add(n);
			}
		}
		return tlist;
	} 
	
	
	
	/**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<Resource> getChildTreeObjects(List<Resource> list, int typeId,String prefix){
        if(list == null) return null;
        for (Iterator<Resource> iterator = list.iterator(); iterator.hasNext();) {
        	Resource node = (Resource) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParent_id()==typeId) {
                recursionFn(list, node,prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }
     
   /**
    * 递归列表 
    * @param list
    * @param node
    * @param p 递归给子菜单加空格  
    * 
    * 示例：
    * 
    * 一级菜单
    * 	二级菜单
    * 		三级菜单
    */
    private void recursionFn(List<Resource> list, Resource node,String p) {
        List<Resource> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node);
            Iterator<Resource> it = childList.iterator();
            while (it.hasNext()) {
            	Resource n = (Resource) it.next();
                n.setRes_name(p+n.getRes_name());
                recursionFn(list, n,p+p);
            }
        } else {
            returnList.add(node);
        }
    }

	/**
	 * 判断是否有子节点
	 * @param list
	 * @param t
	 * @return
	 */
	private boolean hasChild(List<Resource> list, Resource t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
	
	// 本地模拟数据测试
	public void main(String[] args) {
		/*long start = System.currentTimeMillis();
		List<TreeObject> TreeObjectList = new ArrayList<TreeObject>();
		
		TreeObjectUtil mt = new TreeObjectUtil();
		List<TreeObject> ns=mt.getChildTreeObjects(TreeObjectList,0);
		for (TreeObject m : ns) {
			System.out.println(m.getName());
			System.out.println(m.getChildren());
		}
		long end = System.currentTimeMillis();
		System.out.println("用时:" + (end - start) + "ms");*/
	}
	
}
