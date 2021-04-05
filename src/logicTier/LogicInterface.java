package logicTier;

import mediaTier.MediaInterface;
import resourcePackage.MusicResource;

public interface LogicInterface
{
	public MediaInterface<MusicResource> getMediaInterface();
}