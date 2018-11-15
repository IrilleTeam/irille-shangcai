package com.shangcai.service.design.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irille.core.controller.PageView;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.dao.common.ICategoryDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.dao.common.IWorksDao;
import com.shangcai.dao.design.IArticleDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Works;
import com.shangcai.entity.common.Works.Status;
import com.shangcai.entity.common.Works.Type;
import com.shangcai.entity.design.Article;
import com.shangcai.service.design.IArticleService;
import com.shangcai.view.common.WorksView;

import irille.pub.Log;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	private IArticleDao articleDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IWorksDao worksDao;
	
	public static final Log LOG = new Log(ArticleService.class);
	/**
	 * 获取设计师作品列表
	 */
	@Override
	public  PageView<WorksView> designerWorksList(Integer pkey,Integer status, Integer start, Integer limit, String name) {
		
		List<WorksView> viewList=articleDao.list(pkey, status, start, limit, name);
	/*	List<Category> categoryList=CategoryDao.list();
		List<WorksView> viewList=new ArrayList<WorksView>();
		List<WorksView> secondviewList=new ArrayList<WorksView>();
		List<Member>  usrList=MemberDao.list();
			 for (Member member : usrList) {
				 for (WorksView worksView : oldViewList){
				   if(member.getPkey()!=null&&member.getPkey().intValue()==Integer.valueOf(worksView.getPublisher())){
				     worksView.setPublisher(member.getName());
				     viewList.add(worksView);
				   }
				 }
		     }
			 for (WorksView works : viewList) {
				 for (Category Category : categoryList) {
					 if (Category.getPkey()!=null&&Integer.valueOf(works.getCategory())==Category.getPkey()) {
						works.setCategory(Category.getName());
						secondviewList.add(works);
					 }
				}
			}*/
		PageView<WorksView> pageViews=new PageView<>(articleDao.getArticleList().size(), viewList);
		return pageViews;
	}
	
	@Override
	public void publish(Integer memberId, Integer categoryId, String title, String description, String pictures) {
		Category category = categoryDao.get(categoryId);
		if(category == null || category.gtType() != Category.Type.designer)
			throw new WebMessageException(ReturnCode.valid_illegal, "分类不存在");
		Member member = memberDao.get(memberId);
		if(member == null)
			throw new WebMessageException(ReturnCode.valid_illegal, "用户不存在");
		if(title==null||title.trim().isEmpty())
			throw new WebMessageException(ReturnCode.valid_notblank, "标题不能为空");
		if(description==null||description.trim().isEmpty())
			throw new WebMessageException(ReturnCode.valid_notblank, "说明不能为空");
		if(pictures==null||pictures.trim().isEmpty())
			throw new WebMessageException(ReturnCode.valid_notblank, "请至少上传一张图片");
		
		Works work = new Works();
		work.init();
		work.stType(Type.article);
		work.setTitle(title);
		work.setCoverPic(pictures.split(",")[0]);
		work.stStatus(Status.confirm);
		work.setMember(member.getPkey());
		work.setMemberName(member.getName());
		work.setCategroy(category.getPkey());
		work.setCreatedTime(new Date());
		worksDao.add(work);
		
		Article article = new Article();
		article.setWorks(work.getPkey());
		article.setPictures(pictures);
		articleDao.add(article);
	}
}
