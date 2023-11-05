# IDEA EDU course project

## Project
This is the *Rock Paper Scissors (Java)* project that is part of Hyperskill platform from Jetbrains Academy.

Purpose of the project is to learn Java OOP basics and to get more familiar with its ecosystem.

## Technologies

- Java 17

## Project description
Rock Paper Scissors CLI game application.

## Changelog
02.11.2023
- Create git repo
- Started stage 1
- Take input from the user and ensure that (for now) computer always wins.

02.11.2023
- Started stage 2
- refactored rock paper scissors to separate class to use more OOP
- Read option from user
- Choose a random option for the computer
- Print winner
- completed stage 2

02.11.2023
- Started stage 3
- refactored choices into enum, easier to find who won
- added main game loop
- added input validations
- completed stage 3

02.11.2023
- started stage 4
- Now program will ask player for a name and it will keep a score
- Create user class with a default score of 0
- Program will look for a file called `rating.txt` and will read user score from that file.
- If player name does not exist in the file, player will start with default user object score of 0.
- some refactoring done to make some of the code more clean
- completed stage 4

05.11.2023
- Had to refactor most of the code as the requirements have shifted drastically 
- The winner / loser will be determinted based on an initial array of options, where the first half will beat the users option and the second one will lose to it
- Implemented above winner/loser algo
- Completed stage 5

## Project status

Completed 5/5 stages