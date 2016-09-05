create table NotificationRule (
    ID bigint(20) not null auto_increment,
    CreatedAt DATETIME not null,
    UpdatedAt DATETIME not null,
    IsDeleted BOOLEAN,

    NotificationEventType varchar(255) not null,
    RecipientType varchar (255) not null,
    Role varchar(255) not null,
    Mail BOOLEAN not null,
    Push BOOLEAN not null,
    Display BOOLEAN not null,

    MailSubject varchar (5000) not null,
    MailText Text not null,
    DisplayNotificationText varchar (5000) null, -- null for now as there is no display notification text but just the count e,g. {{user}} commented on Question Title

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- todo hary create a unique index on NotificationEventType and Role AND RecipientType columns