# Order Any Way

Order Any Way is a android application mimicking the features of a food delivery app. It is created for learning purposes and is not meant to actually deliver food. The app uses modern android application development technologies and uses kotlin, the best language for android development.

## About

The app is a simple food delivery application in which you can create an account, browse foods, add them to a cart and check them out.

## Features

•Registration - Create an account through Firebase<br />
•Login - Login to account through Firebase or login as a guest<br />
•Category View - Gets categories through API request and displays categories of products in a recycler view<br />
•Product View - Gets meal information through API request<br />
•Shopping Cart - Adds meals to cart using a local database managed by RoomDb<br />
•Checkout - A chain of ui's asks for checkout details and saves order information to a local database<br />
•Profile - Stored profile information through shared preferences<br />
•Order Tracking - Basic geographic tracking implemented using google maps<br />
•Chat Support - A support chat using Firebase Realtime Database<br />

## Screenshots

## Built with

•Kotlin - For easy to read code<br />
•Retrofit - To handle API requests<br />
•Roomdb - To store complex data locally<br />
•Shared Preferences - To store simple data locally<br />
•MVVM Architecture - To organize project in a easy to maintain codebase<br />

## Package Structure

com.example.FoodDeliveryApp          # Root Package<br />
.<br />
├── model                            # Model Layer <br />
│   │<br />
│   ├── remote                       # API handling<br />
│   │   <br />
│   └── local                        # Roomdb Database and Shared Preferences<br />
│<br />
├── view                             # Activiies and Adapters<br />
│<br />
└── viewmodel                        # Logic<br />


## Architecture

This app uses MVVM Architecture
![image](https://user-images.githubusercontent.com/68170232/193273392-c639744e-86e2-48c4-8633-2a616e90bcf5.png)

## Thanks!

Thanks for looking at my project! Happy coding!



