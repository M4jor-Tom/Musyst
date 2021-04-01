package mainPackage;

import mediaTier.*;
import resourcePackage.AudioResource;

public class MainClass
{
	public static void main(String[] args)
	{
		MediaInterface<AudioResource> musicsScraper = new GoogleMusicScraper("grandson");
		for(AudioResource resource: musicsScraper.getResources())
			System.out.println(resource);
	}
}