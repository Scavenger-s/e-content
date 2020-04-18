package com.cusx.content.service;

import java.util.List;

import com.cusx.commons.pojo.EasyUITreeNode;
import com.cusx.commons.utils.E3Result;

public interface ContentCategoryService {
	List<EasyUITreeNode> getContentCatList(long parentId);
	E3Result addContentCategory(long parentId,String name);
}
