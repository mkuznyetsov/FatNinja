package ib.fatninja.managers;
import ib.fatninja.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResourceManager {

	private ResourceManager(){}
	
	private static ResourceManager instance;
	
	public static ResourceManager Instance(){
		if(instance == null)
			instance = new ResourceManager();
		return instance;
	}
	
	private Bitmap GrassResource[] ;

	private Bitmap TreeResource[] ;

	private Bitmap GroundResource[] ;

	private Bitmap BushResource[] ;

	private Bitmap HoleResource[] ;

	private Bitmap AppleResource[] ;

	private Bitmap CloudResource ;

	private Bitmap ForestResource ;

	private Bitmap MenuNinjaResource ;

	private Bitmap NewGameResource ;
	
	private Bitmap GameOverResource;
	
	private Bitmap NewGameTouchedResource;
	
	private Bitmap FatNinjaResource ;

	private Bitmap TrollResource ;

	private Bitmap BearResource ;

	private Bitmap WoolfResource ;
	
	private Bitmap ExitResource ;

	private Bitmap SoundOnResource ;

	private Bitmap SoundOffResource ;

	private Bitmap JoyStickOnResource ;
	
	private Bitmap JoyStickOffResource ;
	
	private Bitmap MovieOnResource ;

	private Bitmap MovieOffResource ;
	
	private Bitmap JoyStick;
		
	public Bitmap getCloudRes() {
		if( CloudResource == null){
			CloudResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.m_clouds);
			CloudResource = Bitmap.createScaledBitmap(CloudResource
					, CoordinateManager.Instance().getScreenWidth()*2
					, CoordinateManager.Instance().getScreenHeight()
					, true);
		}
		return CloudResource;
	}

	public Bitmap getBackgroundRes() {
		if( ForestResource == null){
			ForestResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.m_background);
			ForestResource = Bitmap.createScaledBitmap(ForestResource
					, CoordinateManager.Instance().getScreenWidth()
					, CoordinateManager.Instance().getScreenHeight()
					, true);
		}
		return ForestResource;
	}

	public Bitmap getManuNinjaRes() {
		if( MenuNinjaResource == null){
			MenuNinjaResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.m_ninja);

		}
		return MenuNinjaResource;
	}

	public Bitmap getNewGameRes() {
		if( NewGameResource == null)
			NewGameResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.m_new_game);
		return NewGameResource;
	}
	
	public Bitmap getGameOverRes(){
		if( GameOverResource == null){
			GameOverResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.m_game_over);
		}
		return GameOverResource;
	}
	
	public Bitmap getNewGameTouchedRes() {
		if( NewGameTouchedResource == null)
			NewGameTouchedResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.m_new_game_touched);
		return NewGameTouchedResource;
	}

	public Bitmap getFatNinjaRes() {
		if( FatNinjaResource == null){
			FatNinjaResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.ninja_obj);
		}
		return FatNinjaResource;
	}
	
	public Bitmap getMovieOnRes(){
		if( MovieOnResource == null)
			MovieOnResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.video_on);
		return MovieOnResource;
	}
	
	public Bitmap getMovieOffRes(){
		if(MovieOffResource == null)
			MovieOffResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.video_off);
		return MovieOffResource;
	}
	
	public Bitmap getSoundOnRes(){
		if(SoundOnResource == null)
			SoundOnResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.sound_on);
		return SoundOnResource;
	}
	
	public Bitmap getSoundOffRes(){
		if( SoundOffResource == null)
			SoundOffResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.sound_off);
		return SoundOffResource;
	}
	
	public Bitmap getJoyStickOnRes(){
		if( JoyStickOnResource == null)
			JoyStickOnResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.joystick_on);
		return JoyStickOnResource;
	}
	
	public Bitmap getJoyStickOffRes(){
		if(JoyStickOffResource == null)
			JoyStickOffResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.joystick_off);
		return JoyStickOffResource;
	}
	
	public Bitmap getExitRes() {
		if( ExitResource == null)
			ExitResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.m_exit);
		return ExitResource;
	}
		
	public Bitmap getWoolfRes() {
		if(WoolfResource == null){
			WoolfResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.woolf_obj);
		}
		return WoolfResource;
	}
	
	public Bitmap getBearRes() {
		if( BearResource == null){
			BearResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.bear_obj);
		}
		return BearResource;
	}
	
	public Bitmap getTrollRes() {
		if( TrollResource == null){
			TrollResource = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.troll_obj);
		}
		return TrollResource;
	}

	public Bitmap getAppleRes() {
		if(AppleResource == null){
			AppleResource = new Bitmap[4];
			FillResourceList(AppleResource
					, BitmapFactory.decodeResource(  SettingsManager.Instance().getActivity().getResources(), R.drawable.apple_obj)
					, CoordinateManager.Instance().getSpriteEdge());
		}
		return GetRandomResource(AppleResource);
	}
	
	public Bitmap getHoleRes() {
		if( HoleResource == null){
			HoleResource = new Bitmap[4];
			FillResourceList(HoleResource
					, BitmapFactory.decodeResource(  SettingsManager.Instance().getActivity().getResources(), R.drawable.hole_obj));
		}
		return GetRandomResource(HoleResource);
	}

	public Bitmap getBushRes() {
		if( BushResource == null){
			BushResource = new Bitmap[4];
			FillResourceList(BushResource
					, BitmapFactory.decodeResource(  SettingsManager.Instance().getActivity().getResources(), R.drawable.bush_obj));
		}
		return GetRandomResource(BushResource);
	}

	public Bitmap getGroundRes() {
		if( GroundResource == null){
			GroundResource = new Bitmap[4];
			FillResourceList(GroundResource
					, BitmapFactory.decodeResource(  SettingsManager.Instance().getActivity().getResources(), R.drawable.ground_obj));
		}
		return GetRandomResource(GroundResource);
	}

	public Bitmap getGrassRes() {
		if( GrassResource == null){
			GrassResource = new Bitmap[4];
			FillResourceList(GrassResource
					, BitmapFactory.decodeResource(  SettingsManager.Instance().getActivity().getResources(), R.drawable.grass_obj));
		}
		return GetRandomResource(GrassResource);
	}

	public Bitmap getTreeRes() {
		if( TreeResource == null){
			TreeResource = new Bitmap[4];
			FillResourceList(TreeResource
					, BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.tree_obj));
		}
		return GetRandomResource(TreeResource);
	}
	
	public Bitmap getJoyStick(){
		if(JoyStick == null){
			JoyStick = BitmapFactory.decodeResource( SettingsManager.Instance().getActivity().getResources(), R.drawable.joystick);
			JoyStick = Bitmap.createScaledBitmap(JoyStick
					, CoordinateManager.Instance().getJoyStickEdge()
					, CoordinateManager.Instance().getJoyStickEdge(), true);
		}			
		return JoyStick;
	}

	private Bitmap[] ninjaList;
		
	public Bitmap[] getMenuNinjaAnimation(){
		if (ninjaList == null){
			ninjaList = new Bitmap[4];
			Bitmap ninjaBMP = ResourceManager.Instance().getManuNinjaRes();
			FillResourceList(ninjaList, ninjaBMP, CoordinateManager.Instance().getTileEdge()*5);		
		}
		return ninjaList;
	}
	
	public Bitmap getMovieFrame(int frameID){
		switch(frameID){
			case 0:
				return createScaledMovieFrame(R.drawable.m_1);
			case 1:
				return createScaledMovieFrame(R.drawable.m_2);
			case 2:
				return createScaledMovieFrame(R.drawable.m_3);
			case 3:
				return createScaledMovieFrame(R.drawable.m_5);
			case 4:
				return createScaledMovieFrame(R.drawable.m_6);
			case 5:
				return createScaledMovieFrame(R.drawable.m_7);
			case 6:
				return createScaledMovieFrame(R.drawable.m_8);
			case 7:
				return createScaledMovieFrame(R.drawable.m_9);
			case 8:
				return createScaledMovieFrame(R.drawable.m_10);
			case 9:
				return createScaledMovieFrame(R.drawable.m_11);
			case 10:
				return createScaledMovieFrame(R.drawable.m_12);
			case 11:
				return createScaledMovieFrame(R.drawable.m_13);
		}
		
		return null;
	}
	
	private Bitmap GetRandomResource(Bitmap[] items){
		return items[(int)(Math.random() * items.length)];
	}
	
	private void FillResourceList(Bitmap items[], Bitmap res, int scaleValue){
		int frameWidth = res.getWidth() / 4;
		for(int i = 0; i < 4; i++){
			items[i] = Bitmap.createBitmap(res, i*frameWidth,  0, frameWidth - 1, res.getHeight() - 1);
			items[i] = Bitmap.createScaledBitmap(items[i]
					, scaleValue
					, scaleValue, true);				
		}
	}
	
	private void FillResourceList(Bitmap items[], Bitmap res){
		FillResourceList(items, res, CoordinateManager.Instance().getTileEdge());
	}
	
	private Bitmap createScaledMovieFrame(int resID){
		return BitmapFactory.decodeResource(SettingsManager.Instance().getActivity().getResources(), resID);
/*		return Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(SettingsManager.Instance().getActivity().getResources(), resID)
				, CoordinateManager.Instance().getScreenWidth()
				, CoordinateManager.Instance().getScreenHeight()
				, true);*/
	}	
}
