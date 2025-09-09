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

## Uso típico

Este generador es ideal para:
- Crear datos de prueba para sistemas de gestión de ventas
- Desarrollar y probar algoritmos de análisis de ventas
- Simular escenarios de negocio para testing
- Generar datasets para proyectos de programación

## Notas técnicas

- Los archivos se generan en el directorio raíz del proyecto
- Todos los archivos utilizan el formato de texto plano con separadores de punto y coma (`;`)
- El programa maneja errores de escritura de archivos con try-catch
- Los datos se generan de forma determinística pero con elementos aleatorios
