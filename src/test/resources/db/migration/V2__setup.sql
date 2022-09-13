DELETE FROM chapteree;
INSERT INTO chapteree (first_name, last_name, chapter, level, customer_id)
VALUES ('Kostas', 'Konstantinou', 'Java', 'MID', NULL),
       ('Anna','Pratikaki','ArtAndMotion', 'HIGH', '2'),
       ('Dimitris','Anagnostakis','DevOps', 'LOW', NULL),
        ('Nikos', 'Nikolakis', 'JavascriptFrontend', 'MID', '2'),
       ('Anastasis','Georgiou','AnalyticsAndLiterature', 'HIGH', NULL),
       ('Filippos','Koniaris','ManagementAndGardening', 'LOW', NULL);

INSERT INTO customer (first_name, last_name)
VALUES ('Ioannis', 'Nikitas'),
       ('Menelaos', 'Nikitakis'),
       ('John', 'Johniou');
