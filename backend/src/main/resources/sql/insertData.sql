-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO owner (ID, NAME, CREATED_AT, UPDATED_AT)
VALUES (1, 'Fred', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'Julia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'Kim', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO breeds (breed_enum)
VALUES ('ARABIAN'),
       ('MORGAN'),
       ('PAINT'),
       ('APPALOOSA');

INSERT INTO horse (ID, NAME, DESCRIPTION, RATING, BIRTH_DAY, BREED, IMAGE, CREATED_AT, UPDATED_AT)
VALUES (1, 'FirstHorse', 'hello, issa me luigi', 5, CURRENT_TIMESTAMP(), NULL, NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'SecondHorse', 'NULL oder so', 3, CURRENT_TIMESTAMP(), 'ARABIAN', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'ThorDHorse', 'descriptionAndStuff', 4, CURRENT_TIMESTAMP(), NULL, 'testjeifz783h3jf93j0ur20jf9u', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (4, '4Horse', NULL, 2, CURRENT_TIMESTAMP(), 'paint', 'whatev er', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

