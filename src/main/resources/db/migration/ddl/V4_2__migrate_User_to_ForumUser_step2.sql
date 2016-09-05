-- remove platform, role reference; IsApprovedExpert from user
alter table User drop column IsApprovedExpert;
alter table User drop column ID_Role;
alter table User drop column ID_Platform;
alter table User drop column UserName;

-- remove deprecated foreign key columns:
alter table Answer drop column ID_User;
alter table Answer change column ID_ForumUser ForumUser bigint(20) not null;

alter table AnswerRating drop column ID_User;
alter table AnswerRating change column ID_ForumUser ID_ForumUser bigint(20) not null;

alter table `Comment` drop column ID_User;
alter table `Comment` change column ID_ForumUser ForumUser bigint(20) not null;

alter table ExpertProfile drop column ID_User;
alter table ExpertProfile change column ID_ForumUser ForumUser bigint(20) not null;

alter table ExpertRating drop column ID_User;
alter table ExpertRating change column ID_ForumUser ForumUser bigint(20) not null;

alter table Question drop column ID_User;
alter table Question change column ID_ForumUser ForumUser bigint(20) not null;


-- remove renamed column;
alter table ExpertProfile drop column ExpertCategories;
