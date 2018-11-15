package com.shangcai.service.common.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.dao.common.ICommentDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.dao.common.IWorksDao;
import com.shangcai.entity.common.Comment;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Works;
import com.shangcai.service.common.ICommentService;
import com.shangcai.view.common.CommentView;
import com.shangcai.view.common.MemberView;

@Service
public class CommentService implements ICommentService {
	
	@Autowired
	private ICommentDao commentDao;
	@Autowired
	private IWorksDao worksDao;
	@Autowired
	private IMemberDao memberDao;

	@Override
	public void add(Integer memberId, Integer worksId, String content, Integer replyToId) {
		
		Comment comment = new Comment();
		comment.init();
		
		Works work = worksDao.get(worksId);
		if(work == null) 
			throw new WebMessageException(ReturnCode.valid_illegal, "作品不存在");
		comment.setWorks(worksId);
		if(replyToId!=null) {
			Member replyTo = memberDao.get(replyToId);
			if(replyTo==null)
				throw new WebMessageException(ReturnCode.valid_illegal, "被评论人不存在");
			comment.setReplyTo(replyTo.getPkey());
			comment.setReplyToName(replyTo.getName());
		}
		Member member = memberDao.get(memberId);
		comment.setMember(member.getPkey());
		comment.setMemberName(member.getName());
		comment.setMemberHeadPic(member.getHeadPic());
		
		if(content.trim().length()==0)
			throw new WebMessageException(ReturnCode.valid_notblank, "评论内容不能为空");
		comment.setContents(content);
		comment.setCreatedTime(new Date());
		
		commentDao.add(comment);
		
		work.setCommentCount(work.getCommentCount()+1);
		worksDao.upd(work);
	}

	@Override
	public List<CommentView> list(Integer worksId, Integer start, Integer limit) {
		List<Comment> comments = commentDao.list(worksId, start, limit);
		return comments.stream().sorted((c1, c2) -> {
			return c2.getCreatedTime().compareTo(c1.getCreatedTime());
		}).map(comment -> new CommentView() {
			{
				setContent(comment.getContents());
				setMember(new MemberView() {
					{
						setPkey(comment.getMember());
						setName(comment.getMemberName());
						setHeadPic(comment.getMemberHeadPic());
					}
				});
				if (comment.getReplyTo() != null)
					setReplyTo(new MemberView() {
						{
							setPkey(comment.getReplyTo());
							setName(comment.getReplyToName());
						}
					});
				setCreatedTime(comment.getCreatedTime());
			}
		}).collect(Collectors.toList());
	}

}
