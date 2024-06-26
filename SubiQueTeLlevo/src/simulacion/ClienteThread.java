package simulacion;

import java.time.LocalDateTime;
import java.util.Random;

import modelo.Cliente;
import modelo.Pedido;

public class ClienteThread implements Runnable {
	private RecursoCompartido rc;
	private int viajesPorSolicitar;
	private int ultimaZona = 0;
	private boolean ultimaMascota = false;
	private boolean ultimoBaul = false;
	private Cliente cliente;
	
	public ClienteThread(RecursoCompartido rc, int viajes,int id) {
		this.rc = rc;
		this.viajesPorSolicitar = viajes;
		this.cliente = new Cliente("bot_cliente_" + id,""+id,"123");
	}

	@Override

	/**
	 * El clienteThread, realiza un pedido, y si es válido, queda a la espera de los recursos para su ejecucion.<br>
	 */

	public void run() {
		for (int i = 0; i < this.viajesPorSolicitar; i++) {
			this.cliente.setPedidoValido(false);
			//boolean pedido=false; 
			//pedido=
			this.rc.realizarPedido(LocalDateTime.now(), this.getZona(), this.getMascota(), new Random().nextInt(10)+1, this.getBaul(), this.cliente, new Random().nextInt(2000)+1);
			try {
				Thread.sleep((long)(Math.random() * 1000) + 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(this.cliente.isPedidoValido())
				this.rc.pagarViaje(this.cliente);
			
		}
		this.rc.reduceClientesThread();
		System.out.println(this.cliente.getNombreReal() + " se va.");
	}
	/**
	 * 
	 * @return develve la zona en la que se solicito el pedido
	 */

	private String getZona() {
		String zona = "Estandar";
		if(this.ultimaZona == 2) {
			this.ultimaZona = 0;
		}else {
			this.ultimaZona+=1;
		}
		switch(this.ultimaZona) {
			case 0: zona = "Estandar";
			break;
			case 1: zona = "Calle sin asfaltar";
			break;
			case 2: zona = "Zona Peligrosa";
			break;
		}
		return zona;
	}
	/**
	 * 
	 * @return devuelve True si el Viaje se pidió con Mascota, False en otro caso
	 */

	private boolean getMascota() {
		this.ultimaMascota = !this.ultimaMascota;
		return this.ultimaMascota;
	}
	/**
	 * 
	 * @return Devuelve true si el Viaje se pidió con Baul, False en otro caso
	 */

	private boolean getBaul() {
		this.ultimoBaul = !this.ultimoBaul;
		return this.ultimoBaul;
	}
	/**
	 * 
	 * @return Devuelve la instancia de Cliente que solicito el pedio
	 */

	public Cliente getCliente() {
		return cliente;
	}

}
