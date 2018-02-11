package command;

public abstract class ConfirmButton extends ButtonPattern{
	
	//500 Y WENA WENA
	private boolean pressed = false;
	
	public ConfirmButton(String path, String path2, String title, int posX, int posY, AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY, agendaDevice);
		// TODO Auto-generated constructor stub
	}
	
	public void initVanish(){
		if(!this.secuenceFinished){
			this.hitbox.setLocation(posX, posY);
			this.posY -= 2;
			if(posY < 450){
				this.secuenceFinished = true;
			}
		}

	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean hasBeenPressed) {
		this.pressed = hasBeenPressed;
	}


}
