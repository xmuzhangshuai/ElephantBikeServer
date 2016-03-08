package com.xxn.idao;

import java.util.List;

import com.xxn.entity.College;

public interface ICollegeDao {
	/**
	 * 后台添加大学范围
	 * @param college
	 * @return
	 */
	public int addArea(College college);
	/**
	 * 根据大学名称获取大学的区域地址
	 * @param college
	 * @return
	 */
	public List<String> getAddrByCollege(College college);
}
