package mediaTier;

import java.util.ArrayList;

import resourcePackage.Album;
import resourcePackage.Resource;

public interface MediaInterface<ResourceType extends Resource>
{
	public ArrayList<Album> findAlbums(String authorName);
	public ArrayList<Album> getAlbums();
}