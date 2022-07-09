# OnlineBanking


This is the backend server of an application for online banking. It is written in Java with the Spring Boot framework. The server handles requests from a client written in ReactJs. The server and the client are following REST constraints.

When I began to make this app, the main goal was to learn about web and the web technologies so the app right now is unfinished. You can just register, verify your account (I used a free SMTP server to send emails) , log in with 2FA (with a code on email, not with OTPs), update your data from a profile page and initiate a transactions from a dashboard page. 

I also wanted to learn about banks and how the account data is generated so I am using the ISO 13616 standard to generate IBANs for a romanian bank and ISO/IEC 7812 for card numbers (although I don't use card numbers anywhere). Of course there were more standards I could've used.

I didn't use tools like Docker or Jenkins because I didn't find a need for it, but propably I should have used them.

### The first part of the registration form:

![image](https://user-images.githubusercontent.com/62606710/178097625-19afa113-674d-4796-be12-89c2cfba1aaa.png)

### The payment form:

![image](https://user-images.githubusercontent.com/62606710/178097757-09e8d0ce-96bc-4fec-b7fe-88a04bd6bd23.png)

### Transaction history:

![image](https://user-images.githubusercontent.com/62606710/178097839-a46ee76e-7830-43a9-a037-be86f7273c8d.png)

### Profile page

![image](https://user-images.githubusercontent.com/62606710/178097938-e1fb7dbe-ec4b-4643-a696-6f7b6333abb3.png)

