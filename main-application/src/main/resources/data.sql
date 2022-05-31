insert into routine (id, user_id, name, is_deleted) values (1, 1, '첫번째 루틴', false);
insert into routine (id, user_id, name, is_deleted) values (2, 1, '내 루틴', false);
insert into routine (id, user_id, name, is_deleted) values (3, 1, '즐겨하는 루틴', false);

insert into routine_detail (id, routine_id, exercise_id, content) values (1, 1, 1, '[{"order" : 1, "weight" : 80, "count" : 10}]');
insert into routine_detail (id, routine_id, exercise_id, content) values (2, 1, 2, '[{"order" : 1, "weight" : 80, "count" : 10}]');
insert into routine_detail (id, routine_id, exercise_id, content) values (3, 1, 3, '[{"order" : 1, "weight" : 80, "count" : 10}]');
insert into routine_detail (id, routine_id, exercise_id, content) values (4, 2, 4, '[{"order" : 1, "weight" : 80, "count" : 10}]');
insert into routine_detail (id, routine_id, exercise_id, content) values (5, 2, 5, '[{"order" : 1, "weight" : 80, "count" : 10}]');
insert into routine_detail (id, routine_id, exercise_id, content) values (6, 2, 6, '[{"order" : 1, "weight" : 80, "count" : 10}]');
insert into routine_detail (id, routine_id, exercise_id, content) values (7, 3, 7, '[{"order" : 1, "weight" : 80, "count" : 10}]');
insert into routine_detail (id, routine_id, exercise_id, content) values (8, 3, 8, '[{"order" : 1, "weight" : 80, "count" : 10}]');