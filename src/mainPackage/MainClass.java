package mainPackage;

import mediaTier.*;
import logicTier.*;
import resourcePackage.Album;
import resourcePackage.MusicResource;

public class MainClass
{
	public static void main(String[] args)
	{
		MediaInterface<MusicResource> musicsScraper = new GoogleMusicScraper();
		LogicInterface logic = new Logic(musicsScraper);
		
		for(Album album: logic.getMediaInterface().findAlbums("grandson"))
			for(MusicResource musicResource: album.getMusicResources())
				System.out.println(musicResource);
		
	}
}