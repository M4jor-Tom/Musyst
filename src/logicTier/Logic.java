package logicTier;


import mediaTier.MediaInterface;
import resourcePackage.MusicResource;

public class Logic implements LogicInterface
{
	MediaInterface<MusicResource> _mediaInterface;
	
	public Logic(MediaInterface<MusicResource> mediaInterface)
	{
		setMediaInterface(mediaInterface);
	}

	@Override
	public MediaInterface<MusicResource> getMediaInterface()
	{
		return _mediaInterface;
	}
	
	public void setMediaInterface(MediaInterface<MusicResource> mediaInterface)
	{
		_mediaInterface = mediaInterface;
	}
}