run:  BookMapperFrontend.class
	java BookMapperFrontend

BookMapperFrontend.class: BookMapperFrontend.java
	javac BookMapperFrontend.java

runTests: runFrontendTests runAlgorithmEngineerTests runDataWranglerTests

runAlgorithmEngineerTests: AlgorithmEngineerTest.class
	java AlgorithmEngineerTest

AlgorithmEngineerTest.class: AlgorithmEngineerTest.java
	javac AlgorithmEngineerTest.java

#Book.java file
Book.class: Book.java
	javac Book.java

#Compiles BookLoader.java
BookLoader.class: BookLoader.java
	javac BookLoader.java

#Compils DataWranglerTest.class
DataWranglerTest.class: DataWranglerTest.java
	javac DataWranglerTest.java

runDataWranglerTests: DataWranglerTest.class Book.class BookLoader.class
	java DataWranglerTest

clean:
	rm *.class

runFrontendTests: FrontendTest.class
	java FrontendTest

FrontendTest.class: FrontendTest.java
	javac FrontendTest.java
