-- You can copy and paste this whole document into the MySQL Workbench sql editor and it will execute all below
-- Dummy data for testing the UI
-- Run a clean install and refresh just to make sure everything is up to date
-- If you have an outdated version of the schema it might be a good idea enter the following sql:
-- drop schema goodlife; create schema goodlife; commit;
-- Then redeploy and let hibernate populate the schema for you

USE goodlife;

-- Delete old data from tables
DELETE FROM USERS WHERE 1=1;
-- Reset the auto increment
ALTER TABLE USERS AUTO_INCREMENT = 1;
-- Insert 12 different users, 5 students, 4 admins, 4 facilitators
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force0@tsgforce.com','Hello.','Chicago','force',101010,'force0',current_time(),'0','goodlife','http://google.com',current_time(),true,'a','IL','force0');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force1@tsgforce.com','Hello.','Chicago','force',101011,'force1',current_time(),'1','goodlife','http://google.com',current_time(),true,'s','IL','force1');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force2@tsgforce.com','Hello.','Chicago','force',101012,'force2',current_time(),'2','goodlife','http://google.com',current_time(),true,'f','IL','force2');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force3@tsgforce.com','Hello.','Chicago','force',101013,'force3',current_time(),'3','goodlife','http://google.com',current_time(),true,'a','IL','force3');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force4@tsgforce.com','Hello.','Chicago','force',101014,'force4',current_time(),'4','goodlife','http://google.com',current_time(),true,'s','IL','force4');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force5@tsgforce.com','Hello.','Chicago','force',101015,'force5',current_time(),'5','goodlife','http://google.com',current_time(),true,'f','IL','force5');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force6@tsgforce.com','Hello.','Chicago','force',101016,'force6',current_time(),'6','goodlife','http://google.com',current_time(),true,'a','IL','force6');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force7@tsgforce.com','Hello.','Chicago','force',101017,'force7',current_time(),'7','goodlife','http://google.com',current_time(),true,'s','IL','force7');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force8@tsgforce.com','Hello.','Chicago','force',101018,'force8',current_time(),'8','goodlife','http://google.com',current_time(),true,'f','IL','force8');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force9@tsgforce.com','Hello.','Chicago','force',101019,'force9',current_time(),'9','goodlife','http://google.com',current_time(),true,'a','IL','force9');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force10@tsgforce.com','Hello.','Chicago','force',101020,'force10',current_time(),'10','goodlife','http://google.com',current_time(),true,'s','IL','force10');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force11@tsgforce.com','Hello.','Chicago','force',101021,'force11',current_time(),'11','goodlife','http://google.com',current_time(),true,'f','IL','force11');
insert into goodlife.users 
(usr_nm,rgstrd,pwd,invit_by,invit_dt,invit_cd,role_typ_cd,email)
values('Admin',1,'$2a$10$XH8Xm5r/Rx8fJAZHy2qxyO2xT8WgDfU1QUeZHYI/f95s5CduO8YU.','force0',current_date(),100003,'A','admin@gmail.com');
insert into goodlife.users 
(usr_nm,rgstrd,pwd,invit_by,invit_dt,invit_cd,role_typ_cd,email)
values('student',false,'temp','Admin',current_date(),100004,'S','student@gmail.com');

DELETE FROM STUDENT WHERE 1=1;
INSERT INTO STUDENT
(USERID,CURRENTCHAPTERID,INSTRUCTOR_ID,START_DT)
VALUES(2,1,3,current_time());
INSERT INTO STUDENT
(USERID,CURRENTCHAPTERID,INSTRUCTOR_ID,START_DT)
VALUES(5,1,6,current_time());
INSERT INTO STUDENT
(USERID,CURRENTCHAPTERID,INSTRUCTOR_ID,START_DT)
VALUES(8,1,9,current_time());
INSERT INTO STUDENT
(USERID,CURRENTCHAPTERID,INSTRUCTOR_ID,START_DT)
VALUES(11,1,12,current_time());
INSERT INTO STUDENT
(USERID,CURRENTCHAPTERID,INSTRUCTOR_ID,START_DT)
VALUES(13,1,3,current_time());

DELETE FROM SUPER_ADMIN WHERE 1=1;
INSERT INTO SUPER_ADMIN
(USERID)
VALUES(1);
INSERT INTO SUPER_ADMIN
(USERID)
VALUES(4);
INSERT INTO SUPER_ADMIN
(USERID)
VALUES(7);
INSERT INTO SUPER_ADMIN
(USERID)
VALUES(10);

DELETE FROM INSTRUCTOR WHERE 1=1;
ALTER TABLE INSTRUCTOR AUTO_INCREMENT = 1;
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,3);
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,6);
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,9);
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,12);

DELETE FROM USER_STATUS WHERE 1=1;
ALTER TABLE USER_STATUS AUTO_INCREMENT = 1;
INSERT INTO USER_STATUS
(END_DT,STRT_DT,STS_TYP_CD,USERID)
VALUES('2015-12-31 07:09:09',current_timestamp(),'s',2);
INSERT INTO USER_STATUS
(END_DT,STRT_DT,STS_TYP_CD,USERID)
VALUES('2015-04-30 07:09:09',current_timestamp(),'s',5);
INSERT INTO USER_STATUS
(END_DT,STRT_DT,STS_TYP_CD,USERID)
VALUES('2015-07-31 07:09:09',current_timestamp(),'d',8);

-- Verify that the data went into the tables
SELECT * FROM USERS;
SELECT * FROM STUDENT;
SELECT * FROM SUPER_ADMIN;
SELECT * FROM INSTRUCTOR;
SELECT * FROM USER_STATUS;

DELETE FROM GOODLIFE.CHAPTER WHERE 1=1;
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(1,'Reality Check','',1,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(2,'Inner GPS','',2,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(3,'Soul Bling','',3,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(4,'Your Crew','',4,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(5,'Pimp My Ride','',5,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(6,'I Have A Dream 2','',6,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(7,'Obstacle Or Opportunity','',7,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(8,'Life''s Hurricanes','',8,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(9,'The Good Life Now!','',9,TRUE);
INSERT INTO GOODLIFE.CHAPTER
(CHAP_ID,CHAP_TITLE,CHAP_DESCR,ORDER_ID,PUBLISHED)
VALUES(10,'The Great Life Legacy','',10,TRUE);

DELETE FROM GOODLIFE.SUBCHAPTER WHERE 1=1;
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(1,1,'What is success?\n"Reality is that which, when you stop believing in it, doesn''t go away."\n-Phillip K. Dick','Reality Check...One...Two',1,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(2,1,'"Influence can be defined as "the act or power of producing an effect without apparent exertion or force or direct exercise of command."','Getting Critical (critical thinking)',2,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(3,1,'Power can be defined a the ability to define reality and influence others to accept that definition as if it were their own.','Redefintion (creative reflection)',3,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(4,1,'Quiz a friend or family member on the questions provided in the Reality Check...One...Two exercise in this chapter.','Mic Check (interview)',4,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(5,1,'Reality Check','Word Up (creative writing)',5,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(6,1,'','Dropping Science',6,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(7,1,'','Getting Up (graff wall)',7,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(8,1,'SPARK SONG. \nAfter listening to track 1 on the program CD, answer the following questions:','Radio Retaliation',8,TRUE);
INSERT INTO GOODLIFE.SUBCHAPTER
(SUB_CHAP_ID,CHAPID,SUB_CHAP_DESCR,SUB_CHAP_TITLE,ORDER_ID,PUBLISHED)
VALUES(9,1,'','Pic Perspective',9,TRUE);

DELETE FROM GOODLIFE.MULTI_CHOICE_LIST WHERE 1=1;
INSERT INTO GOODLIFE.MULTI_CHOICE_LIST
(MC_LIST_ID,SUBCHAPID,TITLE,DESCR,ORDER_ID,PUBLISHED,GRADED)
VALUES(1,1,'Complete the questionaire below.','Are you grounded in reality or living a fantasy?',1,TRUE,TRUE);

DELETE FROM GOODLIFE.MULTI_CHOICE_Q WHERE 1=1;
INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,MULTICHOICELISTID,ORDER_ID,PUBLISHED)
VALUES(1,'A person who has a nice car, big house, great career, and makes a lot of money is always happy.','',2,1,1,TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,MULTICHOICELISTID,ORDER_ID,PUBLISHED)
VALUES(2,'There are companies on Wall Street that make billions of dollars a year sending people, especially young people, to jail.','',3,1,2,TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,MULTICHOICELISTID,ORDER_ID,PUBLISHED)
VALUES(3,'Most people who are in prisons graduated from high school.','',6,1,3,TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,MULTICHOICELISTID,ORDER_ID,PUBLISHED)
VALUES(4,'Dispite the fact that teens today have more money and "stuff" than any other generation, they are the most depressed generation to ever live.','',7,1,4,TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,MULTICHOICELISTID,ORDER_ID,PUBLISHED)
VALUES(5,'Although youth crime rates hae significantly been on the declin, television reporting on youth crime has increased.','',9,1,5,TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,MULTICHOICELISTID,ORDER_ID,PUBLISHED)
VALUES(6,'This generation is more dependent on media for information about the world and themselves than any other generation that has ever lived.','',11,1,6,TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_Q
(MC_Q_ID,Q_TXT,HELP_TXT,CORR_ANS,MULTICHOICELISTID,ORDER_ID,PUBLISHED)
VALUES(7,'The suicide rate among teens today is lower than it has ever been.','',14,1,7,TRUE);

DELETE FROM GOODLIFE.MULTI_CHOICE_OPTION WHERE 1=1;
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(1,1,'Reality',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(2,1,'Fantasy',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(3,2,'Reality',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(4,2,'Fantasy',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(5,3,'Reality',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(6,3,'Fantasy',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(7,4,'Reality',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(8,4,'Fantasy',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(9,5,'Reality',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(10,5,'Fantasy',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(11,6,'Reality',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(12,6,'Fantasy',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(13,7,'Reality',TRUE);
INSERT INTO GOODLIFE.MULTI_CHOICE_OPTION
(OPTION_ID,MULTIQUESID,CHOICE_TXT,PUBLISHED)
VALUES(14,7,'Fantasy',TRUE);

DELETE FROM GOODLIFE.MC_USER_ANS WHERE 1=1;
INSERT INTO GOODLIFE.MC_USER_ANS
(USERID,MULTIQUESID,USR_ANS)
VALUES(14,1,2);
INSERT INTO GOODLIFE.MC_USER_ANS
(USERID,MULTIQUESID,USR_ANS)
VALUES(14,2,3);
INSERT INTO GOODLIFE.MC_USER_ANS
(USERID,MULTIQUESID,USR_ANS)
VALUES(14,3,6);
INSERT INTO GOODLIFE.MC_USER_ANS
(USERID,MULTIQUESID,USR_ANS)
VALUES(14,4,7);
INSERT INTO GOODLIFE.MC_USER_ANS
(USERID,MULTIQUESID,USR_ANS)
VALUES(14,5,9);
INSERT INTO GOODLIFE.MC_USER_ANS
(USERID,MULTIQUESID,USR_ANS)
VALUES(14,6,11);
INSERT INTO GOODLIFE.MC_USER_ANS
(USERID,MULTIQUESID,USR_ANS)
VALUES(14,7,14);

DELETE FROM GOODLIFE.UPLOAD_FILE_Q WHERE 1=1;
INSERT INTO GOODLIFE.UPLOAD_FILE_Q
(UP_Q_ID,DESCR,SUBCHAPID,HELP_TXT,PUBLISHED)
VALUES(1,'1. Listen to the Masculinity Poem found on track 2 of the program CD. Think about the consequences of living this media-generated idea of what it means to be a "Man" or even being a "Woman." Is this something substainable or even fufulling? \n2. Show your real power by defining for yourself what a "Real Man" or "Real Woman" is. Write as many of your ideas as you can.',3,'',TRUE);
INSERT INTO GOODLIFE.UPLOAD_FILE_Q
(UP_Q_ID,DESCR,SUBCHAPID,HELP_TXT,PUBLISHED)
VALUES(2,'Write creatively about a time when you thought something was reality but realized that it was a fantasy. This can include a time when you thought someone was a real friend and they proved to not be, or when you learned something that changed your way of thinking.',5,'I had a reality check when...',TRUE);
INSERT INTO GOODLIFE.UPLOAD_FILE_Q
(UP_Q_ID,DESCR,SUBCHAPID,HELP_TXT,PUBLISHED)
VALUES(3,'Draw a picture with the theme reality check.',7,'',TRUE);

DELETE FROM GOODLIFE.UPLOADED_ANS WHERE 1=1;

DELETE FROM GOODLIFE.CHAPTER_PAGE WHERE 1=1;
INSERT INTO GOODLIFE.CHAPTER_PAGE
(PAGE_ID,CHAPID,PAGE_NUM,PAGE_URL,PUBLISHED)
VALUES(1,1,1,'HTTP://GOOGLE.COM',TRUE);
INSERT INTO GOODLIFE.CHAPTER_PAGE
(PAGE_ID,CHAPID,PAGE_NUM,PAGE_URL,PUBLISHED)
VALUES(2,1,2,'HTTP://GO.ESPN.COM',TRUE);
INSERT INTO GOODLIFE.CHAPTER_PAGE
(PAGE_ID,CHAPID,PAGE_NUM,PAGE_URL)
VALUES(3,2,1,'HTTP://GOOGLE.COM');

DELETE FROM GOODLIFE.SHORT_ANS_Q WHERE 1=1;
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(1,2,'When and where did Hiphop culture start? Who created and controlled the direction it was headed?','',1,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(2,2,'What are the five elements of Hiphop culture?','',2,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(3,2,'When did Big Business get interested in Hiphop?','',3,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(4,2,'What products does the Hiphop industry sell? What does G-unit sell?','',4,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(5,2,'What makes this generation unique from other generations?','',5,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(6,2,'What industries are the largest profiteers from this generation?','',6,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(7,2,'Who dictates what direction Hiphop heads in today?','',7,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(8,2,'The author said that in order for us to be grounded in reality we might have to unlearn some things. Why do you think he said this?','',8,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(9,4,'I interviewed','(write the name of the person you interviewed below)',1,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(10,4,'Based on the amount of questions you got right, do you think you are grounded in reality or fantasy?','',2,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(11,4,'Why do advertisers want us to believe the hype about the Good Life being connected to having more things?','',3,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(12,4,'What do you think about companies making money off young people going to jail?','',4,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(13,4,'What do you think could happen if people continue to profit off of the suffering of others?','',5,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(14,4,'Do you have any ideas about what could be done to change things? What are they?','',6,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(15,6,'What is most influencing you right now?','',1,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(16,6,'Do you know anyone who is headed down the track to destruction? Who?','',2,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(17,6,'Do you know anyone who is on the track of distraction? Who?','',3,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(18,6,'Looking at the picture of the train, what direction are you headed in?','',4,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(19,6,'Do you know anyone who is on the track of their dream? Who?','',5,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(20,6,'What will happen if you continue in that direction?','',6,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(21,6,'What is your dream?','',7,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(22,6,'What resources can help you to head toward your dreams?','',8,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(23,6,'What positive influences can you connect to that will help you pursue your dreams?','',9,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(24,8,'What does a spark represent to you?','',1,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(25,8,'According to the rapper in the 1st verse, what are some ways that we can be blinded or distracted from finding our sparks?','',2,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(26,8,'Do you think someone can reach their potential if they only focus on their weaknesses? Why or why not?','',3,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(27,8,'In the first verses, the rapper says, "You''re like a spark in a fire pit, yall but if you desired it/ You could light all this fire wood up and change your environment/" How could finding your spark transform you?','',4,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(28,8,'How could finding your spark and turning it into a flame transform your environment?','',5,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(29,8,'This program is designed to help uncover the resource of your purpose, passion, people, and pain so you can have the power to start fufilling your dreams. What are some of the things in your life that are helping you to move towards your true power and potential?','',6,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(30,8,'What one thing would you say is most helping you to turn your spark into a fire?','',7,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(31,8,'What one thing is more distracting you from turning your spark into a fire?','',8,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(32,9,'What are the names of each subway line?','',1,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(33,9,'Name two stops that come before the final stop for each subway line?','',2,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(34,9,'All the trains have similar resources onboard indicated by the graffiti outside of the car. What are the four resources?','',3,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(35,9,'At any point on the journey, you can transfer to another line by taking the underground tunnels that connect each subway train. Based off what is surrounding these blank spots fill in the blanks.','',4,TRUE);
INSERT INTO GOODLIFE.SHORT_ANS_Q
(SA_Q_ID,SUBCHAPID,QUESTION,HELP_TXT,ORDER_ID,PUBLISHED)
VALUES(36,9,'Can you name a couple people that are on each subway line? what line are you currently on? what line do you think wil bring you the most fufillment?','',5,TRUE);

DELETE FROM GOODLIFE.SHORT_ANS_USER_ANS WHERE 1=1;
