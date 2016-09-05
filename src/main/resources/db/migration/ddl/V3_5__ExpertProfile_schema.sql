create table ExpertProfile (
  ID bigint(20) not null auto_increment,
  CreatedAt DATETIME not null,
  UpdatedAt DATETIME not null,
  IsDeleted BOOLEAN,

  ID_User bigint not null,
  ProfileText LONGTEXT, -- todo: add additional fields
  ExpertCategories VARCHAR(500),
  ExpertStreet varchar(100),
  ExpertZipCode varchar(5),


  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

