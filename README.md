<div align="center">
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/com/coder/hms/icons/main_logo(128X12).png">
  <h1>Hotel-Management-System Application </h1><br>
  <h4>This project is designed and developed by Coder ACJHP.</h4>
</div>                                                                                                                

Coder HMS is hotels properties managing system application, cross platform (Made by <b>Java</b> & <b>Maven</b> & <b>Hibernate</b> & <b>Mysql</b>) Easy to use simple user interface, understandable text with icons and every object colored.
It's support 4 different languages ENGLISH, ARABIC, ESPANIOL, TURKISH and it composed from seven main packs : <br>
<h4>1- Main window </h4> All windows will open on this scene, check your current user, date, €, $, £ exchange as live from status bar, manage your application from menu bar exit, change user, update password, send email, open calculator, exchange, change user interface theme and etc..

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

<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/login.png">
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
<h2>Reporting Section</h2>
<img src="https://github.com/Coder-ACJHP/Hotel-Management-System/blob/master/src/Screenshots/reportWindow.png">
<hr>
and more...

<h1>How to use?</h1>
<ul>
  <li> Clone or download the project and open it in any IDE.</li>
  <li> Install Mysql server(PhpMyAdmin or Mysql Workbench etc..) if you don't have.</li>
  <li> Get dump file that located under <code>com.coder.hms.connection</code> with name <code>'hotel_management_system.sql'</code> and import it to your database server.</li> 
  <li> Congrats enjoy with your day :)</li> 
</ul>
<h3>Still not working? Just click and watch the video</h3>
<a href="https://youtu.be/BPbTwpZIuDE">Video with English subtitle</a>
<br>
<h2>Important : </h2>
<h4>About audit : </h4> If you want to use Audit property you should never stop your database server because it's working with
Event schedular in the database server. In every 18 hours event will update value of Audit from true to false than we can use Audit.
Do not forget Audit is can only be done once in a day.
Add this line in your database to create event : <code>create event dailyAudit on schedule every 18 hour do update HotelSystemStatus set isAuditted = 0</code>

<h2>Another questions ?</h2> 
For any question please email me at : <a href="mailto:hexa.octabin@gmail.com">hexa.octabin@gmail.com</a>
<hr>
<h2>INFO : </h2>Please read the license carefully.
<hr>
<h1>*** UPDATES AND NEWS ***</h1>
<h2>New Features <b>21/10/2017</b></h2>
<ul>
  <li>Download it as cross platform executable .jar <a href="https://github.com/Coder-ACJHP/Hotel-Management-System/releases/download/1.2.0/HotelManagementSystem-0.0.1-SNAPSHOT-jar-with-dependencies.jar">Coder Hotel Managemet System.jar</a></li>
  <li>New add user window (as admin)</li>
  <li>New change rooms properties window (as admin)</li>
  <li>No need to create schema just import the <code>.sql</code> file to your database server.</li>
  <li>Fixed all reservation errors, added new security codes.</li>
  <li>Added generating report at update reservation window also.</li>
</ul>
