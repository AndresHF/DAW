
class ObstacleManager {
  static final int borders = 10;
  ArrayList<Obstacle> obstacles;

  public ObstacleManager() {
    obstacles = new ArrayList<Obstacle>();
    initManager();
  }

  void initManager() {
    obstacles.add(new Obstacle(0, 0, width, borders)); //TOP
    obstacles.add(new Obstacle(width - borders, 0, borders, height)); //RIGTH
    obstacles.add(new Obstacle(0, height - borders, width, borders)); //BOT
    obstacles.add(new Obstacle(0, 0, borders, height)); //LEFT

    obstacles.add(new Obstacle(width/4, height/2, width /2, 20)); // MIDDLE
    /*for (int i = 0; i < 2; i++) {
      obstacles.add(new Obstacle((int)(random(0,width)), (int)(random(0,height)), (int)(random(150,500)), (int)(random(15,50))));
    }*/
  }

  void show() {
    for (Obstacle o : obstacles) {
      o.show();
    }
  }
}
