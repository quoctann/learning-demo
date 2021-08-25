function addToCart(productId) {
    fetch(`/SpringWebapp/api/cart/${productId}`)
            .then(res => res.json())
            .then(data => {
                var d = document.getElementById("cart-counter");
                if (d !== null)
                    d.innerText = data;
            });
}