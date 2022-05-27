# fala-springboot

code challenge for TL role

## Configuración del proyecto con IDE IntelliJ

1. [ ] Importar proyecto a través de archivo **build.gradle** en IntelliJ
2. [ ] Seleccionar en IntelliJ Menu principal **Run -> Edit Configurations**
3. [ ] Presionar signo **"+"** para agregar nueva configuración **"add new configuration"**
4. [ ] Seleccionar **"Application"**
5. [ ] Agregar en name **"fala-springboot"**
6. [ ] Seleccionar la **versión de Java** (creado con versión Java 11 OpenJDK)
7. [ ] Seleccionar modulo **"fala-springboot.main"**
8. [ ] Seleccionar la clase principal **"com.fala.challenge.FalaSpringBootApplication"**
9. [ ] Descargar las dependencias desde el archivo **build.gradle**

## Pasos para la instalación

1.- Instalación Docker 

```
https://www.docker.com/get-started/
```

2.- Instalación de imagen PostgreSQL, abrir una terminal y ejecutar el siguiente comando

```
docker pull postgres
```

3.- Entrar por terminar al root del proyecto proyecto **fala-springboot** 
y en la terminal ejecutar el siguiente comando

```
docker-compose up
```
El comando anterior ubicará el archivo que ya existe en la ruta /fala-springboot/docker-compose.yaml
y comenzará con la creación del contenedor que tendrá nuestra BD con los parametros 
de configuración indicados en este mismo (nombre de la BD, usuario, password, puerto expuesto)


4.- Entrar por terminar al root del proyecto proyecto **fala-springboot**
y en la terminal ejecutar el siguiente comando (**solo cuando se desea 
terminar con la ejecutación de las pruebas localmente**)

```
docker-compose down
```
5.- 

## Non-blocking version

| URL                                               | Method    | description f(x)                                   |
| --------------------------------------------------|:-------   |:----------------------------------------------------|
| http://localhost:8080/product/                    | POST      | Permite crear productos (ver payload)             |
| http://localhost:8080/v1/product/products         | GET       | Permite obtener toda la lista de productos       |
| http://localhost:8080/v1/product/FAL-881898502    | GET       | Permite obtener un producto en especifico indicando por path el SKU
| http://localhost:8080/v1/product/FAL-881898502    | PUT       | Permite actualizar un producto en especifico indicando por path el SKU (ver payload)
| http://localhost:8080/v1/product/FAL-881898502    | DELETE    | Permite eliminar un producto en especifico indicando por path el SKU

### payload creación de producto
```
{
"sku":"FAL-881898503",
"name":"aaa",
"brand":"bbb",
"size":"XL",
"price":1.00,
"principal_image":"http://www.falabella.com"
}
```

### payload actualización de producto
```
{
"sku":"FAL-881898503",
"name":"DDDD",
"brand":"ZZZZZZ",
"size":"XL",
"price":99.00,
"principal_image":"http://www.falabella.com/2"
}
```


> Task :compileJava
> Task :processResources
> Task :classes
> Task :checkstyleMain
> Task :compileTestJava
> Task :processTestResources NO-SOURCE
> Task :testClasses
> Task :checkstyleTest
> Task :pmdMain
> Task :pmdTest
> Task :test