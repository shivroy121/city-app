# city-app


You can clone the ui application from :-https://github.com/shivroy121/cityApp


Role Based Access Control (RBAC) with Spring Boot and JWT

Getting Started
Use mvn clean install in the project root directory to build the project.
Run the main class, com.city.app.CityAppApplication to start the application.


Endpoints
/auth/login -> Public endpoint which returns a signed JWT for valid user credentials (admin/admin)


/cities -> Contains several endpoints to add and remove ,update, get entities. Protected by JWT authentication and authorized based on role.

![image](https://user-images.githubusercontent.com/21003713/177917439-902839a5-4e7c-4db0-ae7b-dfb7e804eab5.png)

![image](https://user-images.githubusercontent.com/21003713/177917480-d4ccd343-2b59-42d7-a16c-3aec9c03cd07.png)

![image](https://user-images.githubusercontent.com/21003713/177917584-cff6a199-0572-430d-bd28-a3c9a9cee3d1.png)

![image](https://user-images.githubusercontent.com/21003713/177917673-59c5b7fc-a1f8-46c5-abdb-bda3c7b6a7bb.png)

![image](https://user-images.githubusercontent.com/21003713/177918176-369e25dd-b4f2-48e0-bebc-0a7f539a74c8.png)


