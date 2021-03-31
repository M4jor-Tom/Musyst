package mediaTier;

import java.io.IOException;
import java.util.ArrayList;

import resourcePackage.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class DirectWebScraper implements MediaInterface
{
	private ArrayList<Resource> _resources;
	public String _fetchUrl;
	final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36";
	
	public DirectWebScraper(String fetchUrl)
	{
		//Initialization of Resources and Cookies to null
		setResources(null);
		
		//Setting data
		setFetchUrl(fetchUrl);
	}

	public abstract void actualizeResources();

	public Document getDocument(String stringUrl)
	{
		try
		{
			return Jsoup.connect(stringUrl).get();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Resource> getResources()
	{
		//Fetching data
		actualizeResources();
		
		//Returning actual resources
		return _resources;
	}
	
	public void setResources(ArrayList<Resource> resources)
	{
		_resources = resources;
	}
	
	public String getFetchUrl()
	{
		return _fetchUrl;
	}
	
	public void setFetchUrl(String fetchUrl)
	{
		_fetchUrl = fetchUrl;
	}
}