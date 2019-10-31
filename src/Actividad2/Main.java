package Actividad2;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	@SuppressWarnings({ "unused", "static-access" })
	public static void main(String[] args) {
		
		InitBBDD iniciar = new InitBBDD();
		iniciar.iniciarBDOO();

		Articulos articulo = new Articulos();
		Clientes cliente = new Clientes();
		Ventas venta = new Ventas();
		ArrayList<Articulos> articulos = new ArrayList<Articulos>();
		HashMap<Integer, Articulos> articulosHMap = new HashMap<Integer, Articulos>();
		HashMap<Integer, Clientes> clientesHMap = new HashMap<Integer, Clientes>();
		HashMap<Integer, Ventas> ventasHMap = new HashMap<Integer, Ventas>();
		ArrayList<Clientes> clientes = new ArrayList<Clientes>();
		ArrayList<Ventas> ventas = new ArrayList<Ventas>();
		
		System.out.println("");
		System.out.println("*************************************************************************************************************************");
		System.out.println("OBTENER DATOS DE ARTICULOS");
		System.out.println("*************************************************************************************************************************");

		ventasHMap = venta.obtenerVentasHMap();
		articulosHMap = articulo.obtenerArticulosHMap();

		articulo.obtenerDatosArticulos(ventasHMap, articulosHMap);

		System.out.println("\n\n\n******************************************************************************************************************");
		System.out.println("OBTENER DATOS DE LAS VENTAS");
		System.out.println("*************************************************************************************************************************");

		ventasHMap = venta.obtenerVentasHMap();
		articulosHMap = articulo.obtenerArticulosHMap();

		venta.obtenerDatosVentas(ventasHMap);

		System.out.println("\n\n\n******************************************************************************************************************");
		System.out.println("OBTENER DATOS DE LOS ARTICULOS");
		System.out.println("*************************************************************************************************************************");

		ventasHMap = venta.obtenerVentasHMap();
		articulosHMap = articulo.obtenerArticulosHMap();

		articulo.obtenerDatosArticulos(ventasHMap, articulosHMap);

		System.out.println("\n\n\n******************************************************************************************************************");
		System.out.println("OBTENER DATOS DE LOS CLIENTES");
		System.out.println("*************************************************************************************************************************");

		ventasHMap = venta.obtenerVentasHMap();
		articulosHMap = articulo.obtenerArticulosHMap();
		clientesHMap = cliente.obtenerClientesHMap();

		cliente.obtenerDatosClientes(clientesHMap, ventasHMap);

		System.out.println("\n\n\n******************************************************************************************************************");
		System.out.println("OBTENER ARTICULOS MAS VENDIDAS - MEDIA X ARTICULO - CLIENTE QUE MAS GASTA - MAS COMPRA ");
		System.out.println("*************************************************************************************************************************");

		ventasHMap = venta.obtenerVentasHMap();
		articulosHMap = articulo.obtenerArticulosHMap();
		clientesHMap = cliente.obtenerClientesHMap();

		articulo.articuloMasVendido(ventasHMap, articulosHMap);
		articulo.mediaXArticulo(ventasHMap, articulosHMap);
		cliente.clienteMasGasta(clientesHMap, ventasHMap);
		cliente.clienteMasCompra(clientesHMap, ventasHMap);
		

	}

}
