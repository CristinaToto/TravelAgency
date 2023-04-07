Table of Contents
1. About The Project
2. Main Features
3. Built With
4. Visuals


About the project

The SpringBoot application developed by the travel agency is a versatile web-based solution designed to facilitate the booking and management of hotel reservations. The application is programmed in Java and features an HTML and CSS front-end interface. By using this application, users can conveniently search for hotels, access hotel information, and make reservations for their preferred dates.

Main Feature

Manage users by creating, updating, or deleting their accounts.
Manage reservations by creating, updating, or deleting them.
Manage hotels by creating or updating their information.
Assign roles to users based on their responsibilities.
Calculate the discounted price.

Build with

Back end: 
-Java
Front end:
-HTML
-CSS
Database:
-H2 database
Framework:
-SpringBoot

Visuals

All reservations page

![Screenshot 2023-04-07 193317](https://user-images.githubusercontent.com/116298533/230644874-748d5643-bd4f-49e4-8aa7-ad46ea4914ac.png)

Reservation details by id

![Screenshot 2023-04-07 193350](https://user-images.githubusercontent.com/116298533/230644877-2a8db5e4-8b15-4fa7-8658-0105f048b5d5.png)

User details by id 

![Screenshot 2023-04-07 193416](https://user-images.githubusercontent.com/116298533/230644864-9b74620c-e4b1-4baf-a50e-73d1cafab34e.png)

Update reservation

![Screenshot 2023-04-07 193446](https://user-images.githubusercontent.com/116298533/230644866-fa440de3-d64a-4117-90fc-4a4915f5a342.png)

Hotel details by id

![Screenshot 2023-04-07 193523](https://user-images.githubusercontent.com/116298533/230644869-5f07ea8a-fa97-4e0d-aa99-e34e5f675788.png)

All users page

![Screenshot 2023-04-07 193553](https://user-images.githubusercontent.com/116298533/230644871-4341827f-5b5b-4fb8-bded-f6b35f83bde4.png)


API documentation:

Hotel:

GET "/getAllHotels": returns a list of all hotels in the database.
POST "/save": creates a new hotel with the provided details in the request body.
GET "/hotel/{id}/show": returns the details of the hotel with the given id.

Reservation:

GET "/reservations" - retrieves all reservations and returns them as a list in the response body.
Show reservation by id: GET "/reservation/{id}/show" - retrieves the reservation with the given id and returns it as a JSON object in the response body.
GET "/reservation/{reservationId}/delete" - deletes the reservation with the given id.
POST "/save/{userId}/{hotelId}" - creates a new reservation with the details in the request body, along with the given user id and hotel id, and returns the created reservation as a JSON object in the response body.
POST "/reservation/update/{id}" - updates the reservation with the given id with the new details in the request body, and redirects to the updated reservation's show page.
GET "/reservation/{id}/update" - retrieves the reservation with the given id and returns it as a JSON object in the response body.
GET "/get/user/{userId}" - retrieves all reservations made by the user with the given user id and returns them as a JSON array in the response body.
GET "/get/price/{userId}" - calculates the total price of all reservations made by the user with the given user id and returns the total price as a Double in the response body.
GET "/get/hotel/{hotelId}" - retrieves all reservations made at the hotel with the given hotel id and returns them as a JSON array in the response body.

User:

GET "/users"- returns all users stored in the system.
GET "/user/{id}/update"- displays a form for updating a specific user identified by the {id} parameter.
GET "/edit/{id}"- displays a form for updating a specific user identified by the {id} parameter.
GET "/user/{id}/show"- displays the user's details for a specific user identified by the {id} parameter.
POST "/saveUser"- creates a new user based on the information provided in the request body in the format specified by the CreareUserDto class.
POST "/user/update/{id}"- updates an existing user identified by the {id} parameter with the information provided in the request body in the format specified by the UpdateUserDto class.

Role:

GET "/get/{roleId}"- returns the role object for a specific role identified by the {roleId} parameter.
