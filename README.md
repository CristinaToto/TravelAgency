 <table>
        <thead>
            <tr>
                <th><strong>Table of Contents</strong></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><strong>1. About The Project</strong</td>
            </tr>
            <tr>
                <td><strong>2. Main Features</strong></td>
            </tr>
            <tr>
                <td><strong>3. Built With</strong></td>
            </tr>
            <tr>
                <td><strong>4 .Visuals</strong></td>
            </tr>
            <tr>
                <td><strong>5. API Documentation</strong></td>
            </tr>
        </tbody>
    </table>

<h2 id="about-the-project">1. About The Project</h2>
The travel agency is a SpringBoot application that serves as a flexible online solution to simplify the process of booking and managing hotel reservations. The application is developed using Java and has a front-end interface based on HTML and CSS. By using this application, users can conveniently search for hotels, access hotel information, and make reservations for their preferred dates.

<h2 id="main-features">2. Main Features</h2>
</br>
•	Manage users by creating, updating, or deleting their accounts.
</br>
•	Manage reservations by creating, updating, or deleting them.
</br>
•	Manage hotels by creating or updating their information.
</br>
•	Assign roles to users based on their responsibilities.
</br>
•	Calculate the discounted price.


<h2 id="built-with">3. Built With</h2>
</br>
<b>Back end:</b>
</br>
•	Java
</br>
<b>Front end:</b>
</br>
•	HTML
</br>
•	CSS
</br>
<b>Database:</b>
</br>
•	H2 database
</br>
<b>Framework:</b>
</br>
•	SpringBoot
</br>


<h2 id="built-with">4. Visual</h2>
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

<h2>API documentation:</h2>
</br>
<h3>Hotel:</h3>
</br>
• <b>GET</b> "/getAllHotels": returns a list of all hotels in the database.</br>
• <b>POST</b> "/save": creates a new hotel with the provided details in the request body.</br>
• <b>GET</b> "/hotel/{id}/show": returns the details of the hotel with the given id.</br>
<h3>Reservation:</h3>
</br>
• <b>GET</b> "/reservations" - retrieves all reservations and returns them as a list in the response body.</br>
• <b>GET</b> "/reservation/{id}/show" - retrieves the reservation with the given id and returns it as a JSON object in the response body.</br>
• <b>GET</b> "/reservation/{reservationId}/delete" - deletes the reservation with the given id.</br>
• <b>POST</b> "/save/{userId}/{hotelId}" - creates a new reservation with the details in the request body, along with the given user id and hotel id, and returns the created reservation as a JSON object in the response body.</br>
• <b>POST</b> "/reservation/update/{id}" - updates the reservation with the given id with the new details in the request body, and redirects to the updated reservation's show page.</br>
• <b>GET</b> "/reservation/{id}/update" - retrieves the reservation with the given id and returns it as a JSON object in the response body.</br>
• <b>GET</b> "/get/user/{userId}" - retrieves all reservations made by the user with the given user id and returns them as a JSON array in the response body.</br>
• <b>GET</b> "/get/price/{userId}" - calculates the total price of all reservations made by the user with the given user id and returns the total price as a Double in the response body.</br>
• <b>GET</b> "/get/hotel/{hotelId}" - retrieves all reservations made at the hotel with the given hotel id and returns them as a JSON array in the response body.</br>
</br>
<h3>User:</h3>
</br>
• <b>GET</b> "/users"- returns all users stored in the system.</br>
• <b>GET</b> "/user/{id}/update"- displays a form for updating a specific user identified by the {id} parameter.</br>
• <b>GET</b> "/edit/{id}"- displays a form for updating a specific user identified by the {id} parameter.</br>
• <b>GET</b> "/user/{id}/show"- displays the user's details for a specific user identified by the {id} parameter.</br>
• <b>POST</b> "/saveUser"- creates a new user based on the information provided in the request body in the format specified by the CreareUserDto class.</br>
• <b>POST</b> "/user/update/{id}"- updates an existing user identified by the {id} parameter with the information provided in the request body in the format specified by the UpdateUserDto class.</br>

<h3>Role:</h3>
</br>
• <b>GET</b> "/get/{roleId}"- returns the role object for a specific role identified by the {roleId} parameter.</br>
