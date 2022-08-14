package micro.spring.auth.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micro.spring.auth.entity.ProductEntity;
import micro.spring.auth.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @RolesAllowed("ROLE_EDITOR")
    public ResponseEntity<ProductEntity> create(@RequestBody @Valid ProductEntity product)
    {
        ProductEntity p = productRepository.save(product);
        URI productUri = URI.create("/api/products/" + p.getId());
        return ResponseEntity.created(productUri).body(p);
    }

    @GetMapping
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_EDITOR"})
    public List<ProductEntity> getAll()
    {
        return productRepository.findAll();
    }
}
