create table Category (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME not null,
  UpdatedAt DATETIME not null,
  IsDeleted BOOLEAN,

  ID_Platform int not null,
  ID_Category_parent int null,
  CategoryName varchar(100),

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
