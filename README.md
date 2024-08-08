# Escuela Politecnica Nacional

Nombre: Anthony Astudillo

# Proyecto Final POO

- ## Descripcion:

El proyecto es una aplicación de una tienda de zapatos "Zapatomart" con dos roles adminitrador y usuario, esta al estar diseñada para gestionar el inventario de productos, facilitar las compras en el carrito de compras y gestionar el inventario disponible. La aplicación interactúa con una base de datos MySQL en la nube para almacenar y actualizar información de inventario.

- ## Main (Main del Programa)

![image](https://github.com/user-attachments/assets/ae283b19-3188-4ee1-a152-3bed3eaba3f0)

El main lleva al form1 (El Menu principal del programa)

- ## Clase clientes:

Esta clase es para cuando el usuario ingrese sus datos en el apartado del registro.
![image](https://github.com/user-attachments/assets/3838ccd8-76cc-48bc-92da-b8563f39b996)
![image](https://github.com/user-attachments/assets/76b22c96-8358-47fa-bf2f-35465c36dbdf)
![image](https://github.com/user-attachments/assets/ef3e6e3d-a9d0-439b-a8a9-f66ee3655e32)

- ## Clase Productos:

Esta clase es para cuando el administrador ingrese los datos de un producto nuevo en el apartado de agregar producto.
![image](https://github.com/user-attachments/assets/ee5fc971-2b10-48e4-b97f-c3b3e947897a)
![image](https://github.com/user-attachments/assets/a8fbf847-03b1-4faf-851b-4320703f0b58)

- ## Form1 (Menu Principal del Programa):

Esta ventana contiene tres botones:
- Adminirador: Al hacer clic en este botón, se abre la ventana correspondiente al (form2: Login Adminitrador) y se cierra la ventana actual.
- Cerrar: Al hacer clic en este botón, el programa se cierra y todas sus funciones.
- Cliente: Al hacer clic en este botón, se abre la ventana correspondiente al (form3: Login Cliente) y se cierra la ventana actual.

![image](https://github.com/user-attachments/assets/7dd5cb3d-51c7-4f1d-907b-e50c1a1b477c)
![image](https://github.com/user-attachments/assets/eae0d08a-8b60-41af-b1be-71710788bd0a)

- ## Form2 (Login Administrador):

Es una ventana para que los administradores puedan iniciar sesión en el sistema. La aplicación verifica las credenciales ingresadas consultando una base de datos MySQL en la nube.

Características:

- Campo de usuario: Un campo de texto para que el administrador ingrese su nombre de usuario.
- Campo de contraseña: Un campo de texto para que el administrador ingrese su contraseña.
- Botón de Ingresar: Al presionarlo, la aplicación verifica las credenciales contra la base de datos.
- Botón de Regresar: Permite al usuario volver al formulario de inicio (form1: Menu Principal del Programa).

![image](https://github.com/user-attachments/assets/278cbbf7-cc9d-478d-9fa6-e559dfcbec7e)
![image](https://github.com/user-attachments/assets/0d130622-45a7-49c6-a4c3-3ac625a6fb76)
![image](https://github.com/user-attachments/assets/49d9d17c-5537-4811-91cb-d69c6f59e081)

- ## Form3 (Login Clientes):

Es una ventana para que los clientes puedan iniciar sesión en el sistema. La aplicación verifica las credenciales ingresadas consultando una base de datos MySQL en la nube.

Características:

- Campo de usuario (Cédula): Un campo de texto para que el cliente ingrese su número de cédula.
- Campo de contraseña: Un campo de texto para que el cliente ingrese su contraseña.
- Botón de Ingresar: Al presionarlo, la aplicación verifica las credenciales contra la base de datos y le envia al aparatdo del (form10: Ingreso al Catalogo).
- Botón de Registrar: Permite al cliente acceder al formulario de registro (form4) para crear una nueva cuenta.
- Botón de Regresar: Permite al usuario volver al formulario de inicio (form1)

![image](https://github.com/user-attachments/assets/dce422bd-15d6-4fd2-a422-3fcb657d9d8a)
![image](https://github.com/user-attachments/assets/e703d00f-b463-40eb-906f-60f5d684f58a)
![image](https://github.com/user-attachments/assets/2cc556f2-d550-4692-8505-5ddc24999574)
![image](https://github.com/user-attachments/assets/89a3a7bd-4ad0-497f-82b7-091e7a423cd8)

- ## Form 4 (Registro de Usario):

Esta ventana permite a los nuevos clientes registrar sus datos en el sistema.

Características:

- Campos de texto: Los clientes pueden ingresar su cédula, nombre, apellido, teléfono, correo electrónico, edad, y contraseña.
- Botón de Registrar: Inserta los datos del cliente en la base de datos MySQL si no existe un registro con la misma cédula.
- Botón de Regresar al Inicio: Permite al usuario volver al formulario de login para clientes (form3).
- Botón de Borrar: Limpia todos los campos del formulario para que el usuario pueda ingresar nuevos datos.

![image](https://github.com/user-attachments/assets/7fcb5e89-4101-478e-ab94-da72e35e30a1)
![image](https://github.com/user-attachments/assets/e8b2fde4-6807-4025-9bab-0d39de06123b)
![image](https://github.com/user-attachments/assets/6acda3f4-a871-4176-a50a-f3425f8f54cf)
![image](https://github.com/user-attachments/assets/0c450930-c53b-43b5-afbb-eb3d9f976f5b)
![image](https://github.com/user-attachments/assets/ab653b80-1188-4fd0-8275-5aac4e218e75)

- ## Form 5 (Menu Administrador):

En esta ventana el administrador puede agregar, eliminar, buscar productos y actualizar el stock, además de regresar al formulario de inicio de sesión de administrador (form2).

Características:

- Botón "AGREGAR PRODUCTO": Abre un nuevo formulario (form6: añadir un nuevo producto) a la base de datos.
- Botón "ELIMINAR PRODUCTO": Abre un nuevo formulario (form7: eliminar un producto existente) de la base de datos.
- Botón "BUSCAR PRODUCTO": Abre un nuevo formulario (form8: buscar un producto) en la base de datos.
- Botón "ACTUALIZAR STOCK": Abre un nuevo formulario (form14: actualizar la cantidad de stock de un producto).
- Botón "REGRESAR": Regresa al formulario de login para administradores (form2).

![image](https://github.com/user-attachments/assets/a967ebba-ec53-4ab8-b3a1-dbb36c983f02)
![image](https://github.com/user-attachments/assets/ee70ac93-e668-490d-8399-bca7c61779ca)
![image](https://github.com/user-attachments/assets/61318620-08a1-4393-b9b7-d2c6a5619bf0)

- ## Form 6 (Agregar Producto):

En esta ventana se proporciona un formulario que permite a los administradores ingresar información sobre nuevos productos, incluido el ID del producto, el nombre, el precio, el stock disponible y la ruta de la imagen del producto. El formulario también le permite seleccionar una imagen del sistema de archivo y mostrarla.

Características:

- Campo de Texto para ID: Permite ingresar el ID único del producto.
- Campo de Texto para Nombre: Permite ingresar el nombre del producto.
- Campo de Texto para Precio: Permite ingresar el precio del producto en formato numérico.
- Campo de Texto para Stock: Permite ingresar la cantidad disponible del producto.
- Campo de Texto para Ruta de Imagen: Muestra la ruta de la imagen seleccionada.
- Botón "SELECCIONAR IMAGEN": Abre un explorador de archivos para seleccionar una imagen del producto.
- Botón "AGREGAR": Inserta el producto en la base de datos.
- Botón "BORRAR": Limpia todos los campos del formulario.
- Botón "REGRESAR": Vuelve al menú principal de administrador (form5).

![image](https://github.com/user-attachments/assets/8ca3b0ac-d6e6-4d74-9283-53cac05bc6a6)
![image](https://github.com/user-attachments/assets/012ea326-8949-428f-bf33-13304dcb0de5)
![image](https://github.com/user-attachments/assets/389638a9-afbe-40c3-ba03-2c490f9abddd)
![image](https://github.com/user-attachments/assets/fd645bbb-f42f-4453-9673-b1b987c3e4cb)
![image](https://github.com/user-attachments/assets/5380356d-bde1-4516-b8bb-647d7fd1ba0e)
![image](https://github.com/user-attachments/assets/327d4e40-b949-4e70-8f6f-78b35234ce87)

- ## Form 7 (Eliminar Producto):

En esta ventana se proporciona un formulario que permite a los administradores ingresar el ID de un producto que desean eliminar. La aplicación verifica si el ID ingresado corresponde a un producto existente en la base de datos y elimina el producto si es encontrado.

Características:

- Campo de Texto para ID: Permite ingresar el ID del producto que se desea eliminar.
- Botón "ELIMINAR": Ejecuta la acción de eliminar el producto con el ID.
- Botón "REGRESAR": Vuelve al menú principal de administrador (form5).
- Etiqueta Mensaje: Muestra mensajes de éxito o error relacionados con la eliminación del producto.

![image](https://github.com/user-attachments/assets/3132b0d7-2768-4d07-a87d-3c4d95e26152)
![image](https://github.com/user-attachments/assets/2b73d85d-a178-44c0-9e7b-c5938d4e17b9)
![image](https://github.com/user-attachments/assets/85e6f28f-e503-43ee-af9f-6cd55c34646c)

- ## Form 8 (Buscar Producto):

En esta ventana se proporciona un formulario que permite a los administradores ingresar el ID de un producto para buscar sus detalles en la base de datos. La aplicación muestra el nombre, el precio y el stock del producto si el ID ingresado es válido.

Características:

- Campo de Texto para ID: Permite ingresar el ID del producto que se desea buscar.
- Botón "BUSCAR": Ejecuta la acción de buscar el producto con el ID.
- Botón "REGRESAR": Vuelve al menú principal de administrador (form5).
- Etiquetas para mostrar información: Muestra el nombre, el precio y el stock del producto encontrado.
- Etiqueta Mensaje: Muestra mensajes relacionados con la búsqueda del producto, como éxito o error.

![image](https://github.com/user-attachments/assets/60ff16e9-c69f-4b81-a228-ac55dd367d65)
![image](https://github.com/user-attachments/assets/b630452c-cf77-4bc6-9fe1-65b73dce6fef)
![image](https://github.com/user-attachments/assets/d5f8bccf-0e64-479e-ba8e-a09f16ab2dfb)

- ## Form14 (Actualizar Stock de un Producto):

En esta ventana se proporciona un formulario que permite a los administradores ingresar el ID del producto y el nuevo stock para actualizar la cantidad disponible en la base de datos.

Características:

- Campo de Texto para ID: Permite ingresar el ID del producto cuyo stock se desea actualizar.
- Campo de Texto para Stock: Permite ingresar el nuevo stock del producto.
- Botón "ACTUALIZAR": Ejecuta la acción de actualizar el stock del producto con el ID.
- Botón "REGRESAR": Regresa al menú principal de administrador (form5).
- Etiquetas de Mensaje: Muestran mensajes de estado, incluyendo éxito o error durante la actualización del stock.

![image](https://github.com/user-attachments/assets/c9b38708-cb06-4602-a3f1-d10be353374e)
![image](https://github.com/user-attachments/assets/314edd19-04a8-4fd6-adea-f3b7cf3b2744)
![image](https://github.com/user-attachments/assets/c8eb2db3-19e1-4fc7-a596-4b783612de40)
![image](https://github.com/user-attachments/assets/a1eb83a8-4029-4b1a-8d2a-92bbc6f9cbf7)

- ## Form10 (Ingreso al Catalogo):

Esta ventana permite a los usuarios ingresar al catálogo de productos de la tienday  la opción de regresar al formulario anterior (form3).

Características:

- Botón "INGRESAR": Abre el catálogo de productos (form9: Catalogo) y llama al método fetchProductData para cargar los datos del catálogo.
- Botón "REGRESAR": Regresa al formulario anterior (form3).
- Catalogo: Instancia de form9, utilizada para mostrar el catálogo de productos.

![image](https://github.com/user-attachments/assets/5b159321-6df9-435d-a40c-efb9c4786a08)
![image](https://github.com/user-attachments/assets/2f674bd6-1a36-4133-9038-5425eda83793)

- ## Form9 (Catalogo):

Se presenta una ventana con una tabla que lista los productos del catálogo. Cada fila de la tabla incluye detalles del producto, una imagen y un botón para comprar el producto.

Características:

- Tabla de Productos: Muestra información sobre los productos, incluyendo stock, nombre, precio, imagen y un botón de comprar.
- Botón "Ver Carrito": Abre el formulario del carrito de compras (form12).
- Botón "Regresar": Regresa al formulario anterior (form10).

![image](https://github.com/user-attachments/assets/84495ccc-18d5-4902-bedf-2514010b609f)
![image](https://github.com/user-attachments/assets/53e62ef4-1241-4b80-b3f4-fccacc6bd4fb)
![image](https://github.com/user-attachments/assets/20945e9e-3485-40b6-a2d0-4ac8d5b67991)
![image](https://github.com/user-attachments/assets/c601ddba-9af2-4fdd-a5a5-ffc7f2a2b448)
![image](https://github.com/user-attachments/assets/5b8ec392-ae7f-40f5-a075-2514e0712aa2)
![image](https://github.com/user-attachments/assets/a30de2c5-a816-48bd-b97d-e39a6ff5abd4)
![image](https://github.com/user-attachments/assets/c819bca4-d75e-43b2-90d6-e3df1289c64d)
![image](https://github.com/user-attachments/assets/08e77f52-29e2-4818-9d3b-223149c5aaf9)
![image](https://github.com/user-attachments/assets/fbfb19e7-d202-44d0-9983-e52c13649e88)

- ## Form11 (Añadir al Carrito):

Este modal se utiliza para ingresar la cantidad de un producto que se desea añadir al carrito. También genera un archivo PDF con los detalles de la compra cuando se confirma la selección del producto.

Características:

- Detalles del Producto: Muestra el nombre del producto, stock disponible y permite ingresar la cantidad deseada.
- Botón "Añadir": Añade el producto al carrito y genera un PDF con la factura de la compra.
- Botón "Cancelar": Cierra el diálogo sin realizar ninguna acción.

![image](https://github.com/user-attachments/assets/7220b61f-9d07-4911-8d0d-8e83adca5b70)
![image](https://github.com/user-attachments/assets/f6dd3076-780b-45f5-b602-63eb4e31361a)
![image](https://github.com/user-attachments/assets/f6e7cd4b-7d9e-4c6d-98dd-8a351a873c3d)
![image](https://github.com/user-attachments/assets/b2015ff2-75be-4a6f-85b7-30832bb8c662)
![image](https://github.com/user-attachments/assets/e8ee1170-86bc-4861-8705-81ab5c9e87a8)
![image](https://github.com/user-attachments/assets/7a881c8d-3233-4482-a65e-e1d2562d66e8)
![image](https://github.com/user-attachments/assets/cbd4f11d-4c53-4902-bf59-f72a8a3d29bb)

- ## Form 12 (Mostrar el Carrito):

Este modal muestra una tabla con los ítems del carrito de compras y con botones para comprar los ítems o cancelar la acción.

Características:

- Tabla de Carrito: Muestra los productos añadidos al carrito con sus respectivas cantidades.
- Botón "Comprar": Procesa la compra, actualiza el stock de los productos en la base de datos y limpia el carrito.
- Botón "Cancelar": Cierra el apartado sin realizar ninguna acción.

![image](https://github.com/user-attachments/assets/5a693521-b86e-4e75-aefc-00fb3c65d62e)
![image](https://github.com/user-attachments/assets/a866db35-d18e-4b6b-b66b-b08275cfeb21)
![image](https://github.com/user-attachments/assets/257ace3a-56dc-4307-9ab6-ce9398c3e9a6)
![image](https://github.com/user-attachments/assets/0b75c067-5fa3-4b48-a91d-cee1bd0a6d43)
![image](https://github.com/user-attachments/assets/c34cf97a-837c-485e-b415-f8fd5ea9dcc6)





























