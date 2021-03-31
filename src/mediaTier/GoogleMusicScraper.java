package mediaTier;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleMusicScraper extends DirectWebScraper implements MediaInterface
{
	private String _author;
	
	public GoogleMusicScraper(String author)
	{
		super("https://www.google.com/");
		setAuthor(author);
	}
	
	public void actualizeResources()
	{
		setFetchUrl(getFetchUrl() + "search?q=" + getAuthor() + " musics");
		
		System.out.println("Finding musics on: \"" + getFetchUrl() + "\" ...");
		
		Document document;
		
		Element author = (document = getDocument(getFetchUrl()))
			.select("span[data-elabel]").first();
		
		setAuthor(author.html());

		Elements songsPanels = document.select("div.rl_feature").select("a.rl_item_base").select(".rl_item");
		Elements songsName = songsPanels.select("div.title");

		//Preparing return variable
		//ArrayList<Resource> resources = new ArrayList<>();
		//int found = 0;
		System.out.println("Found musics for author: " + getAuthor());
		for(Element songPanel: songsPanels)
		{
			String songName = songPanel.select("div.title").html();
			String songUrl = songPanel.attr("href");
			System.out.println(songName + ": " + songUrl);
		}
	}

	public String getAuthor()
	{
		return _author;
	}

	public void setAuthor(String author)
	{
		_author = author;
	}
}