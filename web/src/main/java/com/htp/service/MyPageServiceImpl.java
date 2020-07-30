package com.htp.service;

import com.htp.controller.response.MyPageInfo;
import com.htp.dao.springdata.GroupRepository;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MyPageServiceImpl implements MyPageService {
	private final GroupRepository groupRepository;

	public MyPageServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}


	/**
	 * Fill users's main page info:
	 * 1. user + groups created by user
	 * 2. groups that the user participates in
	 * 3. user's planned activities
	 */
	@Override
	public ResponseEntity<MyPageInfo> getMyPage(HibernateUser user) {
		final MyPageInfo build = MyPageInfo.builder().
				user(user).
				Groups(groupRepository.findAllUserGroups(user.getId())).

				userName(user.getName()).build();
		return new ResponseEntity<>(build, HttpStatus.OK);
	}
}
