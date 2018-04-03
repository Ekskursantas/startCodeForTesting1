package testex.jokefetching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetcherFactory implements IFetcherFactory {
	private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
	private IJokeFetcher edu;
	private IJokeFetcher chuck;
	private IJokeFetcher moma;
	private IJokeFetcher tambal;

	public FetcherFactory() {

	}

	public FetcherFactory(IJokeFetcher edu, IJokeFetcher chuck, IJokeFetcher moma, IJokeFetcher tambal) {
		this.edu = edu;
		this.chuck = chuck;
		this.moma = moma;
		this.tambal = tambal;
	}

	@Override
	public List<String> getAvailableTypes() {
		return availableTypes;
	}

	@Override
	public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
		List<IJokeFetcher> list = new ArrayList();
        String[] tokens = jokesToFetch.split(",");
        for (String token : tokens) {
            switch(token) {
                case "EduJoke":
                    list.add(new EduJoke());
                    break;
                case "ChuckNorris":
                    list.add(new ChuckNorris());
                    break;
                case "Moma":
                    list.add(new Moma());
                    break;
                case "Tambal":
                    list.add(new Tambal());
                    break;
            }
        }
        
        return list;
    }
}
