package Actividad2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class Clientes {
	
	private int numcli;
	private String nombre, pobla;
	
	public Clientes() {}
	
	public Clientes(int numcli, String nombre, String pobla) {
		this.numcli = numcli;
		this.nombre = nombre;
		this.pobla = pobla;
	}

	public int getNumcli() {
		return numcli;
	}

	public void setNumcli(int numcli) {
		this.numcli = numcli;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPobla() {
		return pobla;
	}

	public void setPobla(String pobla) {
		this.pobla = pobla;
	}
	
	public void almacenarCliente(Clientes clientes) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"articulos.db4o");
				try{
						System.out.println(clientes.getNombre()+". Cliente almacenado");
						bd.store(clientes);
					}
				finally{
					bd.close();
				}
	}
	
	public void almacenarPorDefecto() {
		ArrayList<Clientes> clientes=new ArrayList<Clientes>();
		Clientes cli1 = new Clientes(1,"Antonio Ruiz","Talavera");
		Clientes cli2 = new Clientes(2,"La Alameda S.L.","Talavera");
		Clientes cli3 = new Clientes(7,"Los molinos CB","Madrid");
		Clientes cli4 = new Clientes(8,"Pedro Moran S.L.","Talavera");
		Clientes cli5 = new Clientes(12,"Azulejos Martin S.L.","Talavera");
		Clientes cli6 = new Clientes(15,"Bar Girasol","Oropesa");
		Clientes cli7 = new Clientes(9,"Escuela Mayores","Talavera");
		Clientes cli8 = new Clientes(17,"Galeria Madrid S.L.","Madrid");
		Clientes cli9 = new Clientes(19,"El corte Chino","Talavera");
		Clientes cli10 = new Clientes(20,"UNICAS S.A.","Oropesa");
		Clientes cli11 = new Clientes(21,"Deportivo SAS","Talavera");
		Clientes cli12 = new Clientes(22,"Academia Padel","Madrid");
		clientes.add(cli1);
		clientes.add(cli2);
		clientes.add(cli3);
		clientes.add(cli4);
		clientes.add(cli5);
		clientes.add(cli6);
		clientes.add(cli7);
		clientes.add(cli8);
		clientes.add(cli9);
		clientes.add(cli10);
		clientes.add(cli11);
		clientes.add(cli12);
		
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"articulos.db4o");
				try{
					for (Clientes cliente : clientes) {
						System.out.println(cliente.getNombre()+" cliente almacenado");
						bd.store(cliente);
					}
				}
				finally{
					bd.close();
				}
	}
	public float obtenerTotalImp(HashMap<Integer,Ventas> ventas) {
		float total=0;
		for (Entry<Integer, Ventas> v : ventas.entrySet()) {
			if(v.getValue().getNumcli().getNumcli()==this.numcli) {
				total+=v.getValue().getUniven()*v.getValue().getCodarti().getPvp();
			}
		}
		return total;
	}
	
	public int obtenerN_Ventas(HashMap<Integer,Ventas> ventas) {
		int total=0;
		for (Entry<Integer, Ventas> v : ventas.entrySet()) {
			if(v.getValue().getNumcli().getNumcli()==this.numcli) {
				total++;
			}
		}
		return total;
	}
	
	@SuppressWarnings("rawtypes")
	public HashMap<Integer,Clientes> obtenerClientesHMap(){
		HashMap<Integer,Clientes> clientes=new HashMap<Integer,Clientes>();
		Clientes cliente;
		
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"articulos.db4o");
		try{
			ObjectSet res=bd.queryByExample(new Clientes(0, null, null));
			while(res.hasNext()){
				cliente=(Clientes)res.next();
				clientes.put(cliente.getNumcli(),cliente);
				}
			}
				finally{
					bd.close();
				}
			
		return clientes;
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Clientes> obtenerClientes(){
		ArrayList<Clientes> clientes=new ArrayList<Clientes>();
		
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"articulos.db4o");
		try{
			ObjectSet res=bd.queryByExample(new Clientes(0, null, null));
			while(res.hasNext()){
				clientes.add((Clientes)res.next());
				}
			}
				finally{
					bd.close();
				}
			
		return clientes;
	}
	
	@Override
	public String toString() {
		return numcli+" - "+nombre+" - "+pobla;
	}
	
	public void obtenerDatosClientes(HashMap<Integer,Clientes> clientesMap, HashMap<Integer,Ventas> ventasMap) {
		System.out.println("NUMCLI - NOMBRE - POBLACIÓN - TOTAL_IMPORTE - NUM_VENTAS");
		System.out.println("*************************************************************************************************************************");
		for (Entry<Integer, Clientes> c : clientesMap.entrySet()) {
			System.out.println(c.getValue().getNumcli()+" - "+
		c.getValue().getNombre()+" - "+
		c.getValue().getPobla()+" - "+
		c.getValue().obtenerTotalImp(ventasMap)+" - "+
		c.getValue().obtenerN_Ventas(ventasMap)
		);
			};
	}
	
	public void clienteMasGasta(HashMap<Integer,Clientes> clientesMap, HashMap<Integer,Ventas> ventasMap) {
		System.out.println("\n\nCLIENTE QUE MAS HA GASTADO: ");
		float maxGasto=0;
		for (Entry<Integer, Clientes> c : clientesMap.entrySet()) {
			if(c.getValue().obtenerTotalImp(ventasMap)>maxGasto) {
				maxGasto=c.getValue().obtenerTotalImp(ventasMap);
			}
		}
		
		for (Entry<Integer, Clientes> c : clientesMap.entrySet()) {
			if(c.getValue().obtenerTotalImp(ventasMap)==maxGasto) {
				System.out.println(c.getValue().getNumcli()+"-"+c.getValue().getNombre()+"   Gasto: "+c.getValue().obtenerTotalImp(ventasMap));
			}
		}
	}
	
	public void clienteMasCompra(HashMap<Integer,Clientes> clientesMap, HashMap<Integer,Ventas> ventasMap) {
		System.out.println("\n\nCLIENTE CON MAS VENTAS: ");
		int maxVentas=0;
		for (Entry<Integer, Clientes> c : clientesMap.entrySet()) {
			if(c.getValue().obtenerN_Ventas(ventasMap)>maxVentas) {
				maxVentas=c.getValue().obtenerN_Ventas(ventasMap);
			}
		}
		
		for (Entry<Integer, Clientes> c : clientesMap.entrySet()) {
			if(c.getValue().obtenerN_Ventas(ventasMap)==maxVentas) {
				System.out.println(c.getValue().getNumcli()+"-"+c.getValue().getNombre()+"   Nº de ventas: "+c.getValue().obtenerN_Ventas(ventasMap));
			}
		}
	}
	

}
