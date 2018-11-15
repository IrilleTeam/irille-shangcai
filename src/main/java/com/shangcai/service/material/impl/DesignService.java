package com.shangcai.service.material.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.irille.core.controller.JsonWriter;
import com.irille.core.controller.PageView;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.dao.common.ICategoryDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.dao.common.IWorksDao;
import com.shangcai.dao.material.IDesignDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Works;
import com.shangcai.entity.common.Works.Status;
import com.shangcai.entity.common.Works.Type;
import com.shangcai.entity.material.Design;
import com.shangcai.service.material.IDesignService;
import com.shangcai.view.common.WorksView;
import com.shangcai.view.material.ThemeView;

@Service
public class DesignService implements IDesignService {

	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IDesignDao designDao;
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IWorksDao worksDao;

	/**
	 * 获取企业作品列表
	 */
	@Override
	public PageView<WorksView> companyWorksList(Integer pkey, Integer status, Integer start, Integer limit, String name) {
		List<WorksView> viewList = designDao.list(pkey, status, start, limit, name);
	/*	List<Category> categoryList = CategoryDao.list();
		List<WorksView> viewList = new ArrayList<WorksView>();
		List<WorksView> secondviewList = new ArrayList<WorksView>();
		List<Member> usrList = MemberDao.list();
		for (Member member : usrList) {
			for (WorksView worksView : oldViewList) {
				if (member.getPkey() != null && member.getPkey().intValue() == Integer.valueOf(worksView.getPublisher())) {
					worksView.setPublisher(member.getName());
					viewList.add(worksView);
				}
			}
		}
		for (WorksView works : viewList) {
			for (Category Category : categoryList) {
				if (Category.getPkey() != null && Integer.valueOf(works.getCategory()) == Category.getPkey()) {
					works.setCategory(Category.getName());
					secondviewList.add(works);
				}
			}
		}*/
		PageView<WorksView> pageViews=new PageView<>(designDao.getDesignList().size(), viewList);
		return pageViews;
	}
	
	@Override
	public void publish(Integer memberId, Integer categoryId, String title, BigDecimal samplingPrice, List<ThemeView> themes) throws JsonProcessingException {
		Category category = categoryDao.get(categoryId);
		if(category == null || category.gtType() != Category.Type.company)
			throw new WebMessageException(ReturnCode.valid_illegal, "分类不存在");
		Member member = memberDao.get(memberId);
		if(member == null)
			throw new WebMessageException(ReturnCode.valid_illegal, "用户不存在");
		if(themes==null||themes.size()==0)
			throw new WebMessageException(ReturnCode.valid_notempty, "请补全内容");
		if(title==null||title.trim().isEmpty())
			throw new WebMessageException(ReturnCode.valid_notblank, "标题不能为空");
		String coverPic = null;
		for (int i = 0; i < themes.size(); i++) {
			ThemeView theme = themes.get(i);
			int picturesCount = theme.getPictures().split(",").length;
			if(theme.getPictures()==null && theme.getVideo()==null) {
				throw new WebMessageException(ReturnCode.valid_unknow, "请给主题添加图片或视频");
			} else if(theme.getPictures()!=null && theme.getVideo()!=null) {
				throw new WebMessageException(ReturnCode.valid_unknow, "一个主题不能同时上传图片和视频");
			}
			if(i == 0) {
				if(theme.getPictures() != null)
					coverPic = theme.getPictures().split(",")[0];
				else
					coverPic = theme.getVideo();
				if(picturesCount>5)
					throw new WebMessageException(ReturnCode.valid_unknow, "首页主题图片最多上传5张");
			} else {
				if(picturesCount>10)
					throw new WebMessageException(ReturnCode.valid_unknow, "副主题图片最多上传10张");
			}
			if(theme.getName().trim().isEmpty())
				throw new WebMessageException(ReturnCode.valid_notblank, "主题名不能为空");
			if(theme.getContent().trim().isEmpty())
				throw new WebMessageException(ReturnCode.valid_notblank, "主题内容不能为空");
		}
		if(samplingPrice==null)
			throw new WebMessageException(ReturnCode.valid_notnull, "取样价格不能为空");
		
		Works work = new Works();
		work.init();
		work.stType(Type.design);
		work.setTitle(title);
		work.setCoverPic(coverPic);
		work.stStatus(Status.confirm);
		work.setMember(member.getPkey());
		work.setMemberName(member.getName());
		work.setCategroy(category.getPkey());
		work.setCreatedTime(new Date());
		worksDao.add(work);
		
		Design design = new Design();
		design.setSamplingPrice(samplingPrice);
		design.setWorks(work.getPkey());
		design.setThemes(JsonWriter.oMapper.writeValueAsString(themes));
		designDao.add(design);
	}

}
