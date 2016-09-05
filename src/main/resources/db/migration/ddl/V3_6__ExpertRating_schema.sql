create table ExpertRating (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME not null,
  UpdatedAt DATETIME not null,
  IsDeleted BOOLEAN,

  ID_User bigint not null,        -- rating user
  ID_User_rated bigint not null,  -- rated expert
  RatingText text,
  StarRatingValue int not null,

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


