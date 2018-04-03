**- Explain the necessary steps you did to make the code testable, and some of the patterns involved in this step** 


*First things first! Before engaging with any refactoring or adjusting, you have to get to know the code and all its aspects. Afterwards, by following the instructions we refactored the JokeFetcher and DateFormatter. For DateFormater we created interface for the usage of the static method getFormattedDate() which now injects Date as an function argument. Furthermore, we used polymorphism to make sure that each and every joke as its own class that is accompanied by an interface IJokeFetcher.  To make sure the types are correct after the instantiation we created FetcherFactory which implements IFetcherFactory*


----------


**- Execute your test cases** 


![Test Cases](https://github.com/Ekskursantas/startCodeForTesting1/blob/master/testcase.png?raw=true)


----------


**- Explain basically about JUnit, Hamcrest, Mockito and Jacoco, and what problems they solve for testers** 


*JUNIT - Java testing framework. Constant usage of this framework ensures that your code is more or less clean of bugs and potential errors.
HAMCREST - I would call this as a extension for JUnit. It makes the testing more simplified and easier to understand. One of the most commonly used features are matchers especially for automate tests.
MOCKITO - I am not too familiar with this framework, but as far as I understand it feels like it creates a dummy/double. This is beneficial for automated tests.
JACOCO - Measures test coverage within the code. It shows how much source code is being covered when executing particular test suit.


----------


**- Demonstrate how you used Mockito to mock away external Dependencies** 


![FetcherFactoryTest](https://github.com/Ekskursantas/startCodeForTesting1/blob/master/mock1.png?raw=true)


----------


![JokeFetcherTest](https://github.com/Ekskursantas/startCodeForTesting1/blob/master/mock2.png?raw=true)
```xml	
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-all</artifactId>
			<version>1.9.5</version>
			<scope>test</scope>
		</dependency>
```


----------


**- Demonstrate how/where you did state-based testing and how/where you did behaviour based testing** 


```java
@Test
    public void testGetJokesTime() throws JokeException {
        given(ifMock.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("31 mar. 2018 11:08 PM");
        assertThat(jokeFetcher.getJokes("EduJoke,ChuckNorris,ChuckNorris,Moma,Tambal", "Europe/Copenhagen").getTimeZoneString(), is("31 mar. 2018 11:08 PM"));
        verify(ifMock, times(1)).getFormattedDate(eq("Europe/Copenhagen"), anyObject());
    }
```
State based testing is performed when we assert the expected state.
```java
assertThat(jokeFetcher.getJokes("EduJoke,ChuckNorris,ChuckNorris,Moma,Tambal", "Europe/Copenhagen").getTimeZoneString(), is("31 mar. 2018 11:08 PM"));
```
Behavior based testing is performed when we verify that the method was called only once.
```java
 verify(ifMock, times(1)).getFormattedDate(eq("Europe/Copenhagen"), anyObject());
```


----------


**-  Explain about Coverage Criterias, using the results presented by running Jacoco (or a similar tool) against you final test code.** 


![test package](https://github.com/Ekskursantas/startCodeForTesting1/blob/master/part1.png?raw=true)

![testex package](https://github.com/Ekskursantas/startCodeForTesting1/blob/master/part2.png?raw=true)

![jokefetching package](https://github.com/Ekskursantas/startCodeForTesting1/blob/master/part3.png?raw=true)

*These results shows the instructions - information about the amount of code that has been executed.


----------


**-  Explain/demonstrate what was required to make this project use, JUnit (Hamcrest), Mockito and Jacoco**


*Not sure if it is not the same as what the problems they solve which I answered in previous bullet point. To add, conditions of the code is one of the main things that decides if the features are required or not. While it didn't use any other testing frameworks it was the perfect guinea pig for all the frameworks we were given. However, to be able to use all of them you need to add the necessary dependencies.*
