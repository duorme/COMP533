-- Database: project1

-- DROP DATABASE project1;


--create all tables

DROP TABLE IF EXISTS Org,Meet,Participant,Event,Stroke,Distance,Heat,Swim,Leg,StrokeOf;
CREATE TABLE Org(
	id VARCHAR(50),
	name VARCHAR(50),
	is_univ BOOLEAN,
	PRIMARY KEY(id)
);

CREATE TABLE Meet(
name VARCHAR(50),
start_date DATE,
num_days INTEGER,
org_id VARCHAR(50),
PRIMARY KEY(name),
FOREIGN KEY(org_id) REFERENCES Org(id)
ON DELETE CASCADE
);


CREATE TABLE Participant(
	id VARCHAR(50),
	gender VARCHAR(50),
	org_id VARCHAR(50),
	name VARCHAR(50),
	PRIMARY KEY(id),
	FOREIGN KEY(org_id) REFERENCES Org(id)
	ON DELETE CASCADE
);

CREATE TABLE  Leg(
	leg INTEGER,
	PRIMARY KEY(leg)
);
CREATE TABLE Stroke(
	stroke VARCHAR(50),
	PRIMARY KEY(stroke)
);
CREATE TABLE Distance(
  distance INTEGER,
  PRIMARY KEY(distance)
);
CREATE TABLE Event(
	id VARCHAR(50),
	gender VARCHAR(50),
    distance INTEGER,
    PRIMARY KEY(id),
    FOREIGN KEY (distance) REFERENCES Distance(distance)
    ON DELETE CASCADE
);
CREATE TABLE StrokeOf(
	event_id VARCHAR(50),
	leg INTEGER,
	stroke VARCHAR(50),
	PRIMARY KEY(event_id,leg),
	FOREIGN KEY(event_id) REFERENCES Event(id) ON DELETE CASCADE,
	FOREIGN KEY(leg) REFERENCES Leg(leg) ON DELETE CASCADE
);
CREATE TABLE Heat(
	id INTEGER,
	event_id VARCHAR(50),
	meet_name VARCHAR(50),
	PRIMARY KEY (id,event_id,meet_name),
	FOREIGN KEY(event_id) REFERENCES Event(id) ON DELETE CASCADE,
	FOREIGN KEY(meet_name) REFERENCES Meet(name) ON DELETE CASCADE
);
CREATE TABLE Swim(
	heat_id INTEGER,
	event_id VARCHAR(50),
	meet_name VARCHAR(50),
	partition_id VARCHAR(50),
	leg INTEGER,
	_time INTEGER,
	PRIMARY KEY(heat_id,event_id,meet_name,partition_id),
	FOREIGN KEY(heat_id,event_id,meet_name) REFERENCES Heat(id,event_id,meet_name) ON DELETE CASCADE,
	FOREIGN KEY(partition_id) REFERENCES Participant(id) ON DELETE CASCADE,
	FOREIGN KEY(leg) REFERENCES Leg(leg) ON DELETE CASCADE
	);


CREATE OR REPLACE FUNCTION InsertToOrg(_id VARCHAR(50),_name VARCHAR(50),_is_univ BOOLEAN)
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Org(id,name,is_univ)
        VALUES(_id,_name,_is_univ);
      END $$
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION InsertToMeet(name VARCHAR(50),start_date DATE,num_days INTEGER,org_id VARCHAR(50))
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Meet(name,start_date,num_days,org_id)
        VALUES(name,start_date,num_days,org_id);
      END $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION InsertToParticipant(
	id VARCHAR(50),
	gender VARCHAR(50),
	org_id VARCHAR(50),
	name VARCHAR(50))
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Participant(id,gender,org_id,name)
        VALUES(id,gender,org_id,name);
      END $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION InsertToLeg(leg INTEGER)
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Leg(leg)
        VALUES(leg);
      END $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION InsertToStroke(stroke VARCHAR(50))
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Stroke(stroke)
        VALUES(stroke);
      END $$
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION InsertToDistance(distance INTEGER)
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Distance(distance)
        VALUES(distance);
      END $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION InsertToEvent(id VARCHAR(50),gender VARCHAR(50),distance INTEGER)
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Event(id,gender,distance)
        VALUES(id,gender,distance);
      END $$
LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION InsertToStrokeOf(event_id VARCHAR(50),leg INTEGER,stroke VARCHAR(50))
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO StrokeOf(event_id,leg,stroke)
        VALUES(event_id,leg,stroke);
      END $$
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION InsertToHeat(id INTEGER,event_id VARCHAR(50),meet_name VARCHAR(50))
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Heat(id,event_id,meet_name)
        VALUES(id,event_id,meet_name);
      END $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION InsertToSwim(heat_id INTEGER,event_id VARCHAR(50),meet_name VARCHAR(50),partition_id VARCHAR(50),leg INTEGER,_time DOUBLE PRECISION)
  RETURNS void 
AS $$
      BEGIN
        INSERT INTO Swim(heat_id,event_id,meet_name,partition_id,leg,_time)
        VALUES(heat_id,event_id,meet_name,partition_id,leg,_time);
      END $$
LANGUAGE plpgsql;













    