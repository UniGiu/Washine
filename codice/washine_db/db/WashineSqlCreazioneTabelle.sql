BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "COMMUNITYUSERSLIST" (
	"LaundryPersonId"	TEXT,
	"ParticipantId"	TEXT,
	"UserName"	TEXT NOT NULL,
	"CommunityName"	TEXT NOT NULL,
	PRIMARY KEY("LaundryPersonId","ParticipantId"),
	FOREIGN KEY("LaundryPersonId") REFERENCES "USER"("Id"),
	FOREIGN KEY("ParticipantId") REFERENCES "USER"("Id")
);
CREATE TABLE IF NOT EXISTS "GROUPS" (
	"UserId"	TEXT,
	"GroupName"	TEXT NOT NULL,
	PRIMARY KEY("UserId"),
	FOREIGN KEY("UserId") REFERENCES "USER"("Id")
);
CREATE TABLE IF NOT EXISTS "INVITES" (
	"LaundryPersonId"	TEXT,
	"InvitedName"	TEXT,
	"Code"	TEXT NOT NULL,
	"TS"	INTEGER,
	PRIMARY KEY("LaundryPersonId","InvitedName"),
	FOREIGN KEY("LaundryPersonId") REFERENCES "USER"("Id")
);
CREATE TABLE IF NOT EXISTS "USER" (
	"Id"	TEXT,
	"Email"	TEXT NOT NULL,
	"Password"	TEXT NOT NULL,
	"Level"	INTEGER DEFAULT 1,
	"Blocked"	BOOLEAN DEFAULT FALSE,
	PRIMARY KEY("Id")
);
CREATE TABLE IF NOT EXISTS "WASHING" (
	"WashingId"	TEXT,
	"LaundryPersonId"	TEXT NOT NULL,
	"Active" BOOLEAN DEFAULT TRUE,
	PRIMARY KEY("WashingId"),
	FOREIGN KEY("LaundryPersonId") REFERENCES "USER"("Id")
);
CREATE TABLE IF NOT EXISTS "WASHINGOPTIONS" (
	"WashingId"	TEXT,
	"VisibilityTime"	INTEGER,
	"DateTime"	INTEGER,
	"DurationMinutes"	INTEGER,
	"InitialLoad"	DOUBLE,
	"MaxLoad"	DOUBLE,
	"Temperature"	TEXT,
	"SpinSpeed"	TEXT,
	"FabricType"	TEXT,
	"Color"	TEXT,
	"DetergentTypes"	TEXT,
	"RefundType"	TEXT,
	"Underwear"	BOOLEAN,
	"PickupAddress"	TEXT,
	"DeliveryAddress"	TEXT,
	"PickupAvailability"	TEXT,
	"DeliveryAvailability"	TEXT,
	"Drying"	TEXT,
	"Ironing"	BOOLEAN,
	"ParticipantMaxLoad"	DOUBLE,
	"WashingAccessOpenDate"	INTEGER,
	"WashingAccessCloseDate"	INTEGER,
	PRIMARY KEY("WashingId"),
	FOREIGN KEY("WashingId") REFERENCES "WASHING"("WashingId")
);
CREATE TABLE IF NOT EXISTS "WASHINGPARTICIPATION" (
	"WashingId"	TEXT,
	"ParticipantId"	TEXT,
	"Load" DOUBLE,
	PRIMARY KEY("WashingId","ParticipantId"),
	FOREIGN KEY("ParticipantId") REFERENCES "USER"("Id"),
	FOREIGN KEY("WashingId") REFERENCES "WASHING"("WashingId")
);
COMMIT;
