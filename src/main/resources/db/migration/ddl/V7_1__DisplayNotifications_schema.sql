create table DisplayNotification (
    ID bigint(20) not null auto_increment,
    CreatedAt DATETIME not null,
    UpdatedAt DATETIME not null,
    IsDeleted BOOLEAN,

    TriggeredById bigint not null,
    TriggeredByType varchar(255) not null,

    TriggeredOnId bigint not null,
    TriggeredOnType varchar(255) not null,

    NotificationEventType varchar(255),

    DisplayNotificationStatus varchar(255) not null,

      ID_User bigint not null,


  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

