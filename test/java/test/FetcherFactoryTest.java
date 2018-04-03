package test;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import testex.jokefetching.ChuckNorris;
import testex.jokefetching.EduJoke;
import testex.jokefetching.FetcherFactory;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Moma;
import testex.jokefetching.Tambal;


@RunWith(MockitoJUnitRunner.class)
public class FetcherFactoryTest {
    
    @Mock
    EduJoke edu;
    
    @Mock
    ChuckNorris chuck;
    
    @Mock
    Moma moma;
    
    @Mock
    Tambal tambal;    
    
    IFetcherFactory factory;
    
    @Before
    public void setup() {
        factory = new FetcherFactory(edu, chuck, moma, tambal);
    }

    @Test
    public void testThatFactoryHasFourInstancesOfIJokeFetcher() throws Exception {
        List<IJokeFetcher> result = factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal");
        assertThat(result.size(), is(4));
        assertThat(result.get(0), is(instanceOf(EduJoke.class)));
        assertThat(result.get(1), is(instanceOf(ChuckNorris.class)));
        assertThat(result.get(2), is(instanceOf(Moma.class)));
        assertThat(result.get(3), is(instanceOf(Tambal.class)));
        
    }
    
}