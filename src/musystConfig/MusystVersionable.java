package musystConfig;

import versionystPackage.Versionable;

public final class MusystVersionable extends Versionable
{
	public MusystVersionable()
	{
		super();
		getDependencies().put("ResourcystVersionable", 4);
		getDependencies().put("MusicFindystVersionable", 1);
		getDependencies().put("Versionyst", 4);
	}

	@Override
	public Integer getVersionId()
	{
		return 1;
	}
}