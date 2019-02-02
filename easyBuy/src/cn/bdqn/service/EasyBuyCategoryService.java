package cn.bdqn.service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.bdqn.dao.EasyBuyCategoryDao;
import cn.bdqn.entity.EasyBuyCategory;
import cn.bdqn.util.PageBean;



public class EasyBuyCategoryService {
	private EasyBuyCategoryDao easyBuyCategory=new EasyBuyCategoryDao();
	
	public Map<EasyBuyCategory, List<EasyBuyCategory>>findAllCategory(){
		Map<EasyBuyCategory, List<EasyBuyCategory>> map=
				new LinkedHashMap<EasyBuyCategory, List<EasyBuyCategory>>();
		List<EasyBuyCategory> categoryList=easyBuyCategory.findAllCategory();
		for(EasyBuyCategory category:categoryList){
			List<EasyBuyCategory> list=easyBuyCategory.findAllCategory(category.getParentId());
			map.put(category, list);
		}
		return map;
	}
	
	public int addChildCat(String className, Integer paretId) {
		// TODO Auto-generated method stub
		return easyBuyCategory.addChildCat(className,paretId);
	}

	public int addParenCat(String parentNew, int parentId) {
		// TODO Auto-generated method stub
		return easyBuyCategory.addParenCat(parentNew,parentId);
	}

	public int findCount() {
		// TODO Auto-generated method stub
		return easyBuyCategory.findCount();
	}

	public PageBean<EasyBuyCategory> findByPage(int pageNo, int pageSize) {
		PageBean<EasyBuyCategory> pageBean=new PageBean<EasyBuyCategory>();
		pageBean.setPageSize(pageSize);
		int totalCount=easyBuyCategory.getTotalCount();
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		List<EasyBuyCategory> pageList=easyBuyCategory.findPage(pageBean.getPageNo(),pageBean.getPageSize());
		pageBean.setPageList(pageList);
		return pageBean;
	}

	public EasyBuyCategory findById(int epcId) {
		// TODO Auto-generated method stub
		return easyBuyCategory.findById(epcId);
	}

	public int upateCategory(EasyBuyCategory upateCategory) {
		// TODO Auto-generated method stub
		return easyBuyCategory.upateCategory(upateCategory);
	}

	public int delCategory(int epcId) {
		// TODO Auto-generated method stub
		return easyBuyCategory.delCategory(epcId);
	}


}
