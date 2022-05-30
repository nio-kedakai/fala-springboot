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


Debería quedar así:

![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/configuracion_app_intellij.png)

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
y ejecutar el siguiente comando

```
docker-compose up
```
El comando anterior ubicará el archivo que ya existe en la ruta /fala-springboot/docker-compose.yaml
y comenzará con la creación del contenedor que tendrá nuestra BD con los parametros 
de configuración indicados en este mismo (nombre de la BD, usuario, password, puerto expuesto)


4.- Entrar por terminar al root del proyecto proyecto **fala-springboot**
y ejecutar el siguiente comando (**solo cuando se desea 
TERMINAR/FINALIZAR con la ejecutación de las pruebas localmente**)

```
docker-compose down
```
5.- 

## Non-blocking version

| URL                                               | Method    | description f(x)                                   |
| --------------------------------------------------|:-------   |:----------------------------------------------------|
| http://localhost:8080/product/                    | POST      | Permite crear productos (ver payload)             |
| http://localhost:8080/v1/product/products         | GET       | Permite obtener toda la lista de productos       |
| http://localhost:8080/v1/product/FAL-881898510    | GET       | Permite obtener un producto en especifico indicando por path el SKU
| http://localhost:8080/v1/product/FAL-881898510    | PUT       | Permite actualizar un producto en especifico indicando por path el SKU (ver payload)
| http://localhost:8080/v1/product/FAL-881898510    | DELETE    | Permite eliminar un producto en especifico indicando por path el SKU


Primero crear producto con SKU **FAL-881898510** antes de usar  GET, PUT y DELETE method.
### payload creación de producto
```
{
    "sku":"FAL-881898510",
    "name":"aaa",
    "brand":"bbb",
    "size":"XL",
    "price":1.00, 
    "principal_image":"http://www.falabella.com/C04",
    "other_images": ["http://www.falabella.com/OI1", "http://www.falabella.com/OI2"]
}
```

### payload actualización de producto
```
{
    "sku":"FAL-881898510",
    "name":"ZZZZ",
    "brand":"XXXX",
    "size":"XL",
    "price":888.00, 
    "principal_image":"http://falabella.scene7.com/is/image/Falabella/8406270_U7",
    "other_images": [
            "https://falabella.scene7.com/is/image/Falabella/8406270_U8",
            "https://falabella.scene7.com/is/image/Falabella/8406270_U9"
        ]
}
```
## Arquitectura del proyecto

El proyecto está compuesto por una arquitectura hexagonal, la cual nos va a permitir crear adaptadores independientes 
de la tecnología que usemos en el dominio.

Actualmente está usando PostgreSQL, con JPA Hibernate para el mapeo entre objeto entidad.

Para correr esta APP, se levanta un servicio Fly Way, la cual está configurada para crear y llenar la tabla con datos de ejemplo
una vez que se ejecute previamente el paso 3 (docker compose up), con esta tecnología podemos realizar migraciones mucho
más faciles en nuestro microservicio sin necesidad de ingresar a la BD a realizar directamente los ALTER o BULK.

Los scripts se encuentran ubicado en:

`fala-springboot/src/main/resources/db/migration`

![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/mapa_carpetas_main.png)

Está adaptado para ser reactivo, usa WebFlux.

Para mapear los objetos a entidad usa **mapstruct**. 

## Testing

La carpeta que contiene los test unitarios es: `fala-springboot/src/test`

En la carpeta `fala-springboot/src/test/resources/` se alojan los archivos json para los test de 
integracion del servicio rest

![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/mapa_carpetas_test.png)

La APP cree bastantes componentes donde no dí con el 85% de cobertura pero sin con el 79% (enduve cerca).

![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/configuracion_coverage_testing_gradle.png)

Pueden revisar la configuración del criterio de coverage en **gradle/sourceQA.gradle**

![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/mapa_carpetas_QA_gradle.png)

Para revisar los reportes generados se debe ir a **build/reports/**.  Esta carpeta se genera cuando se compila
o construye el proyecto, creando 4 subcarpetas llamadas: **checkstyle, jacoco, pmd y tests**.

![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/mapa_carpetas_reportes_estaticos.png)

Dentro de cada una de ellas existe un archivo **.html** el cual se puede abrir en el navegador y revisar los reportes generados.

En esta APP se usa para el análisis estático:

```Spotbugs, checkstyle, jacoco y PMD.```

![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/mapa_carpetas_analisis_estatico.png)

Las configuraciones están en la carpeta **config/**, la cual respectivamente tiene la configuración para checkstyle, pmd y spotbugs.

## Gradle

En el IDE IntelliJ se puede revisar en la pestaña Gradle


### Tasks Gradle
![diagram](https://raw.githubusercontent.com/nio-kedakai/fala-springboot/master/gradle_app.png)

En la imagen anterior se aprecia en el punto 1, es el task que permite levantar el proyecto o aplicación.

Para lograr esto, primero se debe tener previamente ejecutado los pasos del 1 al 3 (docker y compose).

En el punto 2, es la tarea que se corre para poder generar los reportes de análisis estáticos y ejecutar los TesUnit.