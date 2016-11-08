# Good Life Web App

## Overview
This web app will be built for Good Life in order to build a social media app for their students. 

## Installation
### Required Softwares
- Eclipse IDE for Java EE Developers
- Install M2E
- MySQL

## Setup
### Database
- Create schema called **goodlife**
- Create user called **goodlifeadmin** and password **goodlife** 
- Make sure to grant *ALL* privileges to user *goodlifeadmin*

### Server(in Eclipse)
Create a new server in Eclipse by going to the server tab on the bottom and right clicking inside the tab and add a new apache tomcat 7.0 server. 

*Note:  If there is no Run time env found and you are using windows, click "download and install" button.  If you are using mac and the button is not enabled, download tomcat and point tomcat RTE to this installation directory.*

## Configurations
### Import Project (Non GitHub)
1. Open Eclipse
2. File -> Import
3. Go to folder labeled *Maven* and inside folder click *Existing Maven Project*
4. Under Root Directory find the project
5. Click Finish

### Maven 
#### Dependencies
1. Right click Project and go to Run As -> Run Configurations
2. Right click maven Build and press *New*
3. For Name, type in *Dependencies*
4. For Base Directory, Press the button called *Variables*. Find *project_loc* and press okay
5. Under goals write *eclipse:eclipse*
6. Press the button *Add* and under name type *wtpversion* and under value type *2.0*. 
7. Press Apply 
8. Press Run

#### Clean Install
1. Right click Project and go to Run As -> Run Configurations
2. Right click maven Build and press *New*
3. For name, type in *Clean install*
4. For Base Directory, Press the button called *Variables*. Find *project_loc* and press okay
5. Under goals write *clean install*
6. Press Apply

### Server 
1. Go to the *Servers* tab
2. Right click the Apache Tomcat Server 7.0 and press *Add and Remove*
3. Add the GoodLife Project to the Server

## Startup
1. Right click project. Run the project with the maven label *Clean Install*
2. Go to the server tab. Right click the tomcat server and press clean. Then, right click and press publish. Make sure it says Stop, Synchronized. Now, right click and press Start. 
3. Check the logs to make sure there is no problem with the startup of the server
4. If no problems, then visit <http://localhost:8080/good-life>

## SQL Statements
```
insert into users values('thomas.fuentes@tsgforce.com',true,25,'raj',curdate(),'goodlife')
insert into user_roles (username,role) values('thomas.fuentes@tsgforce.com','ROLE_SUPERADMIN')
insert into users (email, usr_nm, invit_cd, pwd, rgstrd, role_typ_cd)
values ('dhaval.raj@tsgforce.com','dhavalr',123456, 'password',1, '1');
commit;

```