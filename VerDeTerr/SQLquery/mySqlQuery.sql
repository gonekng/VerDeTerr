CREATE TABLE `Member` (
    ID VARCHAR(20) NOT NULL,
    PW VARCHAR(20) NOT NULL,
    PWConfirm VARCHAR(20) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Nickname VARCHAR(100) NOT NULL,
    Age VARCHAR(20) NOT NULL,
    Gender VARCHAR(20) NOT NULL,
    UserType VARCHAR(20) NOT NULL,
    ManagerYn BOOLEAN NOT NULL,
    RegDate DATETIME NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE `Mbti_ML` (
    ID VARCHAR(20) NOT NULL,
    Answer1 Text(100000) NOT NULL,
    Answer2 Text(100000) NOT NULL,
    Answer3 Text(100000) NOT NULL,
    Answer4 Text(100000) NOT NULL,
    Answer5 Text(100000) NOT NULL,
    Answer6 Text(100000) NOT NULL,
    Answer7 Text(100000) NOT NULL,
    Answer8 Text(100000) NOT NULL,
    Answer9 Text(100000) NOT NULL,
    Answer10 Text(100000) NOT NULL,
    Answer11 Text(100000) NOT NULL,
    Answer12 Text(100000) NOT NULL,
    Answer13 Text(100000) NOT NULL,
    Answer14 Text(100000) NOT NULL,
    Answer15 Text(100000) NOT NULL,
    Answer16 Text(100000) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE `Mbti_ML_Output` (
    ID VARCHAR(20) NOT NULL,
	UserAnswer Text(100000) NOT NULL,
    UserType VARCHAR(20) ,
    PRIMARY KEY (ID)
);

CREATE TABLE `Mbti_Type` (
    UserType VARCHAR(20) NOT NULL,
    UserCharacter VARCHAR(1000) NOT NULL,
    Job VARCHAR(1000) NOT NULL,
    Feature VARCHAR(5000) NOT NULL,
    PRIMARY KEY (UserType)
);

CREATE TABLE `Post` (
    Idx INTEGER AUTO_INCREMENT NOT NULL,
    Title VARCHAR(200) NOT NULL,
    Content VARCHAR(5000) NOT NULL,
    Writer VARCHAR(100) NOT NULL,
    ViewCnt INTEGER NOT NULL,
    NoticeYn BOOLEAN NOT NULL,
    DeleteYn BOOLEAN not null,
    InsertTime DATETIME NOT NULL,
    UpdateTime DATETIME,
    DeleteTime DATETIME,
    PostType VARCHAR(20), 
    PRIMARY KEY (Idx)
);

CREATE TABLE `Comment` (
    Idx INTEGER auto_increment NOT NULL,
    BoardIdx INTEGER NOT NULL,
    Content VARCHAR(3000) NOT NULL,
    Writer VARCHAR(100) NOT NULL,
    DeleteYn BOOLEAN NOT NULL,
    InsertTime DATETIME NOT NULL,
    UpdateTime DATETIME,
    DeleteTime DATETIME,
    PostType VARCHAR(20),
    PRIMARY KEY (Idx)
);