package com.htp.service;

import com.htp.controller.response.MyPageInfo;
import com.htp.dao.springdata.*;
import com.htp.domain.hibernate.*;
import com.htp.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPageServiceImpl implements MyPageService {
	private final GroupRepository groupRepository;
	private final UserLinkToGroupRepository linkToGroupRepository;
	private final ActivityRepository activityRepository;
	private final TrainingRepository trainingRepository;
	private final TrainingStateRepository stateRepository;

	public MyPageServiceImpl(GroupRepository groupRepository,
	                         UserLinkToGroupRepository linkToGroupRepository,
	                         ActivityRepository activityRepository,
	                         TrainingRepository trainingRepository,
	                         TrainingStateRepository stateRepository) {
		this.groupRepository = groupRepository;
		this.linkToGroupRepository = linkToGroupRepository;
		this.activityRepository = activityRepository;
		this.trainingRepository = trainingRepository;
		this.stateRepository = stateRepository;
	}


	private java.sql.Date generateCurrentDate() {
		return new java.sql.Date(new java.util.Date().getTime());
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
				userName(user.getName()).
				Activity(activityRepository.findAllByUser(user.getId())).
				build();
		return new ResponseEntity<>(build, HttpStatus.OK);
	}

	/*
	 * joining a user to a group. Throws an exception when the group does not exist
	 * Creates a link to the group when you first log in to the group
	 * Restores the connection when you re-log in to the group
	 * */
	@Override
	public void enterGroup(HibernateUser user, Long groupId) {
		final HibernateGroup group = groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new);
		final Optional<HibernateUserLinkGroup> userLinkToGroup = groupRepository.getUserLinkToGroup(user.getId(), group.getId());
		if (userLinkToGroup.isEmpty()) {
			final HibernateUserLinkGroup newUserLinkToGroup = new HibernateUserLinkGroup();
			newUserLinkToGroup.setUser(user.getId());
			newUserLinkToGroup.setGroup(groupId);
			newUserLinkToGroup.setDateIn(generateCurrentDate());
			linkToGroupRepository.save(newUserLinkToGroup);
		} else {
			if (userLinkToGroup.get().isDeleted()) {
				userLinkToGroup.get().setDateIn(generateCurrentDate());
				userLinkToGroup.get().setDateOut(null);
				userLinkToGroup.get().setDeleted(false);
				linkToGroupRepository.save(userLinkToGroup.get());
			}
		}
	}

	/**
	 * Add some training to plans
	 */
	@Override
	public void leaveGroup(HibernateUser user, Long groupId) {
		final HibernateGroup group = groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new);
		final Optional<HibernateUserLinkGroup> userLinkToGroup = groupRepository.getUserLinkToGroup(user.getId(), group.getId());
		if (userLinkToGroup.isEmpty()) {
			throw new EntityNotFoundException("You can't leave a group that you don't belong to. Try logging in first and then logging out");
		} else {
			if (!userLinkToGroup.get().isDeleted()) {
				userLinkToGroup.get().setDateOut(generateCurrentDate());
				userLinkToGroup.get().setDeleted(true);
				linkToGroupRepository.save(userLinkToGroup.get());
			}
		}
	}

	@Override
	public void addToActivity(HibernateUser user, Long trainingId) {
		final HibernateTraining training = trainingRepository.findById(trainingId).orElseThrow(EntityNotFoundException::new);
		final HibernateTrainingState state = stateRepository.findPlannedState().orElseThrow(EntityNotFoundException::new);
		HibernateActivity activity = new HibernateActivity();
		activity.setUserId(user.getId());
		activity.setLevelId(1L);
		activity.setStateId(state);
		activity.setTrainingId(training.getId());
		activityRepository.save(activity);
	}


}
