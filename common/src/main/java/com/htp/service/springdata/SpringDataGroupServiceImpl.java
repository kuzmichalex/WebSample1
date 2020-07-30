package com.htp.service.springdata;

import com.htp.dao.springdata.GroupRepository;
import com.htp.domain.hibernate.HibernateGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataGroupServiceImpl implements SpringDataGroupService {
	private final GroupRepository groupRepository;

	public SpringDataGroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public List<HibernateGroup> findAll() {
		return groupRepository.findAll();
	}

	@Override
	public HibernateGroup save(HibernateGroup group) {
		return groupRepository.save(group);
	}
}
