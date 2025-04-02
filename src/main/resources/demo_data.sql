insert into users (name, username, password)
values ('John Doe', 'johndoe@gmail.com', '$2a$10$MXSxXFP58GM9aeQDAcRtLeHxOnIYvimq23pJ7fmjaBgysksH/7B3a'),
       ('Kek Bobr', 'kekbobr@gmail.com', '$2a$10$MXSxXFP58GM9aeQDAcRtLeHxOnIYvimq23pJ7fmjaBgysksH/7B3a');

insert into tasks(title, description, status, expiration_date)
values ('Bue Cheese', null, 'TODO', '2023-01-23 12:00:00'),
       ('Do Homework', 'Math, Physics', 'IN_PROGRESS', '2023-01-31 00:00:00'),
       ('Bue Cheese', null, 'DONE', null);

insert into users_tasks(task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2);

insert into users_roles(user_id, role)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');
