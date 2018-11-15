package com.shangcai.dao.design.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.design.IDesignerDao;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.design.Designer;
import com.shangcai.entity.design.Designer.T;
import com.shangcai.view.common.MemberVies;

@Repository
public class DesignerDao extends BaseDao<Designer> implements IDesignerDao {

	/**
	 * 查询设计师列表
	 * 
	 * @param start
	 * @param limit
	 * @param name
	 * @return
	 */
	@Override
	public List<MemberVies> list(Integer start, Integer limit, String name) {
		if (name == null)
			return select(Member.T.PKEY.as("pkey"),Member.T.NAME.as("name"),
					Member.T.CREATED_TIME.as("created_time"), Designer.T.SEX.as("sex"), Designer.T.AGE.as("age"))
							.from(Designer.class).leftJoin(Member.class, Member.T.PKEY, Designer.T.MEMBER)
							.limit(start, limit).queryList(MemberVies.class);
		else
			return select(Member.T.PKEY.as("pkey"), Member.T.NAME.as("name"),
					Member.T.CREATED_TIME.as("created_time"), Designer.T.SEX.as("sex"), Designer.T.AGE.as("age"))
							.from(Designer.class).leftJoin(Member.class, Member.T.PKEY, Designer.T.MEMBER)
							.where(Member.T.NAME.eq(name)).limit(start, limit).queryList(MemberVies.class);
	}

	@Override
	public Designer get(Integer memberId) {
		return selectFrom(Designer.class).leftJoin(Member.class, Designer.T.MEMBER, Member.T.PKEY)
				.where(T.MEMBER.eq(memberId)).query();
	}

	@Override
	public void upd(Designer designer) {
		designer.upd();
	}

	public static void main(String[] args) {
		Designer.table.getClass();
		Member.table.getClass();
		DesignerDao d = new DesignerDao();
		List<MemberVies> s = d.list(0, 15, "骚超");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		for (MemberVies memberVies : s) {
			System.out.println(sdf.format(memberVies.getCreated_time()));
		}
	}

	@Override
	public List<Designer> getAll() {
		return selectFrom(Designer.class).queryList();
	}
}
