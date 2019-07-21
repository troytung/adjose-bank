### endpoints
- sign up
    ```bash
    curl -i -X POST \
       -H "Content-Type:application/x-www-form-urlencoded" \
       -d "username=adjose" \
       -d "password=adjose" \
       -d "email=adjose@adjose.com" \
       -d "phoneNumber=0987654321" \
     'http://localhost:8080/v1/users'
    ```
- login
    ```
    http://localhost:8080/login
    ```
- logout
    ```
    http://localhost:8080/logout
    ```
- get login user's profile
    ```bash
    curl -i -X GET \
     'http://localhost:8080/v1/userprofiles'
    ```
- get all users
    ```bash
    curl -i -X GET \
     'http://localhost:8080/v1/users'
    ```
- get a user
    ```bash
    curl -i -X GET \
     'http://localhost:8080/v1/users/{username}'
    ```
- create account for login user
    ```bash
    curl -i -X POST \
       -H "Content-Type:application/x-www-form-urlencoded" \
       -d "currency=USD" \
     'http://localhost:8080/v1/accounts'
    ```
- get login user's accounts
    ```bash
    curl -i -X GET \
     'http://localhost:8080/v1/accounts'
    ```
- update login user's profile
    ```bash
    curl -i -X PUT \
       -H "Content-Type:application/x-www-form-urlencoded" \
       -d "email=adjose@adjose.com" \
       -d "phoneNumber=0912345678" \
     'http://localhost:8080/v1/userprofiles'
    ```


