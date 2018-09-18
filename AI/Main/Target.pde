
class Target{

  float x;
  float y;
  float w;
  float h;
  
  public Target(float x, float y, float w, float h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  void show(){
    fill(100,255,100,150);
    rect(x,y,w,h);
  }
  
}
