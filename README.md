# League Calculator

This is a simple Java application that will read in a text file of Matches, and output the rankings of the League in a specified output file.

# Instructions for Running

1. Download the source code
2. Build the code using Maven by running `mvn clean install`
3. Create an input file with the League matches (there is an example `input.txt` file in the `test` module)
4. Run the Application, passing through the input file location as well as the output file location. For example:
    `java -jar target/LeagueCalculator-1.0-SNAPSHOT.jar input.txt output.txt`