package com.htp.dao;

import com.htp.domain.UserHistory;
import java.util.List;
import java.util.Optional;

public interface UserHistoryDao {
	List<UserHistory> findAll();

	List<UserHistory> search(String paramSearch);

	Optional<UserHistory> findById(long recordId);

	UserHistory findOne(long recordID);

	UserHistory save(UserHistory userHistory);

	UserHistory update(UserHistory userHistory);

	int Delete(UserHistory userHistory);

	int insertBatch(List<UserHistory> users);
}
