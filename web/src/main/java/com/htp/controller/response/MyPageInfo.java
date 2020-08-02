package com.htp.controller.response;

import com.htp.domain.hibernate.HibernateActivity;
import com.htp.domain.hibernate.HibernateGroup;
import com.htp.domain.hibernate.HibernateUser;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MyPageInfo {
	private String userName;
	private HibernateUser user;
	private List<HibernateGroup> Groups;
	private List<HibernateActivity> Activity;
}
