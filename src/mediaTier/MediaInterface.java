package mediaTier;

import java.util.ArrayList;

import resourcePackage.Resource;

public interface MediaInterface<ResourceType extends Resource>
{
	public ArrayList<ResourceType> getResources();
}