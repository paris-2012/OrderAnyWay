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
•Favorites - Stored favorite meals through RoomDb<br />
•Profile - Stored profile information through shared preferences<br />
•Order Tracking - Basic geographic tracking implemented using google maps<br />
•Testing - Core features such as databases and apis are tested using a combination of junit, espresso, mockito and mockK<br />

## Screenshots
<img src="https://user-images.githubusercontent.com/68170232/193484020-e9361c74-4b85-4c99-b323-20d489022a37.png" width="190"/> <img src="https://user-images.githubusercontent.com/68170232/193484046-1d8425ba-237d-4db2-b66f-1852d8085544.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484058-2fbd2b05-a280-4837-bb30-2c3c3d42593b.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484067-493c89d3-8095-4582-ae08-42dda653deab.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484078-685f8cc8-10a7-47dd-bd01-08d126f9985f.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484209-990e242c-2470-420b-8375-9408b685f71c.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484146-d11375a4-73db-4d28-ba24-3eecf69fd9b0.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484153-b5cb5b55-7d0d-49aa-afb6-9c57a3f0292b.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484265-6775dc69-7b69-43b8-b486-9d3ec9691e9a.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193485473-f9c835d0-9d3c-4606-b346-c6e68cb27645.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484294-f0cf95ce-26d5-4d0a-be4f-c3f96123471b.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193484308-940c53b5-24b6-47ce-8add-f0d7d6b0d4f3.png" width="190"/>
<img src="https://user-images.githubusercontent.com/68170232/193604314-3ad13b34-83ff-42ad-a292-0fdbf764143f.png" width="190"/>


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



