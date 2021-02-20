-- no need id ; spring security expects ROLE_ (prefix)--
insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_PREMIUM_USER');

-- admin user --
insert into user (username, password, active, email) values ('admin', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'admin@ait.asia');
insert into user_roles (users_id, roles_id) values (1, 1);

-- normal user -- 
insert into user (username, password, active, email) values ('john', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'user@ait.asia');
insert into user_roles (users_id, roles_id) values (2, 2);