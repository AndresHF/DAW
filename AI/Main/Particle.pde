class Particle {

  PVector pos;
  PVector acc;
  PVector speed;
  DNA dna;
  final int maxSpeed = 5;
  final float accMag = 0.38;
  int count;
  float fitness;
  float radius = 20;
  boolean goal, dead;

  public Particle(float x, float y) {

    pos = new PVector(x, y);
    acc = new PVector(0, 0);
    speed = new PVector(0, -0.5);
    dna = new DNA();
    fitness = 0;
    count = 0;
    goal = false;
    dead = false;
  }


  void update() {
    if (!goal && !dead) {
      //acc.mult(0);
      acc.add(dna.genes[count]);
      acc.setMag(accMag);
      speed.add(acc);
      pos.add(speed);
      count ++;
    }
  }

  void show() {
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(speed.heading());
    //rectMode(CENTER);
    rect(0, 0, this.radius, this.radius/4);
    popMatrix();
    //ellipse(pos.x, pos.y, this.radius, this.radius);
  }
  void goalReached(Target t) {
    if (pos.x >= t.x && 
      pos.x <= t.x + t.w &&
      pos.y <= t.y + t.h && 
      pos.y >= t.y) this.goal = true;
  }
  void calculateFitness(Target t, ArrayList<Obstacle> obstacles) {
    if (!goal) {
      float d = dist(pos.x, pos.y, t.x, t.y);
      d = map(d, 0, 1200, 1, 0);
      fitness = d;
      if (dead) fitness -= 0.35;
      if (obstacleIntersection(t, obstacles)) fitness -= 0.45;
        
    } else this.fitness = 1;
  }
  boolean obstacleIntersection(Target t, ArrayList<Obstacle> obstacles) {
    Obstacle o = obstacles.get(4);
    if (o.y < pos.y && t.y < o.y &&
      o.x < t.x && o.x + o.w > t.x &&
      o.x < pos.x && o.x + o.w > pos.x) {
      return true;
    }
    return false;
  }
}
