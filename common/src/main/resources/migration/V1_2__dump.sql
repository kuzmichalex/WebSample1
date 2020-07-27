--m_users

INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (423, 'BadAssSportsman', 'Thin Billy', '2014-06-19', 'BADASS');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (2, 'Vasya', 'Vasili Pupkin', '2015-06-05', '1234');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (3, 'user111', 'User Userovich', '2013-06-07', '111');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (4, 'Vasilisa', 'Vaslilisa Pupkina', '2007-06-15', 'princess');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (424, 'Ines', 'Fat Ines', '2018-06-15', 'fat');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (33, 'randomized', 'random', '2007-06-15', 'undefined');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (34, 'noname', 'noname', '2003-02-01', 'no password');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (35, 'BENDER THE GREAT', 'Bender Rodriguez', '3000-06-07', 'Bite my shiny metal ass');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1643, '1string', '1string', '1970-01-01', '1string');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1, 'Admin', 'Administrator', '2013-05-31', 'password');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1664, 't1', 't1', '1980-01-01', 't1');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1665, 'string', 'string', '1970-01-01', 'string');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1674, '1', 'string', '1970-01-01', 'string');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1675, '2', 'string', '1970-01-01', 'string');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1678, 'string1', 'string1', '1980-01-01', 'string');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1681, '213123', 'string1', '1980-01-01', 'string');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1703, 'aa', 'aa', '1972-01-01', 'aa');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1713, 's111g', '1111', '1973-01-01', '111');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1715, 's1111g', '11111', '1973-01-01', '1111');
INSERT INTO public.m_users (id, login, name, birth_date, password)
VALUES (1722, 'sprind2DateCreate', 'sprind2DateCreate', '1971-01-02', 'sprind2DateCreate');

--m_user_history
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

--m_roles
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (3, 'ROLE_USER', false);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (2, 'ROLE_TRAINER', false);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (4, 'ROLE_BANNED', false);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (1, 'ROLE_ADMIN', false);
INSERT INTO public.m_roles (id, role_name, is_deleted)
VALUES (371, 'string', false);

--m_levels
INSERT INTO public.m_levels (id, name, is_deleted)
VALUES (1, 'Beginner', false);
INSERT INTO public.m_levels (id, name, is_deleted)
VALUES (2, 'Intermediate', false);
INSERT INTO public.m_levels (id, name, is_deleted)
VALUES (3, 'Advanced', false);

--m_features
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

--m_activity_state
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

--m_trainings
INSERT INTO public.m_trainings (id, name, description, author_user_id)
VALUES (1, 'Racewalking', 'Racewalking', 1);
INSERT INTO public.m_trainings (id, name, description, author_user_id)
VALUES (4, 'Swimming', 'Swimming', 1);
INSERT INTO public.m_trainings (id, name, description, author_user_id)
VALUES (5, 'Push-Up', 'Push-Up', 1);
INSERT INTO public.m_trainings (id, name, description, author_user_id)
VALUES (2, 'Running', 'Long distance', 1);
INSERT INTO public.m_trainings (id, name, description, author_user_id)
VALUES (3, 'Running (Sprinting)', 'Short distance', 1);

--m_groups


--m_activity


--l_training_features


--l_training_levels


--l_user_groups


--l_user_roles
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
