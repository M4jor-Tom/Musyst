package mainPackage;

import musicFindystPackage.GoogleMusicScraper;
import musicFindystPackage.MusicFindystInterface;
import musicFindystPackage.MusicFindystVersionable;
import musystConfig.MusystVersionable;

import java.util.HashMap;

import logicTier.*;
import resourcePackage.Album;
import resourcePackage.MusicResource;
import resourcePackage.ResourcystVersionable;
import seekyst.SeekystVersionable;
import versionystPackage.Versionable;
import versionystPackage.Versionyst;

public class MainClass
{
	public static void versionsCheck()
	{
		//Variables initialization
		HashMap<String, Integer> existingDependencies = new HashMap<>();
		Versionable[] versionables =
		{
			new MusystVersionable(),
			new Versionyst(),
			new MusicFindystVersionable(),
			new ResourcystVersionable(),
			new SeekystVersionable()
		};
		
		//Versions setting
		existingDependencies.put("Versionyst", versionables[1].getVersionId());
		existingDependencies.put("MusicFindystVersionable", versionables[2].getVersionId());
		existingDependencies.put("ResourcystVersionable", versionables[3].getVersionId());
		existingDependencies.put("SeekystVersionable", versionables[4].getVersionId());

		//Versions checking
		for(Versionable versionable: versionables)
			versionable.checkSubPackagesVersions(existingDependencies);
	}
	
	public static void main(String[] args)
	{
		versionsCheck();
		
		MusicFindystInterface musicInterface = new GoogleMusicScraper();
		LogicInterface logic = new Logic(musicInterface);
		
		logic.getMusicFindystInterface().findMusicResourcesByAuthorName("NEFFEX");
	}
}