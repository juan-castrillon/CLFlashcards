# CLFlashcards
This is a Flashcards App that works with the Command Line. Apart from 
being able to import and export card sets, has an interactive questioning function and 
saves statistics to help you learn better. It also has a logging functionality.

## Flashcard
The program is based on the concept of [flashcards](https://en.wikipedia.org/wiki/Flashcard) to learn topics. Each card
has a **Term** which is the part of the card you know (e.g. Native Language Word, term, etc.) and a
**Definition** which is the one you want to learn.

## App functionality
### Running the app
As already mentioned the app is console based, and all interaction happens within the console. 

The java application is built on gradle, so to run it, even without gradle installed in the system, just navigate to the folder where you can see the build.gradle file and then type the following command in your terminal:
```
./gradlew run
```

If you are on a command prompt (CMD) in Windows:
```
.\gradlew run
``` 

#### Running the app with starting arguments

The app is capable of starting with a predefined file to import from, and a file to export to. 

This means, if the `-import` argument is passed, the starting set will be automatically imported from the file.

In the same way, using the `-export` argument means that when you exit the program ,the current set will be automatically saved into the defined file.

To do that, the run command is the following (remember both arguments are optional):
```
gradle run --args='-import <PATH_TO_IMPORT_FROM> -export <PATH_TO_EXPORT_TO>'
```

For example, if the following command is used:
```
gradle run --args='-import some/path/cards.txt'
```
Then as soon as the program starts, all the cards in `cards.txt` are loaded into the set.

In the same way, if this command is used:
```
gradle run --args='-export some/path/cards.txt'
```
Then as soon as the program ends, (with the `exit` command), all the cards will be saved to `cards.txt`

### Interacting with cards
#### Adding cards manually
To add a card, just type the `add` command when prompted. Then, input the term and definition of the card
The app will also tell you if you are typing in a card whose definition or term is already defined in a card in the set

![add](https://user-images.githubusercontent.com/64461123/92254058-9e9e3c80-eed0-11ea-996f-21ad8faf8870.gif)

#### Removing cards manually
To remove a card, type the `remove` command when prompted. Then, input the term of the card

![remove](https://user-images.githubusercontent.com/64461123/92254069-a1009680-eed0-11ea-96cd-17f5489bae21.gif)

#### Adding cards from a file
To add cards from a text file (.txt) its important that the file has the cards in the following format :
```
<TERM> : <DEFINITION> : 0
```
For example :

![file](https://user-images.githubusercontent.com/64461123/92254064-9fcf6980-eed0-11ea-9edf-dd2fd1a3d765.png)

Once the file is correctly formatted, just type the command `import` and then the path to the file. As an example, if you type :
```
src/main/resources/capitals.txt
```
A preformatted file with the worlds capitals is provided to use the app.

![import](https://user-images.githubusercontent.com/64461123/92254066-a0680000-eed0-11ea-9396-8828a2706f3d.gif)

#### Exporting cards to a file

Exporting a card set, will result in a text file formatted like mentioned in the last section. 
To do that, just enter the `export` command when prompted and specify the path of the file, and its name.
For example:
```
some/path/to/save/cards.txt
```

Specifying only the name (`cards.txt`) will create the file in the project directory.

![export](https://user-images.githubusercontent.com/64461123/92254063-9fcf6980-eed0-11ea-8d61-5ca441dd6172.gif)


### Learning
#### Getting asked
The interactive asking functionality can be used by typing the `ask` command. The program will then ask how many questions 
do you wanna answer, and then select random cards in the set and ask for the definition. The errors get accumulated on the cards, to know the hardest.

After reading an input, the program distinguishes 3 types of answer:
1. Correct answer
2. An incorrect answer that is correct for another card
3. An incorrect answer.

![ask](https://user-images.githubusercontent.com/64461123/92254062-9f36d300-eed0-11ea-86d3-5ae4f29637b1.gif)

#### Hardest Card (s)
When the command `hardest card` is used, the program shows the card (or cards) with the most mistakes

![hardest](https://user-images.githubusercontent.com/64461123/92254065-a0680000-eed0-11ea-8c42-e4ae964c24ed.gif)

### Other functions
#### Reset statistics
Using the command `reset stats` the errors in all the cards in the set get set to 0

#### Log
The program saves a log of everything happening in the command line interface, that is messages printed by the program
itself and input by the user. 

To save that log into a file (txt), use the `log` command and then type the
path to the file, and the name (similar to exporting cards). E.g:
```
path/to/log/log.txt
```

### Exiting the app

To finish the program, just type the `exit` command.
