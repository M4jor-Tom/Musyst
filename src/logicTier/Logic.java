package logicTier;


import musicFindystPackage.MusicFindystInterface;

public class Logic implements LogicInterface
{
	MusicFindystInterface _musicFindystInterface;
	
	public Logic(MusicFindystInterface musicFindystInterface)
	{
		setMusicFindystInterface(musicFindystInterface);
	}

	@Override
	public MusicFindystInterface getMusicFindystInterface()
	{
		return _musicFindystInterface;
	}
	
	public void setMusicFindystInterface(MusicFindystInterface musicFindystInterface)
	{
		_musicFindystInterface = musicFindystInterface;
	}
}