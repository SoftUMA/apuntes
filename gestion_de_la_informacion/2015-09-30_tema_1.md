# Tema 1.1: Arquitectura de bases de datos para el patrón MVC

## Patrón MVC

El modelo-vista-controlador es el patrón arquitectural más usado en la ingeniería de software.  
Descrito por primera vez en __1979__ para __Smalltalk__.  
Usado en múltiples frameworks:

- Java Swing
- J2EE
- XForms
- GTK+
- GWT
- ASP.NET
- Ruby on Rails
- etc.

### Componentes

- __Modelo__
	- gestiona la información
	- avisa a las otras capas de cambios
	- representa el dominio de datos
- __Vista__
	- representación gráfica
- __Controlador__
	- recibe peticiones de la vista
	- actualiza el modelo

### Características

Con un mismo modelo es posible tener varias vistas y varios controladores.  
Las vistas y controladores fuertemente relacionados.

### Flujo de control

1. Acción interfaz
1. Controlador trata el evento
1. Controlador notifica al modelo
1. Se genera / actualiza la vista
1. Espera nueva acción
1. Loop !

### Implementaciones

- __Vista__  
HTML, JFrame, Windows Form, WPF...

- __Controlador__  
Hebra de tratamiento de eventos, captura y propaga eventos a la vista y al modelo

- __Modelo__  
Capa de datos: información almacenada en una base de datos, ficheros o XML.  
Reglas de nogocio que transforman esa información.

### Ejemplo

~~~cs
public partial class MainWindow : Window {
	Conversion con;
	
	public MainWindow() {
		con = new Conversion();
		InitializeComponent();
		
		foreach (string m in con.ListaMonedas()) {
			listBox1.Items.Add(m);
		}
		
		listBox1.SelectedIndex = 0;
	}
	
	private void bGo_Click(object sender, RoutedEventArgs e) {
		string moneda = listBox1.SelectedItem.ToString();
		double ctd = double.Parse(tCtd1.Text);
		double res = con.Convertir(moneda, ctd);
		tCtd2.Text = res.ToString();
	}
}

class Conversion {
	DataClasses1DataContext dc;
	
	public Conversion() {
		dc = new DataClasses1DataContext();
	}
	
	public string[] ListaMonedas() {
		// ...
	}
	
	public double Convertir(string moneda, double ctd) {
		var val = from conversion in dc.conversion
			where (conversion.Moneda == moneda)
			select conversion.valor;
		double cambio = val.First();
		
		return ctd * cambio;
	}
}
~~~

-----

# Tema 1.2: Transformaciones entre el modelo relacional y el modelo de clases

## Introducción

La memoria es limitada y necesito poder almacenar los objetos en un medio que me permita recuperarlos (__persistencia__).  
La persistencia de la información es la parte más crítica en una aplicación de software.

__Opciones__:

- Conservar la misma estructura en una base de objetos.
- Utilizar una base de datos relacional como repositorio de información.

Si la aplicación está diseñada con orientación a objetos, la persistencia se logra mediante:

- Serialización del objeto (ej. *XML*)
- Almacenando en una base de datos

El modelo de objetos difiere en muchos aspectos del modelo relacional.  
La interface que une esos dos modelos se llama marco de __Mapeo Objeto-Relacional__ (*ORM* en inglés).

## Mapeo objeto-relacional

| Objetos | Tablas |
|----:|:----|
| Tienen comportamiento | Constraints o triggers |
| Encapsulan información (`private`) | No tiene esa habilidad |
| Uso de interfaces | En álgebra relacional la interfaz no genera entidad |
| Uso de herencia | No tiene esa habilidad |
| Polimorfismo y vinculación dinámica | No tiene esa habilidad |

## Subclases en el modelo relacional

__3 Posibilidades__:

- Implementar una tabla por cada clase.
- Implementar una tabla por subclase.
- Implementar una única tabla.

> Pros y Contras

| Solución | Pros | Contras |
|-----|-----|-----|
| 1 tabla | __1.__ Facilidad/Performance; __2.__ Evito generar muchas tablas | __1.__ Campos no utilizados; __2.__ Tentación de "reutilizar" atributos para cosas distintas |
| 1 tabla por cada clase (`n + 1`) | __1.__ Es el modelo "ideal" según normalización; __2.__ No necesito saber en qué tabla está la información; __3.__ Permite establecer campos no nulos para cada subclase | __1.__ Es la opción que más entidades requiere crear; __2.__ Requieren hacer `LEFT JOIN` contra todas las tablas que representan las subclases |
| 1 tabla por subclase (`n`) | __1.__ Permite establecer campos no nulos para cada subclase; __2.__ No requiere tener un campo discriminador (`TIPO_PEDIDO`) | __1.__ cada subclase repite atributos "heredados" de la superclase |

> Conveniencia

| Solución | Cuándo conviene |
|-----|-----|
| 1 tabla | __1.__ Cuando las subclases comparten muchos atributos en común; __2.__ Cuando se necesita simplificar las consultas |
| 1 tabla por cada clase (`n + 1`) | __1.__ Cuando hay muchos atributos comunes entre las subclases pero también muchos atributos propios en cada subclase |
| 1 tabla por subclase (`n`) | __1.__ Es la técnica utilizada para las entidades independientes; __2.__ Cuando las subclases comparten muy pocos atributos entre sí; __3.__ Cuando no me interesa trabajar con consultas polimórficas (trabajo en forma independiente cada subclase) |

## Relaciones M:M en el modelo OO

Ejemplo 1: "Un proveedor vende muchos productos y un producto es vendido por muchos proveedores".

__Cómo lo modelo en objetos ?__

Son colecciones desde los objetos raíces !!!

## Navegación entre objetos

~~~sql
-- crea el pedido del cliente 122
SELECT * FROM pedido WHERE cliente_id = 122;

-- obtiene los items del pedido... etc.
SELECT * FROM item i INNER JOIN producto p ON p.id_producto = i.id_producto WHERE i.id_pedido = <?>
~~~

## Lazy association

Una asociación "lazy" es aquella donde voy a traer la información bajo demanda ("solo cuando la necesite").

## O/R vs R/O

1. Generar el modelo relacional y luego adaptar el modelo de objetos en base a las tablas generadas.
1. Generar el modelo de objetos y en base a éste crear las tablas.

- La opción __1__ supone que es más importante la forma en que guardo los datos que las reglas de negocio que modifican esos datos.
- En la opción __2__ el desarrollo con objetos no se ve ensuciado por restricciones propias de otra tecnología.
