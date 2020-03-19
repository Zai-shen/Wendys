-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO owner (ID, NAME, CREATED_AT, UPDATED_AT)
VALUES (1, 'Fred', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'Julia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'Kim', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO horse (ID, OWNER_ID, NAME, DESCRIPTION, RATING, BIRTH_DAY, BREED, IMAGE, CREATED_AT, UPDATED_AT)
VALUES (1, 1,   'FirstHorse',   'hello, issa me', 5, '2011-01-25T21:34:55', 'paint',   'auhwudhwu288282', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 1,   'SecondHorse',  'NULL oder so',   3, '2012-01-25T21:34:55', 'ARABIAN', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, null,'ThorDHorse',   'descriptionBla', 4, '2013-01-25T21:34:55', NULL,      'testjeifz783h3jf93j0ur20jf9u', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (4, 2,   '4Horse',       NULL,             2, CURRENT_TIMESTAMP(),   NULL,      NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

