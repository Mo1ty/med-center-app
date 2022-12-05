# med-center-app
Medcenter backend app - alternative branch with more backend and less frontend functionality, but with an authentication implemented.

Uses reworked database with better (but unrealised) potentional.
[Sho, ne otkryvaetsya? Delom zaymis'!](https://github.com/Mo1ty/med-center-app/blob/master/SQL/EER_diagran.PNG?raw=true "Yep, database")

## How to launch
Actual sql-scripts for database and basic data [are here, inside the repository](https://github.com/Mo1ty/med-center-app/tree/alternative/SQL)

You must have MySQL set and working to use this database. MySQL Workbench would be a big help too.

After setting the database, you can start this application "as it is". 

Swagger API will be available by the link "http://localhost:*YOUR_PORT*/swagger-ui/index.html#/"

[Angular app is also available](https://github.com/Mo1ty/medcenter-external/tree/alternative), but it is mostly used for authentication test. 

## Shortly about security
Authentication and Authorization is done via JWT-tokens and CSRF-tokens. Both tokens must be added to the header of ANY http-request, and interceptor updating CSRF-token afterwards has to be implemented too (Angular repository may be used as a reference for such functionality)

To generate & validate JWT tokens, separate filters were provided. Refreshing token was not implemented.
