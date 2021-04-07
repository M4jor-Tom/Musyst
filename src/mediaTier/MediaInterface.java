package mediaTier;

import java.util.ArrayList;

import resourcePackage.Album;
import resourcePackage.Author;
import resourcePackage.Resource;

public interface MediaInterface<ResourceType extends Resource>
{
	public Album findAlbum(Author author, String albumName);
	public ArrayList<Album> findAlbums(String authorName);
	public ArrayList<Album> getAlbums();
}