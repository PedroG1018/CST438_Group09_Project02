# Wishlist Web App

### Team Members

CST438 Group 9:
- Bryan Fowles
- Pedro Gutierrez Jr. (https://github.com/PedroG1018)
- Roshan Menik Arachchige

### Project Description

The website allows users to create virtual wishlists where they can add items of their choosing for future reference. Users can modify their own lists, modify their items, and delete their lists and items. An admin can view all registered users and delete their accounts. This is all done using a REST API and a persistence database layer to store user, list, and item information that will be shared across the pages where necessary.

Deployed using Heroku: https://vast-beach-99467.herokuapp.com

Admin Access:
Username - admin2022
Password - admin2022

### API Endpoints

GET: /api/allUsers<br>
GET: /api/allLists<br>
GET: /api/allItems<br>
GET: /api/findByUsername?username={username}<br>
GET: /api/findByUserId?userId={userId}<br>
GET: /api/list?listId={listId}<br>
GET: /api/lists?userId={userId}<br>
GET: /api/items?listId={listId}<br>
GET: /api/findByItemId?itemId={itemId}<br>
POST: /api/addUser?firstName={firstName}&lastName={lastName}&username={username}&password={password}<br>
POST: /api/addList?userId={userId}&listName={listName}&description={description}<br>
POST: /api/addItem?listId={listId}&userId={userId}&itemName={itemName}&itemURL={itemURL}&imgURL={imgURL}&description={description}<br>
DELETE: /api/deleteUser?userId={userId}<br>
DELETE: /api/deleteList?listId={listId}<br>
DELETE: /api/deleteLists?userId={userId}<br>
DELETE: /api/deleteItem?itemId={itemId}<br>
DELETE: /api/deleteListItems?listId={listId}<br>
DELETE: /api/deleteAllItems?userId={userId}<br>

### Routes

/ or /home                      (Login Page)<br>
/admin                          (Admin Page)<br>
/logout                         (Log Out)<br>
/admin/addUser                  (Add New User Page)<br>
/register                       (Create Account Page)<br>
/addItem?listId={listId}        (Add Item Page)<br>
/profile                        (User Profile Page)<br>
/lists?userId={userId}          (Lists Page)<br>
/items?listId={listId}          (List Items Page)<br>


### Initial Mockup

![Mock-up Diagram Vers  3](https://user-images.githubusercontent.com/49994182/155939620-2788dec9-93fe-4d69-8729-3775d5cb3978.png)


### Entity Relation Diagram
https://lucid.app/lucidchart/ca9cfcba-a0b3-45eb-a2b1-dfd43ef44816/edit?beaconFlowId=3DB69CE89DFC15F2&invitationId=inv_92fc9869-6aa2-434a-b636-67773748482d&page=0_0#
![Entity Relation Diagram V2](https://user-images.githubusercontent.com/49994182/159839231-828df07a-fc2a-4701-8805-d52d94028de3.png)

There's three tables to hold the users, lists, and items in the database. Each have a unique id field that we can use to link them together to ensure that the correct lists and items are tied to the correct users. The 'isAdmin' field in the User table will be used to check if the current user has admin privileges. The Item table will contain item url's linking the user to listings from official web stores like Amazon as well as image url's so that it's not necessary to store the raw images locally.

### Links

Links to resources we found useful:
  - Postman API Testing: https://www.youtube.com/watch?v=VywxIQ2ZXw4
  - Connecting Spring Boot with Database on Heroku: https://www.codejava.net/heroku/deploy-spring-boot-app-with-mysql
