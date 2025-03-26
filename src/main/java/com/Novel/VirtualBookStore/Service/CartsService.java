package com.Novel.VirtualBookStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Novel.VirtualBookStore.entity.Carts; // Assuming this is your Carts entity
import com.Novel.VirtualBookStore.entity.User;
import com.Novel.VirtualBookStore.RepositoryInterface.CartsRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartsService {

    @Autowired
    private CartsRepository cartsRepository;
    @Autowired
    private UserService userService;
    public Carts saveCart(Carts cart) {
        return cartsRepository.save(cart);
    }

  
    
    public List<Carts> getAllCarts() {
        return cartsRepository.findAll();
    }
   
 
    
    public Carts getCartById(UUID id) {
        Optional<Carts> cartOptional = cartsRepository.findById(id);
        return cartOptional.orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    //Note:As of now no need to update ,if needed we will add it.


    public String deleteCart(UUID id) {
        cartsRepository.deleteById(id);
        return "Cart deleted";
    }
    
    public Carts getCartByUserId(UUID id){
    	//DTO approach is best to solve this issue
    	userService.getUserById(id); //We need to comment-in in order to work this //Why we need to comment-in?? below explain
		return cartsRepository.findByUserId(id);
    }
}


/*  The error you're encountering, `No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor`, is related to the serialization process, typically when converting an object to JSON (e.g., in a REST API response). This issue arises because of how Hibernate handles lazy loading and proxy objects.

### Explanation:

1. **Lazy Loading and Proxies**:
   - Hibernate uses proxies to implement lazy loading. When you query an entity, Hibernate may return a proxy object instead of the actual entity. This proxy object is a dynamically generated subclass that intercepts method calls to load data from the database only when needed.
   - In your case, the `Carts` entity likely has a relationship with the `User` entity, and this relationship is marked as `LAZY` (which is the default for `@ManyToOne` and `@OneToMany` relationships).

2. **Serialization Issue**:
   - When you comment out the line `User user = userService.getUserById(id);`, the `Carts` object returned by `cartsRepository.findByUserId(id)` may contain a proxy for the `User` entity.
   - During serialization (e.g., when returning the `Carts` object in a REST controller), the serializer (like Jackson) tries to serialize the `Carts` object, including its `User` property. However, the `User` property is a proxy, and the serializer doesn't know how to handle the `ByteBuddyInterceptor` (the proxy object's internal mechanism).

3. **Why It Works When You Don't Comment Out the Line**:
   - When you call `userService.getUserById(id);`, Hibernate fetches the `User` entity from the database and initializes it. This replaces the proxy with the actual `User` object.
   - As a result, when the `Carts` object is serialized, the `User` property is no longer a proxy, and the serializer can handle it without issues.

### Solutions:

1. **Eager Fetching**:
   - Change the fetch type of the `User` relationship in the `Carts` entity to `EAGER`. This forces Hibernate to load the `User` entity immediately, avoiding the proxy issue.
   ```java
   @ManyToOne(fetch = FetchType.EAGER)
   private User user;
   ```
   - **Note**: This approach can lead to performance issues if you have large object graphs or unnecessary data loading.

2. **DTO (Data Transfer Object)**:
   - Instead of returning the `Carts` entity directly, create a DTO that contains only the necessary fields. Map the `Carts` entity to the DTO, ensuring that no proxy objects are included.
   ```java
   public class CartDTO {
       private UUID id;
       private UserDTO user; // UserDTO is another DTO for the User entity
       // other fields and getters/setters
   }
   ```

3. **`@JsonIgnoreProperties`**:
   - Use `@JsonIgnoreProperties` to tell Jackson to ignore the proxy-related properties during serialization.
   ```java
   @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
   public class Carts {
       // fields and methods
   }
   ```

4. **Force Initialization**:
   - Before serialization, explicitly initialize the `User` property to replace the proxy with the actual entity.
   ```java
   Carts cart = cartsRepository.findByUserId(id);
   Hibernate.initialize(cart.getUser()); // Force initialization
   return cart;
   ```

5. **`@JsonIgnore` on the Relationship**:
   - If you don't need the `User` data in the response, you can simply ignore it during serialization.
   ```java
   @ManyToOne
   @JsonIgnore
   private User user;
   ```

### Recommendation:
The best approach depends on your use case. If you need the `User` data in the response, consider using DTOs or eager fetching. If you don't need the `User` data, use `@JsonIgnore` or `@JsonIgnoreProperties`. Avoid eager fetching if performance is a concern. */