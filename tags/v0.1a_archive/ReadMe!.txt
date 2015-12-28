Notes: To view the system, you can just follow section "3. Live Demo" at the bottom


========== 1. To Deploy ==========

--------- Requirement ---------
1. Apache Tomcat 6.x or newer
2. JDK/JRE 6
3. MySQL Server 5.x
4. MySQL GUI Tools

------------ Steps ------------

(notes: all the installer for Windows 32bit can be found in "installer" folder)
(p/s: all installation use default settings)

+++
1. Install JDK/JRE 6 (jdk-6u13-windows-i586-p.exe)
2. Install Apache Tomcat 6 (apache-tomcat-6.0.18.exe)
3. Install MySQL server 5 (mysql server 5.exe)
4. Install MySQL GUI tools (mysql-gui-tools-5.0-r16-win32.msi)
+++

5. Restore database using MySQL database dump provided using MySQL Administrator. The MySQL dump can be found inside "data" folder, named "latest 20090526 1859.sql". It will be restored as "hsr4ky_facetedopac"
6. Inside MySQL Administrator, add a new user (User Administration), with
   Username : hsr4ky_opac
   password : opac
7. Assign all privileges of schemata/databasa "hsr4ky_facetedopac" to the user "hsr4ky_opac" (perform this action in Schema Privilleges tab)
+++

8. Open folder "C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps" (default installation folder for Apache Tomcat 6)
9. Copy web archive (facetedOPAC.war) of faceted opac project to that folder. "facetedOPAC.war" can be found in "data" folder
+++

10. As long as the Apache Tomcat service is running, the web archive file will be extracted automatically.
11. Wait one or two minutes, open internet browser, and go to address "http://localhost:8080/facetedOPAC/index.html".
12. It should working now. Notes that internet explorer 7, Firefox 3 or newer are required for proper display and functioning.
    Hint: You may try restart your computer if it's not working after few more minutes
13. Password and username for Adminstrator in the system are both "admin".



========== 2. Create Development Environment ==========
--------- Requirement ---------
1. Apache Tomcat 6.x or newer
2. JDK/JRE 6
3. MySQL Server 5.x
4. MySQL GUI Tools
5. Netbeans 6.5.1 (with java Web/EE)

------------ Steps ------------
(notes: all the installer for Windows 32bit can be found in "installer" folder)
(p/s: all installation use default settings)

+++
1. Install JDK/JRE 6 (jdk-6u13-windows-i586-p.exe)
2. Install MySQL server 5 (mysql server 5.exe)
3. Install MySQL GUI tools (mysql-gui-tools-5.0-r16-win32.msi)
4. Install Netbeans 6.5.1 (netbeans-6.5.1-ml-windows.exe)
+++

5. Restore database using MySQL database dump provided using MySQL Administrator. The MySQL dump can be found inside "data" folder, named "latest 20090526 1859.sql". It will be restored as "hsr4ky_facetedopac"
6. Inside MySQL Administrator, add a new user (User Administration), with
   Username : hsr4ky_opac
   password : opac
7. Assign all privileges of schemata/databasa "hsr4ky_facetedopac" to the user "hsr4ky_opac" (perform this action in Schema Privilleges tab)

+++

8. Open Netbeans 6.5.1 IDE by double click the shortcut at "Desktop"
9. Copy folder "FacetedOPAC" to any place of your hard disk
10. In Netbeans, open project (File ==> Open Project), and browse to the path "FacetOPAC" that you just copied to your hard disk and click on the button "Open Project"
12. After project opened, you can run the system by press the key "F6" in your keyboard, or click "Run" => "Run Main Project"
13. Netbeans will start compile the source code, and will open internet browser automatically after build success.
14. Hint: To change the default browser when running the project, click "Tools" => "Options" in the Netbeans's menu, and in tab "General", locate the "Web browser: " drop down menu. You can change the default browser there.


========== 3. Live Demo ==========
By using technology of virtualization, you may use the VMWare Player to open the ready image to view the system.
--------- Requirement ---------
1. VMWare Player
2. Processor Pentium 4 1.6GHz or above
3. RAM minimum 512MB for Windows XP or 1GB for Windows Vista 
4. Minimu 5GB of free hard disk space

------------ Steps ------------
(notes: all installer can be found in "installer" folder while VMWare image can be found in "Virtual Machine" folder)
1. Copy "Virtual Machine" folder to your hard disk
2. Install the VMWare player (VMware-player-2.5.2-156735.exe)
3. Launch the VMWare player application
4. Click the "Open" on the opened VMWare player application interface
5. Locate the "Virtual Machine" folder that you just copied to your hard disk
6. Open "WinXP demo" folder, and open "Windows XP Professional.vmx" file in that folder
7. If prompt a message, just click "Ok" to continue
8. A Windows XP will start in the VMWare player.
9. Open the "ReadMe!!!.txt" in the VMWare player to continue














