alter table ForumUser change Role ID_Role bigint(20);

alter table Role modify column ID BIGINT(20);
alter table Permission modify column ID BIGINT(20);
alter table Role_Permission modify column ID BIGINT(20);
alter table Role_Permission modify column ID_Role BIGINT(20);
alter table Role_Permission modify column ID_Permission BIGINT(20);

