# GenerateInfoFiles - Sistema de Generación de Archivos de Ventas

## Descripción

Este proyecto es un generador de archivos de prueba para un sistema de gestión de ventas. El programa crea automáticamente archivos de texto con datos simulados de productos, vendedores y ventas para facilitar las pruebas y el desarrollo de sistemas de gestión comercial.

## ¿Qué hace el código?

El programa `GenerateInfoFiles.java` genera tres tipos de archivos:

1. **Archivo de productos** (`products.txt`) - Contiene información de productos disponibles
2. **Archivo de vendedores** (`salesmen_info.txt`) - Contiene información de los vendedores
3. **Archivos de ventas individuales** (`salesman_[ID].txt`) - Un archivo por cada vendedor con sus ventas

## Archivos generados

### 1. `products.txt`
- **Formato**: `ID;Nombre;Precio`
- **Contenido**: 10 productos con nombres aleatorios de tecnología
- **Ejemplo**:
  ```
  P1;Laptop;245.67
  P2;Smartphone;789.23
  P3;Tablet;456.89
  ```

### 2. `salesmen_info.txt`
- **Formato**: `TipoDocumento;NúmeroDocumento;Nombre;Apellido`
- **Contenido**: 5 vendedores con datos aleatorios
- **Tipos de documento**: CC (Cédula de Ciudadanía) o CT (Cédula de Trabajo)
- **Ejemplo**:
  ```
  CC;1234567890;John;Smith
  CT;1987654321;Maria;Garcia
  ```

### 3. `salesman_[ID].txt` (uno por cada vendedor)
- **Formato**: 
  - Primera línea: `TipoDocumento;NúmeroDocumento`
  - Líneas siguientes: `IDProducto;Cantidad;`
- **Contenido**: 5 ventas aleatorias por vendedor
- **Ejemplo**:
  ```
  CC;1234567890
  P1;3;
  P5;7;
  P2;1;
  ```

## Requisitos

- Java 8 o superior
- Maven (para compilación)

## Cómo ejecutar

### Opción 1: Compilar y ejecutar con Maven
```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.GenerateInfoFiles"
```

### Opción 2: Compilar manualmente
```bash
javac src/main/java/com/GenerateInfoFiles.java
java -cp src/main/java com.GenerateInfoFiles
```

## Estructura del proyecto

```
Fundamental-Programming-Concepts/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── GenerateInfoFiles.java
├── pom.xml
└── README.md
```

## Características del generador

- **Datos aleatorios**: Utiliza listas predefinidas de nombres, apellidos y productos para generar datos realistas
- **Precios variables**: Los productos tienen precios aleatorios entre $10 y $1000
- **Cantidades de venta**: Las ventas tienen cantidades aleatorias entre 1 y 10 unidades
- **Documentos únicos**: Cada vendedor tiene un número de documento único de 10 dígitos

## Procesamiento de Datos con Main.java

El programa `Main.java` procesa los archivos generados por `GenerateInfoFiles.java` y crea reportes de ventas. Este programa:

1. Lee la información de productos desde `products.txt`
2. Lee la información de vendedores desde `salesmen_info.txt`
3. Procesa todos los archivos de ventas (`salesman_[ID].txt`)
4. Genera dos reportes en formato CSV

### Reportes generados

#### 1. `salesmen_report.csv`
- **Contenido**: Lista de vendedores ordenados por monto total de ventas (de mayor a menor)
- **Formato**: `Nombre Apellido;MontoTotalVentas`
- **Proceso de generación**:
  1. Lee todos los archivos de ventas de cada vendedor
  2. Calcula el monto total vendido multiplicando la cantidad de cada producto por su precio
  3. Ordena los vendedores por monto total de ventas en orden descendente
  4. Escribe el reporte con el nombre completo del vendedor y su monto total de ventas

#### 2. `products_report.csv`
- **Contenido**: Lista de productos ordenados por cantidad vendida (de mayor a menor)
- **Formato**: `NombreProducto;Precio;CantidadVendida`
- **Proceso de generación**:
  1. Acumula la cantidad vendida de cada producto a través de todos los archivos de ventas
  2. Ordena los productos por cantidad vendida en orden descendente
  3. Escribe el reporte con el nombre del producto, su precio y la cantidad total vendida

## Cómo ejecutar Main.java

```bash
# Primero ejecutar GenerateInfoFiles para crear los archivos de entrada
mvn exec:java -Dexec.mainClass="com.GenerateInfoFiles"

# Luego ejecutar Main para procesar los archivos y generar los reportes
mvn exec:java -Dexec.mainClass="com.Main"
```

## Uso típico

Este sistema es ideal para:
- Crear datos de prueba para sistemas de gestión de ventas
- Desarrollar y probar algoritmos de análisis de ventas
- Simular escenarios de negocio para testing
- Generar datasets para proyectos de programación
- Analizar el rendimiento de vendedores y productos

## Notas técnicas

- Los archivos se generan en el directorio raíz del proyecto
- Todos los archivos utilizan el formato de texto plano con separadores de punto y coma (`;`)
- El programa maneja errores de escritura de archivos con try-catch
- Los datos se generan de forma determinística pero con elementos aleatorios
- Los reportes se generan en formato CSV para fácil importación a hojas de cálculo
