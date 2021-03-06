create table Comment (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME not null,
  UpdatedAt DATETIME not null,
  IsDeleted BOOLEAN,

  ID_Question bigint not null,
  ID_Answer bigint not null,
  ID_User bigint not null, -- creator of the comment
  CommentText TEXT null,
  ID_PublishingStatus int,
  PublishedAt datetime null,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

