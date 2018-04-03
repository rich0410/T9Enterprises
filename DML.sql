INSERT INTO Teacher(TeacherID, FirstName, LastName, EmailAddress) VALUES
('bigg2212', 'Roland', 'Biggs', 'bigg2212@algonquincollege.com'),
('jeff2364', 'Jack', 'Jefferson', 'jeff2364@algonquincollege.com'),
('bert0443', 'Hugo', 'Bertrani', 'bert0443@algonquincollege.com'),
('akil2020', 'Jeff', 'Akil', 'akil2020@algonquincollege.com'),
('isab0023', 'Alfonso', 'Isabella', 'isab0023@algonquincollege.com'),
('vone6744', 'Sigfried', 'VonEden', 'vone6744@algonquincollege.com'),
('snyd9954', 'Erin', 'Snyder', 'snyd9954@algonquincollege.com'),
('muld2247', 'Hanna', 'Mulder', 'muld2247@algonquincollege.com');


INSERT INTO Student(StudentID, FirstName, LastName, EmailAddress)VALUES
('jenk2002', 'John', 'Jenkins', 'jenk2002@algonquinlive.com'),
('morn2332', 'Alice', 'Morner', 'morn2332@algonquinlive.com'),
('tyle2343', 'Eric', 'Tyler', 'tyle2343@algonquinlive.com'),
('scot2456', 'Naiomi', 'Scott', 'scot2456@algonquinlive.com'),
('rich0067', 'Nick', 'Richards', 'rich0067@algonquinlive.com'),
('hall2446', 'Alex', 'Hall', 'hall2446@algonquinlive.com')
;

INSERT INTO Admin(AdminID, FirstName, LastName, EmailAddress)VALUES
('dens1234', 'Dave', 'Denison', 'dens1234@algonquincollege.com')
;

INSERT INTO Courses(CourseCode, LabLecture, Title) VALUES
('CST9245-010', 0, 'Electro-Engineering'),
('CST9245-014', 1, 'Electro-Engineering'),
('CST4435-010', 0, 'Java Programming'),
('CST4435-012', 1, 'Java Programming'),
('CST1134-010', 0, 'Intro to Web'),
('CST1134-013', 1, 'Intro to Web')
;

INSERT INTO Schedule(TeacherID, CourseCode, Duration, DayOfTheWeek, StartTime, RoomNumber) VALUES
('bigg2212', 'CST9245-010', 2, 'Wed', '13:00:00', 'T130'),
('jeff2364', 'CST4435-010', 1, 'Tue', '08:00:00', 'T210'),
('bert0443', 'CST9245-014', 1, 'Thu', '09:00:00', 'T109'),
('isab0023', 'CST1134-013', 3, 'Fri', '16:00:00', 'P213'),
('snyd9954', 'CST4435-012', 2, 'Mon', '19:00:00', 'B220')
;

INSERT INTO OfficeHours(TeacherID, DayOfTheWeek, Time, RoomNumber, Available) VALUES
('akil2020', 'Wed', '09:00:00', 'B231', 1),
('snyd9954', 'Tue', '12:00:00', 'T315', 1),
('bigg2212', 'Thu', '15:00:00', 'T317', 1),
('jeff2364', 'Fri', '13:00:00', 'J115', 1),
('snyd9954', 'Thu', '12:00:00', 'B404', 1),
('isab0023', 'Wed', '17:00:00', 'E410', 1),
('muld2247', 'Mon', '18:00:00', 'B201', 1),
('bigg2212', 'Mon', '19:00:00', 'B343', 1)
;

INSERT INTO StudentCourses(StudentID, CourseCode) VALUES
('jenk2002', 'CST9245-010'),
('jenk2002', 'CST4435-012'),
('morn2332', 'CST1134-010'),
('scot2456', 'CST4435-010'),
('hall2446', 'CST9245-010')
;