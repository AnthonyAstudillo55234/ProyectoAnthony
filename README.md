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
- Botón de Ingresar: Al presionarlo, la aplicación verifica las credenciales contra la base de datos.
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
- Botón "ELIMINAR": Ejecuta la acción de eliminar el producto con el ID especificado.
- Botón "REGRESAR": Vuelve al menú principal de administrador (form5).
- Etiqueta Mensaje: Muestra mensajes de éxito o error relacionados con la eliminación del producto.

![image](https://github.com/user-attachments/assets/3132b0d7-2768-4d07-a87d-3c4d95e26152)
![image](https://github.com/user-attachments/assets/2b73d85d-a178-44c0-9e7b-c5938d4e17b9)
![image](https://github.com/user-attachments/assets/85e6f28f-e503-43ee-af9f-6cd55c34646c)

- ## Form 8 (Buscar Producto):

En esta ventana se proporciona un formulario que permite a los administradores ingresar el ID de un producto para buscar sus detalles en la base de datos. La aplicación muestra el nombre, el precio y el stock del producto si el ID ingresado es válido.

Características:

- Campo de Texto para ID: Permite ingresar el ID del producto que se desea buscar.
- Botón "BUSCAR": Ejecuta la acción de buscar el producto con el ID especificado.
- Botón "REGRESAR": Vuelve al menú principal de administrador (form5).
- Etiquetas para mostrar información: Muestra el nombre, el precio y el stock del producto encontrado.
- Etiqueta Mensaje: Muestra mensajes relacionados con la búsqueda del producto, como éxito o error.

![image](https://github.com/user-attachments/assets/60ff16e9-c69f-4b81-a228-ac55dd367d65)
![image](https://github.com/user-attachments/assets/b630452c-cf77-4bc6-9fe1-65b73dce6fef)
![image](https://github.com/user-attachments/assets/d5f8bccf-0e64-479e-ba8e-a09f16ab2dfb)

- ## Form14 (Actualizar Stock de un Producto):






