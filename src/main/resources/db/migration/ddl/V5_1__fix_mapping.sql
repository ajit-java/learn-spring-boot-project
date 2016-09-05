alter table ForumUser change ID_Role Role varchar(255);
alter table Answer change ID_PublishingStatus PublishingStatus varchar(255);
alter table Comment change ID_PublishingStatus PublishingStatus varchar(255);
alter table Question change ID_PublishingStatus PublishingStatus varchar(255);
alter table ExpertProfile add column DistrictCourt varchar(255);
alter table ExpertProfile add column RegistrationNumber varchar(255);
alter table ExpertProfile add column HrbNumber varchar(255);
alter table ExpertRating change ID_ForumUser ID_RatingUser bigint(20);
