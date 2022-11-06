CREATE TABLE TABLE_USER(
	USER_ID VARCHAR2(100),
	USER_PW VARCHAR2(100),
	USER_NAME VARCHAR2(200),
	USER_PHONE VARCHAR(100),
	USER_EMAIL VARCHAR2(200),
	CONSTRAINT USER_PK PRIMARY KEY(USER_ID)
);

SELECT * FROM TABLE_USER tu;

ALTER TABLE TABLE_USER ADD USER_PHOTO VARCHAR2(100);
ALTER TABLE TABLE_USER ADD USER_JOIN_DATE DATE;
UPDATE TABLE_USER
SET USER_JOIN_DATE = SYSDATE 
WHERE USER_ID = 'korea12345';


CREATE SEQUENCE SCRAP_SEQ;

DROP TABLE TABLE_USER_APPLY;
DROP TABLE TABLE_USER_SCRAP;

CREATE TABLE TABLE_USER_SCRAP(
	NUM NUMBER(10),
	USER_ID VARCHAR2(100),
	JOBS_NUM NUMBER(10),
	CONSTRAINT USER_SCRAP_PK PRIMARY KEY(NUM),
	CONSTRAINT USER_SCRAP_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID),
	CONSTRAINT USER_SCRAP_FK2 FOREIGN KEY(JOBS_NUM) REFERENCES TABLE_OPN(OPN_NUM)
);

SELECT * FROM TABLE_USER_SCRAP;

INSERT INTO TABLE_USER_SCRAP
(NUM, USER_ID, JOBS_NUM)
VALUES(SCRAP_SEQ.NEXTVAL, 'dfs', 2);


CREATE SEQUENCE APPLY_SEQ;

CREATE TABLE TABLE_USER_APPLY(
	NUM NUMBER(10),
	USER_ID VARCHAR2(100),
	JOBS_NUM NUMBER(10),
	APPLY_DATE DATE,
	APPLY_STATUS VARCHAR2(200),
	APPLY_RESULT VARCHAR2(200),
	CONSTRAINT USER_APPLY_PK PRIMARY KEY(NUM),
	CONSTRAINT USER_APPLY_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID),
	CONSTRAINT USER_APPLY_FK2 FOREIGN KEY(JOBS_NUM) REFERENCES TABLE_OPN(OPN_NUM)
);

ALTER TABLE TABLE_USER_APPLY ADD APPLY_AREA VARCHAR2(100);
ALTER TABLE TABLE_USER_APPLY ADD READ_DATE DATE;
ALTER TABLE TABLE_USER_APPLY DROP COLUMN APPLY_RESULT;

SELECT * FROM TABLE_USER_APPLY;

DELETE FROM TABLE_USER_APPLY;

CREATE TABLE TABLE_RESUME_INFO(
	USER_ID VARCHAR2(100),
	USER_NAME VARCHAR2(200),
	USER_BIRTH DATE,
	USER_EMAIL VARCHAR2(200),
	USER_PHONE VARCHAR2(100),
	USER_GENDER VARCHAR2(10),
	USER_ADDRESS VARCHAR2(200),
	USER_ADDRESS_DETAIL VARCHAR2(200),
	CONSTRAINT RESUME_INFO_PK PRIMARY KEY(USER_ID),
	CONSTRAINT RESUME_INFO_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);


SELECT * FROM TABLE_RESUME_INFO tri;

DELETE FROM TABLE_RESUME_INFO tri WHERE USER_ID = 'dfs';

	
DELETE FROM TABLE_RESUME_MILITARY trm WHERE USER_ID = 'akdlwb33';

ALTER TABLE TABLE_RESUME_INFO ADD USER_RESUME_PHOTO VARCHAR2(100);
ALTER TABLE TABLE_RESUME_INFO ADD USER_RESUME_TITLE VARCHAR2(100);

CREATE SEQUENCE RESUME_EDU_SEQ;

CREATE TABLE TABLE_RESUME_EDU(
	NUM NUMBER(10),
	USER_ID VARCHAR2(100),
	EDU_NAME VARCHAR2(300),
	EDU_STATUS VARCHAR2(100),
	EDU_START_DATE VARCHAR2(50),
	EDU_END_DATE VARCHAR2(50),
	EDU_MAJOR VARCHAR(300),
	EDU_GRADE VARCHAR2(10),
	CONSTRAINT RESUME_EDU_PK PRIMARY KEY(NUM),
	CONSTRAINT RESUME_EDU_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);

SELECT * FROM TABLE_RESUME_EDU;

CREATE SEQUENCE RESUME_CAREER_SEQ;

CREATE TABLE TABLE_RESUME_CAREER(
	NUM NUMBER(10),
	USER_ID VARCHAR2(100),
	CAREER_NAME VARCHAR2(300),
	CAREER_START_DATE VARCHAR2(50),
	CAREER_END_DATE VARCHAR2(50),
	CAREER_RANK VARCHAR2(100),
	CAREER_POSITION VARCHAR(100),
	CAREER_DETAIL VARCHAR(500),
	CONSTRAINT RESUME_CAREER_PK PRIMARY KEY(NUM),
	CONSTRAINT RESUME_CAREER_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);

SELECT * FROM TABLE_RESUME_CAREER;

CREATE SEQUENCE RESUME_ACTIVITY_SEQ;

CREATE TABLE TABLE_RESUME_ACTIVITY(
	NUM NUMBER(10),
	USER_ID VARCHAR2(100),
	ACTIVITY_PLACE VARCHAR2(300),
	ACTIVITY_TYPE VARCHAR2(300),
	ACTIVITY_START_DATE VARCHAR2(50),
	ACTIVITY_END_DATE VARCHAR2(50),
	ACTIVITY_DETAIL VARCHAR(500),
	CONSTRAINT RESUME_ACTIVITY_PK PRIMARY KEY(NUM),
	CONSTRAINT RESUME_ACTIVITY_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);

SELECT * FROM TABLE_RESUME_ACTIVITY;

CREATE SEQUENCE RESUME_LISENSE_SEQ;

CREATE TABLE TABLE_RESUME_LISENSE(
	NUM NUMBER(10),
	USER_ID VARCHAR2(100),
	LISENSE_NAME VARCHAR2(300),
	LISENSE_TYPE VARCHAR2(300),
	LISENSE_PUBLISHER VARCHAR2(300),
	LISENSE_DATE VARCHAR2(50),
	LISENSE_RESULT VARCHAR(300),
	CONSTRAINT RESUME_LISENSE_PK PRIMARY KEY(NUM),
	CONSTRAINT RESUME_LISENSE_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);

SELECT * FROM TABLE_RESUME_LISENSE;

CREATE TABLE TABLE_RESUME_INTRODUCE(
	USER_ID VARCHAR2(100),
	INTRODUCE VARCHAR2(3000),
	CONSTRAINT RESUME_INTRODUCE_PK PRIMARY KEY(USER_ID),
	CONSTRAINT RESUME_INTRODUCE_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);

SELECT * FROM TABLE_RESUME_INTRODUCE;

CREATE SEQUENCE RESUME_MILITARY_SEQ;

CREATE TABLE TABLE_RESUME_MILITARY(
	USER_ID VARCHAR2(100),
	MILITARY_NAME VARCHAR2(300),
	MILITARY_TYPE VARCHAR2(300),
	MILITARY_RANK VARCHAR2(300),
	MILITARY_START_DATE VARCHAR2(50),
	MILITARY_END_DATE VARCHAR2(50),
	MILITARY_REASON VARCHAR(500),
	CONSTRAINT RESUME_MILITARY_PK PRIMARY KEY(USER_ID),
	CONSTRAINT RESUME_MILITARY_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);

SELECT * FROM TABLE_RESUME_MILITARY;

CREATE TABLE TABLE_RESUME_PORTFOLIO(
	USER_ID VARCHAR2(100),
	PORTFOLIO_TYPE VARCHAR2(100),
	PORTFOLIO_NAME VARCHAR2(300),
	CONSTRAINT RESUME_PORTFOLIO_PK PRIMARY KEY(PORTFOLIO_NAME),
	CONSTRAINT RESUME_PORTFOLIO_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);

DELETE FROM TABLE_RESUME_PORTFOLIO;
DELETE FROM TABLE_RESUME_URL;
SELECT * FROM TABLE_RESUME_URL;
SELECT * FROM TABLE_RESUME_PORTFOLIO;

CREATE TABLE TABLE_RESUME_URL(
	USER_ID VARCHAR2(100),
	URL VARCHAR2(300),
	CONSTRAINT RESUME_URL_PK PRIMARY KEY(URL),
	CONSTRAINT RESUME_URL_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);
ALTER TABLE TABLE_RESUME_URL ADD URL_TYPE VARCHAR2(100);

INSERT INTO HR.TABLE_USER_APPLY
(NUM, USER_ID, JOBS_NUM, APPLY_DATE, APPLY_STATUS, APPLY_RESULT)
VALUES(APPLY_SEQ.NEXTVAL, 'akdlwb33', 4, SYSDATE, '열람', '합격');

INSERT INTO HR.TABLE_USER_SCRAP 
(NUM, USER_ID, JOBS_NUM)
VALUES(SCRAP_SEQ.NEXTVAL, 'akdlwb33', 4);

SELECT * FROM TABLE_RESUME_URL;



