package com.htp.controller.response;

import com.htp.domain.Activity;
import com.htp.domain.Group;
import com.htp.domain.hibernate.HibernateUser;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MyPageInfo {
	private String userName;
	private HibernateUser user;
	private List<Group> Groups;
	private List<Activity> Activity;
}
