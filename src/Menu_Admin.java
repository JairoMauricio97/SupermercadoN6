import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu_Admin {


	private Usuario u;
	Scanner t= new Scanner(System.in);
	private String [][] matriz_resultado;
	private Conexion conn;
	public Menu_Admin(Usuario u) throws SQLException {
		this.conn=new Conexion();
		this.u=u;
		System.out.println("Menu");
		System.out.println("1 ver todos los productos");
		System.out.println("2 Cargar productos a la aplicación");
		System.out.println("3 Modificar los datos de los productos cargados");
		System.out.println("4 Ver todos los usuarios que realizaron una compra");
		System.out.println("5 Ver listado de productos seleccionados por el usuario");
		
		System.out.println("ingrese una opcion");
		int op=t.nextInt();
		
		if(op==1) {
			
			String sql="select idProducto,Nombre,Stock,categoria,Precio from Productos inner join categorias using(idcategoria) ;";
			ResultSet r=conn.devolverConsulta(sql);
			System.out.println("id|\t Nombre|\t Stock|\t categoria\t |Precio");
			while(r.next()) {

				System.out.println(r.getInt("idProducto")+"\t"+r.getString("Nombre")+"\t"+
				r.getString("Stock")+"\t"+r.getString("categoria")+"\t"+r.getString("Precio"));
			}
			
			
		}{
			
			String sql="select idProducto,Nombre,Stock,categoria,Precio from Productos inner join categorias using(idcategoria) ;";
			ResultSet r=conn.devolverConsulta(sql);
			System.out.println("id|\t Nombre|\t Stock|\t categoria\t| precio");
			while(r.next()) {

				System.out.println(r.getInt("idProducto")+"\t"+r.getString("Nombre")+"\t"+
				r.getString("Stock")+"\t"+r.getString("categoria")+"\t"+r.getString("Precio"));
			}
			
			System.out.print("Ingrese el id del producto a modificar");
			
			int id=t.nextInt();
			System.out.print("Ingrese el nuevo precio");
			String precio=t.next();
			String sql1=" UPDATE Productos SET Precio= "+"'"+precio+"'"+" where idProducto="+id+";";
			
			conn.EjecutarConsulta(sql1);
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
