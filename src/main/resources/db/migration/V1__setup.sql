create table chapteree
(
	chapteree_id  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name   varchar(30) NOT NULL,
    last_name  varchar(30) NOT NULL,
    chapter		varchar(30) NOT NULL,
    level		varchar(30) NOT NULL,
	PRIMARY KEY (chapteree_id)
);