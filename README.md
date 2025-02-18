#REQUISITOS
Tener instalado en el sistema lo siguiente en tu sistema:
Java Development Kit (JDK) 17 o superior.
Node.js (v18 o superior) y npm (v9 o superior) para Angular.
Maven (v3 o superior) para construir el proyecto Spring Boot.
Open Liberty (si se usa embebido, no es necesario instalarlo por separado)


#Estructura del proyecto
PruebaTecnicaRest/
├── frontend/  
├── src/main/frontend/proyecto-prueba/         # Código fuente de Angular
│   ├── src/                                   # Código fuente de Angular
│   └── ....                                   # Código fuente de Angular
├── backend/
├── PruebaTecnicaRest          
│   ├── src/                  
│   │   ├── main/
│   │   │   ├── java/          # Código Java
│   │   │   ├── resources/     # Archivos de configuración y recursos
│   │   │   └── webapp/        # Archivos estáticos (se copiarán aquí los de Angular)
│   └── pom.xml                # Configuración de Maven para Spring Boot
├── pom.xml                    # Archivo principal de Maven (para integrar frontend y backend)
└── README.md                  # Documentación del proyecto


#Descarga dependencias de java spring boot pocicionarse en la carpeta raiz PruebaTecnicaRest/
ejecutar desde la consola
mvn dependency:resolve


#Empaquetacion en war
posicionarse dentro de la ruta principal PruebaTecnicaRest
empaquetar en war la aplicacion spring boot y estaticos de angular
mvn clean install

#Ejecucion y despliegue en servidor de aplicacion
posicionarse dentro de la ruta principal PruebaTecnicaRest
ejecutar en consola
mvn liberty:run

#Url de recurso frontend
http://localhost:9085/prueba-app/


#Url de recurso de backend
registrar
http://localhost:9085/prueba-app/api/v1/empleado/save

obetener lista
http://localhost:9085/prueba-app/api/v1/empleado/

actualizar
http://localhost:9085/prueba-app/api/v1/empleado/update
