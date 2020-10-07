---------------------------ASTRO_TALK Assignment---------------------------------------

About Application :

1 : User can create his account with unique email id and isCreated flag. I added some validations in email that emails should
    contains @ and domain in proper manner and if isCreated flag is true then user is created and can add connections. If isCreated
    is set to false then user is created in database but not able to add connections until its isCreated flag is updated.

2 : User can add one or multiple connections if new connections are not created previously then it will create otherwise not created.
    I added some validations in creation of connections like if connection is already present and linked to his account then it will not created shows an error that connection already exist.

3 : I added bidirectional mapping of connections means if A is a friend of B then B is also a friend of A.

4 : A User can see only his connections not others.

5 : A User can remove his connection only. That removed connection is not actually removed from database but its linking was removed from that user and It is not in bidirectional way.

6 : I created an update mapping of User. which generally updates an isCreated flag of users because of creating connections in it.

7 : I created a module of finding all connections at a certain distance (Integer value) from that user. See connections are bidirectional Hence we will get results based on bidirectional format.

8 : Added some custom exceptions and handlers for managing data.

9 : Using In Memory Database H2.

10 : Created validations of user emails, connection emails, email format, distance key.

11 : Adding Swagger Implementation for better understanding of apis.  (http://localhost:8081/astro-assignment/swagger-ui.html).

12 : Adding Java Documentations.

13 : Adding context path of application :   /astro-assignment

14 : Adding constants and DTOs for manipulations of data.

15 : Shared Postman Collection includes 7 apis.

How to start this assignment :

1 : Find the main file of spring boot application :  com.assignment.AstrotalkAssignment.main.AstrotalkAssignmentApplication.
2 : Run main class as simple java application.
3 : you can see the in memory database using H2 console : http://localhost:8081/astro-assignment/h2-console

