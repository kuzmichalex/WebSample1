package com.htp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UserRecurser {
	private static final int MAX_FRIENDS = 5;
	private static final Random random = new Random();
	private static long counter = 0;
	private final Long id;
	private final String name;
	private final List<UserRecurser> friends;

	public UserRecurser() {
		this.id = counter++;
		this.name = "Name_" + id;
		this.friends = new ArrayList<>();
	}

	public static void showFriends(UserRecurser start, int level) {
		if (level >= 5) return;
		System.out.print("level: " + level + " User: " + start.name);
		System.out.println(" and friends: {" + start.friends.stream().map(u -> u.name).collect(Collectors.joining(", ")) + "}");
		for (UserRecurser friend : start.friends) {
			showFriends(friend, level + 1);
		}
	}

	public static void fillFriends(UserRecurser start, int level) {
		if (level++ >= 6) return;
		int friendsCount = random.nextInt(MAX_FRIENDS) + 1;
		for (int i = 0; i < friendsCount; i++) {
			UserRecurser friend = new UserRecurser();
			start.addFriend(friend);
			fillFriends(friend, level);
		}
	}

	public static void main(String[] args) {
		UserRecurser start = new UserRecurser();
		fillFriends(start, 1);
		showFriends(start, 0);
	}

	public void addFriend(UserRecurser friend) {
		this.friends.add(friend);
	}


}