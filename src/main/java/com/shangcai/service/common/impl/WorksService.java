package com.shangcai.service.common.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irille.core.controller.JsonWriter;
import com.irille.core.controller.PageView;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.dao.common.ICategoryDao;
import com.shangcai.dao.common.ICityDao;
import com.shangcai.dao.common.ICollectionDao;
import com.shangcai.dao.common.ILikesRelationDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.dao.common.IWorksDao;
import com.shangcai.dao.design.IArticleDao;
import com.shangcai.dao.material.IDesignDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Collection;
import com.shangcai.entity.common.LikesRelation;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Works;
import com.shangcai.entity.common.Works.Status;
import com.shangcai.entity.design.Article;
import com.shangcai.entity.material.Design;
import com.shangcai.service.common.IWorksService;
import com.shangcai.view.common.MemberView;
import com.shangcai.view.common.WorksView;
import com.shangcai.view.common.WorksView2;
import com.shangcai.view.design.ArticleView;
import com.shangcai.view.material.DesignView;
import com.shangcai.view.material.ThemeView;

import irille.pub.Log;

@Service
public class WorksService implements IWorksService {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksService.class);
	
	public static final Log LOG = new Log(WorksService.class);
	
	@Autowired
	private IWorksDao worksDao;
	@Autowired
	private IDesignDao designDao;
	@Autowired
	private IArticleDao articleDao;
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private ILikesRelationDao likesRelationDao;
	@Autowired
	private ICollectionDao collectionDao;
	@Autowired
	private ICityDao cityDao;
	
	@Override
	public PageView<WorksView> list(Integer pkey,Integer status, Integer start, Integer limit, String name) throws Exception {
		if (start==null)
			 throw LOG.err("nullErr", "【{0}】开始行编号不可为空","start");
		if (limit==null)
			 throw LOG.err("nullErr", "【{0}】每页显示行数不可为空","limit");
		List<WorksView> viewList=worksDao.list(pkey, start, limit, name, status);
/*		List<WorksView> viewList=new ArrayList<WorksView>();
		List<Member>  usrList=MemberDao.list();
			 for (Member member : usrList) {
				 for (WorksView worksView : oldViewList){
				   if(member.getPkey()!=null&&member.getPkey().intValue()==Integer.valueOf(worksView.getPublisher())){
				     worksView.setPublisher(member.getName());
				    viewList.add(worksView);
				   }
				 }
		     }
		     
*/		PageView<WorksView>pageView=new PageView<>(worksDao.worksList().size(),viewList) ;
		return pageView;
	}
	
	@Override
	public List<WorksView2> list(Member member, Integer memberId, boolean isPrivate, boolean isCollection, boolean isLikes, Integer start, Integer limit) {
		Byte status = null;
		Integer collection = null;
		Integer likes = null;
		if(isPrivate)
			memberId = member.getPkey();
		if(!isPrivate)
			status = Works.Status.confirm.getLine().getKey();
		if(isLikes)
			likes = member.getPkey();
		if(isCollection)
			collection = member.getPkey();
		List<Works> works = worksDao.list(memberId, collection, likes, status, start, limit);
		List<Category> categorys = categoryDao.list(null, false);
		Map<Integer, String> category_map = new HashMap<>();
		for (Category category : categorys) {
			category_map.put(category.getPkey(), category.getName());
		}
		return works.stream().map(work->new WorksView2() {{
			setPkey(work.getPkey());
			setType(work.getType());
			setPicture(work.getCoverPic());
			setTitle(work.getTitle());
			setCategory(category_map.get(work.getCategroy()));
			// TODO 数据库里的pvCount不是实时的 需要加上缓存里的pv数 才是完整的
			setPvCount(work.getPvCount());
			setCommentCount(work.getCommentCount());
			setLikesCount(work.getLikesCount());
			setCreatedTime(work.getCreatedTime());
			setName(work.getMemberName());
			
			Member member = memberDao.get(work.getMember());
			setMember(new MemberView() {{
				setPkey(member.getPkey());
				setName(member.getName());
				setHeadPic(member.getHeadPic());
			}});
			setStatus(work.getStatus());
		}}).collect(Collectors.toList());
	}

	@Override
	public WorksView2 get(Integer memberId, Integer worksId) throws JsonParseException, JsonMappingException, IOException {
		Works work = worksDao.get(worksId);
		if(work==null)
			throw new WebMessageException(ReturnCode.valid_illegal, "作品不存在");
		if(!work.gtStatus().equals(Status.confirm)&&memberId!=work.getMember())
			throw new WebMessageException(ReturnCode.valid_illegal, "作品已下架");
		
		WorksView2 view = null;
		
		switch (work.gtType()) {
		case design:
			view = new DesignView();
			DesignView designView = (DesignView)view;
			Design design = designDao.findByWorksId(work.getPkey());
			designView.setSamplingPrice(design.getSamplingPrice());
			List<ThemeView> themes = JsonWriter.oMapper.readValue(design.getThemes(), JsonWriter.oMapper.getTypeFactory().constructParametricType(ArrayList.class, ThemeView.class));
			designView.setTheme(themes);
			break;
		case article:
			view = new ArticleView();
			ArticleView articleView = (ArticleView)view;
			Article article = articleDao.findByWorksId(work.getPkey());
			articleView.setPrictures(article.getPictures());
			break;
		default:
			logger.error("作品[{}]的type字段数据异常", work.getPkey());
			throw new WebMessageException(ReturnCode.valid_illegal, "作品不存在");
		}
		//其它用户浏览时,增加作品的浏览量
		if(!work.getMember().equals(memberId)) {
			worksDao.incrementPv(worksId);
		}
		
		Collection collection = collectionDao.findByMemberWorks(memberId, worksId);
		view.setCollected(collection != null);
		view.setTitle(work.getTitle());
		view.setDescription(work.getDescription());
		view.setType(work.getType());
		view.setLikesCount(work.getLikesCount());
		view.setCommentCount(work.getCommentCount());
		view.setPvCount(work.getPvCount());
		
		Member member = memberDao.get(work.getMember());
		
		view.setMember(new MemberView() {{
			setPkey(memberId);
			setName(member.getName());
			setHeadPic(member.getHeadPic());
			setCity(cityDao.getView(member.getCity()));
		}});
		
		return view;
	}
     
	/**
	 * 审核作品
	 */
	@Override
	public void appar(Works works) {
		worksDao.appr(works);
	}
	   
	/**
	 * 下线作品
	*/
	@Override
	public void uappar(Works works) {
		worksDao.unappr(works);
	}

	@Override
	public void like(boolean isLike, Integer worksId, Integer memberId) {
		Works works = worksDao.get(worksId);
		if(works == null)
			throw new WebMessageException(ReturnCode.valid_illegal, "该作品不存在");
		if(isLike) {
			try {
				LikesRelation likesRelation = new LikesRelation().init();
				likesRelation.setWorks(worksId);
				likesRelation.setMember(memberId);
				likesRelation.setCreatedTime(new Date());
				likesRelationDao.add(likesRelation);
				//做冗余
				works.setLikesCount(works.getLikesCount()+1);
				worksDao.upd(works);
			} catch (Exception e) {
				throw new WebMessageException(ReturnCode.valid_illegal, "不能重复点赞");
			}
		} else {
			int count = likesRelationDao.deleteByMemberWorks(memberId, worksId);
			if(count > 0)
				works.setLikesCount(works.getLikesCount()-1);
			worksDao.upd(works);
		}
	}

	@Override
	public void collect(boolean isCollect, Integer worksId, Integer memberId) {
		Works works = worksDao.get(worksId);
		if(works == null)
			throw new WebMessageException(ReturnCode.valid_illegal, "该作品不存在");
		if(isCollect) {
			try {
				Collection collection = new Collection().init();
				collection.setWorks(worksId);
				collection.setMember(memberId);
				collection.setCreatedTime(new Date());
				collectionDao.add(collection);
			} catch (Exception e) {
				throw new WebMessageException(ReturnCode.valid_illegal, "不能重复收藏");
			}
		} else {
			collectionDao.deleteByMemberWorks(memberId, worksId);
		}
	}

}
