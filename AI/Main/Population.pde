
class Population {

  Particle[] population;
  final int populationLength = 100;
  float bestFitness;
  Target t;
  ArrayList<Obstacle> obstacles;
  ArrayList<DNA> oldGeneration;
  int generation = 0;
  int count = 0;
  int populationGoals = 0;
  int deadParticles = 0;
  final float fitnessOffset = 0.32;
  int noGoalGeneration = 0;
  int badQualityGenes = 0;

  public Population(Target t, ArrayList<Obstacle> obstacles) {
    this.t = t;
    this.obstacles = obstacles;
    population = new Particle[populationLength];
    oldGeneration = new ArrayList<DNA>();
    bestFitness = 0;
    initPopulation();
  }

  void initPopulation() {

    if (population[0] != null) {
      newGeneration();
    } else {

      for (int i = 0; i < population.length; i++) {
        population[i] = new Particle(width/2, height - 80);
      }
    }
  }
  void newGeneration() {
    oldGeneration = new ArrayList<DNA>();
    noGoalGeneration = 0;
    for (int i = 0; i < population.length; i++) {
      winnerGenes(i);
      looserGenes(i);
    }

    if (noGoalGeneration == population.length) badQualityGenes ++;
    for (int i = 0; i < population.length; i++) {
      Particle parent = population[i];
      population[i] = new Particle(width/2, height - 80);
      if (bestFitness >= fitnessOffset) {
        population[i].dna.crossGenes(oldGeneration.get((int)(random(0, oldGeneration.size() - 1))), parent);
      }
    }

    generation ++;
  }

  void winnerGenes(int pos) {
    if (population[pos].goal) {

      badQualityGenes = 0;
      int quality = (int) map(population[pos].count, 0, DNA.lifetime, 30, 10);
      int fit = (int) (population[pos].fitness * 100);
      for (int j = 0; j <= quality + fit; j++) {
        oldGeneration.add(population[pos].dna);
      }

      this.populationGoals ++;
    } else {
      noGoalGeneration ++;
    }
  }

  void looserGenes(int pos) {
    float random = random(0, 1);
    int fit = (int) (population[pos].fitness * 100);
    println(fit);
    if (population[pos].fitness >= bestFitness - 0.2) {
      for (int i = 0; i < fit; i++) {
        oldGeneration.add(population[pos].dna);
      }
    }
    if (badQualityGenes >= 5) {
      for (int j = 0; j < 2; j++) {
        oldGeneration.add(new DNA());
      }
    }
    /*if (population[pos].fitness >= bestFitness - 0.1) {
     int genesSpreading = (int) (population[pos].fitness *5);
     if (population[pos].fitness == bestFitness) genesSpreading *= 2;
     
     println(genesSpreading);
     
     for (int j = 0; j < genesSpreading; j++) {
     oldGeneration.add(population[pos].dna);
     }
     } else {
     if (random >= 0.65) oldGeneration.add(population[pos].dna);
     }*/
  }
  void drawPopulation() {
    fill(255, 200);
    for (Particle p : population) {
      p.goalReached(t);
      p.update();
      p.show();
      //p.obstacleIntersection(t, obstacles);
    }
    count ++;
    if (count >= DNA.lifetime) {
      for (Particle p : population) {
        p.calculateFitness(t, obstacles);
      }
      populationGoals = 0;
      maxFitness();
      initPopulation();
      count = 0;
    }
    text("No goal: " + badQualityGenes, 20, height -120);
    text("Generation: " + generation, 20, height - 100);
    text("Best fitness: " + bestFitness, 20, height - 70);
    text("Population goals: " + populationGoals, 20, height - 40);
    text("Dead particles: " + deadParticles + "/" + population.length, 20, height - 10);
  }
  void maxFitness() {
    deadParticles = 0;
    float record = 0;
    for (Particle p : population) {
      if (p.fitness > record) record = p.fitness;
      if (p.dead) deadParticles ++;
    }
    bestFitness = record;
  }
  void deadParticle() {

    for (Particle p : population) {
      for (Obstacle o : obstacles) {
        if (p.pos.x >= o.x && 
          p.pos.x <= o.x + o.w &&
          p.pos.y <= o.y + o.h && 
          p.pos.y >= o.y) p.dead = true;
      }
    }
  }
}
