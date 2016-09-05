
alter table ForumUser drop column IsApprovedExpert;


/* ForumUser table: add personal data */

alter table ForumUser add column Email VARCHAR(200) NOT NULL;
alter table ForumUser add column Gender VARCHAR(10);
alter table ForumUser add column FirstName VARCHAR(100);
alter table ForumUser add column LastName VARCHAR(100);
alter table ForumUser add column Street VARCHAR(150);
alter table ForumUser add column ZipCode VARCHAR(5);
alter table ForumUser add column City VARCHAR(100);

alter table User add column SSO_ID varchar(100);

alter table Category drop column ID_Platform;
alter table ForumUser drop column ID_Platform;
alter table Question drop column ID_Platform;


/*
to do: user table: remove address fields
*/

alter table User drop column Gender;
alter table User drop column FirstName;
alter table User drop column LastName;
alter table User drop column Street;
alter table User drop column ZipCode;
alter table User drop column City;

drop table Platform;
