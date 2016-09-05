
insert into Role (ID, CreatedAt, UpdatedAt, IsDeleted, RoleName) values(6, utc_timestamp(), utc_timestamp(), FALSE , 'USER_REQUESTED');

insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(56, utc_timestamp(), utc_timestamp(), FALSE , 6, 1);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(57, utc_timestamp(), utc_timestamp(), FALSE , 6, 2);
insert into Role_Permission (ID, CreatedAt, UpdatedAt, IsDeleted, ID_Role, ID_Permission) values(58, utc_timestamp(), utc_timestamp(), FALSE , 6, 12);
