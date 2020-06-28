package com.htp.service;

import com.htp.dao.RoleDao;
import com.htp.domain.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

	private final RoleDao roleDao;

	//@Qualifier("roleRepositoryJdbcTemplate" нужен, чтобы заавтовайрилось.
	public RoleServiceImpl(@Qualifier("roleRepositoryJdbcTemplate")RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Optional<Role> findById(Long roleId) {
		return roleDao.findById(roleId);
	}

	@Override
	public Optional<Role> findByRoleName(String name) {
		return roleDao.findByRoleName(name);
	}

	@Override
	public Role findOne(Long roleId) {
		return roleDao.findOne(roleId);
	}

	@Override
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleDao.update(role);
	}

	@Override
	public int delete(Role role) {
		return roleDao.delete(role);
	}
}
