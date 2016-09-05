-- -------------
-- step 1b: migrate data
-- -------------


-- User -> ForumUser

insert into `ForumUser` (ID, CreatedAt, UpdatedAt, IsDeleted, UserName, ID_User, ID_Role, ID_Platform, IsApprovedExpert)
  select
    ID, CreatedAt, UpdatedAt, IsDeleted, UserName, ID, ID_Role, ID_Platform, IsApprovedExpert
  from User;


-- ExpertProfile.ExpertCategories -> Expertise
update ExpertProfile set Expertise = ExpertCategories;

-- new foreign key fields:
update Answer set ID_ForumUser = ID_User;
update AnswerRating set ID_ForumUser = ID_User;
update `Comment` set ID_ForumUser = ID_User;
update ExpertProfile set ID_ForumUser = ID_User;
update ExpertRating set ID_ForumUser = ID_User;
update Question set ID_ForumUser = ID_User;

