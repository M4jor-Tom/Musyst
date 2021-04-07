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
	private ArrayList<Album> _albums;
	
	private static final String GOOGLE_STRING_URL = "https://www.google.com";
	
	public GoogleMusicScraper()
	{
		super(GOOGLE_STRING_URL);
		setAlbums(new ArrayList<>());
	}

	@Override
	public Album findAlbum(Author author, String albumName)
	{
		//Instantiation of Album object
		Album album = new Album(author, albumName);
		
		//Getting album's songs
		Document albumGooglePage = getDocument(GOOGLE_STRING_URL + "/search?q=" + albumName + " musics");
		String htmlSongContainerSelector = "div[data-attrid=kc:/music/album:songs]";
		Elements songElements = albumGooglePage.select(htmlSongContainerSelector);
		if(songElements.size() == 0)
		{
			//If no song were found without author's name in query
			albumGooglePage = getDocument(GOOGLE_STRING_URL + "/search?q=" + author.getName() + " " + albumName + " musics");
			songElements = albumGooglePage.select(htmlSongContainerSelector);
		}
		
		//Adding new songs to album, and printing informations
		int foundSong = 0;
		for(Element songElement: songElements)
		{
			//Getting song's name
			String songName = songElement.select("div.title").html();
			
			//Getting song's google stringUrl
			String songStringUrl = GOOGLE_STRING_URL + songElement.select("a.rl_item.rl_item_base").attr("href");
			
			//Getting son's youtube URL
			Document googleSongPage = getDocument(songStringUrl);
			String youtubeSongStringUrl = googleSongPage.select("#rcnt").select("a[href*=\"youtube\"]").attr("href");
			
			try
			{
				URL youtubeSongUrl = new URL(youtubeSongStringUrl);
				
				//Instantiation of MusicResource object
				album.getMusicResources().add(new MusicResource(youtubeSongUrl, null, songName));
				
				System.out.println("Song #" + ++foundSong + ": " + songName + " (" + youtubeSongUrl + ")");
			}
			catch(MalformedURLException e)
			{
				System.out.println("Unknown string url for " + songName + ": " + youtubeSongStringUrl);
			}
		}
		
		return album;
	}
	
	@Override
	public ArrayList<Album> findAlbums(String authorName)
	{
		//Updating fetching URL to match author name
		setFetchUrl(getFetchUrl() + "/search?q=" + authorName + " albums");
		
		//Printing fetching URL
		System.out.println("Finding albums on: \"" + getFetchUrl() + "\" ...");
		
		//Opening web document
		Document document = getDocument(getFetchUrl());
		if(document == null)
			//getDocument() didn't work fetching this author
			return null;
		
		//Acknowledging author name
		Element authorElement = document.select("span[data-elabel]").first();
		if(authorElement == null)
		{
			System.out.println("Can't find author on page, please retry");
			
			//End
			return null;
		}
		String correctAuthorName = authorElement.html();
		
		//Instantiation of Author object
		Author author = new Author(correctAuthorName);

		//Scraping songs from google songs panels
		Elements albumPanels = document.select("a[role=listitem]");
		
		//Presentation of fetching progression
		int foundAlbum = 0;
		System.out.println("Found albums for author: " + correctAuthorName);
		for(Element albumPanel: albumPanels)
		{
			//Getting current album name
			String albumName = albumPanel.attr("aria-label");
			
			//Instantiation of Album object
			getAlbums().add(findAlbum(author, albumName));
			
			//Printing album's data
			System.out.println("Album #" + ++foundAlbum + ": " + albumName);
		}
		
		return getAlbums();
	}

	@Override
	public ArrayList<Album> getAlbums()
	{
		return _albums;
	}
	
	private void setAlbums(ArrayList<Album> albums)
	{
		_albums = albums;
	}
}