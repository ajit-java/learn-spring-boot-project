

INSERT into  PublishingStatus(ID, CreatedAt, UpdatedAt, IsDeleted, PublishingStatusName)
values
  (1, utc_timestamp(), utc_timestamp(), FALSE , 'New'),
  (2, utc_timestamp(), utc_timestamp(), FALSE , 'Approved'),
  (3, utc_timestamp(), utc_timestamp(), FALSE , 'Spam');

INSERT into  QuestionStatus(ID, CreatedAt, UpdatedAt, IsDeleted, QuestionStatusName)
values
  (1, utc_timestamp(), utc_timestamp(), FALSE , 'Open'),
  (2, utc_timestamp(), utc_timestamp(), FALSE , 'Answered'),
  (3, utc_timestamp(), utc_timestamp(), FALSE , 'Helpfully answered'),
  (4, utc_timestamp(), utc_timestamp(), FALSE , 'Closed');

INSERT into  Role(ID, CreatedAt, UpdatedAt, IsDeleted, RoleName)
values
  (1, utc_timestamp(), utc_timestamp(), FALSE , 'Logged-In User'),
  (2, utc_timestamp(), utc_timestamp(), FALSE , 'Requested Expert'),
  (3, utc_timestamp(), utc_timestamp(), FALSE , 'Approved Expert'),
  (4, utc_timestamp(), utc_timestamp(), FALSE , 'Support'),
  (5, utc_timestamp(), utc_timestamp(), FALSE , 'Admin');

INSERT into  Platform(ID, CreatedAt, UpdatedAt, IsDeleted, PlatformName)
values
  (1, utc_timestamp(), utc_timestamp(), FALSE , 'FinanceScout24 Expert Forum'),
  (2, utc_timestamp(), utc_timestamp(), FALSE , 'ImmobilienScout24 Baufi Forum ');

