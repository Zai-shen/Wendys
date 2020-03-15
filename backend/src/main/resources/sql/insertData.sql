-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO owner (ID, NAME, CREATED_AT, UPDATED_AT)
VALUES (1, 'Fred', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'Julia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'Kim', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO horse (ID, NAME, DESCRIPTION, RATING, BIRTH_DAY, CREATED_AT, UPDATED_AT)
VALUES (1, 'FirstHorse', 'hello, issa me luigi', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'SecondHorse', 'NULL oder so', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'ThorDHorse', 'descriptionAndStuff', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

