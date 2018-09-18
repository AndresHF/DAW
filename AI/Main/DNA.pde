
class DNA {
  final static int lifetime = 250;
  PVector[] genes;

  public DNA() {
    genes = new PVector[lifetime];
    generateGenes();
  }

  void generateGenes() {
    for (int i = 0; i < genes.length; i++) {
      genes[i] = PVector.random2D();
    }
  }

  void crossGenes(DNA parent, Particle p) {
    for (int i = 0; i < genes.length; i ++) {
      float random = random(0, 1);
      if (p.goal) {
        if (random >= 0.01) this.genes[i] = parent.genes[i];
      } else {
        if (random >= 0.013) this.genes[i] = parent.genes[i];
      }
    }
  }
}
