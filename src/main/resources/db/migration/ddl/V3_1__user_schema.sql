create table User (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME not null,
  UpdatedAt DATETIME not null,
  IsDeleted BOOLEAN,

  Email varchar(200) not null,
  Gender varchar(10) null,
  FirstName varchar(100) null,
  LastName varchar(100) null,
  UserName varchar(100) null,
  ID_Role INT not NULL,
  ID_Platform INT not NULL,
  IsApprovedExpert BOOLEAN not null DEFAULT 0,
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

