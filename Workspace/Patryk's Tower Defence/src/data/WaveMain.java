package data;

public class WaveMain {
	
	private float timeBetweenEnemies;
	private int waveNumber, enemiesPerWave;
	private Enemy[] enemyTypes;
	private Wave currentWave;
	
	public WaveMain(Enemy [] enemyTypes, float timeBetweenEnemies, int enemiesPerWave) {
		this.enemyTypes = enemyTypes;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.enemiesPerWave = enemiesPerWave;
		this.waveNumber = 0;
		
		this.currentWave = null;
		
		newWave();
		
	}

	public void update() {
		if (!currentWave.isCompleted())
			currentWave.update();
		else
			newWave();
		
	}
	
	private void newWave() {
		currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave);
		waveNumber++;
		enemiesPerWave +=2;
		System.out.println("Beginning Wave" + waveNumber);
	}
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	public int getWaveNumber() {
		return waveNumber;
	}
}
