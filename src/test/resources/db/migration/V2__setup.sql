DELETE FROM chapteree;
INSERT INTO chapteree (first_name, last_name, chapter, level, coach_id)
VALUES ('Kostas', 'Konstantinou', 'Java', 'MID', '2'),
       ('Anna','Pratikaki','ArtAndMotion', 'HIGH', null),
       ('Dimitris','Anagnostakis','DevOps', 'LOW', '2'),
        ('Nikos', 'Nikolakis', 'JavascriptFrontend', 'MID', '1'),
       ('Anastasis','Georgiou','AnalyticsAndLiterature', 'HIGH', '3'),
       ('Filippos','Koniaris','ManagementAndGardening', 'LOW', '1');

INSERT INTO customer (first_name, last_name)
VALUES ('Ioannis', 'Nikitas'),
       ('Menelaos', 'Nikitakis'),
       ('John', 'Johniou');


INSERT INTO chapteree_customer(chapteree_id, customer_id)
VALUES ('1', '2'),
       ('1', '3'),
       ('2', '2');