package com.cusx.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cusx.commons.pojo.EasyUITreeNode;
import com.cusx.commons.utils.E3Result;
import com.cusx.content.service.ContentCategoryService;
import com.cusx.mapper.TbContentCategoryMapper;
import com.cusx.pojo.TbContentCategory;
import com.cusx.pojo.TbContentCategoryExample;
import com.cusx.pojo.TbContentCategoryExample.Criteria;
/**
 * 内容分类管理
 * @author Scavengers
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper ibContentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory>  catList = ibContentCategoryMapper.selectByExample(example);
		//转换成EasyUITreeNode的列表
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : catList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			// 添加到列表
			nodeList.add(node);
		}
		return nodeList;
		
	}
	@Override
	public E3Result addContentCategory(long parentId, String name) {
		//创建一个tb_content_category表对应的pojo对象
				TbContentCategory contentCategory = new TbContentCategory();
				//设置pojo的属性
				contentCategory.setParentId(parentId);
				contentCategory.setName(name);
				//1(正常),2(删除)
				contentCategory.setStatus(1);
				//默认排序就是1
				contentCategory.setSortOrder(1);
				//新添加的节点一定是叶子节点
				contentCategory.setIsParent(false);
				contentCategory.setCreated(new Date());
				contentCategory.setUpdated(new Date());
				//插入到数据库
				ibContentCategoryMapper.insert(contentCategory);
				//判断父节点的isparent属性。如果不是true改为true
				//根据parentid查询父节点
				TbContentCategory parent = ibContentCategoryMapper.selectByPrimaryKey(parentId);
				if (!parent.getIsParent()) {
					parent.setIsParent(true);
					//更新到数数据库
					ibContentCategoryMapper.updateByPrimaryKey(parent);
				}
				//返回结果，返回E3Result，包含pojo
				return E3Result.ok(contentCategory);
		
	}

}
