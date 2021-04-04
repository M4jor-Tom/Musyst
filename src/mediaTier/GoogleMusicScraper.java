package mediaTier;

import resourcePackage.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleMusicScraper extends DirectWebScraper<MusicResource> implements MediaInterface<MusicResource>
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
		//Updating fetching URL to match author name
		setFetchUrl(getFetchUrl() + "search?q=" + getAuthorName() + " musics");
		
		//Printing fetching URL
		System.out.println("Finding musics on: \"" + getFetchUrl() + "\" ...");
		
		//Opening web document
		Document document = getDocument(getFetchUrl());
		
		//Acknowledging author name
		Element author = document.select("span[data-elabel]").first();
		setAuthorName(author.html());

		//Scraping songs from google songs panels
		Elements songsPanels = document.select("div.rl_feature").select("a.rl_item_base").select(".rl_item");
		
		//Presentation of fetching progression
		int found = 0;
		System.out.println("Found musics for author: " + getAuthorName());
		for(Element songPanel: songsPanels)
		{
			//Getting current song's data
			String songName = songPanel.select("div.title").html();
			String songUrl = super.getFetchUrl() + songPanel.attr("href");
			
			//Printing song's data
			System.out.println(++found + " " + songName);// + ": " + songUrl);
			try
			{
				//Added a new resource to resource list
				getResources().add(
					new AudioResource(
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