package com.htp.service;

import com.htp.controller.response.MyPageInfo;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MyPageServiceImpl implements MyPageService {

	/**
	 * Fill users's main page info
	 */
	@Override
	public ResponseEntity<MyPageInfo> getMyPage(HibernateUser user) {
		final MyPageInfo build = MyPageInfo.builder().
				userName(user.getName()).build();
		return new ResponseEntity<>(build, HttpStatus.OK);
	}
}
