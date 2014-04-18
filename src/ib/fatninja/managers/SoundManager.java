package ib.fatninja.managers;

import ib.fatninja.R;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
* ALL SOUNDS SHOULD BE INITIALIZED HERE. 
*/
public class SoundManager {
	
	private SoundPool soundPool;
	
	private int[] BIT_APPLE_SOUNDS;
	
	private MediaPlayer mpMenu;

	private MediaPlayer mpGame;
	
	private static SoundManager instance;
	
	public static SoundManager Instance(){
		if(instance == null){
			instance = new SoundManager();
			
			instance.mpMenu = MediaPlayer.create(SettingsManager.Instance().getActivity(), R.raw.music_menu);
			instance.mpMenu.setLooping(true);
			instance.mpMenu.setVolume(0.2f, 0.2f);

			instance.mpGame = MediaPlayer.create(SettingsManager.Instance().getActivity(), R.raw.music_game);
			instance.mpGame.setLooping(true);
			instance.mpGame.setVolume(0.2f, 0.2f);
			
			instance.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
			instance.BIT_APPLE_SOUNDS = new int[4];
			instance.BIT_APPLE_SOUNDS[0] = instance.soundPool.load(SettingsManager.Instance().getActivity(), R.raw.bit_apple, 1);
			instance.BIT_APPLE_SOUNDS[1]= instance.soundPool.load(SettingsManager.Instance().getActivity(), R.raw.bit_apple2, 1);
			instance.BIT_APPLE_SOUNDS[2] = instance.soundPool.load(SettingsManager.Instance().getActivity(), R.raw.bit_apple3, 1);
			instance.BIT_APPLE_SOUNDS[3] = instance.soundPool.load(SettingsManager.Instance().getActivity(), R.raw.bit_apple4, 1);
		}
		return instance;
	}
	
	public void playMenuSound(){
		if(SettingsManager.Instance().isSoundEnabled && !mpMenu.isPlaying())
			mpMenu.start();
	}
	
	public void stopMenuSound(){
		if(mpMenu.isPlaying())
			mpMenu.pause();
	}
		
	public void playGameSound(){
		if(SettingsManager.Instance().isSoundEnabled && !mpGame.isPlaying())
			mpGame.start();
	}
	
	public void stopGameSound(){
		if(mpGame.isPlaying())
			mpGame.pause();
	}
	
	public void playBitApple(){
		int randomSound = (int)(Math.random() * BIT_APPLE_SOUNDS.length);
		soundPool.play(BIT_APPLE_SOUNDS[randomSound], 0.5f, 0.5f, 0, 0, 1);
	}
}
