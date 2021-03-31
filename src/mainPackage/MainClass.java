package mainPackage;

import mediaTier.*;

public class MainClass
{
	public static void main(String[] args)
	{
		MediaInterface musicTitlesFetcher = new GoogleMusicScraper("grandson");
		musicTitlesFetcher.getResources();
	}
}