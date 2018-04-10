# T9Enterprises
The repository for our project - Software Development Project
The Algonquin College Kiosk System

This system is built to enhance communication between the students and the professors at Algonquin College
using an ability to book office hours with a professor of theirs. 

To set up the T9-Enterprise Kiosk Software :
Run the MySql Web installer - Located in the source folder - and follow the basic installation instructions in the installer.

After MySql has installed, open the command prompt, and;

Change Directory to the bin folder using "cd C:\Program Files\MySQL\MySQL Server 5.7\bin"
Login to the root user using "MySql -u root -p" and enter the administrator password for that computer system
Run the Data Definition Language text file to build the database using "source" followed by the path to the folder with the
   "DDL.txt" and "DML.txt" file added on. 
   Example - source Downloads\Algonquin-Kiosk\DDL.txt; source Downloads\Algonquin-Kiosk\DML.txt;  
That will run the scripts to set up the database. The name of the Database is Algonquin-Kiosk.

Proceeding setting up the database, you can begin to run the executable jar file included with the source code to create the kiosk program,
as it opens to the login screen and can be put into full screen. The login will use either the Student, Professor, or Administrator 
profiles to use the software functionality.

#Screen Shots 
![Alt text]( https://github.com/rich0410/T9Enterprises/blob/lastt/ScreenShots/Capture%201.PNG?raw=true "SreenShot 1")
![Alt text](/ScreenShots/Capture2.png?raw=true "SreenShot 2")
![Alt text](/ScreenShots/Capture 3.png?raw=true "SreenShot 3")
![Alt text](/ScreenShots/Capture 4.png?raw=true "SreenShot 4")
![Alt text](/ScreenShots/Capture 5.png?raw=true "SreenShot 5")
![Alt text](/ScreenShots/Capture6.png?raw=true "SreenShot 6")
![Alt text](/ScreenShots/Capture7.png?raw=true "SreenShot 7")
