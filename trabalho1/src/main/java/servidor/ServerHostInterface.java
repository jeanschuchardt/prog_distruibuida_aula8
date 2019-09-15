package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Interface remota para o exemplo "Hello, world!"
public interface ServerHostInterface extends Remote {
	// servidor

	// ip
	public String registraPeer() throws RemoteException, Exception;

	// retorna todos os recursos disponiveis
	public HashMap<String, HashMap<String, String>> listaRecursos() throws RemoteException;

	// recebe endereço do outro servidor onde esta o recurso
	public String solicitaRecurso() throws RemoteException;

	public int heartbeat() throws RemoteException;

	public String say() throws RemoteException;

	public void registraRecurso(HashMap<String, String> mapFiles) throws RemoteException, ServerNotActiveException;

	/// uma thread de solicitações
	// uma tread para percorrer a lista para ver quem ta vivo
}
