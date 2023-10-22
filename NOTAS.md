### [Controlador (`Controller`)](src\main\java\com\udea\modulogc\controller) 🎛️:
La lógica principal en esta clase se encarga de manejar las solicitudes de creación de cupones.

### [Servicio (`Service`)](src\main\java\com\udea\modulogc\service) 🧠:
Se encarga de construir el código del cupón, validar las fechas y realizar las operaciones necesarias para guardar el cupón en la base de datos.

### [Facade](src\main\java\com\udea\modulogc\service\facade) 🏗️:
Proporciona una interfaz simplificada creando una clase que representa una interfaz de más alto nivel. Esto hace que el sistema sea más fácil de usar y proporciona un punto de entrada único para los clientes.

### [Modelos](src\main\java\com\udea\modulogc\jpa\model) 👤:
Los modelos como `Strategy` y `Coupon` son utilizados para interactuar con la base de datos.

### [DTOs](src\main\java\com\udea\modulogc\jpa\dto) 📑:
Los DTOs, como `StrategyDTO` y `CouponDTO`, son utilizados para las peticiones y respuestas entre el servidor (Front) y el sistema.

### [Utils](src\main\java\com\udea\modulogc\utils) 🧰:
En el paquete de `Utils`, se encuentran las clases auxiliares. Por ejemplo, `CouponBuilder` genera el código para el cupón.

#### Ejemplo de uso 👀:
- `valorMin`: Para limitar el descuento fijo.
- `maxDiscount`: Para limitar el valor en pesos por aplicar descuento porcentaje.

**Nota:**
`descuentoPorcentaje` puede tener `valorMin` y `maxDiscount` debido a la posibilidad de que el viaje tenga un costo específico, y el cupón se aplique a viajes con un `valorMin` definido, y se le pueda restar hasta que el `maxDiscount` sea alcanzado (por ejemplo, un viaje de $50 con `valorMin` de $30 y `maxDiscount` de $45).

**Pruebas APIs:**
`{
  "code": "CUPON123",
  "amount": 50,
  "status": "Inactive",
  "idStrategy": {
    "idStrategy": 1,
    "name": "Estrategia1",
    "description": "Descripción de la estrategia",
    "startDate": "2023-11-01",
    "endDate": "2023-12-01",
    "discountPercentage": 20,
    "discountValue": 10000,
    "minValue": 6000,
    "maxDiscount": 30000,
    "isActive": "1",
    "city": "Medellín"
  }
}`