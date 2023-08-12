# Hangman Game in Scala

This repository contains a simple Hangman game implemented in Scala. The game randomly selects a word from a provided text file and challenges you to guess the letters in the word to complete it before running out of attempts.

## Getting Started

### Prerequisites

To run the Hangman game, you need to have Scala and the Scala Build Tool (sbt) installed on your system.

### Installation

1. Clone this repository to your local machine using:

   ```bash
   git clone https://github.com/mainali123/Hangman_Scala.git

2. Navigate to the project directory:
   ```bash
   cd Hangman_Scala
3. Compile the code using sbt:
   ```bash
   sbt compile
 4. Run the game using sbt:
    ```bash
    sbt run

## Game Logic
The Hangman game includes the following key functionalities:
- randomWord(fileName: String): String: Generates a random word from the list of words in the provided file, meeting specific criteria.
- main(args: Array[String]): Unit: Implements the main game loop, where the player interacts with the game. Guess letters to complete the word within a limited number of attempts.

## Future Plan
Remove all the side-effects and perform convert it to pure functional code.

## License
This project is licensed under the MIT License.

