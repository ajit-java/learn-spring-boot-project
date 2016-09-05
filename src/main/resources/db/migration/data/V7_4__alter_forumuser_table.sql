update Role set RoleName = 'USER' where ID = 1;
update Role set RoleName = 'EXPERT_REQUESTED' where ID = 2;
update Role set RoleName = 'EXPERT_APPROVED' where ID = 3;
update Role set RoleName = 'SUPPORT' where ID = 4;
update Role set RoleName = 'ADMIN' where ID = 5;
insert into Role (ID, CreatedAt, UpdatedAt, IsDeleted, RoleName) values(0, utc_timestamp(), utc_timestamp(), FALSE , 'ANONYMOUS');

insert into Permission () values(1, utc_timestamp(), utc_timestamp(), FALSE , 'SEARCH_SITE');
insert into Permission () values(2, utc_timestamp(), utc_timestamp(), FALSE , 'VIEW_QUESTIONS');
insert into Permission () values(3, utc_timestamp(), utc_timestamp(), FALSE , 'CREATE_QUESTIONS');
insert into Permission () values(4, utc_timestamp(), utc_timestamp(), FALSE , 'PUBLISH_QUESTIONS');
insert into Permission () values(5, utc_timestamp(), utc_timestamp(), FALSE , 'EDIT_QUESTIONS');
insert into Permission () values(6, utc_timestamp(), utc_timestamp(), FALSE , 'ADD_CATEGORIES');
insert into Permission () values(7, utc_timestamp(), utc_timestamp(), FALSE , 'ANSWER_QUESTIONS');
insert into Permission () values(8, utc_timestamp(), utc_timestamp(), FALSE , 'COMMENT_OWN_ANSWERS');
insert into Permission () values(9, utc_timestamp(), utc_timestamp(), FALSE , 'COMMENT_ALL_ANSWERS');
insert into Permission () values(10, utc_timestamp(), utc_timestamp(), FALSE , 'RATE_ANSWERS');
insert into Permission () values(11, utc_timestamp(), utc_timestamp(), FALSE , 'EDIT_ANSWERS');
insert into Permission () values(12, utc_timestamp(), utc_timestamp(), FALSE , 'EDIT_OWN_USER_PROFILE');
insert into Permission () values(13, utc_timestamp(), utc_timestamp(), FALSE , 'EDIT_ALL_USER_PROFILES');
insert into Permission () values(14, utc_timestamp(), utc_timestamp(), FALSE , 'RESET_OWN_PASSWORD');
insert into Permission () values(15, utc_timestamp(), utc_timestamp(), FALSE , 'RESET_ALL_PASSWORDS');
insert into Permission () values(16, utc_timestamp(), utc_timestamp(), FALSE , 'ABANDON_USER');
insert into Permission () values(17, utc_timestamp(), utc_timestamp(), FALSE , 'APPROVE_EXPERTS');
insert into Permission () values(18, utc_timestamp(), utc_timestamp(), FALSE , 'CREATE_PLATFORM');
insert into Permission () values(19, utc_timestamp(), utc_timestamp(), FALSE , 'EDIT_USER_GRANTS');

insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(1, utc_timestamp(), utc_timestamp(), FALSE , 0, 1);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(2, utc_timestamp(), utc_timestamp(), FALSE , 0, 2);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(3, utc_timestamp(), utc_timestamp(), FALSE , 1, 1);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(4, utc_timestamp(), utc_timestamp(), FALSE , 1, 2);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(5, utc_timestamp(), utc_timestamp(), FALSE , 1, 3);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(6, utc_timestamp(), utc_timestamp(), FALSE , 1, 9);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(7, utc_timestamp(), utc_timestamp(), FALSE , 1, 10);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(8, utc_timestamp(), utc_timestamp(), FALSE , 1, 12);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(9, utc_timestamp(), utc_timestamp(), FALSE , 1, 14);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(10, utc_timestamp(), utc_timestamp(), FALSE , 2, 1);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(11, utc_timestamp(), utc_timestamp(), FALSE , 2, 2);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(12, utc_timestamp(), utc_timestamp(), FALSE , 2, 12);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(13, utc_timestamp(), utc_timestamp(), FALSE , 2, 14);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(14, utc_timestamp(), utc_timestamp(), FALSE , 3, 1);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(15, utc_timestamp(), utc_timestamp(), FALSE , 3, 2);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(16, utc_timestamp(), utc_timestamp(), FALSE , 3, 7);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(17, utc_timestamp(), utc_timestamp(), FALSE , 3, 8);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(18, utc_timestamp(), utc_timestamp(), FALSE , 3, 12);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(19, utc_timestamp(), utc_timestamp(), FALSE , 3, 14);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(20, utc_timestamp(), utc_timestamp(), FALSE , 4, 1);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(21, utc_timestamp(), utc_timestamp(), FALSE , 4, 2);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(22, utc_timestamp(), utc_timestamp(), FALSE , 4, 3);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(23, utc_timestamp(), utc_timestamp(), FALSE , 4, 4);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(24, utc_timestamp(), utc_timestamp(), FALSE , 4, 5);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(25, utc_timestamp(), utc_timestamp(), FALSE , 4, 6);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(26, utc_timestamp(), utc_timestamp(), FALSE , 4, 7);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(27, utc_timestamp(), utc_timestamp(), FALSE , 4, 8);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(28, utc_timestamp(), utc_timestamp(), FALSE , 4, 9);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(29, utc_timestamp(), utc_timestamp(), FALSE , 4, 10);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(30, utc_timestamp(), utc_timestamp(), FALSE , 4, 11);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(31, utc_timestamp(), utc_timestamp(), FALSE , 4, 12);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(32, utc_timestamp(), utc_timestamp(), FALSE , 4, 13);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(33, utc_timestamp(), utc_timestamp(), FALSE , 4, 14);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(34, utc_timestamp(), utc_timestamp(), FALSE , 4, 15);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(35, utc_timestamp(), utc_timestamp(), FALSE , 4, 16);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(36, utc_timestamp(), utc_timestamp(), FALSE , 4, 17);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(37, utc_timestamp(), utc_timestamp(), FALSE , 5, 1);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(38, utc_timestamp(), utc_timestamp(), FALSE , 5, 2);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(39, utc_timestamp(), utc_timestamp(), FALSE , 5, 3);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(40, utc_timestamp(), utc_timestamp(), FALSE , 5, 4);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(41, utc_timestamp(), utc_timestamp(), FALSE , 5, 5);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(42, utc_timestamp(), utc_timestamp(), FALSE , 5, 6);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(43, utc_timestamp(), utc_timestamp(), FALSE , 5, 7);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(44, utc_timestamp(), utc_timestamp(), FALSE , 5, 8);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(45, utc_timestamp(), utc_timestamp(), FALSE , 5, 9);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(46, utc_timestamp(), utc_timestamp(), FALSE , 5, 10);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(47, utc_timestamp(), utc_timestamp(), FALSE , 5, 11);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(48, utc_timestamp(), utc_timestamp(), FALSE , 5, 12);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(49, utc_timestamp(), utc_timestamp(), FALSE , 5, 13);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(50, utc_timestamp(), utc_timestamp(), FALSE , 5, 14);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(51, utc_timestamp(), utc_timestamp(), FALSE , 5, 15);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(52, utc_timestamp(), utc_timestamp(), FALSE , 5, 16);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(53, utc_timestamp(), utc_timestamp(), FALSE , 5, 17);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(54, utc_timestamp(), utc_timestamp(), FALSE , 5, 18);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(55, utc_timestamp(), utc_timestamp(), FALSE , 5, 19);