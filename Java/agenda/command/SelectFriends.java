package command;

public class SelectFriends extends SelectAgendaButton{

	public SelectFriends(String path, String path2, String title, int posX, int posY, AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY, agendaDevice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		agendaDevice.selectFriends();
		
	}

}
