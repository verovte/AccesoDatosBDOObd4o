package Actividad2;

public class InitBBDD {
	
	public static void iniciarBDOO() {
		Articulos articulo=new Articulos();
		Clientes cliente=new Clientes();
		Ventas venta=new Ventas();
		
		articulo.almacenarPorDefecto();
		
		cliente.almacenarPorDefecto();
		
		venta.almacenarPorDefecto();

	}

}
