INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (423, 'BadAssSportsman', 'Thin Billy', '2014-06-19', 'BADASS');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (2, 'Vasya', 'Vasili Pupkin', '2015-06-05', '1234');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (4, 'Vasilisa', 'Vaslilisa Pupkina', '2007-06-15', 'princess');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (34, 'noname', 'noname', '2003-02-01', 'no password');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (35, 'BENDER THE GREAT', 'Bender Rodriguez', '3000-06-07', 'Bite my shiny metal ass');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1, 'Admin', 'Administrator', '2013-05-31', 'password');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1722, 'sprind2DateCreate', 'sprind2DateCreate', '1971-01-02', 'sprind2DateCreate');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (424, 'Ines', 'Fat Ines', '2018-06-15', 'Fat');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (23, 'aaaaaaaaaa1', 'aaaaaaaaaa1', '1982-02-02', 'aaaaaaaaaa1');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (26, 'aaaaaaaaaa2', 'aaaaaaaaaa2', '1982-02-02', 'aaaaaaaaaa2');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (33, 'randomized', 'random', '2007-06-15', 'stohastic');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (3, 'user', 'User Userovich', '2013-06-07', '1111');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (32, 'test_1', 'test_1', '1970-02-01', 'test_1');
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (8, 1, '2020-06-06 23:27:43.256000', 77, 180);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (9, 1, '2020-06-06 23:27:46.785000', 77, 180);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (10, 1, '2020-06-06 23:29:25.231000', 81, 188);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (11, 1, '2020-06-13 03:17:38.847000', 84, 181);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (12, 1, '2020-06-13 03:17:55.906000', 85, 187);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (13, 1, '2020-06-13 12:52:09.058000', 72, 179);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (14, 1, '2020-06-13 13:09:34.393000', 81, 174);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (15, 1, '2020-06-13 13:10:54.201000', 77, 173);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (16, 1, '2020-06-13 13:27:46.478000', 74, 173);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (17, 1, '2020-06-13 13:42:44.327000', 84, 182);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (18, 1, '2020-06-13 13:44:33.376000', 77, 171);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (19, 1, '2020-06-13 16:59:22.871000', 180, 78);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (20, 1, '2020-06-13 00:00:00.000000', 177, 72);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (21, 1, '1970-01-01 00:00:00.000000', 183, 71);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (22, 1, '1970-01-15 00:00:00.000000', 175, 82);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (23, 1, '1970-01-01 00:00:00.000000', 178, 69);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (24, 1, '2020-06-13 00:00:00.000000', 185, 83);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (25, 1, '2020-06-13 00:00:00.000000', 182, 76);
INSERT INTO public.m_user_history (id, user_id, date, weight, height)
VALUES (26, 1, '2020-06-13 00:00:00.000000', 82, 181);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (3, 'ROLE_USER', false);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (2, 'ROLE_TRAINER', false);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (4, 'ROLE_BANNED', false);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (1, 'ROLE_ADMIN', false);
INSERT INTO public.m_levels (id, name, is_deleted)
VALUES (1, 'Beginner', false);
INSERT INTO public.m_levels (id, name, is_deleted)
VALUES (2, 'Intermediate', false);
INSERT INTO public.m_levels (id, name, is_deleted)
VALUES (3, 'Advanced', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (1, 'Maintenance, warm up', 'Moderate activity', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (2, 'Fitness training, fat buring', 'Weight control', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (3, 'Cardio, Endurance training', 'Aerobic', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (4, 'Hardcore training', 'Anaerobic', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (5, 'Maximum effort', 'VO2 max', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (6, 'Game', 'Game', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (7, 'Relax', 'Relax', false);
INSERT INTO public.m_features (id, description, name, is_deleted)
VALUES (8, 'Challenge', 'Challenge', false);
INSERT INTO public.m_activity_state (id, state_name, is_deleted)
VALUES (1, 'Planned', false);
INSERT INTO public.m_activity_state (id, state_name, is_deleted)
VALUES (2, 'Started', false);
INSERT INTO public.m_activity_state (id, state_name, is_deleted)
VALUES (3, 'Finished', false);
INSERT INTO public.m_activity_state (id, state_name, is_deleted)
VALUES (4, 'Cancelled', false);
INSERT INTO public.m_activity_state (id, state_name, is_deleted)
VALUES (5, 'Paused', false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (1, 'Racewalking', 'Racewalking', 1, false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (4, 'Swimming', 'Swimming', 1, false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (5, 'Push-Up', 'Push-Up', 1, false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (2, 'Running', 'Long distance', 1, false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (3, 'Running (Sprinting)', 'Short distance', 1, false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (10, 'Ice bucket', 'Ice bucket Challenge', 4, false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (11, 'Hot Pepper', 'Hot Pepper challenge', 4, false);
INSERT INTO public.m_trainings (id, name, description, author_user_id, is_deleted)
VALUES (12, 'Water Bottle Flip', ' Water Bottle Flip Challenge', 4, false);
INSERT INTO public.m_groups (id, name, description, user_founder_id, date_foundation, is_deleted)
VALUES (1, 'Runners', 'We are runners', 1, '2020-07-06', false);
INSERT INTO public.m_groups (id, name, description, user_founder_id, date_foundation, is_deleted)
VALUES (2, 'Walkers', 'We are lazy runners', 1, '2020-07-02', false);
INSERT INTO public.m_groups (id, name, description, user_founder_id, date_foundation, is_deleted)
VALUES (9, 'SpringDataTesters', 'SpringDataTesters', 1, '2020-07-30', false);
INSERT INTO public.m_groups (id, name, description, user_founder_id, date_foundation, is_deleted)
VALUES (12, 'Ladies', 'Ladies', 4, '2020-07-30', false);
INSERT INTO public.m_activity (id, user_id, group_id, level_id, time_start, time_end, state_id, training_id, is_deleted)
VALUES (1, 1, null, 1, '2020-08-02 21:17:49.000000', null, 1, 1, false);

INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (1, 1, 1, false);
INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (72, 1, 2, false);
INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (73, 1, 3, false);
INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (77, 423, 2, false);
INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (78, 423, 3, false);
INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (74, 2, 3, false);
INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (75, 3, 3, false);
INSERT INTO public.l_user_roles (id, user_id, role_id, is_deleted)
VALUES (76, 4, 3, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (1, 1, 3, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (2, 2, 3, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (4, 4, 3, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (3, 3, 4, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (5, 5, 4, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (6, 4, 4, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (7, 10, 7, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (8, 10, 8, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (9, 11, 7, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (10, 11, 8, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (11, 12, 7, false);
INSERT INTO public.l_training_features (id, training_id, feature_id, is_deleted)
VALUES (12, 12, 8, false);

INSERT INTO public.l_user_groups (id, user_id, group_id, date_in, is_deleted, date_out)
VALUES (2, 2, 1, '2020-07-29', false, null);
INSERT INTO public.l_user_groups (id, user_id, group_id, date_in, is_deleted, date_out)
VALUES (3, 4, 1, '2020-07-30', false, null);
INSERT INTO public.l_user_groups (id, user_id, group_id, date_in, is_deleted, date_out)
VALUES (1, 1, 1, '2020-08-02', true, '2020-08-02');
INSERT INTO public.l_user_groups (id, user_id, group_id, date_in, is_deleted, date_out)
VALUES (5, 1, 2, '2020-08-02', true, '2020-08-02');