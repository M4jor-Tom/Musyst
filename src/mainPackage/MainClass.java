package mainPackage;

import mediaTier.*;
import resourcePackage.MusicResource;

public class MainClass
{
	public static void main(String[] args)
	{
		MediaInterface<MusicResource> musicsScraper = new GoogleMusicScraper("grandson");
		for(MusicResource resource: musicsScraper.getResources())
			System.out.println(resource);
	}
}