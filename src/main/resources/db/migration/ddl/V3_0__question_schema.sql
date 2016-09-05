
create table Question (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME null,
  UpdatedAt DATETIME null,
  IsDeleted BOOLEAN,
  ID_User bigint not null,
  ID_Platform bigint not null,
  ID_Category bigint not null,
  Title varchar(500) not null,
  Text varchar(5000) not null,
  PublishedAt DATETIME not null,
  ID_QuestionStatus int,
  ID_PublishingStatus int,
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

