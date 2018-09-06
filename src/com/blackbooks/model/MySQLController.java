package com.blackbooks.model;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
/**
 * Interface para gestionar las sentencias DML básicas de MySQL
 * 
 * @author Alfonsaurus
 *
 */
public interface MySQLController<E> {
	/**
	 * Asigna un {@link DataSource} y el nombre en formato "schema.table" {@code String} 
	 * de la base de datos
	 * 
	 * @param dataSource el {@link DataSource} 
	 * @param schemaTableName el nombre en {@code String} con formato "schema.table" 
	 */
	public void setDataInitialization(DataSource dataSource,String schemaTableName);
	/**
	 * Crea una sentencia INSERT con un objeto dado
	 * @param e el {@code Object} a insertar
	 * @return {@code true} si el objeto ha sido insertado, {@code false} si no
	 * @throws Exception 
	 */
	public boolean create(E e) throws SQLException;
	/**
	 * Recupera un objeto de la base de datos, indicando el campo y el valor para 
	 * encontrarlo
	 * @param column el campo en el que se buscará
	 * @param value el valor para encontrarlo
	 * @return el objeto encontrado, {@code null} si no se encuentra
	 * @throws SQLException
	 */
	public E get(String column, String value) throws SQLException;
	/**
	 * Método para recuperar un grupo de objetos de la base de datos siguiendo un 
	 * criterio de búsqueda
	 * @param column el campo en que se buscará
	 * @param value el valor que debe tener el campo
	 * @param exact {@code true} para un valor exacto, {@code false} para usar el value como substring de búsqueda
	 * @return una {@code List} de los objetos recuperados
	 * @throws SQLException
	 */
	public List<E> getGroup(String column, String value,boolean exact) throws SQLException;
	/**
	 * Método para leer un objeto de la base de datos (no se recuperará)
	 * 
	 * @param column column el campo en que se buscará
	 * @param value el valor para encontrarlo
	 * @return {@code true} si existe, {@code false} si no
	 * @throws SQLException
	 */
	public boolean read(String column, String value) throws SQLException;
	/**
	 * Método para actualizar un objeto en la base de datos
	 * @param updateColumn campo a actualizar
	 * @param updateValue valor para el campo a actualizar
	 * @param column campo para realizar la búsqueda
	 * @param value el valor para encontrar el objeto
	 * @return
	 * @throws SQLException
	 */
	public boolean update(String updateColumn, String updateValue,String column,String value) throws SQLException;
	/**
	 * Método para eliminar un registro de la base de datos 
	 * ¡CUIDADO! no se guardará el objeto de ninguna forma
	 * 
	 * @param column column campo para realizar la búsqueda
	 * @param value value el valor para encontrar el objeto
	 * @return {@code true} si se ha podido eliminar, {@code false} si no
	 * @throws SQLException
	 */
	public boolean delete(String column, String value) throws SQLException;
}
