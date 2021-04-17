package musystConfig;

import versionystPackage.Versionable;

public final class MusystVersionable extends Versionable
{
	public MusystVersionable()
	{
		super();
		//getDependencies().put(null, 0);
	}

	@Override
	public Integer getVersionId()
	{
		return 1;
	}
}