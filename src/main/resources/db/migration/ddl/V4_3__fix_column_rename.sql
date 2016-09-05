
alter table Answer change column ForumUser ID_ForumUser bigint(20) not null;
alter table `Comment` change column ForumUser ID_ForumUser bigint(20) not null;
alter table ExpertProfile change column ForumUser ID_ForumUser bigint(20) not null;
alter table ExpertRating change column ForumUser ID_ForumUser bigint(20) not null;
alter table Question change column ForumUser ID_ForumUser bigint(20) not null;
