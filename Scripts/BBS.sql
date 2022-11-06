/*질문 게시판 테이블 시퀀스*/
CREATE SEQUENCE BBS_NUM_SEQ;

/*질문 게시판 테이블*/
CREATE TABLE TABLE_BBS(
	USER_ID VARCHAR2(100),
	BBS_NUM NUMBER(10),
	BBS_DATE DATE,
	BBS_TITLE VARCHAR2(1000),
	BBS_JOB VARCHAR2(100),
	BBS_CATEGORY VARCHAR2(100),
	BBS_CONTENT VARCHAR2(3000),
	BBS_READCOUNT NUMBER(10),
	CONSTRAINT BBS_PK PRIMARY KEY(BBS_NUM),
	CONSTRAINT BBS_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_USER(USER_ID)
);
INSERT INTO TABLE_BBS 
VALUES( 'wnsrbod', BBS_NUM_SEQ.NEXTVAL , SYSDATE ,'면접 복장 질문입니다.','건설직','면접 관련 질문', '면접 보러 갈 때 어떤 복장이 괜찮을까요?', 0);

SELECT * FROM (SELECT ROWNUM R, T.* FROM (SELECT * FROM TABLE_BBS B ORDER BY 1 DESC)T)S;
/*WHERE S.R > 10 AND S.R < 21*/


INSERT INTO TABLE_BBS_COMMENT 
VALUES( BBS_COMMENT_NUM_SEQ.NEXTVAL , 1, 'korea1234' , SYSDATE ,'검은 정장과 검은 구두를 추천합니다.');

/*질문 게시판의 첨부파일 테이블*/
CREATE TABLE TABLE_BBS_FILES(
	BBS_FILE_NAME VARCHAR2(100),
	BBS_NUM NUMBER(10),
	CONSTRAINT BBS_FILES_PK PRIMARY KEY(BBS_FILE_NAME),
	CONSTRAINT BBS_FILES_FK FOREIGN KEY(BBS_NUM) REFERENCES TABLE_BBS(BBS_NUM)
);

SELECT * FROM TABLE_BBS WHERE BBS_CATEGORY = '면접 관련 질문';
SELECT * FROM TABLE_BBS_FILES;
SELECT * FROM TABLE_BBS;

