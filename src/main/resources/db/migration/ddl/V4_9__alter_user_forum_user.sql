alter table User drop column Telephone;
alter table User drop column Mobile;

alter table ForumUser add column Mobile varchar(50);
alter table ForumUser add column Telephone varchar(50);


