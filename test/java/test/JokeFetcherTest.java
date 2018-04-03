package test;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import testex.IDateFormatter;
import testex.Joke;
import testex.JokeException;
import testex.JokeFetcher;
import testex.Jokes;
import testex.jokefetching.ChuckNorris;
import testex.jokefetching.EduJoke;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Moma;
import testex.jokefetching.Tambal;


@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

	private JokeFetcher jokeFetcher;
	@Mock IDateFormatter ifMock;
	@Mock IFetcherFactory factory;
    
    @Mock
    private EduJoke edu;
    
    @Mock
    private ChuckNorris chuck;
    
    @Mock
    private Moma moma;
    
    @Mock
    private Tambal tambal;
    

    @Before
    public void setup() throws Exception {
    	List<IJokeFetcher> fetchers = Arrays.asList(edu, chuck,moma,tambal);
    	when(factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
    	List<String> types = Arrays.asList("EduJoke","ChuckNorris","Moma","Tambal");
    	when(factory.getAvailableTypes()).thenReturn(types);
    	jokeFetcher = new JokeFetcher (ifMock, factory);

    	 given(edu.getJoke()).willReturn(new Joke("Educational Joke", "EducationalJokes.org"));
         given(chuck.getJoke()).willReturn(new Joke("Chuck Norris Joke.", "ChuckNorrisJokes.org"));
         given(moma.getJoke()).willReturn(new Joke("Jo Mama Joke", "JoMamaJokes.org"));
         given(tambal.getJoke()).willReturn(new Joke("Tambal Joke", "TambalaJokes.org"));
        
    }

    @Test
    public void testGetAvailableTypes() {
        assertThat(jokeFetcher.getAvailableTypes(), hasItems("EduJoke", "ChuckNorris", "Moma", "Tambal"));
        assertThat(jokeFetcher.getAvailableTypes().size(), is(4));
    }

    @Test
    public void testIsStringValid() {
        String jokeValidTokens = "EduJoke,ChuckNorris,Moma,Tambal";
        assertThat(jokeFetcher.isStringValid(jokeValidTokens), is(true));
        String jokeNonValidTokens = "EduJokez,ChuckNorrisz,Momaz,Tambalz";
        assertThat(jokeFetcher.isStringValid(jokeNonValidTokens), is(false));
    }

    @Test
    public void testGetJokesTime() throws JokeException {
        given(ifMock.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("31 mar. 2018 11:08 PM");
        assertThat(jokeFetcher.getJokes("EduJoke,ChuckNorris,ChuckNorris,Moma,Tambal",
                "Europe/Copenhagen").getTimeZoneString(), is("31 mar. 2018 11:08 PM"));
        verify(ifMock, times(1)).getFormattedDate(eq("Europe/Copenhagen"), anyObject());
    }
    
    @Test
    public void testEduJoke() throws JokeException {
        String expectedJoke = "Educational Joke";
        String expectedReference = "EducationalJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(0).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }
    
    @Test
    public void testChuckNorrisJoke() throws JokeException {
        String expectedJoke = "Chuck Norris Joke.";
        String expectedReference = "ChuckNorrisJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(1).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }
    
    @Test
    public void testMomaJoke() throws JokeException {
        String expectedJoke = "Jo Mama Joke";
        String expectedReference = "JoMamaJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(2).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }
    
     @Test
    public void testTambalJoke() throws JokeException {
        String expectedJoke = "Tambal Joke";
        String expectedReference = "TambalaJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(3).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }

}
