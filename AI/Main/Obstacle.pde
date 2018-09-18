
class Obstacle {

  int x;
  int y;
  int w;
  int h;

  public Obstacle(int x, int y, int w, int h) {

    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  void show() {
    fill(255, 100, 100, 100);
    rect(x, y, w, h);
  }
}
