DELETE FROM chapteree;
INSERT INTO chapteree (first_name, last_name, chapter, level, coach_id, customer_id)
VALUES ('Kostas', 'Konstantinou', 'Java', 'MID', '2', NULL),
       ('Anna','Pratikaki','ArtAndMotion', 'HIGH', null, '2'),
       ('Dimitris','Anagnostakis','DevOps', 'LOW', '2', NULL),
        ('Nikos', 'Nikolakis', 'JavascriptFrontend', 'MID', '1', '2'),
       ('Anastasis','Georgiou','AnalyticsAndLiterature', 'HIGH', '3', NULL),
       ('Filippos','Koniaris','ManagementAndGardening', 'LOW', '1', NULL);

INSERT INTO customer (first_name, last_name)
VALUES ('Ioannis', 'Nikitas'),
       ('Menelaos', 'Nikitakis'),
       ('John', 'Johniou');