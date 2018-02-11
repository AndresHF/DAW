package command;

public class SelectDoctor extends SelectAgendaButton{

	public SelectDoctor(String path, String path2, String title, int posX, int posY, AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY, agendaDevice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		agendaDevice.selectDoctor();
		
	}

}
