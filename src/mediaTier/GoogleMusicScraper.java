package mediaTier;

import resourcePackage.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleMusicScraper extends DirectWebScraper<AudioResource> implements MediaInterface<AudioResource>
{
	private String _authorName;
	
	public GoogleMusicScraper(String authorName)
	{
		super("https://www.google.com/");
		setAuthorName(authorName);
		actualizeResources();
	}
	
	public void actualizeResources()
	{
		setFetchUrl(getFetchUrl() + "search?q=" + getAuthorName() + " musics");
		
		System.out.println("Finding musics on: \"" + getFetchUrl() + "\" ...");
		
		Document document;
		
		Element author = (document = getDocument(getFetchUrl()))
			.select("span[data-elabel]").first();
		
		setAuthorName(author.html());

		Elements songsPanels = document.select("div.rl_feature").select("a.rl_item_base").select(".rl_item");

		//Preparing return variable
		setResources(new ArrayList<>());
		int found = 0;
		System.out.println("Found musics for author: " + getAuthorName());
		for(Element songPanel: songsPanels)
		{
			String songName = songPanel.select("div.title").html();
			String songUrl = super.getFetchUrl() + songPanel.attr("href");
			System.out.println(++found + " " + songName);// + ": " + songUrl);
			try
			{
				getResources().add(
					new AudioResource(
						Resource.UNDEFINED,
						new URL(songUrl),
						null,
						songName,
						new Author(getAuthorName())
					)
				);
			}
			catch(MalformedURLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getAuthorName()
	{
		return _authorName;
	}

	public void setAuthorName(String authorName)
	{
		_authorName = authorName;
	}
}