# ValorApp

## How to run
Run the project as any other project but please use JAVA 11.

## API
I used the Valorant API(no official) 
- https://dash.valorant-api.com/endpoints/weapons

## Architecture
This app was created thinking how to scale, it's compose by 3 modules that can be developed for 3 different teams:
- App: Is the Main module where all the other feature modules meets
- WeaponsList: The module that contains the List of Valorant Weapons. Use MVVM in the presentation package and Clean Architecture as architecture
- WeaponDetails: The module that contains the Details of any selected Weapon, Use MVVM in the presentation package and Clean Architecture as architecture.

## Testing
Two libraries were used to create the tests: Junit and mockito

## Libraries
I used the most usual libraries to develop the application:
- Retrofit: To make the Requests to the API
- Picasso: To show and cache the images

## Background Strategy
I used coroutines from Kotlin to make the request in background