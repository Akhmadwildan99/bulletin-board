# Basic Guides

## Running the app

`./gradlew bootRun`
When running the application, it will automatically run the migration database

## Setting database
Change database, username and password according to your environment
```
  spring:
  application:
    name: bulletin-board

  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/bulletin
    username: movieapp
    password: 'movieapp'
```

## Main page
berada di route `http://localhost:9990/posts`
<img width="1680" alt="Screenshot 2025-01-27 at 19 39 49" src="https://github.com/user-attachments/assets/715a20b9-8f5a-4c4b-b312-47ab3ce60ace" />



Clicking Id will take you to the post details
<img width="1680" alt="Screenshot 2025-01-27 at 19 37 08" src="https://github.com/user-attachments/assets/d504d595-0f5b-41f1-829a-2b78fe55bda5" />

<img width="1680" alt="Screenshot 2025-01-27 at 19 37 13" src="https://github.com/user-attachments/assets/0834a947-54fa-4fec-a120-3fa322918a82" />

To edit and delete, you must first enter the password in the modal.

<img width="1680" alt="Screenshot 2025-01-27 at 19 37 13" src="https://github.com/user-attachments/assets/8f71cd3e-fb8e-4cd3-b413-c4f6490b83b6" />
