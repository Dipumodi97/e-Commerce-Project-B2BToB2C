package com.dipu.ecommerce.product.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dipu.ecommerce.product.entities.Product;

/**
 * JpaRepository me bahut saare operation form karte hai like:
 * findAll,findBy,existById,count,save,delete,....etc apart of this we need
 * custom logic then, we'll use filtering,searching,sorting...we'll use Drag
 * Query method
 * 
 * all these method have called Derived-Query-Method
 * @return
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * findByCategoryId spring data jpa ke through method name se query bana kar
	 * data se product ki list laayega.
	 * 
	 * jaise Springboot start hota hai uss time spring data jpa , repository
	 * interface ko scan karta hai and dynamic proxy banata hai jo har ek method
	 * call ko intercept karta hai.isme koi query nhi chalti bass @Proxy banta hai
	 * 
	 * @Proxy -> ye automatically spring ke through application start hone pe
	 *        generate hota hai.Spring internally interface ka ek proxy object
	 *        banata hai. ye proxy hi repo ka real implementation hota hai.
	 * @Proxy me kisi class/interface ka fake object jo real object ki tarah behave
	 *        karta hai. means spring repository ka aisi class baanta hai jo method
	 *        ko implement kare,SQL query generate kare,db ko call kar data return
	 *        kare.
	 * 
	 *        findByCategoryId find -> Data fetch karna hai by -> Data filter karna
	 *        hai,kis column ke saath use karna hai wo hai CategoryId
	 * 
	 * @return
	 */
	Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
	// To Give Range of Price
	Page<Product> findByPriceBetween(Double min,Double max,Pageable pageable);
	// user ek keyword dega aur details aayegi
	Page<Product> findByNameContainingIgnoreCase(String keyword,Pageable pageable);
	
	@Query("SELECT p FROM Product p "+
	  "WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', : keyword,'%')) "+
			"OR LOWER(p.description) LIKE LOWER(CONCAT('%', : keyword,'%'))")
	Page<Product> searchProducts(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT p FROM Product p "+
	"WHERE (: keyword IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%',:keyword)))"+
	"AND(:categoryId IS NULL OR p.category.categoryId = :id) "
	+ "AND (p.price BETWEEN :minPrice AND :maxPrice)")
	Page<Product> advanceFilter(
			@Param("keyword") String keyword,
			@Param("categoryId") Long categoryId,
			@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice,
			Pageable pageable
			);

}
