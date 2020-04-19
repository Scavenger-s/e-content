package com.cusx.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cusx.commons.utils.E3Result;
import com.cusx.content.service.ContentService;
import com.cusx.mapper.TbContentMapper;
import com.cusx.pojo.TbContent;
import com.cusx.pojo.TbContentExample;
import com.cusx.pojo.TbContentExample.Criteria;
/**
 * 内容管理
 * @author Scavengers
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	
	public E3Result addContent(TbContent content) {
		// 将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		// 插入到数据库
		contentMapper.insert(content);
		return E3Result.ok();
	}
    /**
     * 根据内容id查询内容列表
     */
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}

}
