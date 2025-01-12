CREATE TABLE IF NOT EXISTS USER (
Id TEXT PRIMARY KEY,
Email TEXT NOT NULL,
Password TEXT NOT NULL,
Level INTEGER NOT NULL,
Blocked BOOLEAN DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS GROUP ( 
UserId TEXT PRIMARY KEY,
GroupName TEXT NOT NULL,
FOREIGN KEY (UserId) REFERENCES USER(Id)
);
CREATE TABLE IF NOT EXISTS GROUPUSERSLIST (
LaundryPersonId TEXT PRIMARY KEY,
ParticipantId TEXT NOT NULL,
Name TEXT NOT NULL,
FOREIGN KEY (LaundryPersonId) REFERENCES USER(Id),
FOREIGN KEY (ParticipantId) REFERENCES USER(Id)
);
CREATE TABLE IF NOT EXISTS INVITES (
LaundryPersonId TEXT PRIMARY KEY,
InvitedName TEXT NOT NULL,
Code TEXT NOT NULL,
Accepted BOOLEAN DEFAULT FALSE, 
FOREIGN KEY (LaundryPersonId) REFERENCES USER(Id)
);
CREATE TABLE IF NOT EXISTS WASHING (
WashingId TEXT PRIMARY KEY,
LaundryPersonId TEXT NOT NULL,
FOREIGN KEY (LaundryPersonId) REFERENCES USER(Id)
);
CREATE TABLE IF NOT EXISTS WASHINGPARTICIPATION ( 
WashingId TEXT PRIMARY KEY,
ParticipantId TEXT NOT NULL,
FOREIGN KEY (WashingId) REFERENCES WASHING(WashingId),
FOREIGN KEY (ParticipantId) REFERENCES USER(Id)
);
CREATE TABLE IF NOT EXISTS WASHINGOPTIONS (
WashingId TEXT PRIMARY KEY,
VisibilityTime INTEGER NOT NULL,
DateTime DATE NOT NULL,
DurationMinutes INTEGER NOT NULL,
InitialLoad DOUBLE NOT NULL,
MaxLoad DOUBLE NOT NULL,
Temperature TEXT NOT NULL,
SpinSpeed TEXT NOT NULL,
FabricType TEXT NOT NULL,
Color TEXT NOT NULL,
DetergentTypes TEXT NOT NULL,
RefundType TEXT NOT NULL,
Underwear BOOLEAN NOT NULL,
PickupAddress TEXT,
DeliveryAddress TEXT,
PickupAvailability TEXT,
DeliveryAvailability TEXT,
Drying TEXT,
Ironing BOOLEAN,
ParticipantMaxLoad DOUBLE,
WashingAccessOpenDate DATE,
WashingAccessCloseDate DATE,
FOREIGN KEY (WashingId) REFERENCES WASHING(WashingId)
);