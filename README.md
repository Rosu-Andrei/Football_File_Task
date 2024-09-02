# **Football Data Application**



The following repository consists of a Java application that uses Maven as a dependency managment. The main purpose of the application is that given an input file that contains data about teams in the Premier League, data such as goals scored and goals received,
total number of points gathered etc., the Java application takes as an input the file with the data and as an output will return the name of the team that has the smallest difference between goals scored and goals received.

Bellow, you can see an example of the format of the data inside the input file:

        Team            P     W    L   D    F      A     Pts
    1. Arsenal         38    26   9   3    79  -  36    87
    2. Liverpool       38    24   8   6    67  -  30    80
    3. Manchester_U    38    24   5   9    87  -  45    77
    4. Newcastle       38    21   8   9    74  -  52    71

To make sure that only the necesserly data is extracted from the file, a Regex Pattern was used.