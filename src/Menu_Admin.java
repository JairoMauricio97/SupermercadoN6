import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_Admin {

	private Usuario u;
	Scanner t = new Scanner(System.in);
	private String[][] matriz_resultado;
	private Conexion conn;

	public Menu_Admin(Usuario u) throws SQLException {
		this.conn = new Conexion();
		this.u = u;
		System.out.println("Menu");
		System.out.println("1 ver todos los productos");
		System.out.println("2 Cargar productos a la aplicación");
		System.out.println("3 Modificar los datos de los productos cargados");
		System.out.println("4 Ver todos los usuarios que realizaron una compra");
		System.out.println("5 Ver listado de productos seleccionados por el usuario");

		System.out.println("ingrese una opcion");
		int op = t.nextInt();

		switch (op) {
		case 1:
			this.VerProductos();
			break;
		case 2:
			this.addProducto();
			break;
		case 3:
			this.ModificarProductos();
			break;

		case 4:
			this.VerUsuariosCompras();
			break;
		}

	}

	public void VerUsuariosCompras() throws SQLException {
		String sql = "select idCompra,u.Nombre as Usuario from Compras as c "
				+ "inner join Usuario as u using(idUsuario);";

		ResultSet r = conn.devolverConsulta(sql);

		System.out.println("idCompra|\t Usuario\t");
		while (r.next()) {
			System.out.print(r.getString("idCompra") + "\t");
			System.out.print(r.getString("Usuario") + "\t");
			System.out.print("\n");
		}

	}

	public void addProducto() throws SQLException {

		Conexion conn = new Conexion();
		System.out.println("Esta por agregar un producto");
		System.out.println("Ingrese el nombre del producto");
		String n = t.next();
		System.out.println("Ingrese el Stock");
		String s = t.next();
		System.out.println("Ingrese la categoria  id :");
		ResultSet r = conn.devolverConsulta("select * from Categorias;");
		while (r.next()) {
	
			System.out.println(r.getInt("idCategoria") + ": " + r.getString("categoria"));
		}

		String cat = t.next();
		System.out.println("Ingrese el precio");

		String d = t.next();

		ArrayList<String> e = new ArrayList<>();
		e.add(n);
		e.add(s);
		e.add(cat);
		e.add(d);

		conn.AgregarProducto(e);
		System.out.println("Producto agreagado con exito");
	}

	public void VerProductos() throws SQLException {
		String sql = "select idProducto,Nombre,Stock,categoria,Precio from Productos inner join categorias using(idcategoria) ;";
		ResultSet r = conn.devolverConsulta(sql);
		System.out.println("id|\t Nombre|\t Stock|\t categoria\t |Precio");
		while (r.next()) {

			System.out.println(r.getInt("idProducto") + "\t" + r.getString("Nombre") + "\t" + r.getString("Stock")
					+ "\t" + r.getString("categoria") + "\t" + r.getString("Precio"));

		}

	}

	public void ModificarProductos() throws SQLException {

		String sql = "select idProducto,Nombre,Stock,categoria,Precio from Productos inner join categorias using(idcategoria) ;";
		ResultSet r = conn.devolverConsulta(sql);
		System.out.println("id|\t Nombre|\t Stock|\t categoria\t| precio");
		while (r.next()) {

			System.out.println(r.getInt("idProducto") + "\t" + r.getString("Nombre") + "\t" + r.getString("Stock")
					+ "\t" + r.getString("categoria") + "\t" + r.getString("Precio"));
		}

		System.out.print("Ingrese el id del producto a modificar");

		int id = t.nextInt();
		
		System.out.print("Ingrese el nuevo precio");
		String precio = t.next();
		String sql1 = " UPDATE Productos SET Precio= " + "'" + precio + "'" + " where idProducto=" + id + ";";

		conn.EjecutarConsulta(sql1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
