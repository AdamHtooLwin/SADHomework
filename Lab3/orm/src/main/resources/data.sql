insert into employee_info (employee_age, fname, lname, mname) values (20, 'Chaklam', 'Silpasuwanchai', ' ');
insert into employee_info (employee_age, fname, lname, mname) values (69, 'John', 'Cahill', ' ');
insert into employee_info (employee_age, fname, lname, mname) values (73, 'Peter', 'Cahill', ' ');

insert into user (username, password, active, role, emp_id) values ('chaklam', '$2y$12$2LA4/IzwsfoF.SFtdxwJIus48N6JwFzTdMwlrc9lXRHnA9EOBU7AS', true, 'ROLE_ADMIN', 1);
insert into user (username, password,  active, role, emp_id) values ('john', '$2y$10$uwslZFS.czXR6VE7XzyBTuH.xGtdDmBBnioj2J/KZqJr0cHDEr3fa', true, 'ROLE_USER', 2);
insert into user (username, password,  active, role, emp_id) values ('peter', '$2y$10$uwslZFS.czXR6VE7XzyBTuH.xGtdDmBBnioj2J/KZqJr0cHDEr3fa', true, 'ROLE_USER', 3);

insert into benefit(title) values ('Benefit Free Water');
insert into benefit(title) values ('Benefit Free Coffee');

insert into employee_info_benefits (emp_id, benefits_id) values (1, 1);
insert into employee_info_benefits (emp_id, benefits_id) values (1, 2);
insert into employee_info_benefits (emp_id, benefits_id) values (2, 1);

insert into address (city, house_no, street_address, zipcode, emp_id) values ('Bangkok', '30/6', 'Ramindra', '10220', 1);
insert into address (city, house_no, street_address, zipcode, emp_id) values ('Bangkok', '30/7', 'Victory Monument', '12220', 2);