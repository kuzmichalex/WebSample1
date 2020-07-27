package com.htp.service;

import com.htp.controller.response.MyPageInfo;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.http.ResponseEntity;

public interface MyPageService {
	ResponseEntity<MyPageInfo> getMyPage(HibernateUser user);
}
