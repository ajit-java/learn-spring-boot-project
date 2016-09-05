create table AnswerRating (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME not null,
  UpdatedAt DATETIME not null,
  IsDeleted BOOLEAN,

  ID_Question bigint not null,
  ID_Answer bigint not null,
  ID_User bigint not null, -- creator of the comment
  VoteCount int not null default 1,
  StarRatingValue int not null, -- rating 0..5

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

