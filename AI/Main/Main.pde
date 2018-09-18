
Population population;
Target target;
ObstacleManager obsMan;

void mousePressed() {
  Obstacle o = obsMan.obstacles.get(4);
  o.x = mouseX;
  o.y = mouseY;
}
void keyPressed() {
  int code = keyCode;
  if (code == 32) {
    target.x = mouseX;
    target.y = mouseY;
  }
}
void setup() {
  size(1600, 830);
  fill(255, 200);
  target = new Target(width/2 - 20, 20, 40, 40);
  obsMan = new ObstacleManager();
  population = new Population(target,obsMan.obstacles);
  
  textSize(16);
}
void draw() {
  background(0);
  population.deadParticle();
  population.drawPopulation();
  target.show();
  obsMan.show();
}
