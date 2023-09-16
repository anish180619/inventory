document.addEventListener('DOMContentLoaded', function () {
    const addProductForm = document.getElementById('addProductForm');
    const productList = document.getElementById('productList');

    // Function to fetch and display products
    function displayProducts() {
        fetch('/api/products/')
            .then(response => response.json())
            .then(data => {
                productList.innerHTML = ''; // Clear the list
                data.forEach(product => {
                    const listItem = document.createElement('li');
                    listItem.textContent = `${product.name} - ${product.description} - $${product.price}`;
                    productList.appendChild(listItem);
                });
            })
            .catch(error => console.error('Error fetching products:', error));
    }

    // Handle form submission to add a new product
    addProductForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const name = document.getElementById('name').value;
        const description = document.getElementById('description').value;
        const price = document.getElementById('price').value;

        const newProduct = {
            name: name,
            description: description,
            price: price,
        };

        fetch('/api/products/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newProduct),
        })
            .then(response => response.json())
            .then(() => {
                displayProducts();
                addProductForm.reset(); // Clear the form
            })
            .catch(error => console.error('Error adding product:', error));
    });
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

    // Initial display of products
    displayProducts();
});
