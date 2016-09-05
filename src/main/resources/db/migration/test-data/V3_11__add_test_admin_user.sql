-- default Admin User:
INSERT INTO User (ID, CreatedAt, UpdatedAt, IsDeleted, Email, Gender, FirstName, LastName, UserName, ID_Role, ID_Platform, IsApprovedExpert)
VALUES
  (1,utc_timestamp(),utc_timestamp(),false,'harald.leithner@financescout24.de','m','ExpertForum','Admin','EFO_Admin',5,1,0);

