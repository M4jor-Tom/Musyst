package logicTier;

import mediaTier.MediaInterface;
import resourcePackage.MusicResource;

public interface LogicInterface
{
	MediaInterface<MusicResource> getMediaInterface();
}