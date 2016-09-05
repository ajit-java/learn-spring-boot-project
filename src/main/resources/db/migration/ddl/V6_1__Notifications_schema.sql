create table NotificationQueue (
    ID bigint(20) not null auto_increment,
    CreatedAt DATETIME not null,
    UpdatedAt DATETIME not null,
    IsDeleted BOOLEAN,

    TriggeredById bigint not null,
    TriggeredByType varchar(255) not null,
    TriggeredByWebUrl varchar(5000) null,

    TriggeredOnId bigint not null,
    TriggeredOnType varchar(255) not null,
    TriggeredOnWebUrl varchar(5000) null,
    NotificationEventType varchar(255),

    NotificationText TEXT null,
    NotificationSubject varchar(2000) null,

    NotificationStatus varchar(255) not null, -- New, processing, processed
#     NotificationChannel varchar(255) not null, -- Push,email,DisplayNotification
#     SentAt datetime null,

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

