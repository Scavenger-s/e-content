package com.cusx.content.service;

import java.util.List;

import com.cusx.commons.utils.E3Result;
import com.cusx.pojo.TbContent;

public interface ContentService {
	E3Result addContent(TbContent content);
	
	List<TbContent> getContentListByCid(long cid);
}
