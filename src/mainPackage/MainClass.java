package mainPackage;

import musicFindystPackage.GoogleMusicScraper;
import musicFindystPackage.MusicFindystInterface;
import logicTier.*;
import resourcePackage.Album;
import resourcePackage.MusicResource;

public class MainClass
{
	public static void main(String[] args)
	{
		MusicFindystInterface musicInterface = new GoogleMusicScraper();
		LogicInterface logic = new Logic(musicInterface);
		
		logic.getMusicFindystInterface().findMusicResourcesByAuthorName("NEFFEX");
	}
}