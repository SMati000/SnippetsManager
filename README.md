# SnippetsManager
## Contenidos
* [Descripcion](https://github.com/SMati000/SnippetsManager/blob/master/README.md#descripcion)
* [Instalacion](https://github.com/SMati000/SnippetsManager/blob/master/README.md#instalacion)
* [Uso](https://github.com/SMati000/SnippetsManager/blob/master/README.md#uso)
    * [Como inciar?](https://github.com/SMati000/SnippetsManager/blob/master/README.md#como-iniciar)
    * [Ejemplos de comandos, parametros, y argumentos](https://github.com/SMati000/SnippetsManager/blob/master/README.md#ejemplos-de-algunos-posibles-comandos-parametros-y-argumentos)
* [Ejemplos de instrucciones completas](https://github.com/SMati000/SnippetsManager/blob/master/README.md#ejemplos)
* [Contribuciones](https://github.com/SMati000/SnippetsManager/blob/master/README.md#contribuciones)
* [Creditos](https://github.com/SMati000/SnippetsManager/blob/master/README.md#creditos)
* [Licensia](https://github.com/SMati000/SnippetsManager/blob/master/README.md#licensia)

## Descripcion
Snippets Manager es un programa para windows, sin interfaz grafica, se maneja a traves de comandos.  
Su funcion primordial es almacenar y administrar snippets. Esta pensado desde el lado del codigo, es decir, 
almacenar snippets de codigo, pero pueden almacenarse cualquier tipo de archivos.  
Este proyecto intenta facilitar el manejo efectivo de snippets por medio de comandos.  
Es importante tener en cuenta que es un proyecto personal y no demasiado amplio, por lo tanto, no esta diseñado para
funcionar fuera de un entorno local, sino que solo para ser usado con una unica cuenta y en entornos locales.  
A pesar de que la ayuda y el programa estan en español, los comandos estan en ingles, los snippets se almacernan
en una carpeta local llamada SnippetsDB y dentro, son organizados por categorias. Tanto el lugar donde se crea la SnippetsDB,
como las categorias, son personalizables por el usuario.  
El objetivo es hacer de la administración de estos archivos algo más simple, la idea es que sea flexible y configurable.  
Las operaciones que permite son, entre otras: crear categorias y subcategorias, añadir, eliminar, mover y renombrar snippets y
categorias, ver y editar los snippets, sea desde un editor externo o uno integrado en el programa (VIM), permite
configurar donde se crea la SnippetsDB y poder moverla, establecer una contraseña y tambien cambiarla,
todas estas configuraciones son modificables, es capaz de recuperar los archivos en caso de que pase algo 
y tambien permite hacer backups, además, tiene un comando de ayuda para ver tanto en consola como en alguna interfaz 
grafica mas comoda, la ayuda puede verse especifica para cada comando, y tambien de forma general.

## Instalacion
.........................................................................................

## Uso
Como se aclaro en la descripcion, el programa funciona con **comandos**. Estos comandos pueden necesitar **argumentos** para funcionar.  
*Los argumentos son valores que especifican detalles sobre la accion a realizar por el comando.*  
En caso de ser necesario, los comandos tambien pueden tener **parametros** para especificar diferentes detalles o acciones.  
Estos parametros deben indicarse luego del comando y su argumento y empezar con un guion (-), estos al igual
que el comando, pueden requerir argumentos.  
**El conjunto del comando con sus posibles parametros y argumentos se llama instruccion.**  
Los parametros y argumentos son flexibles, es decir que si son necesarios pero de todas formas el usuario no los
especifica en su instrucción, se solicitan luego. En caso de no ser necesarios tienen un valor por defecto.  
Siempre que se escriban instrucciones, **si los argumentos requieren cadenas de texto con espacios, deberan estar entre
comillas. Si no hay espacios, las comillas son opcionales.**  
add "ubicacion": permite añadir snippets a la SnippetsDB.

* Instruccion
    * Comando
        * Argumento del comando 
        * Parametros
            * Argumentos (de los parametros)
    
#### Como iniciar?
* Para inciar por primera vez en el programa, este cuenta con un comando que te permitira configurar dos datos esenciales para funcionar:  
  1. Donde se almacenaran los snippets  
  2. Si tendra o no contraseña cada vez que se inicie  
El comando es `start`, al utilizarlo, el programa solicitara los datos antes mencionados, que luego podran ser modificados utilizando este mismo comando con los parametros correspondientes. *(utilizar el comando `start -help` para ver la ayuda)*  
* Para añadir snippets a la *SnippetsDB*, se utiliza el comando `add "ubicacion del snippet"`. Si desea guardar el snippet en alguna categoria particular, utilice el parametro `-category "categoria"`, si no especifica una categoria, el snippet se guardara en *\SnippetsDB\Default*  
* Para crear categorias y subcategorias, utilice el comando `new -cat "nombre de la nueva cat"`  
* Para navegar dentro de la *SnippetDB*, utilice el comando `cd`, asi solo mostrara la ubicacion actual.  
si usa `cd "ubicacion"`, ira a esa ubicacion, si usa `cd ..`, retrocedera una ubicacion,  
si usa `cd \`, volvera a la raiz (SnippetsDB).  
**_Es importante tener en cuenta que todos los comandos se ejecutan dentro de la ubicacion en la que se encuentre, es decir, si usa el comando `new -cat "hola"`, en la ubicacion raiz, la categoria "hola" se creara en la raiz,  
si quisiera crear la categoria "hola" dentro de default, podria poner `new -cat "default/hola"` desde la ubicacion raiz,  
o usar `cd "default"`, y desde ese punto usar el comando `new -cat "hola"`_**

#### Ejemplos de algunos posibles comandos, parametros, y argumentos
* `add "ubicacion"`: permite añadir snippets a la SnippetsDB.  
    `-category "categoria"`: Este parametro permite indicar a que categoria añadir el snippet.  
    Si no se indica este parametro, el snippet se añadira a la categoria 'Default'  
    cd "ubicacion": permite navegar en la SnippetsDB.    

* `cd "ubicacion"`: permite navegar en la SnippetsDB.  
    `-help`: Este parametro muestra la ayuda del comando.  
    move "ubicacion": permite mover snippets en la SnippetsDB.    

* `move "ubicacion"`: permite mover snippets en la SnippetsDB.  
    `-to "nueva ubicacion"`: Este parametro permite especificar la nueva ubicacion.  
    open "ubicacion": permite abrir los snippets de la SnippetsDB.    

* `open "ubicacion"`: permite abrir los snippets de la SnippetsDB.  
    [-openwith "editor"]: Este parametro permite especificar con que editor se debe abrir. 
    
## Ejemplos
* Renombrar un archivo o carpeta:  
    `SnippetsM> rename "default/snippet.txt" -to "snippetNuevo.txt"`  
      
    O  
      
    `SnippetsM> rename`  
    `Archivo a renombrar> "default/snippet.txt"`  
    `Nombre nuevo> "snippetNuevo.txt"`  

## Contribuciones
Cualquier clase de contribucion es bienvenida

## Creditos
@SMati000

## Licencia
The Snippets Manager Project is distributed under the MIT License.

See [license](https://github.com/SMati000/SnippetsManager/blob/master/LICENSE.txt) for more information.
