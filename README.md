<div align="center">
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/com/coder/hms/icons/main_logo(128X12).png">
  <h1>Hotel-Properties-Management-System Application </h1><br>
  <h4>This project is designed and developed by Coder ACJHP.</h4>
</div>                                                                                                                

Coder HPMS is hotels properties managing system application, operates independently of the operating system (Made by <b>Java</b> & <b>Maven</b> & <b>Hibernate</b> & <b>Mysql</b> & <b>Jaspy Security</b>) Easy to use, secure, simple user interface, understandable text with icons and every object colored.
For now it's support 4 different languages ENGLISH, العربية, ESPANOL, TÜRKÇE and it composed from seven main and many side windows : <br>
<h4>1- Main window </h4> All windows will open on this scene, check your current user, date, €, $, £ exchange and weather as live from status bar, manage your application from menu bar exit, change user, update password, send email, open calculator, exchange, change user interface theme and etc..

<h4>2- Rooms interface </h4>You can manage your all rooms in one page, just focus on the room you can read all information from tooltip text <br> and there is some shortcuts for quick checkin, set as clean, dirty, dnd and etc. show reservation, do checkout etc. */* for
entrying the room just do one click on the room and manage it {posting, payment, customer  detail etc.}

<h4>3- Guests window : </h4> All your customers detail listed on this table you can search easly and fast your customers from this <br>
page

<h4>4- Reservation window </h4> Control all your reservation, hotel fullness, emptinees, wait list, cancelled list as persentage,
search your reservation with date, referance number or agency referange number, create a new reservation with full details.

<h4>5- Blockade window </h4> Check your all reservation for one week in single list with room datails and dates.

<h4>6- Rooms status manging window </h4> Easily play with your room status from this page, set as clean, dirty, dnd single or plural

<h4>7- Cash desk window </h4> Check your cash desk as difference currencies and report them.Add, delete payments 
<hr>
<h2>Login : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/New_Login.png">
<hr>
<h2>Main window : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/main.png">
<hr>
<h2>Reservation window : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/reservations.png">
<hr>
<h2>Blockade window : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/blockade.png">
<hr>
<h2>Customers window : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/customers.png">
<hr>
<h2>Room cleaning window : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/roomStatus.png">
<hr>
<h2>Cash desk window : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/cashDesk.png">
<hr>
<h2>All rooms window : </h2>

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/roomPlan.png">
<hr>
<h2>Audit window : </h2>
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/audit.png">
<hr>
<h2>Checkin window : </h2>
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/roomsAndCheckin.png">
<hr>
<h2>Interior of room window : </h2>
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/roomInner.png">
<hr>
<h2>System log interior window : </h2>
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/systemLog.png">
<hr>
<h2>All reservations window</h2>
<img src="https://github.com/Coder-ACJHP/Hotel-Properties-Management-System/blob/master/src/Screenshots/allReservations.png">
<hr>
<h2>Reporting Section</h2>
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/reportWindow.png">
and more...

<h1>How to use?</h1>
<ul>
  <li> Clone or download the project and open it in any IDE and run following Maven command <code>mvn clean compile assembly:single</code> and it will create executable jar with dependencies.</li>
  <li>OR</li>
  <li>Download as Execuatble .jar(All os) & .app(for Mac) & .exe(for Windows)</li>
  <li> Install Mysql server(PhpMyAdmin or Mysql Workbench etc..) if you don't have.</li>
  <li>Don't worry about database and schemas <i> It will do it automatically for you.</i>If you want to do it by yourself with dumping (importing) <a href="https://github.com/Coder-ACJHP/Hotel-Properties-Management-System/blob/master/src/com/coder/hms/connection/CoderHPMSdatabase.sql">this</a> '.sql' file to your database server (creating schema also included)</li> 
  <li>Default user name = Admin --|-- default password = Admin958</li>
  <li>For accessing full features user name = System --|-- default password = System958 -> This will unlock Utils menu and you can truncate and create your database and tables, you can change hotel & room properties</li>
  <li> Congrats enjoy with your day :)</li> 
</ul>

<h3>Still not working? Just click and watch the video</h3>
<i>For old versions!</i><a href="https://youtu.be/BPbTwpZIuDE">Video with English subtitle</a>
<br>
<h2>Important : </h2>
<h4>About audit : </h4> If you want to use Audit property you should never stop your database server because it's working with
Event schedular in the database server. In every 18 hours event will update value of Audit from true to false than we can use Audit.
Do not forget Audit is can only be done once in a day.
Add this line in your database to create event : <code>create event dailyAudit on schedule every 18 hour do update HotelSystemStatus set isAuditted = 0</code>

<h2>Download newest version : </h2>
<ul>
  <li>Download it for cross platform v1.3.2 as <b><i>.jar</i></b> from <a href="https://github.com/Coder-ACJHP/Hotel-Properties-Management-System/releases/download/v.1.3.2/HotelPropertiesManagementSystem-1.3.2-SNAPSHOT-jar-with-dependencies.jar">here</a></li>
  <li>Download it for MacOs application <b><i>.app</i></b> from <a href="https://github.com/Coder-ACJHP/Hotel-Properties-Management-System/releases/download/1.3.1/CoderHPMSA.app.zip">here</a></li>
   <li>Download it for Windows application <b><i>.exe</i></b> from <a href="https://github.com/Coder-ACJHP/Hotel-Properties-Management-System/releases/download/1.3.1/CoderHPMSA.exe">here</a></li>
</ul>

<h2>New Features v1.2.1  <b>02/11/2017</b></h2>
<ul>
  <li>New release platform independed and MacOS verison</li>
  <li>Added new feature for auto detecting and fetching database [No need to import .sql file by hand app will do it for you]</li> 
  <li>Added new button in Login window for truncate databse and tables with importing all from scratch</li>
  <li>Fixed login delay and information labels</li>
</ul>

<h2>New Features v1.3.1  <b>21/11/2017</b></h2>
<ul>
  <li>New release platform independed and MacOS v1.3.1</li>
  <li>Added new all reservation window</li>
  <li>Fixed inner room currency issue</li>
  <li>Added password encryption for security</li>
  <li>Hotel properties window renewed and fixed issues</li> 
  <li>Prepare database button moved to new menubar section (utils)</li>
  <li>Fixed Hibernate transaction & session errors</li>
</ul>
<h2>New Features v1.3.2  <b>29/11/2017</b></h2>
<ul>
  <li>New release platform independed and MacOS v1.3.2</li>
  <li>Added show and edit property to all reservation window</li>
  <li>Added show and edit property to all customers window</li>
  <li>Credit card payment added to new transaction window & cash desk</li>
  <li>A lot of code lines & algorithms refactored</li>
</ul>
<h2>Another questions ?</h2> 
For any question please email me at : <a href="mailto:hexa.octabin@gmail.com">hexa.octabin@gmail.com</a>
<hr>
<h2>INFO : </h2>Please read the license carefully.
