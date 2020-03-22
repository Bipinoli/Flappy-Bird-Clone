package com.bipinoli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bipinoli.helpers.AssetLoader;
import com.bipinoli.screens.GameScreen;

public class FlappyGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("FlappyGame", "created!");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
