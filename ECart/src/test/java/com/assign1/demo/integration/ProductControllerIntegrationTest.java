package com.assign1.demo.integration;

//import com.assign1.demo.entity.Product;
//import com.assign1.demo.entity.ProductRequestDTO;
//import com.assign1.demo.repository.ProductRepository;
//import com.assign1.demo.services.ProductService;
//import lombok.AllArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import java.util.List;
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//@AllArgsConstructor(onConstructor_ = @Autowired)
//
//public class ProductControllerIntegrationTest {
//
//    private MockMvc mockMvc;
//
//    private ProductRepository productRepository;
//
//    private ProductService productService;
//
//    @BeforeEach
//    public void setup() {
//        productRepository.deleteAll();
//    }
//
//
//    @Test
//    public void testCreateProductAuthorized() throws Exception {
//
//        String productJson = "{\"productName\":\"Laptop\", \"quantity\":10, \"brandName\":\"Dell\"}";
//
//
//        MvcResult result = mockMvc.perform(post("/api/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(productJson)
//                .with(httpBasic("user2", "pass2")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.productName").value("Laptop"))
//                .andExpect(jsonPath("$.quantity").value(10))
//                .andExpect(jsonPath("$.brandName").value("Dell"))
//                .andReturn();
//
//
//        assertEquals(1, productRepository.count());
//    }
//
//    @Test
//    public void testGetProductById() throws Exception {
//
//
//        productService.createProduct(new ProductRequestDTO("Smartphone", 20, "Samsung"));
//        Product product=productRepository.findByProductName("SmartPhone");
//        mockMvc.perform(get("/api/products?id=" + product.getProductId())
//                        .with(httpBasic("user1", "pass1"))
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].productName").value("Smartphone"))
//                .andExpect(jsonPath("$[0].quantity").value(20))
//                .andExpect(jsonPath("$[0].brandName").value("Samsung"));
//    }
//
//
//
//    @Test
//    public void testUpdateProduct() throws Exception {
//
//        productService.createProduct(new ProductRequestDTO("Smartphone", 20, "Samsung"));
//        Product product=productRepository.findByProductName("SmartPhone");
//
//
//        String updatedProductJson = "{\"productId\":" + product.getProductId() + ", \"productName\":\"Headphones Pro\", \"quantity\":45, \"brandName\":\"Sony\"}";
//
//
//        mockMvc.perform(put("/api/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(updatedProductJson)
//                .with(httpBasic("user2", "pass2")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.productName").value("Headphones Pro"))
//                .andExpect(jsonPath("$.quantity").value(45))
//                .andExpect(jsonPath("$.brandName").value("Sony"));
//
//
//        Product updatedProduct = productRepository.findById(product.getProductId()).orElse(null);
//        assertNotNull(updatedProduct);
//        assertEquals("Headphones Pro", updatedProduct.getProductName());
//        assertEquals(45, updatedProduct.getQuantity());
//    }
//
//    @Test
//    public void testDeleteProduct() throws Exception {
//
//        productService.createProduct(new ProductRequestDTO("Smartphone", 20, "Samsung"));
//        Product product=productRepository.findByProductName("SmartPhone");
//
//
//        mockMvc.perform(delete("/api/products/" + product.getProductId())
//                .with(httpBasic("user3", "pass3")))
//                .andExpect(status().isOk());
//
//
//        assertEquals(0, productRepository.count());
//    }
//    @Test
//    public void testCreateProductUnauthorized() throws Exception {
//
//        String productJson = "{\"productName\":\"Laptop\", \"quantity\":10, \"brandName\":\"Dell\"}";
//
//
//        MvcResult result = mockMvc.perform(post("/api/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(productJson))
//                .andExpect(status().isUnauthorized())
//                .andReturn();
//    }
//    @Test
//    public void testCreateProductForbidden() throws Exception {
//
//        String productJson = "{\"productName\":\"Laptop\", \"quantity\":10, \"brandName\":\"Dell\"}";
//
//
//        MvcResult result = mockMvc.perform(post("/api/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(productJson)
//                .with(httpBasic("user1", "pass1")))
//                .andExpect(status().isForbidden())
//                .andReturn();
//    }
//}
