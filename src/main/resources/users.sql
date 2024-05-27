DROP TABLE IF EXISTS SUSERS;
CREATE TABLE IF NOT EXISTS SUSERS
(
    user_id identity primary key,
    user_guid varchar(50) not null,
    user_name varchar(50) not null
);

