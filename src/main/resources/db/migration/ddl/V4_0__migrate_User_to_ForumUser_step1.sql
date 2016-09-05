-- --------
-- step 1
-- --------

-- migration: User table becomes main table for Users
-- add address fields

alter table User add column Street varchar(150);
alter table User add column ZipCode varchar(5);
alter table User add column City varchar(100);

-- introduce ForumUser
-- add relationship fields (Platform, Role)

CREATE TABLE `ForumUser` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedAt` datetime NOT NULL,
  `UpdatedAt` datetime NOT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `UserName` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ID_User` bigint(20) NOT NULL,
  `ID_Role` int(11) NOT NULL,
  `ID_Platform` int(11) NOT NULL,
  `IsApprovedExpert` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- add new foreign key columns to referenced tables (ID_User columns will be deleted later on) & set old ones nullable

alter table Answer add column ID_ForumUser bigint(20) null;
alter table Answer change column ID_User ID_User bigint(20) null;

alter table AnswerRating add column ID_ForumUser bigint(20) null;
alter table AnswerRating change column ID_User ID_User bigint(20) null;

alter table `Comment` add column ID_ForumUser bigint(20) null;
alter table `Comment` change column ID_User ID_User bigint(20) null;

alter table ExpertProfile add column ID_ForumUser bigint(20) null;
alter table ExpertProfile change column ID_User ID_User bigint(20) null;

alter table ExpertRating add column ID_ForumUser bigint(20) null;
alter table ExpertRating change column ID_User ID_User bigint(20) null;

alter table Question add column ID_ForumUser bigint(20) null;
alter table Question change column ID_User ID_User bigint(20) null;



-- ExpertProfile: ExpertCategories -> Expertise
alter table ExpertProfile add column Expertise varchar(500);

