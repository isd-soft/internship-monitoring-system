insert into internships values (
    'f4a13c2d-68b5-4153-9837-dd43f49c1b8a',
    'JAVA',
    'C:\Users\admin\Desktop\internship-monitoring-system',
    'https://github.com/isd-soft/internship-monitoring-system.git',
    'IN_PROGRESS',
    '2022-03-14',
    '2022-04-08',
    'C:\Users\admin\Desktop\internship-monitoring-system\presentation',
    'Internship Monitoring System',
    'https://trello.com/b/UmlBCRKR/internship-monitoring-system');

insert into internships values(
    '7f6a5399-8c1d-4083-a0d7-7084666182dc',
    'JAVA',
    'C:\Users\admin\Desktop\parking',
    'https://github.com/isd-soft/parking.git',
    'DONE',
    '2020-03-14',
    '2020-04-08',
    'C:\Users\admin\Desktop\parking\presentation',
    'ISD Parking',
    'https://trello.com/b/UmlBCRKR/parking');
insert into internships values (
    '8009c92e-37f3-411a-a7b8-53edc2ba9a71',
    'JAVA',
    'C:\Users\admin\Desktop\employee-performance-evaluator',
    'https://github.com/isd-soft/employee-performance-evaluator.git',
    'DONE',
    '2021-12-06',
    '2021-12-30',
    'C:\Users\admin\Desktop\employee-performance-evaluator\presentation',
    'Employee performance evaluator',
    'https://trello.com/b/UmlBCRKR/employee-performance-evaluator');


DELETE FROM internships;

insert into tech_question_list values
    ('f4a13c2d-68b5-4153-9837-dd43f49c1b8a', 'Java technical questions 1'),
    ('9af351cf-7edc-4e60-a4e6-1dd2f650e135', 'Java technical questions 2'),
    ('c64138cc-6279-425a-b6f6-91a0b2044654', 'Java technical questions 3'),
    ('c5e161b2-4941-4edf-8ac7-af229baeeece', 'Java technical questions 4');

insert into users (id, job_position, name, surname) values
('9989468f-e724-457c-b21b-cdb358d55fbd', 'JAVA', 'Oxana', 'Dunav'),
('518446b5-965e-41cc-b5de-8d9fb720c84f','JAVA', 'Nicolae', 'Sirbu'),
('0675c3c3-034b-463c-92af-0a9ab5e533c9','JAVA', 'Mentor1-name', 'Mentor1-surname'),
('11ec7a9f-ccd0-4064-874a-5f2ccbe55c65','JAVA', 'Mentor2-name', 'Mentor2-surname'),
('5f1b18cf-b537-4666-9f1a-a656807990e4','C_SHARP', 'Mentor3-name', 'Mentor3-surname'),
('c93d5dd5-60de-456b-a60b-da3aebab5872','C_SHARP', 'Mentor4-name', 'Mentor3-surname'),
('b0afdc21-a418-4270-8870-8e908bd244a1','C_SHARP', 'Mentor5-name', 'Mentor5-surname'),
('ae6e13ea-bfa5-45dc-8311-572d9ea4e065','C_SHARP', 'Mentor6-name', 'Mentor6-surname'),
('8a90fa82-571c-4ac5-9467-1e154bbc093d','PLC', 'Mentor7-name', 'Mentor7-surname'),
('7116febc-0fc3-43aa-868d-9a62c8076c62','PLC', 'Mentor8-name', 'Mentor8-surname'),
('dfaaae76-4a91-452b-bab4-3e7a56592d3b','PLC', 'Mentor9-name', 'Mentor9-surname');

insert into mentors_internships values
('f4a13c2d-68b5-4153-9837-dd43f49c1b8a', '9989468f-e724-457c-b21b-cdb358d55fbd'),
('f4a13c2d-68b5-4153-9837-dd43f49c1b8a', '9989468f-e724-457c-b21b-cdb358d55fbd');