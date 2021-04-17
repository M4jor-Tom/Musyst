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
			new MusicFindystVersionable(),
			new ResourcystVersionable(),
			new Versionyst()
		};
		
		//Versions setting
		existingDependencies.put("MusicFindystVersionable", versionables[1].getVersionId());
		existingDependencies.put("ResourcystVersionable", versionables[2].getVersionId());
		existingDependencies.put("Versionyst", versionables[3].getVersionId());

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