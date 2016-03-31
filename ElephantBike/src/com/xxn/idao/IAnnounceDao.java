package com.xxn.idao;

import java.util.Map;

/**
 * 
* @ClassName: IAnnounceDao 
* @Description: 内容管理公告类 
* @author kunsen-lee
* @date 2016年3月31日 上午11:41:54 
*
 */
public interface IAnnounceDao {
	public int updateAnnounce(Map<String, String> val, Map<String, String> query);
}
