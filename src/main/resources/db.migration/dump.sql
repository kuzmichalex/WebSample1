create table m_users
(
    id         bigserial    not null
        constraint m_users_pk
            primary key,
    login      varchar(50)  not null,
    name       varchar(100) not null,
    birth_date date         not null,
    password   varchar(100) not null
);

alter table m_users
    owner to postgres;

create unique index m_users_id_uindex
    on m_users (id);

create unique index m_users_login_uindex
    on m_users (login);

create table m_roles
(
    id        bigserial    not null
        constraint m_roles_pk
            primary key,
    role_name varchar(100) not null
        constraint m_roles_pk_2
            unique
);

alter table m_roles
    owner to postgres;

create unique index m_roles_id_uindex
    on m_roles (id);

create table l_user_roles
(
    id      serial not null
        constraint l_roles_pk
            unique,
    user_id bigint not null
        constraint l_users_m_users_id_fk
            references m_users,
    role_id bigint not null
        constraint l_users_m_roles_id_fk
            references m_roles,
    constraint l_user_roles_pk
        primary key (role_id, user_id)
);

alter table l_user_roles
    owner to postgres;

create unique index l_roles_id_uindex
    on l_user_roles (id);

create table m_features
(
    id          bigserial    not null
        constraint m_training_features_pk
            primary key,
    description varchar(200) not null,
    name        varchar(50)  not null
);

alter table m_features
    owner to postgres;

create unique index m_training_features_id_uindex
    on m_features (id);

create unique index m_training_features_name_uindex
    on m_features (name);

create unique index m_training_features_description_uindex
    on m_features (description);

create table m_trainings
(
    id             serial       not null
        constraint m_trainings_pk
            primary key,
    name           varchar(100) not null,
    description    varchar(200) not null,
    author_user_id bigint       not null
        constraint m_trainings_m_users_id_fk
            references m_users
);

alter table m_trainings
    owner to postgres;

create unique index m_trainings_id_uindex
    on m_trainings (id);

create unique index m_trainings_name_uindex
    on m_trainings (name);

create table l_training_features
(
    id          bigserial not null
        constraint l_training_features_pk
            primary key,
    id_training bigint    not null
        constraint l_training_features_m_trainings_id_fk
            references m_trainings,
    id_feature  bigserial not null
        constraint l_training_features_m_features_id_fk
            references m_features
);

alter table l_training_features
    owner to postgres;

create unique index l_training_features_id_uindex
    on l_training_features (id);

create unique index l_training_features_id_feature_id_training_uindex
    on l_training_features (id_feature, id_training);

create table m_user_history
(
    id      bigserial    not null
        constraint m_user_info_pk
            primary key,
    user_id bigserial    not null
        constraint m_user_history_m_users_id_fk
            references m_users,
    date    timestamp(6) not null,
    weight  integer      not null,
    height  integer      not null
);

alter table m_user_history
    owner to postgres;

create unique index m_user_info_id_uindex
    on m_user_history (id);

create table m_groups
(
    id              bigserial    not null
        constraint m_groups_pk
            primary key,
    name            varchar(100) not null,
    description     varchar(200) not null,
    id_user_founder bigserial    not null,
    date_foundation date         not null
);

alter table m_groups
    owner to postgres;

create unique index m_groups_id_uindex
    on m_groups (id);

create table m_levels
(
    id   bigserial   not null
        constraint m_levels_pk
            primary key,
    name varchar(50) not null
);

alter table m_levels
    owner to postgres;

create unique index m_levels_name_uindex
    on m_levels (name);

create unique index m_levels_id_uindex
    on m_levels (id);

create table l_training_levels
(
    id              bigserial not null
        constraint l_training_levels_pk
            primary key,
    id_training     bigserial not null
        constraint l_training_levels_m_trainings_id_fk
            references m_trainings,
    id_level        bigserial not null
        constraint l_training_levels_m_levels_id_fk
            references m_levels,
    repetitions_min integer   not null,
    repetitions_max integer,
    description     varchar(200)
);

alter table l_training_levels
    owner to postgres;

create unique index l_training_levels_id_uindex
    on l_training_levels (id);

create unique index l_training_levels_id_training_id_level_uindex
    on l_training_levels (id_training, id_level);

create table l_user_groups
(
    id        bigserial not null,
    id_user   bigserial not null
        constraint l_user_groups_m_users_id_fk
            references m_users,
    id_group  bigserial not null
        constraint l_user_groups_m_groups_id_fk
            references m_groups,
    date_in   date      not null,
    is_active boolean   not null,
    date_out  date
);

alter table l_user_groups
    owner to postgres;

create unique index l_user_groups_id_uindex
    on l_user_groups (id);

create index l_user_groups_id_user_id_group_is_active_index
    on l_user_groups (id_user, id_group, is_active);

create table m_activity_state
(
    id         bigserial   not null
        constraint activity_state_pk
            primary key,
    state_name varchar(50) not null
);

alter table m_activity_state
    owner to postgres;

create unique index activity_state_id_uindex
    on m_activity_state (id);

create unique index activity_state_state_name_uindex
    on m_activity_state (state_name);

create table m_activity
(
    id          bigserial not null
        constraint m_activity_pk
            primary key,
    id_user     bigint    not null
        constraint m_activity_m_users_id_fk
            references m_users,
    id_group    bigint
        constraint m_activity_m_groups_id_fk
            references m_groups,
    id_level    bigint    not null
        constraint m_activity_m_levels_id_fk
            references m_levels,
    time_start  timestamp,
    time_end    timestamp,
    id_state    bigint    not null
        constraint m_activity_m_activity_state_id_fk
            references m_activity_state,
    id_training bigserial not null
        constraint m_activity_m_trainings_id_fk
            references m_trainings
);

alter table m_activity
    owner to postgres;

create unique index m_activity_id_uindex
    on m_activity (id);

create index m_activity_id_user_index
    on m_activity (user_id);

create index m_activity_id_group_index
    on m_activity (group_id);

create table m_training_programs
(
    id             bigserial    not null
        constraint training_programs_pk
            primary key,
    name           varchar(50)  not null,
    desctiption    varchar(200) not null,
    id_user_author bigserial    not null
        constraint m_training_programs_m_users_id_fk
            references m_users
);

alter table m_training_programs
    owner to postgres;

create unique index training_programs_id_uindex
    on m_training_programs (id);

