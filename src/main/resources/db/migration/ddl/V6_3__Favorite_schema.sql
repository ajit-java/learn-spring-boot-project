create table Favorite (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME not null,
  UpdatedAt DATETIME not null,
  IsDeleted BOOLEAN,

  Id_Question bigint(20),
  Id_ForumUser bigint(20), -- whose marks this question as fav

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

