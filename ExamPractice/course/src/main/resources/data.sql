insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_GOLD');
insert into role (name) values ('ROLE_SILVER');
insert into role (name) values ('ROLE_FREE');

-- admin user --
insert into user (username, password, active, email) values ('admin', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'admin@ait.asia');
insert into user_roles (users_id, roles_id) values (1, 1);

-- gold user -- 
insert into user (username, password, active, email) values ('gold', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'gold@ait.asia');
insert into user_roles (users_id, roles_id) values (2, 2);

-- gold user -- 
insert into user (username, password, active, email) values ('silver', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'silver@ait.asia');
insert into user_roles (users_id, roles_id) values (3, 3);

-- gold user -- 
insert into user (username, password, active, email) values ('free', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'free@ait.asia');
insert into user_roles (users_id, roles_id) values (4, 4);

insert into course (name, discipline, type, date_offered, revenue_generated) values ('Machine Learning', 0, 0, parsedatetime('17-09-2012', 'dd-MM-yyyy'), 1500.25);
insert into course (name, discipline, type, date_offered, revenue_generated) values ('Basic Calculus', 0, 1, parsedatetime('17-09-2012', 'dd-MM-yyyy'), 0);