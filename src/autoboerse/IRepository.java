package autoboerse;

import java.util.List;

/**
* This class was taken from persistence-with-jpa-examples
* 
* @author  Harald Habiger
* @version 1.0
*/

public interface IRepository<T> {

	T find(int id);

	List<T> findAll();

}